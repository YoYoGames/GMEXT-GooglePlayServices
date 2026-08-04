package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;

import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;
import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.GMExtWire.GMFunction;
import ${YYAndroidPackageName}.GMExtWire.GMValue;
import ${YYAndroidPackageName}.GMExtUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.AuthenticationResult;
import com.google.android.gms.games.GamesClientStatusCodes;
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

    private boolean isAuthenticated()
    {
        return authenticationKnown && authenticated;
    }

    private static String error(Throwable throwable)
    {
        if (throwable == null)
            return "Unknown Google Play Services error.";

        String message = throwable.getMessage();
        return message != null ? message : throwable.toString();
    }

    // GamesClientStatusCodes.NETWORK_ERROR_NO_DATA (26504) is genuinely ambiguous:
    // GMS reports it both when the device is online and the player simply has no
    // data yet (a documented GMS quirk - a forced reload with an empty result gets
    // reported as this error instead of a clean empty success), and when the
    // device is offline with nothing cached, where it truly doesn't know whether
    // data exists. Only the caller who checks connectivity at the same moment can
    // tell those apart - see isOnline().
    private static boolean isNoLocalDataError(Throwable throwable)
    {
        return throwable instanceof ApiException
            && ((ApiException)throwable).getStatusCode() == GamesClientStatusCodes.NETWORK_ERROR_NO_DATA;
    }

    // NET_CAPABILITY_VALIDATED (not just NET_CAPABILITY_INTERNET) confirms the
    // network has actually been validated to reach the internet, not just that a
    // transport (Wi-Fi/cellular) is up - e.g. excludes a captive portal with no
    // real connectivity, which matters here since isNoLocalDataError() needs to
    // know whether GMS could really have reached the server.
    private static boolean isOnline()
    {
        Activity activity = activity();
        if (activity == null)
            return false;

        ConnectivityManager connectivityManager =
            (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null)
            return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null)
                return false;

            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        }

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
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

    public PlayServicesError play_services_sign_in(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            return PlayServicesError.ActivityNull;
        }

        main(() -> PlayGames.getGamesSignInClient(activity).signIn()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(new PlayServicesResult(false, error(task.getException())), false);
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean isAuth = result != null && result.isAuthenticated();
                cacheAuthentication(isAuth);

                callback.call(
                    new PlayServicesResult(true, isAuth ? "" : "Sign-in completed but the user is not authenticated."),
                    isAuth
                );
            }));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_is_authenticated(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            return PlayServicesError.ActivityNull;
        }

        PlayGames.getGamesSignInClient(activity).isAuthenticated()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(new PlayServicesResult(false, error(task.getException())), false);
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean isAuth = result != null && result.isAuthenticated();
                cacheAuthentication(isAuth);
                callback.call(new PlayServicesResult(true, ""), isAuth);
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_request_server_side_access(
        String serverClientId,
        boolean forceRefreshToken,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getGamesSignInClient(activity)
            .requestServerSideAccess(serverClientId, forceRefreshToken)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(new PlayServicesResult(true, ""), Optional.ofNullable(task.getResult()));
                else
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
            });

        return PlayServicesError.Ok;
    }

    // -------------------------------------------------------------------------
    // Player
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_player_current(final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getPlayersClient(activity).getCurrentPlayer()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful() && task.getResult() != null)
                {
                    callback.call(new PlayServicesResult(true, ""), playerToInfo(task.getResult()));
                }
                else
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                }
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_player_current_id(final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getPlayersClient(activity).getCurrentPlayerId()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(new PlayServicesResult(true, ""), Optional.ofNullable(task.getResult()));
                else
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_player_stats_load(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getPlayerStatsClient(activity)
            .loadPlayerStats(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    return;
                }

                AnnotatedData<PlayerStats> annotatedData = task.getResult();
                PlayerStats stats = annotatedData != null ? annotatedData.get() : null;

                if (stats == null)
                {
                    callback.call(new PlayServicesResult(false, "No player statistics were returned."), Optional.empty());
                    return;
                }

                callback.call(new PlayServicesResult(true, ""), Optional.of(new PlayServicesPlayerStatsInfo(
                    stats.getAverageSessionLength(),
                    stats.getDaysSinceLastPlayed(),
                    stats.getNumberOfPurchases(),
                    stats.getNumberOfSessions(),
                    stats.getSessionPercentile(),
                    stats.getSpendPercentile(),
                    stats.getChurnProbability(),
                    stats.getHighSpenderProbability(),
                    stats.getSpendProbability(),
                    stats.getTotalSpendNext28Days()
                )));
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_player_load(
        String player_id,
        boolean force_reload,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getPlayersClient(activity)
            .loadPlayer(player_id, force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    return;
                }

                AnnotatedData<Player> annotatedData = task.getResult();
                Player player = annotatedData != null ? annotatedData.get() : null;

                if (player == null)
                {
                    callback.call(new PlayServicesResult(false, "No player data was returned."), Optional.empty());
                    return;
                }

                callback.call(new PlayServicesResult(true, ""), playerToInfo(player));
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_friends_load(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

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

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_friends_load_more(
        double page_size,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        if (!friendsLoaded)
            return PlayServicesError.InvalidArgument;

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

        return PlayServicesError.Ok;
    }

    // Shared (status, players, has_more, needs_consent) callback shape for every
    // friends-list function - status carries success/error only, the array is a
    // real typed GMExtWire.TypedArrayStream rather than bundled into a per-function
    // Result class, per the split-status callback pattern (see gmext-callback-design).
    // needs_consent is true only when the friends-permission dialog was itself
    // denied/cancelled (see handleFriendsConsentResult) - false for every other
    // failure so callers can tell "show a re-request UI" from a real error.
    private static void failFriendsList(GMFunction callback, String error)
    {
        failFriendsList(callback, error, false);
    }

    private static void failFriendsList(GMFunction callback, String error, boolean needsConsent)
    {
        callback.call(
            new PlayServicesResult(false, error),
            new GMExtWire.TypedArrayStream<>(PlayServicesPlayerInfo.class),
            false,
            needsConsent
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
                    players.add(playerToInfo(player).orElseThrow());

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
        callback.call(new PlayServicesResult(true, ""), players, hasMore, false);
    }

    // -------------------------------------------------------------------------
    // Player Profile UI
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_player_profile_show(String player_id)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getPlayersClient(activity)
            .getCompareProfileIntent(player_id)
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_SHOW_PROFILE))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show player profile UI.", exception));

        return PlayServicesError.Ok;
    }

    // -------------------------------------------------------------------------
    // Player Search UI
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_player_search_show(final GMFunction callback)
    {
        if (!isAuthenticated())
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

    public PlayServicesError play_services_friends_load_with_consent(
        boolean force_reload,
        double max_results,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        int clampedResults = (int) Math.max(MIN_PAGE_SIZE, Math.min(MAX_FRIENDS_PAGE_SIZE, max_results));
        if (clampedResults != (int)max_results)
            Log.w(TAG, "play_services_friends_load_with_consent: max_results " + (int)max_results + " clamped to [" + MIN_PAGE_SIZE + ", " + MAX_FRIENDS_PAGE_SIZE + "]");

        loadFriendsWithConsentHandling(activity, clampedResults, force_reload, callback);

        return PlayServicesError.Ok;
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

    private static Optional<PlayServicesPlayerInfo> playerToInfo(Player player)
    {
        if (player == null)
            return Optional.empty();

        return Optional.of(new PlayServicesPlayerInfo(
            Optional.ofNullable(player.getPlayerId()),
            Optional.ofNullable(player.getDisplayName()),
            Optional.ofNullable(player.getTitle()),
            Optional.ofNullable(player.getIconImageUri()).map(Uri::toString),
            Optional.ofNullable(player.getHiResImageUri()).map(Uri::toString)
        ));
    }

    // -------------------------------------------------------------------------
    // Achievements
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_achievements_show()
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity).getAchievementsIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_ACHIEVEMENT_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show achievements UI.", exception));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_achievements_increment(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity)
            .incrementImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task -> completeAchievement(task, callback));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_achievements_reveal(
        String achievementId,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity)
            .revealImmediate(achievementId)
            .addOnCompleteListener(task -> completeAchievement(task, callback));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_achievements_set_steps(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity)
            .setStepsImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task -> completeAchievement(task, callback));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_achievements_unlock(
        String achievementId,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity)
            .unlockImmediate(achievementId)
            .addOnCompleteListener(task -> completeAchievement(task, callback));

        return PlayServicesError.Ok;
    }

    private void completeAchievement(Task<?> task, GMFunction callback)
    {
        if (task.isSuccessful())
            callback.call(new PlayServicesResult(true, ""));
        else
            callback.call(new PlayServicesResult(false, error(task.getException())));
    }

    public PlayServicesError play_services_achievements_get_status(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getAchievementsClient(activity)
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(
                        new PlayServicesResult(false, error(task.getException())),
                        new GMExtWire.TypedArrayStream<>(PlayServicesAchievement.class)
                    );
                    return;
                }

                AchievementBuffer buffer = task.getResult() != null ? task.getResult().get() : null;
                GMExtWire.TypedArrayStream<PlayServicesAchievement> achievements =
                    new GMExtWire.TypedArrayStream<>(PlayServicesAchievement.class);

                if (buffer != null)
                {
                    try
                    {
                        for (Achievement achievement : buffer)
                            achievements.add(achievementToRecord(achievement));
                    }
                    catch (Exception exception)
                    {
                        callback.call(
                            new PlayServicesResult(false, error(exception)),
                            new GMExtWire.TypedArrayStream<>(PlayServicesAchievement.class)
                        );
                        return;
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(new PlayServicesResult(true, ""), achievements);
            });

        return PlayServicesError.Ok;
    }

    // -------------------------------------------------------------------------
    // Leaderboards
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_leaderboard_show_all()
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getLeaderboardsClient(activity).getAllLeaderboardsIntent()
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboards UI.", exception));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_leaderboard_show(String leaderboardId)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getLeaderboardsClient(activity)
            .getLeaderboardIntent(leaderboardId)
            .addOnSuccessListener(intent ->
                activity.startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboard UI.", exception));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_leaderboard_submit_score(
        String leaderboardId,
        double score,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getLeaderboardsClient(activity)
            .submitScoreImmediate(leaderboardId, (long)score)
            .addOnCompleteListener(task -> completeScoreSubmission(task, callback));

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_leaderboard_submit_score_with_tag(
        String leaderboardId,
        double score,
        String scoreTag,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getLeaderboardsClient(activity)
            .submitScoreImmediate(leaderboardId, (long)score, scoreTag)
            .addOnCompleteListener(task -> completeScoreSubmission(task, callback));

        return PlayServicesError.Ok;
    }

    private void completeScoreSubmission(Task<ScoreSubmissionData> task, GMFunction callback)
    {
        if (!task.isSuccessful())
        {
            callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
            return;
        }

        ScoreSubmissionData report = task.getResult();
        ScoreSubmissionData.Result daily = report.getScoreResult(LeaderboardVariant.TIME_SPAN_DAILY);
        ScoreSubmissionData.Result weekly = report.getScoreResult(LeaderboardVariant.TIME_SPAN_WEEKLY);
        ScoreSubmissionData.Result all_time = report.getScoreResult(LeaderboardVariant.TIME_SPAN_ALL_TIME);

        callback.call(new PlayServicesResult(true, ""), Optional.of(new PlayServicesScoreReportInfo(
            new PlayServicesScoreSubmission(daily.rawScore, Optional.ofNullable(daily.formattedScore), Optional.ofNullable(daily.scoreTag), daily.newBest),
            new PlayServicesScoreSubmission(weekly.rawScore, Optional.ofNullable(weekly.formattedScore), Optional.ofNullable(weekly.scoreTag), weekly.newBest),
            new PlayServicesScoreSubmission(all_time.rawScore, Optional.ofNullable(all_time.formattedScore), Optional.ofNullable(all_time.scoreTag), all_time.newBest)
        )));
    }

    public PlayServicesError play_services_leaderboard_load_player_centered_scores(
        String leaderboard_id,
        PlayServicesLeaderboardTimeSpan span,
        PlayServicesLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

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

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_leaderboard_load_top_scores(
        String leaderboard_id,
        PlayServicesLeaderboardTimeSpan span,
        PlayServicesLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

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

        return PlayServicesError.Ok;
    }

    private void completeScores(
        Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task,
        GMFunction callback)
    {
        if (!task.isSuccessful())
        {
            callback.call(
                new PlayServicesResult(false, error(task.getException())),
                Optional.empty(),
                new GMExtWire.TypedArrayStream<>(PlayServicesLeaderboardScore.class)
            );
            return;
        }

        AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
        LeaderboardsClient.LeaderboardScores scores = annotatedData != null ? annotatedData.get() : null;

        if (scores == null)
        {
            callback.call(
                new PlayServicesResult(false, "No leaderboard score data."),
                Optional.empty(),
                new GMExtWire.TypedArrayStream<>(PlayServicesLeaderboardScore.class)
            );
            return;
        }

        LeaderboardScoreBuffer buffer = scores.getScores();
        GMExtWire.TypedArrayStream<PlayServicesLeaderboardScore> scoreList =
            new GMExtWire.TypedArrayStream<>(PlayServicesLeaderboardScore.class);

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

        Leaderboard leaderboard = scores.getLeaderboard();
        callback.call(
            new PlayServicesResult(true, ""),
            leaderboard != null ? Optional.of(leaderboardToRecord(leaderboard)) : Optional.empty(),
            scoreList
        );
    }

    // -------------------------------------------------------------------------
    // URI to local path
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_uri_to_path(
        String uriString,
        final GMFunction callback)
    {
        final Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

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
                callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
            }
        });

        return PlayServicesError.Ok;
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
                            callback.call(new PlayServicesResult(false, "The URI image could not be loaded."), Optional.empty());
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

                        callback.call(new PlayServicesResult(true, ""), Optional.of(output.getAbsolutePath()));
                    }
                    catch (Exception exception)
                    {
                        callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
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
            callback.call(new PlayServicesResult(false, "Image loading timed out."), Optional.empty());
            LIVE.remove(this);
        }
    }

    // -------------------------------------------------------------------------
    // Saved Games
    // -------------------------------------------------------------------------

    public PlayServicesError play_services_saved_games_show_saved_games_ui(
        String title,
        boolean buttonAdd,
        boolean buttonDelete,
        double max_results,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

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
                    pending.call(
                        new PlayServicesResult(false, error(exception)),
                        PlayServicesSavedGamesUIResult.Error,
                        Optional.empty()
                    );
                }
            });

        return PlayServicesError.Ok;
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
            callback.call(new PlayServicesResult(true, ""), PlayServicesSavedGamesUIResult.Cancelled, Optional.empty());
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA))
        {
            SnapshotMetadata metadata =
                data.getParcelableExtra(
                    SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

            callback.call(
                new PlayServicesResult(true, ""),
                PlayServicesSavedGamesUIResult.Selected,
                snapshotMetadataToRecord(metadata)
            );
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW))
        {
            callback.call(new PlayServicesResult(true, ""), PlayServicesSavedGamesUIResult.CreatedNew, Optional.empty());
            return;
        }

        callback.call(new PlayServicesResult(true, ""), PlayServicesSavedGamesUIResult.Cancelled, Optional.empty());
    }

    public PlayServicesError play_services_saved_games_commit_and_close(
        PlayServicesSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!isAuthenticated())
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
                            callback.call(new PlayServicesResult(true, ""), snapshotMetadataToRecord(task.getResult()));
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

    public PlayServicesError play_services_saved_games_load(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        PlayGames.getSnapshotsClient(activity)
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    if (isNoLocalDataError(task.getException()) && isOnline())
                    {
                        callback.call(
                            new PlayServicesResult(true, ""),
                            new GMExtWire.TypedArrayStream<>(PlayServicesSnapshotMetadata.class)
                        );
                        return;
                    }

                    callback.call(
                        new PlayServicesResult(false, error(task.getException())),
                        new GMExtWire.TypedArrayStream<>(PlayServicesSnapshotMetadata.class)
                    );
                    return;
                }

                SnapshotMetadataBuffer buffer = task.getResult() != null ? task.getResult().get() : null;
                GMExtWire.TypedArrayStream<PlayServicesSnapshotMetadata> snapshotList =
                    new GMExtWire.TypedArrayStream<>(PlayServicesSnapshotMetadata.class);

                if (buffer != null)
                {
                    try
                    {
                        for (SnapshotMetadata metadata : buffer)
                            snapshotList.add(snapshotMetadataToRecord(metadata).orElseThrow());
                    }
                    catch (Exception exception)
                    {
                        callback.call(
                            new PlayServicesResult(false, error(exception)),
                            new GMExtWire.TypedArrayStream<>(PlayServicesSnapshotMetadata.class)
                        );
                        return;
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(new PlayServicesResult(true, ""), snapshotList);
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_saved_games_open(
        String name,
        boolean create_if_not_found,
        PlayServicesSavedGamesConflictPolicy conflict_policy,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        // Run the completion on the background executor: the success path calls
        // Snapshot.readFully() (synchronous disk I/O) which must not run on the UI
        // thread. Callback delivery is thread-agnostic (marshalled via DispatchQueue).
        PlayGames.getSnapshotsClient(activity)
            .open(name, create_if_not_found, (int)conflict_policy.value())
            .addOnCompleteListener(background, task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())), Optional.empty());
                    return;
                }

                try
                {
                    PlayServicesSnapshotOpenInfo response = buildSnapshotOpenResult(task.getResult());
                    callback.call(new PlayServicesResult(true, ""), Optional.of(response));
                }
                catch (Exception exception)
                {
                    callback.call(new PlayServicesResult(false, error(exception)), Optional.empty());
                }
            });

        return PlayServicesError.Ok;
    }

    // Shared by play_services_saved_games_open and play_services_saved_games_resolve_conflict -
    // both wrap Task<DataOrConflict<Snapshot>> and need identical is_conflict/metadata handling.
    // Only PlayServicesSavedGamesConflictPolicy.Manual can ever produce a conflict result (every
    // other policy value makes GMS auto-resolve), so there is no separate "reject conflicts" gate
    // here - a caller passing Manual is by definition prepared to handle one.
    private PlayServicesSnapshotOpenInfo buildSnapshotOpenResult(
        DataOrConflict<Snapshot> result) throws Exception
    {
        if (!result.isConflict())
        {
            Snapshot snapshot = result.getData();

            if (snapshot == null)
                throw new IllegalStateException("No snapshot was returned.");

            trackSnapshot(snapshot.getMetadata().getUniqueName(), snapshot);

            return new PlayServicesSnapshotOpenInfo(
                false,
                snapshotMetadataToRecord(snapshot.getMetadata()),
                Optional.of(readSnapshot(snapshot)),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            );
        }

        conflictLocal = result.getConflict().getConflictingSnapshot();
        conflictRemote = result.getConflict().getSnapshot();

        return new PlayServicesSnapshotOpenInfo(
            true,
            Optional.empty(),
            Optional.empty(),
            Optional.ofNullable(result.getConflict().getConflictId()),
            snapshotMetadataToRecord(conflictLocal.getMetadata()),
            Optional.of(readSnapshot(conflictLocal)),
            snapshotMetadataToRecord(conflictRemote.getMetadata()),
            Optional.of(readSnapshot(conflictRemote))
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

    public PlayServicesError play_services_saved_games_delete(
        String name,
        final GMFunction callback)
    {
        if (!isAuthenticated())
            return PlayServicesError.NotAuthenticated;

        Activity activity = activity();
        if (activity == null)
            return PlayServicesError.ActivityNull;

        Snapshot snapshot = snapshots.get(name);
        if (snapshot == null)
            return PlayServicesError.InvalidArgument;

        PlayGames.getSnapshotsClient(activity)
            .delete(snapshot.getMetadata())
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    snapshots.remove(name);
                    callback.call(new PlayServicesResult(true, ""));
                }
                else
                {
                    callback.call(new PlayServicesResult(false, error(task.getException())));
                }
            });

        return PlayServicesError.Ok;
    }

    public PlayServicesError play_services_saved_games_resolve_conflict(
        String conflictId,
        boolean useLocal,
        final GMFunction callback)
    {
        if (!isAuthenticated())
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
                    PlayServicesSnapshotOpenInfo data = buildSnapshotOpenResult(result);

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

            callback.call(new PlayServicesResult(true, ""), playerToInfo(selected));
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
            failFriendsList(callback, "User denied friends access permission.", true);
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
            Optional.ofNullable(achievement.getAchievementId()),
            Optional.ofNullable(achievement.getName()),
            Optional.ofNullable(achievement.getDescription()),
            PlayServicesAchievementState.from(achievement.getState()),
            PlayServicesAchievementType.from(type),
            currentSteps,
            totalSteps,
            achievement.getLastUpdatedTimestamp(),
            achievement.getXpValue(),
            Optional.ofNullable(achievement.getRevealedImageUri()).map(Uri::toString),
            Optional.ofNullable(achievement.getUnlockedImageUri()).map(Uri::toString)
        );
    }

    private static PlayServicesLeaderboard leaderboardToRecord(Leaderboard leaderboard)
    {
        java.util.List<PlayServicesLeaderboardVariant> variants = new java.util.ArrayList<>();

        for (LeaderboardVariant variant : leaderboard.getVariants())
        {
            variants.add(new PlayServicesLeaderboardVariant(
                PlayServicesLeaderboardCollection.from(variant.getCollection()),
                PlayServicesLeaderboardTimeSpan.from(variant.getTimeSpan()),
                variant.hasPlayerInfo()
            ));
        }

        return new PlayServicesLeaderboard(
            Optional.ofNullable(leaderboard.getLeaderboardId()),
            Optional.ofNullable(leaderboard.getDisplayName()),
            PlayServicesLeaderboardScoreOrder.from(leaderboard.getScoreOrder()),
            variants
        );
    }

    private static PlayServicesLeaderboardScore leaderboardScoreToRecord(LeaderboardScore score)
    {
        return new PlayServicesLeaderboardScore(
            Optional.ofNullable(score.getDisplayRank()),
            Optional.ofNullable(score.getDisplayScore()),
            score.getRawScore(),
            Optional.ofNullable(score.getScoreTag()),
            score.getTimestampMillis(),
            playerToInfo(score.getScoreHolder())
        );
    }

    private static Optional<PlayServicesSnapshotMetadata> snapshotMetadataToRecord(SnapshotMetadata metadata)
    {
        if (metadata == null)
            return Optional.empty();

        return Optional.of(new PlayServicesSnapshotMetadata(
            Optional.ofNullable(metadata.getUniqueName()),
            Optional.ofNullable(metadata.getDescription()),
            Optional.ofNullable(metadata.getDeviceName()),
            metadata.getLastModifiedTimestamp(),
            metadata.getPlayedTime(),
            metadata.getProgressValue(),
            metadata.hasChangePending(),
            Optional.ofNullable(metadata.getCoverImageUri()).map(Uri::toString)
        ));
    }

}
