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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Extension Generator conversion of YYGooglePlayServices.
 *
 * Social Async events have been replaced by GMFunction callbacks.
 * Outbound structured payloads use GMExtWire.StructStream and ArrayStream.
 * Generated records are used only for structured values received from GML.
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

    private final ExecutorService background = Executors.newCachedThreadPool();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Map<String, Snapshot> snapshots = new ConcurrentHashMap<>();

    private volatile Snapshot conflictLocal;
    private volatile Snapshot conflictRemote;
    private volatile GMFunction savedGamesUiCallback;

    private volatile PlayerBuffer friendsBuffer;
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
        releaseFriendsBuffer();
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

    private boolean requireAuthentication(GMFunction callback, boolean requireAuth, String value, String error)
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
            callback, false, "", authenticationError()))
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

    public void play_services_player_current(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return;
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
    }

    public void play_services_player_current_id(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        PlayGames.getPlayersClient(activity).getCurrentPlayerId()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(new PlayServicesTaskResult(true, safeString(task.getResult()), ""));
                else
                    callback.call(new PlayServicesTaskResult(false, "", error(task.getException())));
            });
    }

    public void play_services_player_stats_load(
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayerStats(false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, authenticationError()));
            return;
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
    }

    public void play_services_player_load(
        String player_id,
        boolean force_reload,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayer(false, new PlayServicesPlayerInfo("", "", "", "", ""), authenticationError()));
            return;
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
    }

    public void play_services_friends_load(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, authenticationError()));
            return;
        }

        int clampedResults = (int) Math.max(1, Math.min(25, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_friends_load: max_results " + (int)max_results + " clamped to [1, 25]");

        PlayGames.getPlayersClient(activity)
            .loadFriends(clampedResults, force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    releaseFriendsBuffer();
                    callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(task.getException())));
                    return;
                }

                AnnotatedData<PlayerBuffer> annotatedData = task.getResult();
                PlayerBuffer buffer = annotatedData != null ? annotatedData.get() : null;

                releaseFriendsBuffer();
                friendsBuffer = buffer;

                java.util.List<PlayServicesPlayerInfo> players = new java.util.ArrayList<>();

                if (buffer != null)
                {
                    try
                    {
                        for (Player player : buffer)
                            players.add(playerToInfo(player));
                    }
                    catch (Exception exception)
                    {
                        releaseFriendsBuffer();
                        callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(exception)));
                        return;
                    }
                }

                boolean hasMore = buffer != null && buffer.getCount() > 0;

                callback.call(new PlayServicesPlayerList(
                    true,
                    players,
                    hasMore,
                    ""
                ));
            });
    }

    public void play_services_friends_load_more(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, authenticationError()));
            return;
        }

        if (friendsBuffer == null)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "No friends buffer available. Call play_services_friends_load first."));
            return;
        }

        PlayGames.getPlayersClient(activity)
            .loadMoreFriends(25)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    releaseFriendsBuffer();
                    callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(task.getException())));
                    return;
                }

                AnnotatedData<PlayerBuffer> annotatedData = task.getResult();
                PlayerBuffer buffer = annotatedData != null ? annotatedData.get() : null;

                releaseFriendsBuffer();
                friendsBuffer = buffer;

                java.util.List<PlayServicesPlayerInfo> players = new java.util.ArrayList<>();

                if (buffer != null)
                {
                    try
                    {
                        for (Player player : buffer)
                            players.add(playerToInfo(player));
                    }
                    catch (Exception exception)
                    {
                        releaseFriendsBuffer();
                        callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(exception)));
                        return;
                    }
                }

                boolean hasMore = buffer != null && buffer.getCount() > 0;

                callback.call(new PlayServicesPlayerList(
                    true,
                    players,
                    hasMore,
                    ""
                ));
            });
    }


    private void releaseFriendsBuffer()
    {
        PlayerBuffer buffer = friendsBuffer;
        if (buffer != null)
        {
            friendsBuffer = null;
            buffer.release();
        }
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

    public void play_services_player_search_show(final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
        {
            callback.call(playerSearchResultError("Google Play Games user is not authenticated."));
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            callback.call(playerSearchResultError("Activity is null."));
            return;
        }

        playerSearchCallback = callback;

        PlayGames.getPlayersClient(activity)
            .getPlayerSearchIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_PLAYER_SEARCH))
            .addOnFailureListener(exception ->
            {
                playerSearchCallback = null;
                callback.call(playerSearchResultError(error(exception)));
            });
    }

    // -------------------------------------------------------------------------
    // Friends Load with Consent
    // -------------------------------------------------------------------------

    public void play_services_friends_load_with_consent(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "Activity is null."));
            return;
        }

        if (!authenticationKnown || !authenticated)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, authenticationError()));
            return;
        }

        int clampedResults = (int) Math.max(1, Math.min(25, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_friends_load_with_consent: max_results " + (int)max_results + " clamped to [1, 25]");

        loadFriendsWithConsentHandling(activity, clampedResults, force_reload, callback);
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
                PlayerBuffer buffer = annotatedData != null ? annotatedData.get() : null;

                releaseFriendsBuffer();
                friendsBuffer = buffer;

                java.util.List<PlayServicesPlayerInfo> players = new java.util.ArrayList<>();

                if (buffer != null)
                {
                    try
                    {
                        for (Player player : buffer)
                            players.add(playerToInfo(player));
                    }
                    catch (Exception exception)
                    {
                        releaseFriendsBuffer();
                        callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(exception)));
                        return;
                    }
                }

                boolean hasMore = buffer != null && buffer.getCount() > 0;

                callback.call(new PlayServicesPlayerList(
                    true,
                    players,
                    hasMore,
                    ""
                ));
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
                        callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(e)));
                    }
                }
                else
                {
                    callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, error(exception)));
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
            callback, false, achievementId, authenticationError()))
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
            callback, false, achievementId, authenticationError()))
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
            callback, false, achievementId, authenticationError()))
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
            callback, false, achievementId, authenticationError()))
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

        int clampedResults = (int) Math.max(1, Math.min(25, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_leaderboard_load_player_centered_scores: max_results " + (int)max_results + " clamped to [1, 25]");

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

        int clampedResults = (int) Math.max(1, Math.min(25, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_leaderboard_load_top_scores: max_results " + (int)max_results + " clamped to [1, 25]");

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

    public void play_services_saved_games_commit_and_close(
        PlayServicesSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        if (options == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Commit options cannot be null."));
            return;
        }

        String name = options.name();
        Snapshot snapshot = snapshots.get(name);

        if (snapshot == null)
        {
            callback.call(new PlayServicesTaskResult(false, name, "Snapshot is not opened or has already been closed."));
            return;
        }

        commitSnapshot(snapshot, options, callback);
    }

    public void play_services_saved_games_commit_new(
        PlayServicesSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        if (options == null)
        {
            callback.call(new PlayServicesTaskResult(false, "", "Commit options cannot be null."));
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, options.name(), "Activity is null."));
            return;
        }

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
                    callback.call(new PlayServicesTaskResult(false, name, error(task.getException())));
                    return;
                }

                Snapshot snapshot = task.getResult().getData();
                if (snapshot == null)
                {
                    callback.call(new PlayServicesTaskResult(false, name, "No snapshot was returned."));
                    return;
                }

                String snapshotName = snapshot.getMetadata().getUniqueName();
                snapshots.put(snapshotName, snapshot);
                commitSnapshot(snapshot, options, callback);
            });
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
                    callback.call(new PlayServicesTaskResult(false, name, "Activity is null."));
                    return;
                }

                activity.runOnUiThread(() -> PlayGames.getSnapshotsClient(activity)
                    .commitAndClose(snapshot, builder.build())
                    .addOnCompleteListener(task ->
                    {
                        snapshots.remove(name);

                        if (task.isSuccessful())
                            callback.call(new PlayServicesTaskResult(true, name, ""));
                        else
                            callback.call(new PlayServicesTaskResult(false, name, error(task.getException())));
                    }));
            }
            catch (Exception exception)
            {
                callback.call(new PlayServicesTaskResult(false, name, error(exception)));
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

                DataOrConflict<Snapshot> result = task.getResult();

                try
                {
                    PlayServicesSnapshotOpenResult response;

                    if (!result.isConflict())
                    {
                        Snapshot snapshot = result.getData();

                        if (snapshot == null)
                        {
                            throw new IllegalStateException(
                                "No snapshot was returned."
                            );
                        }

                        String snapshotName = snapshot.getMetadata().getUniqueName();
                        snapshots.put(snapshotName, snapshot);

                        response = new PlayServicesSnapshotOpenResult(
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
                    else
                    {
                        if (!includeConflict)
                        {
                            throw new IllegalStateException(
                                "A Saved Games conflict was returned."
                            );
                        }

                        conflictLocal =
                            result.getConflict().getConflictingSnapshot();

                        conflictRemote =
                            result.getConflict().getSnapshot();

                        response = new PlayServicesSnapshotOpenResult(
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
            callback, false, name, authenticationError()))
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

    public void play_services_saved_games_resolve_conflict(
        String conflictId,
        boolean useLocal,
        final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesTaskResult(false, conflictId, "Activity is null."));
            return;
        }

        if (!requireAuthentication(
            callback, false, conflictId, authenticationError()))
            return;

        Snapshot selected = useLocal ? conflictLocal : conflictRemote;
        if (selected == null)
        {
            callback.call(new PlayServicesTaskResult(false, conflictId, "There is no active conflict."));
            return;
        }

        PlayGames.getSnapshotsClient(activity)
            .resolveConflict(conflictId, selected)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesTaskResult(false, conflictId, error(task.getException())));
                    return;
                }

                conflictLocal = null;
                conflictRemote = null;
                callback.call(new PlayServicesTaskResult(true, conflictId, ""));
            });
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
            callback.call(playerSearchResultError("User canceled player search."));
            return;
        }

        if (resultCode != Activity.RESULT_OK)
        {
            callback.call(playerSearchResultError("Player search failed with result code: " + resultCode));
            return;
        }

        if (data == null)
        {
            callback.call(playerSearchResultError("No data returned from player search."));
            return;
        }

        try
        {
            java.util.ArrayList<Player> results = data.getParcelableArrayListExtra(
                PlayersClient.EXTRA_PLAYER_SEARCH_RESULTS);

            if (results == null || results.size() == 0)
            {
                callback.call(playerSearchResultError("No players found in search results."));
                return;
            }

            // Google typically returns at most one player from search UI
            Player selected = results.get(0);

            GMExtWire.StructStream stream = streamStruct()
                .kv("status", 1)
                .kv("player_id", safeString(selected.getPlayerId()))
                .kv("display_name", safeString(selected.getDisplayName()))
                .kv("icon_image_url", selected.getIconImageUri() != null ? selected.getIconImageUri().toString() : "")
                .kv("hi_res_image_url", selected.getHiResImageUri() != null ? selected.getHiResImageUri().toString() : "");

            callback.call(stream);
        }
        catch (Exception exception)
        {
            callback.call(playerSearchResultError(error(exception)));
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
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "User denied friends access permission."));
            return;
        }

        if (resultCode != Activity.RESULT_OK)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "Friends permission result: " + resultCode));
            return;
        }

        Activity activity = activity();
        if (activity == null)
        {
            callback.call(new PlayServicesPlayerList(false, new java.util.ArrayList<>(), false, "Activity is null."));
            return;
        }

        loadFriendsWithConsentHandling(activity, friendsConsentPageSize, friendsConsentForceReload, callback);
    }

    // -------------------------------------------------------------------------
    // Dynamic GML stream conversion
    // -------------------------------------------------------------------------

    private static GMExtWire.StructStream streamStruct()
    {
        return new GMExtWire.StructStream(4096);
    }

    private static GMExtWire.ArrayStream streamArray()
    {
        return new GMExtWire.ArrayStream(32768);
    }

    private static GMExtWire.StructStream playerToStream(
        com.google.android.gms.games.Player player)
    {
        if (player == null)
            return emptyPlayerStream();

        return streamStruct()
            .kv("player_id", safeString(player.getPlayerId()))
            .kv("display_name", safeString(player.getDisplayName()))
            .kv("title", safeString(player.getTitle()))
            .kv(
                "icon_image_uri",
                player.getIconImageUri() != null
                    ? player.getIconImageUri().toString()
                    : ""
            )
            .kv(
                "hi_res_image_uri",
                player.getHiResImageUri() != null
                    ? player.getHiResImageUri().toString()
                    : ""
            );
    }

    private static GMExtWire.StructStream playerStatsToStream(
        PlayerStats stats)
    {
        return streamStruct()
            .kv(
                "average_session_length",
                stats.getAverageSessionLength()
            )
            .kv(
                "days_since_last_played",
                stats.getDaysSinceLastPlayed()
            )
            .kv(
                "number_of_purchases",
                stats.getNumberOfPurchases()
            )
            .kv(
                "number_of_sessions",
                stats.getNumberOfSessions()
            )
            .kv(
                "session_percentile",
                stats.getSessionPercentile()
            )
            .kv(
                "spend_percentile",
                stats.getSpendPercentile()
            )
            .kv(
                "churn_probability",
                stats.getChurnProbability()
            )
            .kv(
                "high_spender_probability",
                stats.getHighSpenderProbability()
            )
            .kv(
                "spend_probability",
                stats.getSpendProbability()
            )
            .kv(
                "total_spend_next_28_days",
                stats.getTotalSpendNext28Days()
            );
    }

    private static GMExtWire.StructStream achievementToStream(
        Achievement achievement)
    {
        int type = achievement.getType();
        boolean incremental = type == Achievement.TYPE_INCREMENTAL;

        int currentSteps =
            incremental ? achievement.getCurrentSteps() : 0;

        int totalSteps =
            incremental ? achievement.getTotalSteps() : 0;

        return streamStruct()
            .kv(
                "achievement_id",
                safeString(achievement.getAchievementId())
            )
            .kv("name", safeString(achievement.getName()))
            .kv(
                "description",
                safeString(achievement.getDescription())
            )
            .kv("state", achievement.getState())
            .kv("type", type)
            .kv("current_steps", currentSteps)
            .kv("total_steps", totalSteps)
            .kv(
                "last_updated_timestamp",
                achievement.getLastUpdatedTimestamp()
            )
            .kv("xp_value", achievement.getXpValue())
            .kv(
                "revealed_image_uri",
                achievement.getRevealedImageUri() != null
                    ? achievement.getRevealedImageUri().toString()
                    : ""
            )
            .kv(
                "unlocked_image_uri",
                achievement.getUnlockedImageUri() != null
                    ? achievement.getUnlockedImageUri().toString()
                    : ""
            );
    }

    private static GMExtWire.StructStream leaderboardToStream(
        Leaderboard leaderboard)
    {
        if (leaderboard == null)
            return emptyLeaderboardStream();

        GMExtWire.ArrayStream variants = streamArray();

        for (LeaderboardVariant variant : leaderboard.getVariants())
        {
            variants.add(
                streamStruct()
                    .kv("collection", variant.getCollection())
                    .kv("time_span", variant.getTimeSpan())
                    .kv("has_player_info", variant.hasPlayerInfo())
            );
        }

        return streamStruct()
            .kv(
                "leaderboard_id",
                safeString(leaderboard.getLeaderboardId())
            )
            .kv(
                "display_name",
                safeString(leaderboard.getDisplayName())
            )
            .kv("score_order", leaderboard.getScoreOrder())
            .kv("variants", variants);
    }

    private static GMExtWire.ArrayStream leaderboardScoresToStream(
        LeaderboardScoreBuffer buffer)
    {
        GMExtWire.ArrayStream scores = streamArray();

        if (buffer == null)
            return scores;

        for (LeaderboardScore score : buffer)
        {
            scores.add(
                streamStruct()
                    .kv(
                        "display_rank",
                        safeString(score.getDisplayRank())
                    )
                    .kv(
                        "display_score",
                        safeString(score.getDisplayScore())
                    )
                    .kv("raw_score", score.getRawScore())
                    .kv(
                        "score_tag",
                        safeString(score.getScoreTag())
                    )
                    .kv(
                        "timestamp_millis",
                        score.getTimestampMillis()
                    )
                    .kv(
                        "score_holder",
                        playerToStream(score.getScoreHolder())
                    )
            );
        }

        return scores;
    }

    private static GMExtWire.StructStream scoreResultToStream(
        ScoreSubmissionData.Result result)
    {
        if (result == null)
            return emptyScoreResultStream();

        return streamStruct()
            .kv("raw_score", result.rawScore)
            .kv(
                "formatted_score",
                safeString(result.formattedScore)
            )
            .kv("score_tag", safeString(result.scoreTag))
            .kv("new_best", result.newBest);
    }

    private static GMExtWire.StructStream scoreReportToStream(
        ScoreSubmissionData report)
    {
        if (report == null)
            return emptyScoreReportStream();

        GMExtWire.StructStream results = streamStruct()
            .kv(
                "daily",
                scoreResultToStream(
                    report.getScoreResult(
                        LeaderboardVariant.TIME_SPAN_DAILY
                    )
                )
            )
            .kv(
                "weekly",
                scoreResultToStream(
                    report.getScoreResult(
                        LeaderboardVariant.TIME_SPAN_WEEKLY
                    )
                )
            )
            .kv(
                "all_time",
                scoreResultToStream(
                    report.getScoreResult(
                        LeaderboardVariant.TIME_SPAN_ALL_TIME
                    )
                )
            );

        return streamStruct()
            .kv(
                "leaderboard_id",
                safeString(report.getLeaderboardId())
            )
            .kv("player_id", safeString(report.getPlayerId()))
            .kv("results", results);
    }

    private static GMExtWire.StructStream snapshotMetadataToStream(
        SnapshotMetadata metadata)
    {
        if (metadata == null)
            return emptySnapshotMetadataStream();

        return streamStruct()
            .kv(
                "unique_name",
                safeString(metadata.getUniqueName())
            )
            .kv(
                "description",
                safeString(metadata.getDescription())
            )
            .kv(
                "device_name",
                safeString(metadata.getDeviceName())
            )
            .kv(
                "last_modified_timestamp",
                metadata.getLastModifiedTimestamp()
            )
            .kv("played_time", metadata.getPlayedTime())
            .kv("progress_value", metadata.getProgressValue())
            .kv(
                "has_change_pending",
                metadata.hasChangePending()
            )
            .kv(
                "cover_image_uri",
                metadata.getCoverImageUri() != null
                    ? metadata.getCoverImageUri().toString()
                    : ""
            );
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

    private static GMExtWire.StructStream playerSearchResultError(String errorMessage)
    {
        return streamStruct()
            .kv("status", 0)
            .kv("player_id", "")
            .kv("display_name", "")
            .kv("icon_image_url", "")
            .kv("hi_res_image_url", "");
    }

    private static GMExtWire.StructStream emptyPlayerStream()
    {
        return streamStruct()
            .kv("player_id", "")
            .kv("display_name", "")
            .kv("title", "")
            .kv("icon_image_uri", "")
            .kv("hi_res_image_uri", "");
    }

    private static GMExtWire.StructStream emptyPlayerStatsStream()
    {
        return streamStruct()
            .kv("average_session_length", 0.0)
            .kv("days_since_last_played", 0.0)
            .kv("number_of_purchases", 0.0)
            .kv("number_of_sessions", 0.0)
            .kv("session_percentile", 0.0)
            .kv("spend_percentile", 0.0)
            .kv("churn_probability", 0.0)
            .kv("high_spender_probability", 0.0)
            .kv("spend_probability", 0.0)
            .kv("total_spend_next_28_days", 0.0);
    }

    private static GMExtWire.StructStream emptyScoreResultStream()
    {
        return streamStruct()
            .kv("raw_score", 0.0)
            .kv("formatted_score", "")
            .kv("score_tag", "")
            .kv("new_best", false);
    }

    private static GMExtWire.StructStream emptyScoreReportStream()
    {
        return streamStruct()
            .kv("leaderboard_id", "")
            .kv("player_id", "")
            .kv(
                "results",
                streamStruct()
                    .kv("daily", emptyScoreResultStream())
                    .kv("weekly", emptyScoreResultStream())
                    .kv("all_time", emptyScoreResultStream())
            );
    }

    private static GMExtWire.StructStream emptyLeaderboardStream()
    {
        return streamStruct()
            .kv("leaderboard_id", "")
            .kv("display_name", "")
            .kv(
                "score_order",
                PlayServicesLeaderboardScoreOrder.SmallerIsBetter.value()
            )
            .kv("variants", streamArray());
    }

    private static GMExtWire.StructStream emptySnapshotMetadataStream()
    {
        return streamStruct()
            .kv("unique_name", "")
            .kv("description", "")
            .kv("device_name", "")
            .kv("last_modified_timestamp", 0.0)
            .kv("played_time", 0.0)
            .kv("progress_value", 0.0)
            .kv("has_change_pending", false)
            .kv("cover_image_uri", "");
    }

    private static GMExtWire.StructStream emptySavedGameOpenResultStream()
    {
        return streamStruct()
            .kv("is_conflict", false)
            .kv(
                "snapshot_metadata",
                emptySnapshotMetadataStream()
            )
            .kv("data", "")
            .kv("conflict_id", "")
            .kv(
                "snapshot_metadata_local",
                emptySnapshotMetadataStream()
            )
            .kv("data_local", "")
            .kv(
                "snapshot_metadata_remote",
                emptySnapshotMetadataStream()
            )
            .kv("data_remote", "");
    }
}
