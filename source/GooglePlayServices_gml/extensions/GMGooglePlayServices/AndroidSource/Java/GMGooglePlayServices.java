package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;

import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;
import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.GMExtWire.GMFunction;
import ${YYAndroidPackageName}.GMExtWire.GMValue;
import ${YYAndroidPackageName}.GMExtUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.AuthenticationResult;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.SnapshotsClient.DataOrConflict;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.games.FriendsResolutionRequiredException;
import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.tasks.Task;



import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Extension Generator conversion of YYGooglePlayServices.
 *
 * Social Async events have been replaced by GMFunction callbacks.
 * Every callback payload is a generated typed record (GMExtWire.ITypedStruct).
 */
public class GMGooglePlayServices extends GMGooglePlayServicesInternal
{
    private static final String TAG = "yoyo";
    private static final int RC_ACHIEVEMENT_UI = 9003;
    private static final int RC_LEADERBOARD_UI = 9004;
    private static final int RC_SAVED_GAMES = 9009;
    private static final int RC_PLAYER_SEARCH = 9012;
    private static final int RC_FRIENDS_CONSENT = 9013;
    private static final int RC_SHOW_PROFILE = 9014;

    // Google Play Services API constraints
    private static final int MAX_FRIENDS_PAGE_SIZE = 25;     // API limit
    private static final int MAX_LEADERBOARD_RESULTS = 25;   // API limit
    private static final int MIN_PAGE_SIZE = 1;              // Minimum page size

    private final ExecutorService background = Executors.newCachedThreadPool();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<String, Snapshot> snapshots = new ConcurrentHashMap<>();

    private volatile Snapshot conflictLocal;
    private volatile Snapshot conflictRemote;
    private volatile GMFunction savedGamesUiCallback;

    private volatile boolean friendsLoaded = false;
    private volatile GMFunction playerSearchCallback;
    private volatile GMFunction friendsConsentCallback;
    private volatile int friendsConsentPageSize;
    private volatile boolean friendsConsentForceReload;

    private volatile boolean authenticationKnown = false;
    private volatile boolean authenticated = false;

    public GMGooglePlayServices()
    {
        PlayGamesSdk.initialize(activity());
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (scheduler != null && !scheduler.isShutdown())
        {
            scheduler.shutdown();
            try
            {
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS))
                {
                    scheduler.shutdownNow();
                }
            }
            catch (InterruptedException e)
            {
                scheduler.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        if (background != null && !background.isShutdown())
        {
            background.shutdown();
            try
            {
                if (!background.awaitTermination(5, TimeUnit.SECONDS))
                {
                    background.shutdownNow();
                }
            }
            catch (InterruptedException e)
            {
                background.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static Activity activity()
    {
        return RunnerActivity.CurrentActivity;
    }

    private static void main(Runnable runnable)
    {
        Activity activity = activity();
        if (activity != null)
            activity.runOnUiThread(runnable);
    }

    private void cacheAuthentication(boolean value)
    {
        authenticationKnown = true;
        authenticated = value;
    }

    private String authenticationError()
    {
        if (authenticationKnown)
            return "Google Play Games user is not authenticated.";

        return "Google Play Games authentication state is unknown; call "
            + "play_services_is_authenticated or play_services_sign_in first.";
    }

    private boolean requireAuthentication(GMFunction callback, String value, String error)
    {
        if (authenticationKnown && authenticated)
            return true;

        callback.call(new PlayServicesTaskResult(false, value, error));
        return false;
    }

    private static String safeString(String value)
    {
        return value != null ? value : "";
    }

    private static String error(Throwable throwable)
    {
        if (throwable == null)
            return "Unknown Google Play Services error.";

        String message = throwable.getMessage();
        return message != null ? message : throwable.toString();
    }

    // -------------------------------------------------------------------------
    // Base
    // -------------------------------------------------------------------------

    public boolean play_services_is_available()
    {
        Activity activity = activity();
        return activity != null
            && GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(activity) == ConnectionResult.SUCCESS;
    }

    public void play_services_sign_in(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            callback.call(new PlayServicesAuthResult(false, false, "Activity is null."));
            return;
        }

        main(() -> PlayGames.getGamesSignInClient(activity).signIn()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(new PlayServicesAuthResult(false, false, error(task.getException())));
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean isAuth = result != null && result.isAuthenticated();
                cacheAuthentication(isAuth);

                callback.call(new PlayServicesAuthResult(
                    true,
                    isAuth,
                    isAuth ? "" : "Sign-in completed but the user is not authenticated."
                ));
            }));
    }

    public void play_services_is_authenticated(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            callback.call(new PlayServicesAuthResult(false, false, "Activity is null."));
            return;
        }

