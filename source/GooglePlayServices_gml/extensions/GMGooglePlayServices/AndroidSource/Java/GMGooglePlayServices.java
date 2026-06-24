package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;

import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.GPGSSavedGameCommitOptions;
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



import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private final ExecutorService background = Executors.newCachedThreadPool();
    private final Map<String, Snapshot> snapshots = new ConcurrentHashMap<>();

    private volatile Snapshot conflictLocal;
    private volatile Snapshot conflictRemote;
    private volatile GMFunction savedGamesUiCallback;

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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(playerResultStream(
                false,
                emptyPlayerStream(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getPlayersClient(activity()).getCurrentPlayer()
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful() && task.getResult() != null)
                {
                    callback.call(playerResultStream(
                        true,
                        playerToStream(task.getResult()),
                        ""
                    ));
                }
                else
                {
                    callback.call(playerResultStream(
                        false,
                        emptyPlayerStream(),
                        error(task.getException())
                    ));
                }
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

    public void gpgs_player_stats_load(
        boolean force_reload,
        final GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
        {
            callback.call(playerStatsResultStream(
                false,
                emptyPlayerStatsStream(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getPlayerStatsClient(activity())
            .loadPlayerStats(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(playerStatsResultStream(
                        false,
                        emptyPlayerStatsStream(),
                        error(task.getException())
                    ));
                    return;
                }

                AnnotatedData<PlayerStats> annotatedData = task.getResult();
                PlayerStats stats =
                    annotatedData != null ? annotatedData.get() : null;

                if (stats == null)
                {
                    callback.call(playerStatsResultStream(
                        false,
                        emptyPlayerStatsStream(),
                        "No player statistics were returned."
                    ));
                    return;
                }

                callback.call(playerStatsResultStream(
                    true,
                    playerStatsToStream(stats),
                    ""
                ));
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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(achievementsResultStream(
                false,
                streamArray(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getAchievementsClient(activity())
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(achievementsResultStream(
                        false,
                        streamArray(),
                        error(task.getException())
                    ));
                    return;
                }

                AchievementBuffer buffer =
                    task.getResult() != null
                        ? task.getResult().get()
                        : null;

                GMExtWire.ArrayStream achievements =
                    streamArray();

                if (buffer != null)
                {
                    try
                    {
                        for (Achievement achievement : buffer)
                            achievements.add(achievementToStream(achievement));
                    }
                    catch (Exception exception)
                    {
                        callback.call(achievementsResultStream(
                            false,
                            streamArray(),
                            error(exception)
                        ));
                        return;
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(achievementsResultStream(
                    true,
                    achievements,
                    ""
                ));
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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(leaderboardSubmitResultStream(
                false,
                leaderboardId,
                score,
                safeString(scoreTag),
                emptyScoreReportStream(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getLeaderboardsClient(activity())
            .submitScoreImmediate(leaderboardId, (long)score, scoreTag)
            .addOnCompleteListener(task ->
            {
                if (task.isSuccessful())
                {
                    callback.call(leaderboardSubmitResultStream(
                        true,
                        leaderboardId,
                        score,
                        safeString(scoreTag),
                        scoreReportToStream(task.getResult()),
                        ""
                    ));
                }
                else
                {
                    callback.call(leaderboardSubmitResultStream(
                        false,
                        leaderboardId,
                        score,
                        safeString(scoreTag),
                        emptyScoreReportStream(),
                        error(task.getException())
                    ));
                }
            });
    }

    public void gpgs_leaderboard_load_player_centered_scores(
        String leaderboard_id,
        GPGSLeaderboardTimeSpan span,
        GPGSLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
        {
            callback.call(leaderboardScoresResultStream(
                false,
                emptyLeaderboardStream(),
                streamArray(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getLeaderboardsClient(activity())
            .loadPlayerCenteredScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                (int)max_results,
                force_reload)
            .addOnCompleteListener(task -> completeScores(task, callback));
    }

    public void gpgs_leaderboard_load_top_scores(
        String leaderboard_id,
        GPGSLeaderboardTimeSpan span,
        GPGSLeaderboardCollection leaderboard_collection,
        double max_results,
        boolean force_reload,
        GMFunction callback)
    {
        if (!authenticationKnown || !authenticated)
        {
            callback.call(leaderboardScoresResultStream(
                false,
                emptyLeaderboardStream(),
                streamArray(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getLeaderboardsClient(activity())
            .loadTopScores(
                leaderboard_id,
                (int)span.value(),
                (int)leaderboard_collection.value(),
                (int)max_results,
                force_reload)
            .addOnCompleteListener(task -> completeScores(task, callback));
    }

    private void completeScores(
        Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task,
        GMFunction callback)
    {
        if (!task.isSuccessful())
        {
            callback.call(leaderboardScoresResultStream(
                false,
                emptyLeaderboardStream(),
                streamArray(),
                error(task.getException())
            ));
            return;
        }

        AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData =
            task.getResult();

        LeaderboardsClient.LeaderboardScores scores =
            annotatedData != null ? annotatedData.get() : null;

        if (scores == null)
        {
            callback.call(leaderboardScoresResultStream(
                false,
                emptyLeaderboardStream(),
                streamArray(),
                "No leaderboard score data."
            ));
            return;
        }

        LeaderboardScoreBuffer buffer = scores.getScores();
        GMExtWire.ArrayStream scoreArray;

        try
        {
            scoreArray = leaderboardScoresToStream(buffer);
        }
        finally
        {
            if (buffer != null)
                buffer.release();
        }

        callback.call(leaderboardScoresResultStream(
            true,
            leaderboardToStream(scores.getLeaderboard()),
            scoreArray,
            ""
        ));
    }

    // -------------------------------------------------------------------------
    // URI to local path
    // -------------------------------------------------------------------------

    public void gpgs_uri_to_path(
        String uriString,
        final GMFunction callback)
    {
        final Activity activity = activity();
        if (activity == null)
        {
            callback.call(false, "", "Activity is null.");
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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(savedGamesUIResultStream(
                GPGSSavedGamesUIResult.Error.value(),
                emptySnapshotMetadataStream(),
                authenticationError()
            ));
            return;
        }

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
                {
                    pending.call(savedGamesUIResultStream(
                        GPGSSavedGamesUIResult.Error.value(),
                        emptySnapshotMetadataStream(),
                        error(exception)
                    ));
                }
            });
    }

    public void onActivityResult(
        int requestCode,
        int resultCode,
        Intent data)
    {
        if (requestCode != RC_SAVED_GAMES)
            return;

        GMFunction callback = savedGamesUiCallback;
        savedGamesUiCallback = null;

        if (callback == null)
            return;

        if (data == null || resultCode != Activity.RESULT_OK)
        {
            callback.call(savedGamesUIResultStream(
                GPGSSavedGamesUIResult.Cancelled.value(),
                emptySnapshotMetadataStream(),
                ""
            ));
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA))
        {
            SnapshotMetadata metadata =
                data.getParcelableExtra(
                    SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

            callback.call(savedGamesUIResultStream(
                GPGSSavedGamesUIResult.Selected.value(),
                snapshotMetadataToStream(metadata),
                ""
            ));
            return;
        }

        if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW))
        {
            callback.call(savedGamesUIResultStream(
                GPGSSavedGamesUIResult.CreatedNew.value(),
                emptySnapshotMetadataStream(),
                ""
            ));
            return;
        }

        callback.call(savedGamesUIResultStream(
            GPGSSavedGamesUIResult.Cancelled.value(),
            emptySnapshotMetadataStream(),
            ""
        ));
    }

    public void gpgs_saved_games_commit_and_close(
        GPGSSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        if (options == null)
        {
            callback.call(false, "", "Commit options cannot be null.");
            return;
        }

        String name = options.name();
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

        commitSnapshot(snapshot, options, callback);
    }

    public void gpgs_saved_games_commit_new(
        GPGSSavedGameCommitOptions options,
        final GMFunction callback)
    {
        if (!requireAuthentication(
            callback, false, "", authenticationError()))
            return;

        if (options == null)
        {
            callback.call(false, "", "Commit options cannot be null.");
            return;
        }

        String name = options.name();

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
                commitSnapshot(snapshot, options, callback);
            });
    }

    private void commitSnapshot(
        Snapshot snapshot,
        GPGSSavedGameCommitOptions options,
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
                    callback.call(false, name, "Activity is null.");
                    return;
                }

                activity.runOnUiThread(() -> PlayGames.getSnapshotsClient(activity)
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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(savedGamesLoadResultStream(
                false,
                streamArray(),
                authenticationError()
            ));
            return;
        }

        PlayGames.getSnapshotsClient(activity())
            .load(force_reload)
            .addOnCompleteListener(task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(savedGamesLoadResultStream(
                        false,
                        streamArray(),
                        error(task.getException())
                    ));
                    return;
                }

                SnapshotMetadataBuffer buffer =
                    task.getResult() != null
                        ? task.getResult().get()
                        : null;

                GMExtWire.ArrayStream snapshotArray =
                    streamArray();

                if (buffer != null)
                {
                    try
                    {
                        for (SnapshotMetadata metadata : buffer)
                            snapshotArray.add(snapshotMetadataToStream(metadata));
                    }
                    finally
                    {
                        buffer.release();
                    }
                }

                callback.call(savedGamesLoadResultStream(
                    true,
                    snapshotArray,
                    ""
                ));
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
        if (!authenticationKnown || !authenticated)
        {
            callback.call(savedGameOpenCallbackResultStream(
                false,
                emptySavedGameOpenResultStream(),
                authenticationError()
            ));
            return;
        }

        // Run the completion on the background executor: the success path calls
        // Snapshot.readFully() (synchronous disk I/O) which must not run on the UI
        // thread. Callback delivery is thread-agnostic (marshalled via DispatchQueue).
        PlayGames.getSnapshotsClient(activity())
            .open(name, false, policy)
            .addOnCompleteListener(background, task ->
            {
                if (!task.isSuccessful())
                {
                    callback.call(savedGameOpenCallbackResultStream(
                        false,
                        emptySavedGameOpenResultStream(),
                        error(task.getException())
                    ));
                    return;
                }

                DataOrConflict<Snapshot> result = task.getResult();

                try
                {
                    GMExtWire.StructStream response;

                    if (!result.isConflict())
                    {
                        Snapshot snapshot = result.getData();

                        if (snapshot == null)
                        {
                            throw new IllegalStateException(
                                "No snapshot was returned."
                            );
                        }

                        snapshots.put(
                            snapshot.getMetadata().getUniqueName(),
                            snapshot
                        );

                        response = new GMExtWire.StructStream()
                            .kv("is_conflict", false)
                            .kv(
                                "snapshot_metadata",
                                snapshotMetadataToStream(
                                    snapshot.getMetadata()
                                )
                            )
                            .kv("data", readSnapshot(snapshot))
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

                        response = new GMExtWire.StructStream()
                            .kv("is_conflict", true)
                            .kv(
                                "snapshot_metadata",
                                emptySnapshotMetadataStream()
                            )
                            .kv("data", "")
                            .kv(
                                "conflict_id",
                                safeString(
                                    result.getConflict().getConflictId()
                                )
                            )
                            .kv(
                                "snapshot_metadata_local",
                                snapshotMetadataToStream(
                                    conflictLocal.getMetadata()
                                )
                            )
                            .kv(
                                "data_local",
                                readSnapshot(conflictLocal)
                            )
                            .kv(
                                "snapshot_metadata_remote",
                                snapshotMetadataToStream(
                                    conflictRemote.getMetadata()
                                )
                            )
                            .kv(
                                "data_remote",
                                readSnapshot(conflictRemote)
                            );
                    }

                    callback.call(savedGameOpenCallbackResultStream(
                        true,
                        response,
                        ""
                    ));
                }
                catch (Exception exception)
                {
                    callback.call(savedGameOpenCallbackResultStream(
                        false,
                        emptySavedGameOpenResultStream(),
                        error(exception)
                    ));
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

    private static GMExtWire.StructStream playerResultStream(
        boolean success,
        GMExtWire.StructStream player,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("player", player)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream playerStatsResultStream(
        boolean success,
        GMExtWire.StructStream stats,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("stats", stats)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream achievementsResultStream(
        boolean success,
        GMExtWire.ArrayStream achievements,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("achievements", achievements)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream leaderboardSubmitResultStream(
        boolean success,
        String leaderboardId,
        double score,
        String scoreTag,
        GMExtWire.StructStream report,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("leaderboard_id", safeString(leaderboardId))
            .kv("score", score)
            .kv("score_tag", safeString(scoreTag))
            .kv("report", report)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream leaderboardScoresResultStream(
        boolean success,
        GMExtWire.StructStream leaderboard,
        GMExtWire.ArrayStream scores,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("leaderboard", leaderboard)
            .kv("scores", scores)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream savedGamesUIResultStream(
        double result,
        GMExtWire.StructStream snapshotMetadata,
        String error)
    {
        return streamStruct()
            .kv("result", result)
            .kv("snapshot_metadata", snapshotMetadata)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream savedGamesLoadResultStream(
        boolean success,
        GMExtWire.ArrayStream snapshots,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("snapshots", snapshots)
            .kv("error", safeString(error));
    }

    private static GMExtWire.StructStream savedGameOpenCallbackResultStream(
        boolean success,
        GMExtWire.StructStream result,
        String error)
    {
        return streamStruct()
            .kv("success", success)
            .kv("result", result)
            .kv("error", safeString(error));
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
                GPGSLeaderboardScoreOrder.SmallerIsBetter.value()
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
