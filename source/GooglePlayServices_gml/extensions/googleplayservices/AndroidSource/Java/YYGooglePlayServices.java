package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import android.content.Context;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.Exception;
import java.lang.Void;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONObject;
import org.json.JSONArray;

import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.AuthenticationResult;

import com.google.android.gms.tasks.Task;

import com.google.android.gms.games.Game;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerLevel;
import com.google.android.gms.games.PlayerLevelInfo;

import com.google.android.gms.games.stats.PlayerStats;

import com.google.android.gms.games.achievement.Achievement;
import com.google.android.gms.games.achievement.AchievementBuffer;

import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.SnapshotsClient.DataOrConflict;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;

import com.google.android.gms.common.images.ImageManager;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;

public class YYGooglePlayServices extends RunnerSocial {
	private static final int EVENT_OTHER_SOCIAL = 70;

	private final HashMap<String, Snapshot> snapshotHashMap;

	// Activity handler

	private static Activity GetActivity() {
		return RunnerActivity.CurrentActivity;
	}

	// Threading & lifecycle

	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	private static void runBackground(Runnable r) {
		executorService.execute(r);
	}

	private static void runMain(Runnable r) {
		Activity a = GetActivity();
		if (a != null)
			a.runOnUiThread(r);
	}

	private final AtomicInteger asyncInd = new AtomicInteger();

	public YYGooglePlayServices() {
		PlayGamesSdk.initialize(GetActivity());
		snapshotHashMap = new HashMap<>();
	}

	private int getAsyncInd() {
		return asyncInd.incrementAndGet();
	}

	public double GooglePlayServices_IsAvailable() {
		return (double) GoogleApiAvailability.getInstance()
				.isGooglePlayServicesAvailable(GetActivity()) == ConnectionResult.SUCCESS ? 1 : 0;
	}