        PlayGames.getGamesSignInClient(activity).isAuthenticated()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(new PlayServicesAuthResult(false, false, error(task.getException())));
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean isAuth = result != null && result.isAuthenticated();
                cacheAuthentication(isAuth);
                callback.call(new PlayServicesAuthResult(true, isAuth, ""));
            });
    }

    public void play_services_request_server_side_access(
        String serverClientId,
        boolean forceRefreshToken,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, "", authenticationError()))
            return;

        PlayGames.getGamesSignInClient(activity)
            .requestServerSideAccess(serverClientId, forceRefreshToken)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(new PlayServicesTaskResult(true, safeString(task.getResult()), ""));
                else
                    callback.call(new PlayServicesTaskResult(false, "", error(task.getException())));
            });
    }

    // -------------------------------------------------------------------------
    // Player
    // -------------------------------------------------------------------------

    public boolean play_services_player_current(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return true;
        }

        PlayGames.getPlayersClient(activity).getCurrentPlayer()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful() && task.getResult() != null)
                {
                    com.google.android.gms.games.Player p = task.getResult();
                    callback.call(new PlayServicesPlayer(
                        true,
                        new PlayServicesPlayerInfo(
                            safeString(p.getPlayerId()),
                            safeString(p.getDisplayName()),
                            safeString(p.getTitle()),
                            p.getIconImageUri() != null ? p.getIconImageUri().toString() : "",
                            p.getHiResImageUri() != null ? p.getHiResImageUri().toString() : ""
                        ),
                        ""
                    ));
                }
                else
                {
                    callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), error(task.getException())));
                }
            });

        return true;
    }

    public boolean play_services_player_current_id(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Activity is null."));
            return true;
        }

        if (!requireAuthentication(
            callback, "", authenticationError()))
            return true;

        PlayGames.getPlayersClient(activity).getCurrentPlayerId()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(new PlayServicesTaskResult(true, safeString(task.getResult()), ""));
                else
                    callback.call(new PlayServicesTaskResult(false, "", error(task.getException())));
            });

        return true;
    }

    public boolean play_services_player_stats_load(
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "Activity is null."));
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, authenticationError()));
            return true;
        }

        PlayGames.getPlayerStatsClient(activity)
            .loadPlayerStats(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, error(task.getException())));
                    return;
                }

                AnnotatedData<PlayerStats> annotatedData = task.getResult();
                PlayerStats stats = annotatedData != null ? annotatedData.get() : null;

                if (stats == null)
                {
                    callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "No player statistics were returned."));
                    return;
                }

                callback.call(new PlayServicesPlayerStats(
                    true,
                    stats.getAverageSessionLength(),
                    stats.getDaysSinceLastPlayed(),
                    stats.getNumberOfPurchases(),
                    stats.getNumberOfSessions(),
                    stats.getSessionPercentile(),
                    stats.getSpendPercentile(),
                    stats.getChurnProbability(),
                    stats.getHighSpenderProbability(),
                    stats.getSpendProbability(),
                    stats.getTotalSpendNext28Days(),
                    ""
                ));
            });

        return true;
    }

    public boolean play_services_player_load(
        String player_id,
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), "Activity is null."));
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return true;
        }

        PlayGames.getPlayersClient(activity)
            .loadPlayer(player_id, force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), error(task.getException())));
                    return;
                }

                AnnotatedData<Player> annotatedData = task.getResult();
                Player player = annotatedData != null ? annotatedData.get() : null;

                if (player == null)
                {
                    callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), "No player data was returned."));
                    return;
                }

                callback.call(new PlayServicesPlayer(
                    true,
                    new PlayServicesPlayerInfo(
                        safeString(player.getPlayerId()),
                        safeString(player.getDisplayName()),
                        safeString(player.getTitle()),
                        player.getIconImageUri() != null ? player.getIconImageUri().toString() : "",
                        player.getHiResImageUri() != null ? player.getHiResImageUri().toString() : ""
                    ),
                    ""
                ));
            });

        return true;
    }

    public boolean play_services_friends_load(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            failFriendsList(callback, "Activity is null.");
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            failFriendsList(callback, authenticationError());
            return true;
        }

        int clampedResults = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_FRIENDS_PAGE_SIZE, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_friends_load: max_results " + (int)max_results + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_FRIENDS_PAGE_SIZE + "]");

        PlayGames.getPlayersClient(activity)
            .loadFriends(clampedResults, force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    failFriendsList(callback, error(task.getException()));
                    return;
                }

                AnnotatedData<PlayerBuffer> annotatedData = task.getResult();
                completeFriendsList(annotatedData != null ? annotatedData.get() : null, callback);
            });

        return true;
    }

    public boolean play_services_friends_load_more(
        double page_size,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            failFriendsList(callback, "Activity is null.");
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            failFriendsList(callback, authenticationError());
            return true;
        }

        if (!friendsLoaded)
        {
            failFriendsList(callback, "No friends buffer available. Call play_services_friends_load first.");
            return true;
        }

        int clampedPageSize = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_FRIENDS_PAGE_SIZE, page_size));
        if (clampedPageSize != (int)page_size)
            Log.w(TAG, "play_services_friends_load_more: page_size " + (int)page_size + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_FRIENDS_PAGE_SIZE + "]");

        PlayGames.getPlayersClient(activity)
            .loadMoreFriends(clampedPageSize)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    failFriendsList(callback, error(task.getException()));
                    return;
                }

                AnnotatedData<PlayerBuffer> annotatedData = task.getResult();
                completeFriendsList(annotatedData != null ? annotatedData.get() : null, callback);
            });

        return true;
    }

    // Shared (status, players, has_more) callback shape for every friends-list
    // function - status carries success/error only, the array is a real typed
    // GMExtWire.TypedArrayStream rather than bundled into a per-function Result
    // class, per the split-status callback pattern (see gmext-callback-design).
    private static void failFriendsList(GMFunction callback, String error)
    {
        callback.call(
            new PlayServicesResult(false, error),
            new GMExtWire.TypedArrayStream<>(PlayServicesPlayerInfo.class),
            false
        );
    }

    private void completeFriendsList(PlayerBuffer buffer, GMFunction callback)
    {
        GMExtWire.TypedArrayStream<PlayServicesPlayerInfo> players =
            new GMExtWire.TypedArrayStream<>(PlayServicesPlayerInfo.class);
        boolean hasMore = false;

        if (buffer != null)
        {
            try
            {
                for (Player player : buffer)
                    players.add(playerToInfo(player));

                hasMore = buffer.getCount() > 0;
            }
            catch (Exception exception)
            {
                failFriendsList(callback, error(exception));
                return;
            }
            finally
            {
                buffer.release();
            }
        }

        friendsLoaded = true;
        callback.call(new PlayServicesResult(true, ""), players, hasMore);
    }

    // -------------------------------------------------------------------------
    // Player Profile UI
    // -------------------------------------------------------------------------

    public void play_services_player_profile_show(String player_id)
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Player_Profile_Show blocked: " + authenticationError());
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            Log.w(TAG, "Player_Profile_Show blocked: Activity is null.");
            return;
        }

        PlayGames.getPlayersClient(activity)
            .getCompareProfileIntent(player_id)
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_SHOW_PROFILE))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show player profile UI.", exception));
    }

    // -------------------------------------------------------------------------
    // Player Search UI
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_player_search_show(final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        playerSearchCallback = callback;

        PlayGames.getPlayersClient(activity)
            .getPlayerSearchIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_PLAYER_SEARCH))
            .addOnFailureListener(exception ->
            {
                playerSearchCallback = null;
                callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
            });

        return PlayServicesError.Ok;
    }

    // -------------------------------------------------------------------------
    // Friends Load with Consent
    // -------------------------------------------------------------------------

    public boolean play_services_friends_load_with_consent(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            failFriendsList(callback, "Activity is null.");
            return true;
        }

        if (!authenticationKnown || !authenticated)
        {
            failFriendsList(callback, authenticationError());
            return true;
        }

        int clampedResults = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_FRIENDS_PAGE_SIZE, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_friends_load_with_consent: max_results " + (int)max_results + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_FRIENDS_PAGE_SIZE + "]");

        loadFriendsWithConsentHandling(activity, clampedResults, force_reload, callback);

        return true;
    }

    private void loadFriendsWithConsentHandling(
        Activity activity,
        int pageSize,
        boolean forceReload,
        GMFunction callback)
    {
        PlayGames.getPlayersClient(activity)
            .loadFriends(pageSize, forceReload)
            .addOnSuccessListener(task ->
            {
                AnnotatedData<PlayerBuffer> annotatedData = task;
                completeFriendsList(annotatedData != null ? annotatedData.get() : null, callback);
            })
            .addOnFailureListener(exception ->
            {
                if (exception instanceof FriendsResolutionRequiredException)
                {
                    FriendsResolutionRequiredException friendsException =
                        (FriendsResolutionRequiredException) exception;

                    friendsConsentCallback = callback;
                    friendsConsentPageSize = pageSize;
                    friendsConsentForceReload = forceReload;

                    try
                    {
                        friendsException.startResolutionForResult(activity, RC_FRIENDS_CONSENT);
                    }
                    catch (Exception e)
                    {
                        friendsConsentCallback = null;
                        failFriendsList(callback, error(e));
                    }
                }
                else
                {
                    failFriendsList(callback, error(exception));
                }
            });
    }

    private static PlayServicesPlayerInfo playerToInfo(Player player)
    {
        if (player == null)
            return emptyPlayerInfo();

        return new PlayServicesPlayerInfo(
            safeString(player.getPlayerId()),
            safeString(player.getDisplayName()),
            safeString(player.getTitle()),
            player.getIconImageUri() != null ? player.getIconImageUri().toString() : "",
            player.getHiResImageUri() != null ? player.getHiResImageUri().toString() : ""
        );
    }

    // -------------------------------------------------------------------------
    // Achievements
    // -------------------------------------------------------------------------

    public void play_services_achievements_show()
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Achievements_Show blocked: " + authenticationError());
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            Log.w(TAG, "Achievements_Show blocked: Activity is null.");
            return;
        }

        PlayGames.getAchievementsClient(activity).getAchievementsIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_ACHIEVEMENT_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show achievements UI.", exception));
    }

    public void play_services_achievements_increment(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, achievementId, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity)
            .incrementImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void play_services_achievements_reveal(
        String achievementId,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, achievementId, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity)
            .revealImmediate(achievementId)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void play_services_achievements_set_steps(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, achievementId, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity)
            .setStepsImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void play_services_achievements_unlock(
        String achievementId,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, achievementId, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity)
            .unlockImmediate(achievementId)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    private void completeAchievement(
        Task<?> task,
        String achievementId,
        GMFunction callback)
    {
        if (task.isSuccessful())
            callback.call(new PlayServicesTaskResult(true, achievementId, ""));
        else
            callback.call(new PlayServicesTaskResult(false, achievementId, error(task.getException())));
    }

    public void play_services_achievements_get_status(
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesAchievementList(
                false,
                new java.util.ArrayList<>(),
                "Activity is null."
            ));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesAchievementList(
                false,
                new java.util.ArrayList<>(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getAchievementsClient(activity)
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesAchievementList(
                        false,
                        new java.util.ArrayList<>(),
                        error(task.getException())
                    ));
                    return;
                }

                AchievementBuffer buffer = task.getResult() != null ? task.getResult().get() : null;
                java.util.List<PlayServicesAchievement> achievements = new java.util.ArrayList<>();

                if (buffer != null)
                {
                    try
                    {
                        for (Achievement achievement : buffer)
                            achievements.add(achievementToRecord(achievement));
                    }
                    catch (Exception exception)
                    {
                        callback.call(new PlayServicesAchievementList(
                            false,
                            new java.util.ArrayList<>(),
                            error(exception)
                        ));
                        return;
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(new PlayServicesAchievementList(
                    true,
                    achievements,
                    ""
                ));
            });
    }

    // -------------------------------------------------------------------------
    // Leaderboards
    // -------------------------------------------------------------------------

    public void play_services_leaderboard_show_all()
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Leaderboard_ShowAll blocked: " + authenticationError());
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            Log.w(TAG, "Leaderboard_ShowAll blocked: Activity is null.");
            return;
        }

        PlayGames.getLeaderboardsClient(activity).getAllLeaderboardsIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboards UI.", exception));
    }

    public void play_services_leaderboard_show(String leaderboardId)
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Leaderboard_Show blocked: " + authenticationError());
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            Log.w(TAG, "Leaderboard_Show blocked: Activity is null.");
            return;
        }

        PlayGames.getLeaderboardsClient(activity)
            .getLeaderboardIntent(leaderboardId)
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboard UI.", exception));
    }

    public void play_services_leaderboard_submit_score(
        String leaderboardId,
        double score,
        String scoreTag,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesScoreReport(false, leaderboardId, score, safeString(scoreTag),
                new PlayServicesScoreResult(0.0, "", "", false),
                new PlayServicesScoreResult(0.0, "", "", false),
                new PlayServicesScoreResult(0.0, "", "", false),
                "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesScoreReport(false, leaderboardId, score, safeString(scoreTag),
                new PlayServicesScoreResult(0.0, "", "", false),
                new PlayServicesScoreResult(0.0, "", "", false),
                new PlayServicesScoreResult(0.0, "", "", false),
                authenticationError()));
            return;
        }

        PlayGames.getLeaderboardsClient(activity)
            .submitScoreImmediate(leaderboardId, (long)score, scoreTag)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    ScoreSubmissionData report = task.getResult();
                    ScoreSubmissionData.Result daily = report.getScoreResult(LeaderboardVariant.TIME_SPAN_DAILY);
                    ScoreSubmissionData.Result weekly = report.getScoreResult(LeaderboardVariant.TIME_SPAN_WEEKLY);
                    ScoreSubmissionData.Result all_time = report.getScoreResult(LeaderboardVariant.TIME_SPAN_ALL_TIME);

                    callback.call(new PlayServicesScoreReport(
                        true,
                        leaderboardId,
                        score,
                        safeString(scoreTag),
                        new PlayServicesScoreResult(daily.rawScore, safeString(daily.formattedScore), safeString(daily.scoreTag), daily.newBest),
                        new PlayServicesScoreResult(weekly.rawScore, safeString(weekly.formattedScore), safeString(weekly.scoreTag), weekly.newBest),
                        new PlayServicesScoreResult(all_time.rawScore, safeString(all_time.formattedScore), safeString(all_time.scoreTag), all_time.newBest),
                        ""
                    ));
                }
                else
                {
                    callback.call(new PlayServicesScoreReport(false, leaderboardId, score, safeString(scoreTag),
                        new PlayServicesScoreResult(0.0, "", "", false),
                        new PlayServicesScoreResult(0.0, "", "", false),
                        new PlayServicesScoreResult(0.0, "", "", false),
                        error(task.getException())));
                }
            });
    }

    public void play_services_leaderboard_load_player_centered_scores(
        String leaderboard_id,
        PlayServicesLeaderboardTimeSpan span,
        PlayServicesLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                "Activity is null."
            ));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                authenticationError()
            ));
            return;
        }

        int clampedResults = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_LEADERBOARD_RESULTS, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_leaderboard_load_player_centered_scores: max_results " + (int)max_results + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_LEADERBOARD_RESULTS + "]");

        PlayGames.getLeaderboardsClient(activity)
            .loadPlayerCenteredScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                clampedResults,
                force_reload)
            .addOnCompleteListener(task -> completeScores(task, callback));
    }

    public void play_services_leaderboard_load_top_scores(
        String leaderboard_id,
        PlayServicesLeaderboardTimeSpan span,
        PlayServicesLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                "Activity is null."
            ));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                authenticationError()
            ));
            return;
        }

        int clampedResults = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_LEADERBOARD_RESULTS, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_leaderboard_load_top_scores: max_results " + (int)max_results + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_LEADERBOARD_RESULTS + "]");

        PlayGames.getLeaderboardsClient(activity)
            .loadTopScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                clampedResults,
                force_reload)
            .addOnCompleteListener(task -> completeScores(task, callback));
    }

    private void completeScores(
        Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task,
        GMFunction callback)
    {
        if (!task.isSuccessful())
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                error(task.getException())
            ));
            return;
        }

        AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
        LeaderboardsClient.LeaderboardScores scores = annotatedData != null ? annotatedData.get() : null;

        if (scores == null)
        {
            callback.call(new PlayServicesLeaderboardScores(
                false,
                emptyLeaderboardRecord(),
                new java.util.ArrayList<>(),
                "No leaderboard score data."
            ));
            return;
        }

        LeaderboardScoreBuffer buffer = scores.getScores();
        java.util.List<PlayServicesLeaderboardScore> scoreList = new java.util.ArrayList<>();

        try
        {
            if (buffer != null)
            {
                for (LeaderboardScore score : buffer)
                    scoreList.add(leaderboardScoreToRecord(score));
            }
        }
        finally
        {
            if (buffer != null)
                buffer.release();
        }

        callback.call(new PlayServicesLeaderboardScores(
            true,
            leaderboardToRecord(scores.getLeaderboard()),
            scoreList,
            ""
        ));
    }

    // -------------------------------------------------------------------------
    // URI to local path
    // -------------------------------------------------------------------------

    public void play_services_uri_to_path(
        String uriString,
        final GMFunction callback)
    {
        final Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Activity is null."));
            return;
        }

        main(() ->
        {
            try
            {
                UriImageListener.register(
                    activity,
                    Uri.parse(uriString),
                    callback,
                    background,
                    scheduler
                );
            }
            catch (Exception exception)
            {
                callback.call(new PlayServicesTaskResult(false, "", error(exception)));
            }
        });
    }

    private static final class UriImageListener
        implements ImageManager.OnImageLoadedListener
    {
        private static final Map<UriImageListener, UriImageListener> LIVE =
            new ConcurrentHashMap<>();
        private static final long TIMEOUT_MILLIS = 30000;

        private final WeakReference<Activity> activity;
        private final GMFunction callback;
        private final ExecutorService background;
        private final java.util.concurrent.ScheduledFuture<?> timeoutTask;
        private volatile boolean completed = false;

        static void register(
            Activity activity,
            Uri uri,
            GMFunction callback,
            ExecutorService background,
            ScheduledExecutorService scheduler)
        {
            UriImageListener listener =
                new UriImageListener(activity, callback, background, scheduler);

            LIVE.put(listener, listener);
            ImageManager.create(activity).loadImage(listener, uri);
        }

        private UriImageListener(
            Activity activity,
            GMFunction callback,
            ExecutorService background,
            ScheduledExecutorService scheduler)
        {
            this.activity = new WeakReference<>(activity);
            this.callback = callback;
            this.background = background;
            this.timeoutTask = scheduler.schedule(
                this::onTimeout,
                TIMEOUT_MILLIS,
                TimeUnit.MILLISECONDS
            );
        }

        @Override
        public void onImageLoaded(
            @NonNull Uri uri,
            Drawable drawable,
            boolean isRequestedDrawable)
        {
            background.execute(() ->
            {
                if (!completed && timeoutTask.cancel(false))
                {
                    completed = true;
                    try
                    {
                        if (!isRequestedDrawable
                            || !(drawable instanceof BitmapDrawable))
                        {
                            callback.call(new PlayServicesTaskResult(false, "", "The URI image could not be loaded."));
                            return;
                        }

                        Activity activity = Objects.requireNonNull(this.activity.get());
                        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                        File output = File.createTempFile(
                            "play_services_",
                            ".png",
                            activity.getCacheDir()
                        );

                        try (FileOutputStream stream = new FileOutputStream(output))
                        {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        }

                        callback.call(new PlayServicesTaskResult(true, output.getAbsolutePath(), ""));
                    }
                    catch (Exception exception)
                    {
                        callback.call(new PlayServicesTaskResult(false, "", error(exception)));
                    }
                    finally
                    {
                        LIVE.remove(this);
                    }
                }
            });
        }

        private void onTimeout()
        {
            if (completed)
                return;

            completed = true;
            callback.call(new PlayServicesTaskResult(false, "", "Image loading timed out."));
            LIVE.remove(this);
        }
    }

    // -------------------------------------------------------------------------
    // Saved Games
    // -------------------------------------------------------------------------

    public void play_services_saved_games_show_saved_games_ui(
        String title,
        boolean buttonAdd,
        boolean buttonDelete,
        double max_results,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesSavedGamesUIEvent(PlayServicesSavedGamesUIResult.Error.value(), new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""), "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesSavedGamesUIEvent(PlayServicesSavedGamesUIResult.Error.value(), new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""), authenticationError()));
            return;
        }

        savedGamesUiCallback = callback;

        PlayGames.getSnapshotsClient(activity)
            .getSelectSnapshotIntent(
                title,
                buttonAdd,
                buttonDelete,
                (int)max_results)
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_SAVED_GAMES))
            .addOnFailureListener(exception ->
            {
                GMFunction pending = savedGamesUiCallback;
                savedGamesUiCallback = null;

                if (pending != null)
                {
                    pending.call(new PlayServicesSavedGamesUIEvent(PlayServicesSavedGamesUIResult.Error.value(), new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""), error(exception)));
                }
            });
    }

    public void onActivityResult(
        int requestCode,
        int resultCode,
        Intent data)
    {
        if (requestCode == RC_PLAYER_SEARCH)
        {
            handlePlayerSearchResult(resultCode, data);
            return;
        }

        if (requestCode == RC_FRIENDS_CONSENT)
        {
            handleFriendsConsentResult(resultCode);
            return;
        }

        if (requestCode == RC_SHOW_PROFILE)
        {
            return;
        }

        if (requestCode != RC_SAVED_GAMES)
            return;

        GMFunction callback = savedGamesUiCallback;
        savedGamesUiCallback = null;

        if (callback == null)
            return;

        if (data == null || resultCode != Activity.RESULT_OK)
        {
            callback.call(new PlayServicesSavedGamesUIEvent(
                PlayServicesSavedGamesUIResult.Cancelled.value(),
                new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""),
                ""
            ));
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA))
        {
            SnapshotMetadata metadata =
                data.getParcelableExtra(
                    SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

            callback.call(new PlayServicesSavedGamesUIEvent(
                PlayServicesSavedGamesUIResult.Selected.value(),
                new PlayServicesSnapshotMetadata(
                    safeString(metadata.getUniqueName()),
                    safeString(metadata.getDescription()),
                    safeString(metadata.getDeviceName()),
                    metadata.getLastModifiedTimestamp(),
                    metadata.getPlayedTime(),
                    metadata.getProgressValue(),
                    metadata.hasChangePending(),
                    metadata.getCoverImageUri() != null ? metadata.getCoverImageUri().toString() : ""
                ),
                ""
            ));
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW))
        {
            callback.call(new PlayServicesSavedGamesUIEvent(
                PlayServicesSavedGamesUIResult.CreatedNew.value(),
                new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""),
                ""
            ));
            return;
        }

        callback.call(new PlayServicesSavedGamesUIEvent(
            PlayServicesSavedGamesUIResult.Cancelled.value(),
            new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, ""),
            ""
        ));
    }

    public PlayServicesError play_services_saved_games_commit_and_close(
        PlayServicesSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        if (options == null)
            return PlayServicesError.InvalidArgument;

        String name = options.name();
        Snapshot snapshot = snapshots.get(name);

        if (snapshot == null)
            return PlayServicesError.InvalidArgument;

        commitSnapshot(snapshot, options, callback);
        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_saved_games_commit_new(
        PlayServicesSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        if (options == null)
            return PlayServicesError.InvalidArgument;

        String name = options.name();

        PlayGames.getSnapshotsClient(activity)
            .open(
                name,
                true,
                SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    return;
                }

                Snapshot snapshot = task.getResult().getData();
                if (snapshot == null)
                {
                    callback.call(new PlayServicesResult(false, "No snapshot was returned."), Optional.empty());
                    return;
                }

                trackSnapshot(snapshot.getMetadata().getUniqueName(), snapshot);
                commitSnapshot(snapshot, options, callback);
            });

        return PlayServicesError.Ok;
    }

    private void commitSnapshot(
        Snapshot snapshot,
        PlayServicesSavedGameCommitOptions options,
        GMFunction callback)
    {
        background.execute(() ->
        {
            String name = snapshot.getMetadata().getUniqueName();

            try
            {
                String data = options.data() != null ? options.data() : "";
                snapshot.getSnapshotContents()
                    .writeBytes(data.getBytes(StandardCharsets.UTF_8));

                SnapshotMetadataChange.Builder builder =
                    new SnapshotMetadataChange.Builder();

                if (options.desc() != null && !options.desc().isEmpty())
                    builder.setDescription(options.desc());

                if (options.played_time_millis() >= 0)
                    builder.setPlayedTimeMillis((long)options.played_time_millis());

                if (options.progress_value() >= 0)
                    builder.setProgressValue((long)options.progress_value());

                String coverImagePath = options.cover_image_path();
                if (coverImagePath != null && !coverImagePath.isEmpty())
                {
                    Bitmap bitmap = android.graphics.BitmapFactory
                        .decodeFile(coverImagePath);

                    if (bitmap != null)
                        builder.setCoverImage(bitmap);
                }

                Activity activity = activity();
                if (activity == null)
                {
                    snapshots.remove(name);
                    callback.call(new PlayServicesResult(false, "Activity is null."), Optional.empty());
                    return;
                }

                activity.runOnUiThread(() -> PlayGames.getSnapshotsClient(activity)
                    .commitAndClose(snapshot, builder.build())
                    .addOnCompleteListener(task ->
                    {
                        snapshots.remove(name);

                        if (task.isSuccessful())
                            callback.call(new PlayServicesResult(true, ""), Optional.of(snapshotMetadataToRecord(task.getResult())));
                        else
                            callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    }));
            }
            catch (Exception exception)
            {
                callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
            }
        });
    }

    public void play_services_saved_games_load(
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesSnapshotMetadataList(
                false,
                new java.util.ArrayList<>(),
                "Activity is null."
            ));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesSnapshotMetadataList(
                false,
                new java.util.ArrayList<>(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getSnapshotsClient(activity)
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesSnapshotMetadataList(
                        false,
                        new java.util.ArrayList<>(),
                        error(task.getException())
                    ));
                    return;
                }

                SnapshotMetadataBuffer buffer = task.getResult() != null ? task.getResult().get() : null;
                java.util.List<PlayServicesSnapshotMetadata> snapshotList = new java.util.ArrayList<>();

                if (buffer != null)
                {
                    try
                    {
                        for (SnapshotMetadata metadata : buffer)
                            snapshotList.add(snapshotMetadataToRecord(metadata));
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(new PlayServicesSnapshotMetadataList(
                    true,
                    snapshotList,
                    ""
                ));
            });
    }

    public void play_services_saved_games_open(
        String name,
        final GMFunction callback)
    {
        openSnapshot(
            name,
            SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED,
            false,
            callback
        );
    }

    public void play_services_saved_games_open_conflict(String name, PlayServicesSavedGamesConflictPolicy conflict_policy, GMFunction callback)
    {
        openSnapshot(name, (int)conflict_policy.value(), true, callback);
    }

    private void openSnapshot(
        String name,
        int policy,
        boolean includeConflict,
        GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesSnapshot(
                false,
                emptySnapshotOpenResult(),
                authenticationError()
            ));
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesSnapshot(
                false,
                emptySnapshotOpenResult(),
                "Activity is null."
            ));
            return;
        }

        // Run the completion on the background executor: the success path calls
        // Snapshot.readFully() (synchronous disk I/O) which must not run on the UI
        // thread. Callback delivery is thread-agnostic (marshalled via DispatchQueue).
        PlayGames.getSnapshotsClient(activity)
            .open(name, false, policy)
            .addOnCompleteListener(background, task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesSnapshot(
                        false,
                        emptySnapshotOpenResult(),
                        error(task.getException())
                    ));
                    return;
                }

                try
                {
                    PlayServicesSnapshotOpenResult response =
                        buildSnapshotOpenResult(task.getResult(), includeConflict);

                    callback.call(new PlayServicesSnapshot(
                        true,
                        response,
                        ""
                    ));
                }
                catch (Exception exception)
                {
                    callback.call(new PlayServicesSnapshot(
                        false,
                        emptySnapshotOpenResult(),
                        error(exception)
                    ));
                }
            });
    }

    // Shared by openSnapshot and play_services_saved_games_resolve_conflict - both wrap
    // Task<DataOrConflict<Snapshot>> and need identical is_conflict/metadata handling.
    private PlayServicesSnapshotOpenResult buildSnapshotOpenResult(
        DataOrConflict<Snapshot> result,
        boolean includeConflict) throws Exception
    {
        if (!result.isConflict())
        {
            Snapshot snapshot = result.getData();

            if (snapshot == null)
                throw new IllegalStateException("No snapshot was returned.");

            trackSnapshot(snapshot.getMetadata().getUniqueName(), snapshot);

            return new PlayServicesSnapshotOpenResult(
                false,
                snapshotMetadataToRecord(snapshot.getMetadata()),
                readSnapshot(snapshot),
                "",
                emptySnapshotMetadata(),
                "",
                emptySnapshotMetadata(),
                ""
            );
        }

        if (!includeConflict)
            throw new IllegalStateException("A Saved Games conflict was returned.");

        conflictLocal = result.getConflict().getConflictingSnapshot();
        conflictRemote = result.getConflict().getSnapshot();

        return new PlayServicesSnapshotOpenResult(
            true,
            emptySnapshotMetadata(),
            "",
            safeString(result.getConflict().getConflictId()),
            snapshotMetadataToRecord(conflictLocal.getMetadata()),
            readSnapshot(conflictLocal),
            snapshotMetadataToRecord(conflictRemote.getMetadata()),
            readSnapshot(conflictRemote)
        );
    }

    // Fixes the snapshot handle leak: re-tracking an already-open name used to
    // silently overwrite the map entry without releasing the prior Snapshot handle.
    private void trackSnapshot(String name, Snapshot snapshot)
    {
        Snapshot previous = snapshots.put(name, snapshot);

        if (previous != null && previous != snapshot)
        {
            Activity activity = activity();
            if (activity != null)
            {
                PlayGames.getSnapshotsClient(activity)
                    .discardAndClose(previous)
                    .addOnFailureListener(exception ->
                        Log.w(TAG, "Failed to discard superseded snapshot handle for " + name, exception));
            }
        }
    }

    public void play_services_saved_games_delete(
        String name,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, name, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, name, authenticationError()))
            return;

        Snapshot snapshot = snapshots.get(name);
        if (snapshot == null)
        {
            callback.call(new PlayServicesTaskResult(false, name, "Snapshot is not opened or has already been closed."));
            return;
        }

        PlayGames.getSnapshotsClient(activity)
            .delete(snapshot.getMetadata())
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    snapshots.remove(name);
                    callback.call(new PlayServicesTaskResult(true, name, ""));
                }
                else
                {
                    callback.call(new PlayServicesTaskResult(false, name, error(task.getException())));
                }
            });
    }

    public PlayServicesError play_services_saved_games_resolve_conflict(
        String conflictId,
        boolean useLocal,
        final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        Snapshot selected = useLocal ? conflictLocal : conflictRemote;
        if (selected == null)
            return PlayServicesError.InvalidArgument;

        PlayGames.getSnapshotsClient(activity)
            .resolveConflict(conflictId, selected)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    return;
                }

                DataOrConflict<Snapshot> result = task.getResult();

                try
                {
                    // resolveConflict() returns the same DataOrConflict<Snapshot> shape open()
                    // does - it can itself race into a fresh conflict, so only clear the retry
                    // state when the result actually isn't conflicted anymore.
                    PlayServicesSnapshotOpenResult data = buildSnapshotOpenResult(result, true);

                    if (!result.isConflict())
                    {
                        conflictLocal = null;
                        conflictRemote = null;
                    }

                    callback.call(new PlayServicesResult(true, ""), Optional.of(data));
                }
                catch (Exception exception)
                {
                    callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
                }
            });

        return PlayServicesError.Ok;
    }

    private static String readSnapshot(Snapshot snapshot) throws Exception
    {
        SnapshotContents contents = snapshot.getSnapshotContents();
        return new String(contents.readFully(), StandardCharsets.UTF_8);
    }

    // -------------------------------------------------------------------------
    // Activity Result Handlers
    // -------------------------------------------------------------------------

    private void handlePlayerSearchResult(int resultCode, Intent data)
    {
        GMFunction callback = playerSearchCallback;
        playerSearchCallback = null;

        if (callback == null)
            return;

        if (resultCode == Activity.RESULT_CANCELED)
        {
            callback.call(new PlayServicesResult(false, "User canceled player search."), Optional.empty());
            return;
        }

        if (resultCode != Activity.RESULT_OK)
        {
            callback.call(new PlayServicesResult(false, "Player search failed with result code: " + resultCode), Optional.empty());
            return;
        }

        if (data == null)
        {
            callback.call(new PlayServicesResult(false, "No data returned from player search."), Optional.empty());
            return;
        }

        try
        {
            java.util.ArrayList<Player> results = data.getParcelableArrayListExtra(
                PlayersClient.EXTRA_PLAYER_SEARCH_RESULTS);

            if (results == null || results.size() == 0)
            {
                callback.call(new PlayServicesResult(false, "No players found in search results."), Optional.empty());
                return;
            }

            // Google typically returns at most one player from search UI
            Player selected = results.get(0);

            callback.call(new PlayServicesResult(true, ""), Optional.of(playerToInfo(selected)));
        }
        catch (Exception exception)
        {
            callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
        }
    }

    private void handleFriendsConsentResult(int resultCode)
    {
        GMFunction callback = friendsConsentCallback;
        friendsConsentCallback = null;

        if (callback == null)
            return;

        if (resultCode == Activity.RESULT_CANCELED)
        {
            failFriendsList(callback, "User denied friends access permission.");
            return;
        }

        if (resultCode != Activity.RESULT_OK)
        {
            failFriendsList(callback, "Friends permission result: " + resultCode);
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            failFriendsList(callback, "Activity is null.");
            return;
        }

        loadFriendsWithConsentHandling(activity, friendsConsentPageSize, friendsConsentForceReload, callback);
    }

    private static PlayServicesAchievement achievementToRecord(Achievement achievement)
    {
        int type = achievement.getType();
        boolean incremental = type == Achievement.TYPE_INCREMENTAL;

        int currentSteps = incremental ? achievement.getCurrentSteps() : 0;
        int totalSteps = incremental ? achievement.getTotalSteps() : 0;

        return new PlayServicesAchievement(
            safeString(achievement.getAchievementId()),
            safeString(achievement.getName()),
            safeString(achievement.getDescription()),
            achievement.getState(),
            type,
            currentSteps,
            totalSteps,
            achievement.getLastUpdatedTimestamp(),
            achievement.getXpValue(),
            achievement.getRevealedImageUri() != null ? achievement.getRevealedImageUri().toString() : "",
            achievement.getUnlockedImageUri() != null ? achievement.getUnlockedImageUri().toString() : ""
        );
    }

    private static PlayServicesLeaderboard leaderboardToRecord(Leaderboard leaderboard)
    {
        if (leaderboard == null)
            return emptyLeaderboardRecord();

        java.util.List<PlayServicesLeaderboardVariant> variants = new java.util.ArrayList<>();

        for (LeaderboardVariant variant : leaderboard.getVariants())
        {
            variants.add(new PlayServicesLeaderboardVariant(
                variant.getCollection(),
                variant.getTimeSpan(),
                variant.hasPlayerInfo()
            ));
        }

        return new PlayServicesLeaderboard(
            safeString(leaderboard.getLeaderboardId()),
            safeString(leaderboard.getDisplayName()),
            leaderboard.getScoreOrder(),
            variants
        );
    }

    private static PlayServicesLeaderboardScore leaderboardScoreToRecord(LeaderboardScore score)
    {
        return new PlayServicesLeaderboardScore(
            safeString(score.getDisplayRank()),
            safeString(score.getDisplayScore()),
            score.getRawScore(),
            safeString(score.getScoreTag()),
            score.getTimestampMillis(),
            playerInfoFromScoreHolder(score.getScoreHolder())
        );
    }

    private static PlayServicesPlayerInfo playerInfoFromScoreHolder(com.google.android.gms.games.Player player)
    {
        if (player == null)
            return emptyPlayerInfo();

        return new PlayServicesPlayerInfo(
            safeString(player.getPlayerId()),
            safeString(player.getDisplayName()),
            safeString(player.getTitle()),
            player.getIconImageUri() != null ? player.getIconImageUri().toString() : "",
            player.getHiResImageUri() != null ? player.getHiResImageUri().toString() : ""
        );
    }

    private static PlayServicesSnapshotMetadata snapshotMetadataToRecord(SnapshotMetadata metadata)
    {
        if (metadata == null)
            return emptySnapshotMetadata();

        return new PlayServicesSnapshotMetadata(
            safeString(metadata.getUniqueName()),
            safeString(metadata.getDescription()),
            safeString(metadata.getDeviceName()),
            metadata.getLastModifiedTimestamp(),
            metadata.getPlayedTime(),
            metadata.getProgressValue(),
            metadata.hasChangePending(),
            metadata.getCoverImageUri() != null ? metadata.getCoverImageUri().toString() : ""
        );
    }

    private static PlayServicesLeaderboard emptyLeaderboardRecord()
    {
        return new PlayServicesLeaderboard(
            "",
            "",
            PlayServicesLeaderboardScoreOrder.SmallerIsBetter.value(),
            new java.util.ArrayList<>()
        );
    }

    private static PlayServicesSnapshotMetadata emptySnapshotMetadata()
    {
        return new PlayServicesSnapshotMetadata("", "", "", 0.0, 0.0, 0.0, false, "");
    }

    private static PlayServicesSnapshotOpenResult emptySnapshotOpenResult()
    {
        return new PlayServicesSnapshotOpenResult(
            false,
            emptySnapshotMetadata(),
            "",
            "",
            emptySnapshotMetadata(),
            "",
            emptySnapshotMetadata(),
            ""
        );
    }

    private static PlayServicesPlayerInfo emptyPlayerInfo()
    {
        return new PlayServicesPlayerInfo("", "", "", "", "");
    }

}
