package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;

import ${YYAndroidPackageName}.enums.*;
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


import org.json.JSONArray;
import org.json.JSONObject;

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

/**
 * Extension Generator conversion of YYGooglePlayServices.
 *
 * Social Async events have been replaced by GMFunction callbacks.
 * Nested structures are returned as JSON strings.
 */
public class GMGooglePlayServices extends GMGooglePlayServicesInternal
{
    private static final String TAG = "yoyo";
    private static final int RC_ACHIEVEMENT_UI = 9003;
    private static final int RC_LEADERBOARD_UI = 9004;
    private static final int RC_SAVED_GAMES = 9009;

    private static final int SAVED_UI_ERROR = -1;
    private static final int SAVED_UI_CANCELLED = 0;
    private static final int SAVED_UI_SELECTED = 1;
    private static final int SAVED_UI_CREATED_NEW = 2;
    private static final int SAVED_UI_DELETED = 3;

    private final ExecutorService background = Executors.newCachedThreadPool();
    private final HashMap<String, Snapshot> snapshots = new HashMap<>();

    private Snapshot conflictLocal;
    private Snapshot conflictRemote;
    private GMFunction savedGamesUiCallback;

    private volatile boolean authenticationKnown = false;
    private volatile boolean authenticated = false;

    public GMGooglePlayServices()
    {
        PlayGamesSdk.initialize(activity());
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
            + "gpgs_is_authenticated or gpgs_sign_in first.";
    }