	public double GooglePlayServices_SignIn() {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).signIn()
				.addOnCompleteListener((Task<AuthenticationResult> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SignIn");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						AuthenticationResult result = task.getResult();
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "isAuthenticated",
								result.isAuthenticated() ? 1 : 0);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public double GooglePlayServices_IsAuthenticated() {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).isAuthenticated()
				.addOnCompleteListener((Task<AuthenticationResult> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_IsAuthenticated");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						AuthenticationResult result = task.getResult();
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "isAuthenticated",
								result.isAuthenticated() ? 1 : 0);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_RequestServerSideAccess(String serverClientId, double forceRefreshToken) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).requestServerSideAccess(serverClientId, forceRefreshToken >= 0.5)
				.addOnCompleteListener((Task<String> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_RequestServerSideAccess");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						RunnerJNILib.DsMapAddString(dsMapIndex, "authCode", task.getResult());
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		int dsMapIndex;

		switch (requestCode) {

			case RC_SAVED_GAMES:

				if (data == null) {
					dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type",
							"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnExit");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", uiAsyncId);
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				} else {
					if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA)) {
						SnapshotMetadata snapshotMetadata = data
								.getParcelableExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

						if (snapshotMetadata != null) {
							dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
							RunnerJNILib.DsMapAddString(dsMapIndex, "type",
									"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen");
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", uiAsyncId);
							RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotMetadata",
									SnapshotMetadataToJSON(snapshotMetadata).toString());
							RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);

							GooglePlayServices_SavedGames_Open(snapshotMetadata.getUniqueName());
						}
					} else if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW)) {
						dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type",
								"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", uiAsyncId);
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				}

				break;

			case RC_ACHIEVEMENT_UI:
			case RC_LEADERBOARD_UI:
				break;
		}
	}

	// ====================================
	// Player Info
	// ====================================

	public double GooglePlayServices_Player_Current() {

		final double asyncIndex = getAsyncInd();

		PlayGames.getPlayersClient(GetActivity()).getCurrentPlayer()
				.addOnCompleteListener((Task<Player> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Player_Current");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						Player mPlayer = task.getResult();
						RunnerJNILib.DsMapAddString(dsMapIndex, "player", PlayerToJSON(mPlayer).toString());
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public void GooglePlayServices_Player_CurrentID() {
		PlayGames.getPlayersClient(GetActivity()).getCurrentPlayerId()
				.addOnCompleteListener((Task<String> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Player_CurrentID");

					if (task.isSuccessful()) {
						String PlayerID = task.getResult();
						RunnerJNILib.DsMapAddString(dsMapIndex, "playerID", PlayerID);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
	}

	// ====================================
	// Achievements
	// ====================================

	private static final int RC_ACHIEVEMENT_UI = 9003;

	public void GooglePlayServices_Achievements_Show() {
		PlayGames.getAchievementsClient(GetActivity()).getAchievementsIntent()
				.addOnSuccessListener((Intent intent) -> {
					try {
						GetActivity().startActivityForResult(intent, RC_ACHIEVEMENT_UI);
					} catch (Exception e) {
						Log.e("yoyo", "ERROR GooglePlayServices_Achievements_Show: " + e.getMessage(), e);
					}
				});

	}

	public double GooglePlayServices_Achievements_Increment(final String arch_id, double steps) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).incrementImmediate(arch_id, (int) steps)
				.addOnCompleteListener((Task<Boolean> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Increment");
					RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						task.getResult();
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_Reveal(final String arch_id) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).revealImmediate(arch_id)
				.addOnCompleteListener((Task<Void> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Reveal");
					RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_SetSteps(final String arch_id, double steps) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).setStepsImmediate(arch_id, (int) steps)
				.addOnCompleteListener((Task<Boolean> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_SetSteps");
					RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_Unlock(final String arch_id) {
		final double asyncIndex = getAsyncInd();

		PlayGames.getAchievementsClient(GetActivity()).unlockImmediate(arch_id)
				.addOnCompleteListener((Task<Void> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Unlock");
					RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_GetStatus(double force_reload) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).load(force_reload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<AchievementBuffer>> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_GetStatus");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {

						AnnotatedData<AchievementBuffer> annotatedData = task.getResult();
						AchievementBuffer achievementBuffer = annotatedData.get();

						JSONArray achievementJSON = new JSONArray();
						if (achievementBuffer != null) {

							for (Achievement achievement : achievementBuffer) {
								achievementJSON.put(AchievementToJSON(achievement));
							}
							achievementBuffer.release();
						}
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						RunnerJNILib.DsMapAddString(dsMapIndex, "data", achievementJSON.toString());

					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	static JSONObject AchievementToJSON(Achievement achievement) {

		JSONObject achievementJSON = new JSONObject();

		try {
			achievementJSON.put("id", achievement.getAchievementId());
			achievementJSON.put("description", achievement.getDescription());
			achievementJSON.put("lastUpdatedTimestamp", (double) achievement.getLastUpdatedTimestamp());
			achievementJSON.put("name", achievement.getName());
			if (achievement.getRevealedImageUri() != null)
				achievementJSON.put("revealedImage", achievement.getRevealedImageUri().toString());
			achievementJSON.put("state", (double) achievement.getState());
			achievementJSON.put("typeAchievement", (double) achievement.getType());
			if (achievement.getUnlockedImageUri() != null)
				achievementJSON.put("unlockedImage", achievement.getUnlockedImageUri().toString());
			achievementJSON.put("xpValue", (double) achievement.getXpValue());

			if (achievement.getType() == Achievement.TYPE_INCREMENTAL) {
				achievementJSON.put("currentSteps", (double) achievement.getCurrentSteps());
				achievementJSON.put("formattedCurrentSteps", achievement.getFormattedCurrentSteps());
				achievementJSON.put("formattedTotalSteps", achievement.getFormattedTotalSteps());
				achievementJSON.put("totalSteps", (double) achievement.getTotalSteps());
			}
		} catch (Exception e) {
			Log.e("yoyo", "AchievementToJSON : failed to create Achievement json object - " + e.getMessage());
		}

		return achievementJSON;
	}

	// ====================================
	// Leaderboards
	// ====================================

	private static final int RC_LEADERBOARD_UI = 9004;

	public void GooglePlayServices_Leaderboard_ShowAll() {
		PlayGames.getLeaderboardsClient(GetActivity()).getAllLeaderboardsIntent()
				.addOnSuccessListener(intent -> {
					try {
						GetActivity().startActivityForResult(intent, RC_LEADERBOARD_UI);
					} catch (Exception e) {
						Log.e("yoyo", "ERROR GooglePlayServices_Leaderboard_ShowAll: " + e.getMessage(), e);
					}
				});
	}

	public void GooglePlayServices_Leaderboard_Show(String leaderboardId) {
		PlayGames.getLeaderboardsClient(GetActivity()).getLeaderboardIntent(leaderboardId)
				.addOnSuccessListener(intent -> {
					try {
						GetActivity().startActivityForResult(intent, RC_LEADERBOARD_UI);
					} catch (Exception e) {
						Log.e("yoyo", "ERROR GooglePlayServices_Leaderboard_Show: " + e.getMessage(), e);
					}
				});
	}

	static JSONObject PlayerToJSON(Player player) {

		// https://developers.google.com/android/reference/com/google/android/gms/games/Player

		JSONObject playJSON = new JSONObject();

		try {

			if (player.getBannerImageLandscapeUri() != null)
				playJSON.put("bannerImageLandscapeUri", player.getBannerImageLandscapeUri().toString());
			if (player.getBannerImagePortraitUri() != null)
				playJSON.put("bannerImagePortraitUri", player.getBannerImagePortraitUri().toString());
			playJSON.put("displayName", player.getDisplayName());
			if (player.getHiResImageUri() != null)
				playJSON.put("hiResImageUri", player.getHiResImageUri().toString());
			if (player.getIconImageUri() != null)
				playJSON.put("iconImageUri", player.getIconImageUri().toString());

			PlayerLevelInfo levelInfo = player.getLevelInfo();
			if (levelInfo != null) {
				playJSON.put("currentXpTotal", (double) levelInfo.getCurrentXpTotal());
				playJSON.put("lastLevelUpTimestamp", (double) levelInfo.getLastLevelUpTimestamp());

				PlayerLevel currentLevel = levelInfo.getCurrentLevel();
				playJSON.put("currentLevelNumber", (double) currentLevel.getLevelNumber());
				playJSON.put("currentMaxXp", (double) currentLevel.getMaxXp());
				playJSON.put("currentMinXp", (double) currentLevel.getMinXp());

				PlayerLevel nextLevel = levelInfo.getCurrentLevel();
				playJSON.put("nextLevelNumber", (double) nextLevel.getLevelNumber());
				playJSON.put("nextMaxXp", (double) nextLevel.getMaxXp());
				playJSON.put("nextMinXp", (double) nextLevel.getMinXp());
			}

			playJSON.put("playerId", player.getPlayerId());
			playJSON.put("retrievedTimestamp", (double) player.getRetrievedTimestamp());
			if (player.getTitle() != null)
				playJSON.put("title", player.getTitle());

			playJSON.put("hasHiResImage", player.hasHiResImage());
			playJSON.put("hasIconImage", player.hasIconImage());

		} catch (Exception e) {
			Log.e("yoyo", "PlayerToJSON : failed to create Player json object - " + e.getMessage());
		}

		return playJSON;
	}

	public double GooglePlayServices_Leaderboard_SubmitScore(final String leaderboardId, final double score,
			final String scoreTag) {

		final double asyncIndex = getAsyncInd();
		PlayGames.getLeaderboardsClient(GetActivity()).submitScoreImmediate(leaderboardId, (long) score, scoreTag)
				.addOnCompleteListener((Task<ScoreSubmissionData> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Leaderboard_SubmitScore");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {

						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboardId", leaderboardId);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "score", score);
						RunnerJNILib.DsMapAddString(dsMapIndex, "scoreTag", scoreTag);

						ScoreSubmissionData result = task.getResult();
						JSONObject reportJSON = BuildScoreReport(result);

						RunnerJNILib.DsMapAddString(dsMapIndex, "report", reportJSON.toString());
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	private static JSONObject BuildScoreReport(ScoreSubmissionData data) {
		JSONObject reportJSON = new JSONObject();

		try {
			AddScoreReportPeriod(reportJSON, "daily", data.getScoreResult(LeaderboardVariant.TIME_SPAN_DAILY));
			AddScoreReportPeriod(reportJSON, "weekly", data.getScoreResult(LeaderboardVariant.TIME_SPAN_WEEKLY));
			AddScoreReportPeriod(reportJSON, "allTime", data.getScoreResult(LeaderboardVariant.TIME_SPAN_ALL_TIME));
		} catch (Exception e) {
			Log.e("yoyo", "BuildScoreReport: JSON error", e);
		}

		return reportJSON;
	}

	private static void AddScoreReportPeriod(JSONObject root, String label, ScoreSubmissionData.Result r)
			throws Exception {
		if (r == null)
			return;

		JSONObject periodJSON = new JSONObject();
		periodJSON.put("isNewBest", r.newBest ? 1 : 0);
		periodJSON.put("score", (double) r.rawScore);
		periodJSON.put("scoreTag", r.scoreTag);
		root.put(label, periodJSON);
	}

	static JSONArray LeaderboardScoreBufferToJSON(LeaderboardScoreBuffer leaderboardScoreBuffer) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/LeaderboardScore
		JSONArray scoresJSON = new JSONArray();

		for (LeaderboardScore leaderboardScore : leaderboardScoreBuffer) {
			scoresJSON.put(LeaderboardScoreToJSON(leaderboardScore));
		}

		return scoresJSON;
	}

	static JSONObject LeaderboardScoreToJSON(LeaderboardScore mLeaderboardScore) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/LeaderboardScore
		JSONObject leaderboardScoreJSON = new JSONObject();

		try {
			leaderboardScoreJSON.put("displayRank", mLeaderboardScore.getDisplayRank());
			leaderboardScoreJSON.put("displayScore", mLeaderboardScore.getDisplayScore());
			leaderboardScoreJSON.put("rank", (double) mLeaderboardScore.getRank());
			leaderboardScoreJSON.put("rawScore", (double) mLeaderboardScore.getRawScore());

			Player scoreHolder = mLeaderboardScore.getScoreHolder();
			if (scoreHolder != null) {
				leaderboardScoreJSON.put("scoreHolder", PlayerToJSON(scoreHolder));
			}

			leaderboardScoreJSON.put("scoreHolderDisplayName", mLeaderboardScore.getScoreHolderDisplayName());
			leaderboardScoreJSON.put("scoreHolderHiResImageUri",
					mLeaderboardScore.getScoreHolderHiResImageUri().toString());

			leaderboardScoreJSON.put("scoreHolderIconImageUri",
					mLeaderboardScore.getScoreHolderIconImageUri().toString());

			leaderboardScoreJSON.put("scoreTag", mLeaderboardScore.getScoreTag());
			leaderboardScoreJSON.put("timestampMillis", (double) mLeaderboardScore.getTimestampMillis());

		} catch (Exception e) {
			Log.e("yoyo", "LeaderboardScoreToJSON : failed to create LeaderboardScore json object - " + e.getMessage());
		}
		return leaderboardScoreJSON;
	}

	static JSONObject LeaderboardToJSON(Leaderboard mLeaderboard) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/Leaderboard
		JSONObject leaderboardJSON = new JSONObject();

		try {
			leaderboardJSON.put("displayName", mLeaderboard.getDisplayName());
			leaderboardJSON.put("iconImageUri", mLeaderboard.getIconImageUri().toString());
			leaderboardJSON.put("leaderboardId", mLeaderboard.getLeaderboardId());
			leaderboardJSON.put("scoreOrder", mLeaderboard.getScoreOrder());

		} catch (Exception e) {
			Log.e("yoyo", "LeaderboardToJSON : failed to create Leaderboard json object - " + e.getMessage());
		}

		return leaderboardJSON;
	}

	public double GooglePlayServices_Leaderboard_LoadPlayerCenteredScores(String leaderboardId, double span,
			double leaderboardCollection, double maxResults, double forceReload) {
		final double asyncIndex = getAsyncInd();

		PlayGames.getLeaderboardsClient(GetActivity())
				.loadPlayerCenteredScores(leaderboardId, (int) span, (int) leaderboardCollection, (int) maxResults,
						forceReload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task) -> {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type",
							"GooglePlayServices_Leaderboard_LoadPlayerCenteredScores");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {

						AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
						LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

						LeaderboardScoreBuffer leaderboardScoreBuffer = Objects.requireNonNull(leaderboardScores)
								.getScores();
						JSONArray scoresJSON = LeaderboardScoreBufferToJSON(leaderboardScoreBuffer);
						leaderboardScoreBuffer.release(); // avoid memory leaks

						JSONObject leaderboardJSON = LeaderboardToJSON(leaderboardScores.getLeaderboard());

						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboard", leaderboardJSON.toString());
						RunnerJNILib.DsMapAddString(dsMapIndex, "data", scoresJSON.toString());

					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public double GooglePlayServices_Leaderboard_LoadTopScores(String leaderboardId, double span,
			double leaderboardCollection, double maxResults, double forceReload) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getLeaderboardsClient(GetActivity())
				.loadTopScores(leaderboardId, (int) span, (int) leaderboardCollection, (int) maxResults,
						forceReload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type",
							"GooglePlayServices_Leaderboard_LoadTopScores");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {

						AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
						LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

						if (leaderboardScores != null) {

							LeaderboardScoreBuffer leaderboardScoreBuffer = leaderboardScores.getScores();
							JSONArray scoresJSON = LeaderboardScoreBufferToJSON(leaderboardScoreBuffer);
							leaderboardScoreBuffer.release();

							JSONObject leaderboardJSON = LeaderboardToJSON(leaderboardScores.getLeaderboard());

							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
							RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboard", leaderboardJSON.toString());
							RunnerJNILib.DsMapAddString(dsMapIndex, "data", scoresJSON.toString());
						}

					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
		return asyncIndex;
	}

	public double GooglePlayServices_UriToPath(String uriString) {
		double asyncIndex = getAsyncInd();

		(new Obj_UriToPath()).UriToPath(uriString, asyncIndex);

		return asyncIndex;
	}

	private static class Obj_UriToPath {
		public void UriToPath(final String uriString, final double asyncIndex) {
			GetActivity().runOnUiThread(() -> {
				Uri uri = Uri.parse(uriString);
				try {
					ImageManager mgr = ImageManager.create(GetActivity());
					mgr.loadImage((uri1, drawable, isRequestedDrawable) -> {

						if (isRequestedDrawable) {
							runBackground(() -> {
								try {
									ContextWrapper cw = new ContextWrapper(
											Objects.requireNonNull(GetActivity()).getApplicationContext());
									File dir = cw.getDir("profile", Context.MODE_PRIVATE);
									if (!dir.exists())
                                        //noinspection ResultOfMethodCallIgnored
                                        dir.mkdir();

									Bitmap bmp = ((BitmapDrawable) Objects.requireNonNull(drawable)).getBitmap();

									File out = new File(dir, "thumbnail" + asyncIndex + ".png");
									try (FileOutputStream fos = new FileOutputStream(out)) {
										bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
									}

									sendUriResult(asyncIndex, true, out.getPath());

								} catch (Exception e) {
									sendUriResult(asyncIndex, false, null);
								}
							});
						} else {
							sendUriResult(asyncIndex, false, null);
						}
					}, uri);
				} catch (Exception e) {
					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_UriToPath");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				}
			});
		}
	}

	private static void sendUriResult(double asyncIndex, boolean ok, @Nullable String path) {
		runMain(() -> {
			int map = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(map, "type", "GooglePlayServices_UriToPath");
			RunnerJNILib.DsMapAddDouble(map, "ind", asyncIndex);
			RunnerJNILib.DsMapAddDouble(map, "success", ok ? 1 : 0);
			if (ok && path != null)
				RunnerJNILib.DsMapAddString(map, "path", path);
			RunnerJNILib.CreateAsynEventWithDSMap(map, EVENT_OTHER_SOCIAL);
		});
	}

	// ====================================
	// SavedGames
	// ====================================

	@NonNull
	static JSONObject SnapshotMetadataToJSON(SnapshotMetadata snapshotMetadata) {
		JSONObject snapshotMetadataJSON = new JSONObject();

		try {
			snapshotMetadataJSON.put("coverImageAspectRatio", snapshotMetadata.getCoverImageAspectRatio());

			Uri coverImageUri = snapshotMetadata.getCoverImageUri();
			if (coverImageUri != null) {
				snapshotMetadataJSON.put("coverImageUri", coverImageUri.toString());
			}
			String description = snapshotMetadata.getDescription();
			snapshotMetadataJSON.put("description", description);
			String deviceName = snapshotMetadata.getDeviceName();
			if (deviceName != null) {
				snapshotMetadataJSON.put("deviceName", deviceName);
			}

			snapshotMetadataJSON.put("game", GameToJSON(snapshotMetadata.getGame()));
			snapshotMetadataJSON.put("hasChangePending", snapshotMetadata.hasChangePending() ? 1 : 0);
			snapshotMetadataJSON.put("lastModifiedTimestamp", snapshotMetadata.getLastModifiedTimestamp());
			snapshotMetadataJSON.put("owner", PlayerToJSON(snapshotMetadata.getOwner()));
			snapshotMetadataJSON.put("playedTime", snapshotMetadata.getPlayedTime());
			snapshotMetadataJSON.put("progressValue", snapshotMetadata.getProgressValue());

			String uniqueName = snapshotMetadata.getUniqueName();
			snapshotMetadataJSON.put("uniqueName", uniqueName);

		} catch (Exception e) {
			Log.e("yoyo", "SnapshotMetadataToJSON : failed to create SnapshotMetadata json object - " + e.getMessage());
		}

		return snapshotMetadataJSON;
	}

	@NonNull
	static JSONObject GameToJSON(Game game) {
		JSONObject gameJSON = new JSONObject();

		try {
			gameJSON.put("areSnapshotsEnabled", game.areSnapshotsEnabled() ? 1 : 0);
			gameJSON.put("achievementTotalCount", game.getAchievementTotalCount());
			gameJSON.put("applicationId", game.getApplicationId());
			gameJSON.put("description", game.getDescription());
			gameJSON.put("developerName", game.getDeveloperName());

			String displayName = game.getDisplayName();
			gameJSON.put("displayName", displayName);
			Uri featuredImageUri = game.getFeaturedImageUri();
			gameJSON.put("featuredImageUri", featuredImageUri.toString());

			gameJSON.put("gamepadSupport", game.hasGamepadSupport() ? 1 : 0);

			Uri hiResImageUri = game.getHiResImageUri();
			gameJSON.put("hiResImageUri", hiResImageUri.toString());
			Uri iconImageUri = game.getIconImageUri();
			gameJSON.put("iconImageUri", iconImageUri.toString());

			gameJSON.put("leaderboardCount", game.getLeaderboardCount());
			gameJSON.put("primaryCategory", game.getPrimaryCategory());
			gameJSON.put("secondaryCategory", game.getSecondaryCategory());
			gameJSON.put("themeColor", game.getThemeColor());

		} catch (Exception e) {
			Log.e("yoyo", "GameToJSON : failed to create Game json object - " + e.getMessage());
		}

		return gameJSON;
	}

	private static final int RC_SAVED_GAMES = 9009;
	private int uiAsyncId;

	public double GooglePlayServices_SavedGames_ShowSavedGamesUI(String title, double buttonAdd, double buttonDelete,
			double max) {
		uiAsyncId = getAsyncInd();

		boolean showAddButton = buttonAdd > 0.5;
		boolean showDeleteButton = buttonDelete > 0.5;
		int maxToShow = (int) max;

		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.getSelectSnapshotIntent(title, showAddButton, showDeleteButton, maxToShow)
				.addOnSuccessListener((Intent intent) -> {
					try {
						GetActivity().startActivityForResult(intent, RC_SAVED_GAMES);
					} catch (Exception e) {
						Log.e("yoyo", "GooglePlayServices_SavedGames_ShowSavedGamesUI: failed to show save games UI - "
								+ e.getMessage());
					}
				});

		return uiAsyncId;
	}

	/*
	 * ===========================================================================
	 * 🔧 Helpers used by both commit methods
	 * ===========================================================================
	 */

	private void fillMetadata(@NonNull SnapshotMetadataChange.Builder b,
			@NonNull String desc,
			@NonNull String coverImagePath) {

		if (!desc.isEmpty())
			b.setDescription(desc);

		if (!coverImagePath.isEmpty()) {
			File imgFile = new File(GetActivity().getFilesDir(), coverImagePath);
			if (imgFile.exists()) {
				Bitmap bmp = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				if (bmp != null)
					b.setCoverImage(bmp);
			}
		}
	}

	private void commitSnapshot(@NonNull Snapshot snap, @NonNull SnapshotMetadataChange change,
			@NonNull String gmsEventType, double asyncIndex) {

		// This might be called in the background so lets be safe
		Activity act = GetActivity();
		if (act == null)
			return;

		PlayGames.getSnapshotsClient(act)
				.commitAndClose(snap, change)
				.addOnCompleteListener(act, (Task<SnapshotMetadata> t) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", gmsEventType);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (t.isSuccessful()) {
						snapshotHashMap.remove(snap.getMetadata().getUniqueName());

						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						RunnerJNILib.DsMapAddString(
								dsMapIndex, "snapshotMetadata",
								SnapshotMetadataToJSON(t.getResult()).toString());
					} else {
						addTaskFailureDetails(dsMapIndex, t.getException());
					}
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});
	}

	/*
	 * ===========================================================================
	 * ✨ 1. Commit *existing* snapshot and close
	 * ===========================================================================
	 */
	public double GooglePlayServices_SavedGames_CommitAndClose(final String name, final String desc, final String data, final String coverImagePath) {

		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null)
			return -1;

		final double asyncIndex = getAsyncInd();

		runBackground(() -> {
			try {
				snapshot.getSnapshotContents()
						.writeBytes(data.getBytes(StandardCharsets.UTF_8));

				SnapshotMetadataChange.Builder b = new SnapshotMetadataChange.Builder();
				fillMetadata(b, desc, coverImagePath);

				runMain(() -> commitSnapshot(
						snapshot, b.build(),
						"GooglePlayServices_SavedGames_CommitAndClose", asyncIndex));

			} catch (Exception ex) {
				runMain(() -> {
					int map = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(map, "type", "GooglePlayServices_SavedGames_CommitAndClose");
					RunnerJNILib.DsMapAddDouble(map, "ind", asyncIndex);
					addTaskFailureDetails(map, ex);
					RunnerJNILib.CreateAsynEventWithDSMap(map, EVENT_OTHER_SOCIAL);
				});
			}
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_CommitNew(final String name, final String desc, final String data,
			final String coverImagePath) {

		final double asyncIndex = getAsyncInd();

		Activity act = GetActivity();
		PlayGames.getSnapshotsClient(act)
				.open(name, /* create */ true, /* conflict policy */ 1)
				.addOnCompleteListener(act,
						(Task<DataOrConflict<Snapshot>> openTask) -> {

							int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
							RunnerJNILib.DsMapAddString(dsMapIndex, "type",
									"GooglePlayServices_SavedGames_CommitNew");
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

							if (!openTask.isSuccessful()) {
								addTaskFailureDetails(dsMapIndex, openTask.getException());
								RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
								return;
							}

							DataOrConflict<Snapshot> dc = openTask.getResult();
							if (dc.isConflict()) {
								RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
								RunnerJNILib.DsMapAddString(dsMapIndex, "error", "Snapshot conflict");
								RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
								return;
							}

							final Snapshot snapshot = dc.getData();
							if (snapshot == null) { // should never happen, but be safe
								addTaskFailureDetails(dsMapIndex, null);
								RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
								return;
							}

							snapshotHashMap.put(snapshot.getMetadata().getUniqueName(), snapshot);

							/* heavy write -> background */
							runBackground(() -> {
								try {
									snapshot.getSnapshotContents()
											.writeBytes(data.getBytes(StandardCharsets.UTF_8));

									SnapshotMetadataChange.Builder b = new SnapshotMetadataChange.Builder();
									fillMetadata(b, desc, coverImagePath);

									runMain(() -> commitSnapshot(
											snapshot, b.build(),
											"GooglePlayServices_SavedGames_CommitNew", asyncIndex));

								} catch (Exception ex) {
									addTaskFailureDetails(dsMapIndex, ex);
									RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
								}
							});
						});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Load(final double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());

		snapshotsClient.load(shouldForceReload)
				.addOnCompleteListener((Task<AnnotatedData<SnapshotMetadataBuffer>> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Load");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						AnnotatedData<SnapshotMetadataBuffer> annotatedData = task.getResult();
						SnapshotMetadataBuffer snapshotMetadataBuffer = annotatedData.get();

						JSONArray snapshots = new JSONArray();
						if (snapshotMetadataBuffer != null) {
							for (SnapshotMetadata snapshotMetadata : snapshotMetadataBuffer) {
								snapshots.put(SnapshotMetadataToJSON(snapshotMetadata));
							}
							snapshotMetadataBuffer.release();
						}

						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						RunnerJNILib.DsMapAddString(dsMapIndex, "snapshots", snapshots.toString());
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Open(final String name) {
		final double asyncIndex = getAsyncInd();

		boolean createIfNotFound = false;
		int conflictPolicy = 1;

		PlayGames.getSnapshotsClient(GetActivity()).open(name, createIfNotFound, conflictPolicy)
				.addOnCompleteListener((Task<DataOrConflict<Snapshot>> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Open");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						DataOrConflict<Snapshot> dataOrConflict = task.getResult();

						if (!dataOrConflict.isConflict()) {
							Snapshot snapshot = dataOrConflict.getData();

							assert snapshot != null;
							SnapshotMetadata snapshotMetadata = snapshot.getMetadata();

							snapshotHashMap.put(snapshotMetadata.getUniqueName(), snapshot);

							RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotMetadata",
									SnapshotMetadataToJSON(snapshotMetadata).toString());

							try {
								SnapshotContents snapshotContents = snapshot.getSnapshotContents();
								byte[] dataInBytes = snapshotContents.readFully();
								String dataString = new String(dataInBytes, StandardCharsets.UTF_8);

								RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
								RunnerJNILib.DsMapAddString(dsMapIndex, "data", dataString);

							} catch (Exception exception) {
								RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
								RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
							}
						} else {
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", "Found conflict while commiting data");
							RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
						}
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Delete(final String name) {

		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null) {
			return -1;
		}

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		SnapshotMetadata snapshotMetadata = snapshot.getMetadata();

		snapshotsClient.delete(snapshotMetadata).addOnCompleteListener((Task<String> task) -> {

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Delete");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

			if (task.isSuccessful()) {
				snapshotHashMap.remove(name);

				String snapshotID = task.getResult();
				RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotID", snapshotID);
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
			} else {
				addTaskFailureDetails(dsMapIndex, task.getException());
			}

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_DiscardAndClose(String name) {

		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null) {
			return -1;
		}

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.discardAndClose(snapshot).addOnCompleteListener((Task<Void> task) -> {

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_DiscardAndClose");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

			if (task.isSuccessful()) {
				snapshotHashMap.remove(name);

				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
			} else {
				addTaskFailureDetails(dsMapIndex, task.getException());
			}

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
		});

		return asyncIndex;
	}

	// ====================================
	// PlayerStats
	// ====================================

	public double GooglePlayServices_PlayerStats_LoadPlayerStats(double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;

		PlayGames.getPlayerStatsClient(GetActivity()).loadPlayerStats(shouldForceReload)
				.addOnCompleteListener((Task<AnnotatedData<PlayerStats>> task) -> {

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_PlayerStats_LoadPlayerStats");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

					if (task.isSuccessful()) {
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);

						AnnotatedData<PlayerStats> annotatedData = task.getResult();
						PlayerStats playerStats = annotatedData.get();
						if (playerStats != null) {
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "AverageSessionLength",
									playerStats.getAverageSessionLength());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "DaysSinceLastPlayed",
									playerStats.getDaysSinceLastPlayed());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "NumberOfPurchases",
									playerStats.getNumberOfPurchases());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "NumberOfSessions",
									playerStats.getNumberOfSessions());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "SessionPercentile",
									playerStats.getSessionPercentile());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "SpendPercentile",
									playerStats.getSpendPercentile());
						}
					} else {
						addTaskFailureDetails(dsMapIndex, task.getException());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	private static void addTaskFailureDetails(int map, @Nullable Exception ex) {
		RunnerJNILib.DsMapAddDouble(map, "success", 0);
		RunnerJNILib.DsMapAddString(map, "error", ex != null ? ex.getLocalizedMessage() : "unknown");
		//if (ex != null)
		//	Log.i("yoyo", "GooglePlayServices :: Task failed", ex);
	}
}