    private boolean requireAuthentication(GMFunction callback, Object... failureArguments)
    {
        if (authenticationKnown && authenticated)
            return true;

        callback.call(failureArguments);
        return false;
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

    public boolean gpgs_is_available()
    {
        Activity activity = activity();
        return activity != null
            && GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(activity) == ConnectionResult.SUCCESS;
    }

    public void gpgs_sign_in(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            callback.call(false, false, "Activity is null.");
            return;
        }

        main(() -> PlayGames.getGamesSignInClient(activity).signIn()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(false, false, error(task.getException()));
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean value = result != null && result.isAuthenticated();
                cacheAuthentication(value);

                callback.call(
                    value,
                    value,
                    value ? "" : "Sign-in completed but the user is not authenticated."
                );
            }));
    }

    public void gpgs_is_authenticated(final GMFunction callback)
    {
        Activity activity = activity();
        if (activity == null)
        {
            cacheAuthentication(false);
            callback.call(false, false, "Activity is null.");
            return;
        }

        PlayGames.getGamesSignInClient(activity).isAuthenticated()
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    cacheAuthentication(false);
                    callback.call(false, false, error(task.getException()));
                    return;
                }

                AuthenticationResult result = task.getResult();
                boolean value = result != null && result.isAuthenticated();
                cacheAuthentication(value);
                callback.call(true, value, "");
            });
    }

    public void gpgs_request_server_side_access(
        String serverClientId,
        boolean forceRefreshToken,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        PlayGames.getGamesSignInClient(activity())
            .requestServerSideAccess(serverClientId, forceRefreshToken)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(true, task.getResult(), "");
                else
                    callback.call(false, "", error(task.getException()));
            });
    }

    // -------------------------------------------------------------------------
    // Player
    // -------------------------------------------------------------------------

    public void gpgs_player_current(final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "{}", authenticationError()))
            return;

        PlayGames.getPlayersClient(activity()).getCurrentPlayer()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful() && task.getResult() != null)
                    callback.call(true, playerToJson(task.getResult()).toString(), "");
                else
                    callback.call(false, "{}", error(task.getException()));
            });
    }

    public void gpgs_player_current_id(final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        PlayGames.getPlayersClient(activity()).getCurrentPlayerId()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                    callback.call(true, task.getResult(), "");
                else
                    callback.call(false, "", error(task.getException()));
            });
    }

    /**
     * Loads analytics/statistics for the currently authenticated player.
     *
     * Callback:
     *     callback(success, player_stats_json, error)
     */
    public void gpgs_player_stats_load(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "{}", authenticationError()))
            return;

        PlayGames.getPlayerStatsClient(activity())
            .loadPlayerStats(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(
                        false,
                        "{}",
                        error(task.getException())
                    );
                    return;
                }

                AnnotatedData<PlayerStats> annotatedData = task.getResult();
                PlayerStats stats =
                    annotatedData != null ? annotatedData.get() : null;

                if (stats == null)
                {
                    callback.call(
                        false,
                        "{}",
                        "No player statistics were returned."
                    );
                    return;
                }

                callback.call(
                    true,
                    playerStatsToJson(stats).toString(),
                    ""
                );
            });
    }

    // -------------------------------------------------------------------------
    // Achievements
    // -------------------------------------------------------------------------

    public void gpgs_achievements_show()
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Achievements_Show blocked: " + authenticationError());
            return;
        }

        PlayGames.getAchievementsClient(activity()).getAchievementsIntent()
            .addOnSuccessListener(intent ->
                activity().startActivityForResult(intent, RC_ACHIEVEMENT_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show achievements UI.", exception));
    }

    public void gpgs_achievements_increment(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity())
            .incrementImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void gpgs_achievements_reveal(
        String achievementId,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity())
            .revealImmediate(achievementId)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void gpgs_achievements_set_steps(
        String achievementId,
        double steps,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity())
            .setStepsImmediate(achievementId, (int)steps)
            .addOnCompleteListener(task ->
                completeAchievement(task, achievementId, callback));
    }

    public void gpgs_achievements_unlock(
        String achievementId,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, achievementId, authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity())
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
            callback.call(true, achievementId, "");
        else
            callback.call(false, achievementId, error(task.getException()));
    }

    public void gpgs_achievements_get_status(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "[]", authenticationError()))
            return;

        PlayGames.getAchievementsClient(activity()).load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(false, "[]", error(task.getException()));
                    return;
                }

                AchievementBuffer buffer = task.getResult().get();
                JSONArray data = new JSONArray();

                if (buffer != null)
                {
                    for (Achievement achievement : buffer)
                        data.put(achievementToJson(achievement));
                    buffer.release();
                }

                callback.call(true, data.toString(), "");
            });
    }

    // -------------------------------------------------------------------------
    // Leaderboards
    // -------------------------------------------------------------------------

    public void gpgs_leaderboard_show_all()
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Leaderboard_ShowAll blocked: " + authenticationError());
            return;
        }

        PlayGames.getLeaderboardsClient(activity()).getAllLeaderboardsIntent()
            .addOnSuccessListener(intent ->
                activity().startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboards UI.", exception));
    }

    public void gpgs_leaderboard_show(String leaderboardId)
    {
        if (!authenticationKnown || !authenticated)
        {
            Log.w(TAG, "Leaderboard_Show blocked: " + authenticationError());
            return;
        }

        PlayGames.getLeaderboardsClient(activity())
            .getLeaderboardIntent(leaderboardId)
            .addOnSuccessListener(intent ->
                activity().startActivityForResult(intent, RC_LEADERBOARD_UI))
            .addOnFailureListener(exception ->
                Log.e(TAG, "Could not show leaderboard UI.", exception));
    }

    public void gpgs_leaderboard_submit_score(
        String leaderboardId,
        double score,
        String scoreTag,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, leaderboardId, score, scoreTag, "{}",
            authenticationError()))
            return;

        PlayGames.getLeaderboardsClient(activity())
            .submitScoreImmediate(leaderboardId, (long)score, scoreTag)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    callback.call(
                        true,
                        leaderboardId,
                        score,
                        scoreTag,
                        scoreReportToJson(task.getResult()).toString(),
                        ""
                    );
                }
                else
                {
                    callback.call(
                        false,
                        leaderboardId,
                        score,
                        scoreTag,
                        "{}",
                        error(task.getException())
                    );
                }
            });
    }

    public void gpgs_leaderboard_load_player_centered_scores(String leaderboard_id, GPGSLeaderboardTimeSpan span, GPGSLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "{}", "[]", authenticationError()))
            return;

        PlayGames.getLeaderboardsClient(activity())
            .loadPlayerCenteredScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                (int)max_results,
                force_reload)
            .addOnCompleteListener(task ->
                completeScores(task, callback));
    }

    public void gpgs_leaderboard_load_top_scores(String leaderboard_id, GPGSLeaderboardTimeSpan span, GPGSLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "{}", "[]", authenticationError()))
            return;

        PlayGames.getLeaderboardsClient(activity())
            .loadTopScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                (int)max_results,
                force_reload)
            .addOnCompleteListener(task ->
                completeScores(task, callback));
    }

    private void completeScores(
        Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task,
        GMFunction callback)
    {
        if (!task.isSuccessful())
        {
            callback.call(false, "{}", "[]", error(task.getException()));
            return;
        }

        LeaderboardsClient.LeaderboardScores scores = task.getResult().get();
        if (scores == null)
        {
            callback.call(false, "{}", "[]", "No leaderboard score data.");
            return;
        }

        LeaderboardScoreBuffer buffer = scores.getScores();
        JSONArray scoreArray = leaderboardScoresToJson(buffer);
        buffer.release();

        callback.call(
            true,
            leaderboardToJson(scores.getLeaderboard()).toString(),
            scoreArray.toString(),
            ""
        );
    }

    // -------------------------------------------------------------------------
    // URI to local path
    // -------------------------------------------------------------------------

    public void gpgs_uri_to_path(
        String uriString,
        final GMFunction callback)
    {
        main(() ->
        {
            try
            {
                Activity activity = activity();
                if (activity == null)
                    throw new IllegalStateException("Activity is null.");

                UriImageListener.register(
                    activity,
                    Uri.parse(uriString),
                    callback,
                    background
                );
            }
            catch (Exception exception)
            {
                callback.call(false, "", error(exception));
            }
        });
    }

    private static final class UriImageListener
        implements ImageManager.OnImageLoadedListener
    {
        private static final Map<UriImageListener, UriImageListener> LIVE =
            new ConcurrentHashMap<>();

        private final WeakReference<Activity> activity;
        private final GMFunction callback;
        private final ExecutorService background;

        static void register(
            Activity activity,
            Uri uri,
            GMFunction callback,
            ExecutorService background)
        {
            UriImageListener listener =
                new UriImageListener(activity, callback, background);

            LIVE.put(listener, listener);
            ImageManager.create(activity).loadImage(listener, uri);
        }

        private UriImageListener(
            Activity activity,
            GMFunction callback,
            ExecutorService background)
        {
            this.activity = new WeakReference<>(activity);
            this.callback = callback;
            this.background = background;
        }

        @Override
        public void onImageLoaded(
            @NonNull Uri uri,
            Drawable drawable,
            boolean isRequestedDrawable)
        {
            background.execute(() ->
            {
                try
                {
                    if (!isRequestedDrawable
                        || !(drawable instanceof BitmapDrawable))
                    {
                        callback.call(false, "", "The URI image could not be loaded.");
                        return;
                    }

                    Activity activity = Objects.requireNonNull(this.activity.get());
                    Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                    File output = File.createTempFile(
                        "gpgs_",
                        ".png",
                        activity.getCacheDir()
                    );

                    try (FileOutputStream stream = new FileOutputStream(output))
                    {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    }

                    callback.call(true, output.getAbsolutePath(), "");
                }
                catch (Exception exception)
                {
                    callback.call(false, "", error(exception));
                }
                finally
                {
                    LIVE.remove(this);
                }
            });
        }
    }

    // -------------------------------------------------------------------------
    // Saved Games
    // -------------------------------------------------------------------------

    public void gpgs_saved_games_show_saved_games_ui(
        String title,
        boolean buttonAdd,
        boolean buttonDelete,
        double max_results,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, SAVED_UI_ERROR, "{}", authenticationError()))
            return;

        savedGamesUiCallback = callback;

        PlayGames.getSnapshotsClient(activity())
            .getSelectSnapshotIntent(
                title,
                buttonAdd,
                buttonDelete,
                (int)max_results)
            .addOnSuccessListener(intent ->
                activity().startActivityForResult(intent, RC_SAVED_GAMES))
            .addOnFailureListener(exception ->
            {
                GMFunction pending = savedGamesUiCallback;
                savedGamesUiCallback = null;
                if (pending != null)
                    pending.call(SAVED_UI_ERROR, "{}", error(exception));
            });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode != RC_SAVED_GAMES)
            return;

        GMFunction callback = savedGamesUiCallback;
        savedGamesUiCallback = null;

        if (callback == null)
            return;

        if (data == null || resultCode != Activity.RESULT_OK)
        {
            callback.call(SAVED_UI_CANCELLED, "{}", "");
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA))
        {
            SnapshotMetadata metadata =
                data.getParcelableExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

            callback.call(
                SAVED_UI_SELECTED,
                metadata != null ? snapshotMetadataToJson(metadata).toString() : "{}",
                ""
            );
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW))
        {
            callback.call(SAVED_UI_CREATED_NEW, "{}", "");
            return;
        }

        callback.call(SAVED_UI_CANCELLED, "{}", "");
    }

    public void __gpgs_saved_games_commit_and_close(
        String optionsJson,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        try
        {
            JSONObject options = new JSONObject(optionsJson);
            String name = options.getString("name");
            Snapshot snapshot = snapshots.get(name);

            if (snapshot == null)
            {
                callback.call(
                    false,
                    name,
                    "Snapshot is not opened or has already been closed."
                );
                return;
            }

            commitSnapshot(
                snapshot,
                options,
                true,
                callback
            );
        }
        catch (Exception exception)
        {
            callback.call(false, "", error(exception));
        }
    }

    public void __gpgs_saved_games_commit_new(
        String optionsJson,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        try
        {
            JSONObject options = new JSONObject(optionsJson);
            String name = options.getString("name");

            PlayGames.getSnapshotsClient(activity())
                .open(
                    name,
                    true,
                    SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED)
                .addOnCompleteListener(task ->
                {
                    if (!task.isSuccessful())
                    {
                        callback.call(false, name, error(task.getException()));
                        return;
                    }

                    Snapshot snapshot = task.getResult().getData();
                    if (snapshot == null)
                    {
                        callback.call(false, name, "No snapshot was returned.");
                        return;
                    }

                    snapshots.put(snapshot.getMetadata().getUniqueName(), snapshot);
                    commitSnapshot(snapshot, options, true, callback);
                });
        }
        catch (Exception exception)
        {
            callback.call(false, "", error(exception));
        }
    }

    private void commitSnapshot(
        Snapshot snapshot,
        JSONObject options,
        boolean close,
        GMFunction callback)
    {
        background.execute(() ->
        {
            String name = snapshot.getMetadata().getUniqueName();

            try
            {
                String data = options.optString("data", "");
                snapshot.getSnapshotContents()
                    .writeBytes(data.getBytes(StandardCharsets.UTF_8));

                SnapshotMetadataChange.Builder builder =
                    new SnapshotMetadataChange.Builder();

                if (options.has("desc"))
                    builder.setDescription(options.optString("desc", ""));

                if (options.has("playedTimeMillis"))
                    builder.setPlayedTimeMillis(
                        (long)options.optDouble("playedTimeMillis", 0.0));

                if (options.has("progressValue"))
                    builder.setProgressValue(
                        (long)options.optDouble("progressValue", 0.0));

                String coverImagePath =
                    options.optString("coverImagePath", "");

                if (!coverImagePath.isEmpty())
                {
                    Bitmap bitmap = android.graphics.BitmapFactory
                        .decodeFile(coverImagePath);

                    if (bitmap != null)
                        builder.setCoverImage(bitmap);
                }

                main(() -> PlayGames.getSnapshotsClient(activity())
                    .commitAndClose(snapshot, builder.build())
                    .addOnCompleteListener(task ->
                    {
                        snapshots.remove(name);

                        if (task.isSuccessful())
                            callback.call(true, name, "");
                        else
                            callback.call(false, name, error(task.getException()));
                    }));
            }
            catch (Exception exception)
            {
                callback.call(false, name, error(exception));
            }
        });
    }

    public void gpgs_saved_games_load(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "[]", authenticationError()))
            return;

        PlayGames.getSnapshotsClient(activity()).load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(false, "[]", error(task.getException()));
                    return;
                }

                SnapshotMetadataBuffer buffer = task.getResult().get();
                JSONArray data = new JSONArray();

                if (buffer != null)
                {
                    for (SnapshotMetadata metadata : buffer)
                        data.put(snapshotMetadataToJson(metadata));
                    buffer.release();
                }

                callback.call(true, data.toString(), "");
            });
    }

    public void gpgs_saved_games_open(
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

    public void gpgs_saved_games_open_conflict(String name, GPGSSavedGamesConflictPolicy conflict_policy, GMFunction callback)
    {
        openSnapshot(name, (int)conflict_policy.value(), true, callback);
    }

    private void openSnapshot(
        String name,
        int policy,
        boolean includeConflict,
        GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "{}", authenticationError()))
            return;

        PlayGames.getSnapshotsClient(activity()).open(name, false, policy)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(false, "{}", error(task.getException()));
                    return;
                }

                DataOrConflict<Snapshot> result = task.getResult();
                JSONObject response = new JSONObject();

                try
                {
                    if (!result.isConflict())
                    {
                        Snapshot snapshot = result.getData();
                        if (snapshot == null)
                            throw new IllegalStateException("No snapshot was returned.");

                        snapshots.put(
                            snapshot.getMetadata().getUniqueName(),
                            snapshot
                        );

                        response.put("isConflict", false);
                        response.put(
                            "snapshotMetadata",
                            snapshotMetadataToJson(snapshot.getMetadata())
                        );
                        response.put("data", readSnapshot(snapshot));
                    }
                    else
                    {
                        if (!includeConflict)
                            throw new IllegalStateException(
                                "A Saved Games conflict was returned."
                            );

                        conflictLocal =
                            result.getConflict().getConflictingSnapshot();
                        conflictRemote =
                            result.getConflict().getSnapshot();

                        response.put("isConflict", true);
                        response.put(
                            "conflictId",
                            result.getConflict().getConflictId()
                        );
                        response.put(
                            "snapshotMetadataLocal",
                            snapshotMetadataToJson(conflictLocal.getMetadata())
                        );
                        response.put("dataLocal", readSnapshot(conflictLocal));
                        response.put(
                            "snapshotMetadataRemote",
                            snapshotMetadataToJson(conflictRemote.getMetadata())
                        );
                        response.put("dataRemote", readSnapshot(conflictRemote));
                    }

                    callback.call(true, response.toString(), "");
                }
                catch (Exception exception)
                {
                    callback.call(false, "{}", error(exception));
                }
            });
    }

    public void gpgs_saved_games_delete(
        String name,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, name, authenticationError()))
            return;

        Snapshot snapshot = snapshots.get(name);
        if (snapshot == null)
        {
            callback.call(
                false,
                name,
                "Snapshot is not opened or has already been closed."
            );
            return;
        }

        PlayGames.getSnapshotsClient(activity())
            .delete(snapshot.getMetadata())
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    snapshots.remove(name);
                    callback.call(true, name, "");
                }
                else
                {
                    callback.call(false, name, error(task.getException()));
                }
            });
    }

    public void gpgs_saved_games_resolve_conflict(
        String conflictId,
        boolean useLocal,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, conflictId, authenticationError()))
            return;

        Snapshot selected = useLocal ? conflictLocal : conflictRemote;
        if (selected == null)
        {
            callback.call(false, conflictId, "There is no active conflict.");
            return;
        }

        PlayGames.getSnapshotsClient(activity())
            .resolveConflict(conflictId, selected)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(
                        false,
                        conflictId,
                        error(task.getException())
                    );
                    return;
                }

                conflictLocal = null;
                conflictRemote = null;
                callback.call(true, conflictId, "");
            });
    }

    private static String readSnapshot(Snapshot snapshot) throws Exception
    {
        SnapshotContents contents = snapshot.getSnapshotContents();
        return new String(contents.readFully(), StandardCharsets.UTF_8);
    }

    // -------------------------------------------------------------------------
    // JSON conversion
    // -------------------------------------------------------------------------

    private static JSONObject playerToJson(
        com.google.android.gms.games.Player player)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("playerId", player.getPlayerId());
            json.put("displayName", player.getDisplayName());
            json.put("title", player.getTitle());
            json.put(
                "iconImageUri",
                player.getIconImageUri() != null
                    ? player.getIconImageUri().toString()
                    : ""
            );
            json.put(
                "hiResImageUri",
                player.getHiResImageUri() != null
                    ? player.getHiResImageUri().toString()
                    : ""
            );
        }
        catch (Exception ignored) {}

        return json;
    }


    private static JSONObject playerStatsToJson(PlayerStats stats)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put(
                "averageSessionLength",
                stats.getAverageSessionLength()
            );
            json.put(
                "daysSinceLastPlayed",
                stats.getDaysSinceLastPlayed()
            );
            json.put(
                "numberOfPurchases",
                stats.getNumberOfPurchases()
            );
            json.put(
                "numberOfSessions",
                stats.getNumberOfSessions()
            );
            json.put(
                "sessionPercentile",
                stats.getSessionPercentile()
            );
            json.put(
                "spendPercentile",
                stats.getSpendPercentile()
            );

            // These fields are deprecated in newer Play Games SDK versions,
            // but are retained for compatibility with the legacy extension.
            json.put(
                "churnProbability",
                stats.getChurnProbability()
            );
            json.put(
                "highSpenderProbability",
                stats.getHighSpenderProbability()
            );
            json.put(
                "spendProbability",
                stats.getSpendProbability()
            );
            json.put(
                "totalSpendNext28Days",
                stats.getTotalSpendNext28Days()
            );
        }
        catch (Exception ignored)
        {
        }

        return json;
    }

    private static JSONObject achievementToJson(Achievement achievement)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("achievementId", achievement.getAchievementId());
            json.put("name", achievement.getName());
            json.put("description", achievement.getDescription());
            json.put("state", achievement.getState());
            json.put("type", achievement.getType());
            json.put("currentSteps", achievement.getCurrentSteps());
            json.put("totalSteps", achievement.getTotalSteps());
            json.put("lastUpdatedTimestamp", achievement.getLastUpdatedTimestamp());
            json.put("xpValue", achievement.getXpValue());
            json.put(
                "revealedImageUri",
                achievement.getRevealedImageUri() != null
                    ? achievement.getRevealedImageUri().toString()
                    : ""
            );
            json.put(
                "unlockedImageUri",
                achievement.getUnlockedImageUri() != null
                    ? achievement.getUnlockedImageUri().toString()
                    : ""
            );
        }
        catch (Exception ignored) {}

        return json;
    }

    private static JSONObject leaderboardToJson(Leaderboard leaderboard)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("leaderboardId", leaderboard.getLeaderboardId());
            json.put("displayName", leaderboard.getDisplayName());
            json.put("scoreOrder", leaderboard.getScoreOrder());

            JSONArray variants = new JSONArray();
            for (LeaderboardVariant variant : leaderboard.getVariants())
            {
                JSONObject item = new JSONObject();
                item.put("collection", variant.getCollection());
                item.put("timeSpan", variant.getTimeSpan());
                item.put("hasPlayerInfo", variant.hasPlayerInfo());
                variants.put(item);
            }

            json.put("variants", variants);
        }
        catch (Exception ignored) {}

        return json;
    }

    private static JSONArray leaderboardScoresToJson(
        LeaderboardScoreBuffer buffer)
    {
        JSONArray json = new JSONArray();

        if (buffer == null)
            return json;

        for (LeaderboardScore score : buffer)
        {
            JSONObject item = new JSONObject();

            try
            {
                item.put("displayRank", score.getDisplayRank());
                item.put("displayScore", score.getDisplayScore());
                item.put("rawScore", score.getRawScore());
                item.put("scoreTag", score.getScoreTag());
                item.put("timestampMillis", score.getTimestampMillis());

                if (score.getScoreHolder() != null)
                    item.put("scoreHolder", playerToJson(score.getScoreHolder()));
            }
            catch (Exception ignored) {}

            json.put(item);
        }

        return json;
    }

    private static JSONObject scoreReportToJson(
        ScoreSubmissionData report)
    {
        JSONObject json = new JSONObject();

        if (report == null)
            return json;

        try
        {
            json.put("leaderboardId", report.getLeaderboardId());
            json.put("playerId", report.getPlayerId());

            JSONObject results = new JSONObject();
            putScoreResult(
                results,
                "daily",
                report.getScoreResult(LeaderboardVariant.TIME_SPAN_DAILY));
            putScoreResult(
                results,
                "weekly",
                report.getScoreResult(LeaderboardVariant.TIME_SPAN_WEEKLY));
            putScoreResult(
                results,
                "allTime",
                report.getScoreResult(LeaderboardVariant.TIME_SPAN_ALL_TIME));

            json.put("results", results);
        }
        catch (Exception ignored) {}

        return json;
    }

    private static void putScoreResult(
        JSONObject target,
        String key,
        ScoreSubmissionData.Result result)
    {
        if (result == null)
            return;

        try
        {
            JSONObject json = new JSONObject();
            json.put("rawScore", result.rawScore);
            json.put("formattedScore", result.formattedScore);
            json.put("scoreTag", result.scoreTag);
            json.put("newBest", result.newBest);
            target.put(key, json);
        }
        catch (Exception ignored) {}
    }

    private static JSONObject snapshotMetadataToJson(
        SnapshotMetadata metadata)
    {
        JSONObject json = new JSONObject();

        try
        {
            json.put("uniqueName", metadata.getUniqueName());
            json.put("description", metadata.getDescription());
            json.put("deviceName", metadata.getDeviceName());
            json.put("lastModifiedTimestamp", metadata.getLastModifiedTimestamp());
            json.put("playedTime", metadata.getPlayedTime());
            json.put("progressValue", metadata.getProgressValue());
            json.put("hasChangePending", metadata.hasChangePending());
            json.put(
                "coverImageUri",
                metadata.getCoverImageUri() != null
                    ? metadata.getCoverImageUri().toString()
                    : ""
            );
        }
        catch (Exception ignored) {}

        return json;
    }
}
