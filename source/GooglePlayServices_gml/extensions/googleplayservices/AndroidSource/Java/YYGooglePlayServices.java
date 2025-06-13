
package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import android.os.Bundle;
import android.content.Context;
import android.content.res.Configuration;
import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.widget.AbsoluteLayout;
import android.view.ViewGroup;
import android.view.View;
import androidx.annotation.NonNull;
import android.net.Uri;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.content.ContextWrapper;
import android.os.Build;
import android.widget.Toast;
import android.os.Environment;

import java.lang.Exception;
import java.lang.Boolean;
import java.lang.Void;
import java.lang.ref.WeakReference;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

import com.google.android.gms.games.PlayGamesSdk;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.AuthenticationResult;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayersClient;

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
import com.google.android.gms.games.SnapshotsClient.SnapshotConflict;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;

import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.images.ImageManager.OnImageLoadedListener;

import com.google.android.gms.common.api.Scope;

import com.google.android.gms.drive.Drive;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;

public class YYGooglePlayServices extends RunnerSocial {
	private static final int EVENT_OTHER_SOCIAL = 70;

	private HashMap<String, Snapshot> mapSnapshot;

	private static WeakReference<Activity> activityRef = new WeakReference<>(RunnerActivity.CurrentActivity);

	private static Activity GetActivity() { // helper – always call this
		return activityRef.get();
	}

	// update the reference when the Activity is recreated
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onResume();
		activityRef.clear();
		activityRef.enqueue();
		activityRef = new WeakReference<>(RunnerActivity.CurrentActivity);
	}

	int AsyncInd = 0;

	public YYGooglePlayServices() {
		PlayGamesSdk.initialize(GetActivity());
		mapSnapshot = new HashMap<String, Snapshot>();
	}

	private double getAsyncInd() {
		AsyncInd++;
		return (AsyncInd);
	}

	public double GooglePlayServices_IsAvailable() {
		return (double) GoogleApiAvailability.getInstance()
				.isGooglePlayServicesAvailable(GetActivity()) == ConnectionResult.SUCCESS ? 1 : 0;
	}

	public double GooglePlayServices_SignIn() {
		final double ind = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).signIn()
				.addOnCompleteListener(new OnCompleteListener<AuthenticationResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthenticationResult> isAuthenticatedTask) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SignIn");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (isAuthenticatedTask.isSuccessful()) {
							AuthenticationResult result = isAuthenticatedTask.getResult();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "isAuthenticated",
									result.isAuthenticated() ? 1 : 0);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = isAuthenticatedTask.getException();
							// Log.e("yoyo", "ERROR GooglePlayServices_SignIn: " + exception.getMessage(),
							// exception);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});

		return (ind);
	}

	public double GooglePlayServices_IsAuthenticated() {
		final double ind = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).isAuthenticated()
				.addOnCompleteListener(new OnCompleteListener<AuthenticationResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthenticationResult> isAuthenticatedTask) {

						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_IsAuthenticated");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (isAuthenticatedTask.isSuccessful()) {
							AuthenticationResult result = isAuthenticatedTask.getResult();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "isAuthenticated",
									result.isAuthenticated() ? 1 : 0);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = isAuthenticatedTask.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_RequestServerSideAccess(String serverClientId, double forceRefreshToken) {
		final double ind = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).requestServerSideAccess(serverClientId, forceRefreshToken >= 0.5)
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {

						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_RequestServerSideAccess");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							RunnerJNILib.DsMapAddString(dsMapIndex, "authCode", task.getResult());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		int dsMapIndex;

		switch (requestCode) {

			case RC_SAVED_GAMES:

				if (data == null) {
					dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type",
							"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnExit");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind_ShowSavedGamesUI);
					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				} else {
					if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA)) {
						SnapshotMetadata snapshotMetadata = data
								.getParcelableExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA);
						dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type",
								"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind_ShowSavedGamesUI);
						RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotMetadata",
								SnapshotMetadataToJSON(snapshotMetadata).toString());
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
						GooglePlayServices_SavedGames_Open(snapshotMetadata.getUniqueName());
					} else if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW)) {
						dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type",
								"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind_ShowSavedGamesUI);
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				}

				break;

			/*
			 * case RC_GETPLAYERSEARCHINTENT:
			 * break;
			 * 
			 * case RC_ACHIEVEMENT_UI:
			 * break;
			 * 
			 * 
			 * case RC_LEADERBOARD_UI:
			 * break;
			 */
		}
	}

	// ====================================
	// Player Info
	// ====================================

	public double GooglePlayServices_Player_Current() {
		final double ind = getAsyncInd();

		PlayGames.getPlayersClient(GetActivity()).getCurrentPlayer()
				.addOnCompleteListener(new OnCompleteListener<Player>() {
					@Override
					public void onComplete(@NonNull Task<Player> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Player_Current");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							Player mPlayer = task.getResult();
							RunnerJNILib.DsMapAddString(dsMapIndex, "player", PlayerToJSON(mPlayer).toString());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});

		return (ind);
	}

	public void GooglePlayServices_Player_CurrentID() {
		PlayGames.getPlayersClient(GetActivity()).getCurrentPlayerId()
				.addOnCompleteListener(new OnCompleteListener<String>() {
					@Override
					public void onComplete(@NonNull Task<String> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Player_CurrentID");

						if (task.isSuccessful()) {
							String PlayerID = task.getResult();
							RunnerJNILib.DsMapAddString(dsMapIndex, "playerID", PlayerID);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							// Log.e("yoyo", "ERROR GooglePlayServices_Player_CurrentID: " +
							// exception.getMessage(), exception);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
	}

	// ====================================
	// Achievements
	// ====================================

	private static final int RC_ACHIEVEMENT_UI = 9003;

	public void GooglePlayServices_Achievements_Show() {
		PlayGames.getAchievementsClient(GetActivity()).getAchievementsIntent()
				.addOnSuccessListener(new OnSuccessListener<Intent>() {
					@Override
					public void onSuccess(Intent intent) {
						try {
							GetActivity().startActivityForResult(intent, RC_ACHIEVEMENT_UI);
						} catch (Exception e) {
							Log.e("yoyo", "ERROR GooglePlayServices_Achievements_Show: " + e.getMessage(), e);
						}
					}
				});

	}

	public double GooglePlayServices_Achievements_Increment(final String arch_id, double steps) {
		final double ind = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).incrementImmediate(arch_id, (int) steps)
				.addOnCompleteListener(new OnCompleteListener<Boolean>() {
					@Override
					public void onComplete(@NonNull Task<Boolean> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Increment");
						RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							task.getResult();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							// Log.e("yoyo", "ERROR GooglePlayServices_Achievements_Increment: " +
							// exception.getMessage(), exception);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_Achievements_Reveal(final String arch_id) {
		final double ind = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).revealImmediate(arch_id)
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Reveal");
						RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_Achievements_SetSteps(final String arch_id, double steps) {
		final double ind = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).setStepsImmediate(arch_id, (int) steps)
				.addOnCompleteListener(new OnCompleteListener<Boolean>() {
					@Override
					public void onComplete(@NonNull Task<Boolean> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_SetSteps");
						RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_Achievements_Unlock(final String arch_id) {
		final double ind = getAsyncInd();

		PlayGames.getAchievementsClient(GetActivity()).unlockImmediate(arch_id)
				.addOnCompleteListener(new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_Unlock");
						RunnerJNILib.DsMapAddString(dsMapIndex, "achievement_id", arch_id);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_Achievements_GetStatus(double force_reload) {
		final double ind = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).load(force_reload >= 0.5)
				.addOnCompleteListener(new OnCompleteListener<AnnotatedData<AchievementBuffer>>() {
					@Override
					public void onComplete(@NonNull Task<AnnotatedData<AchievementBuffer>> task) {

						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Achievements_GetStatus");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {

							AnnotatedData<AchievementBuffer> annotatedData = task.getResult();
							AchievementBuffer achievementBuffer = annotatedData.get();

							try {
								JSONArray list = new JSONArray();
								for (Achievement achievement : achievementBuffer) {
									list.put(AchievementToJSON(achievement));
								}

								RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
								RunnerJNILib.DsMapAddString(dsMapIndex, "data", list.toString());

							} catch (Exception e) {
								RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
								RunnerJNILib.DsMapAddString(dsMapIndex, "error", e.getMessage());
							} finally {
								achievementBuffer.release();
							}

						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
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
				.addOnSuccessListener(new OnSuccessListener<Intent>() {
					@Override
					public void onSuccess(Intent intent) {
						try {
							GetActivity().startActivityForResult(intent, RC_LEADERBOARD_UI);
						} catch (Exception e) {
							Log.e("yoyo", "ERROR GooglePlayServices_Leaderboard_ShowAll: " + e.getMessage(), e);
						}
					}
				});
	}

	public void GooglePlayServices_Leaderboard_Show(String leaderboardId) {
		PlayGames.getLeaderboardsClient(GetActivity()).getLeaderboardIntent(leaderboardId)
				.addOnSuccessListener(new OnSuccessListener<Intent>() {
					@Override
					public void onSuccess(Intent intent) {
						try {
							GetActivity().startActivityForResult(intent, RC_LEADERBOARD_UI);
						} catch (Exception e) {
							Log.e("yoyo", "ERROR GooglePlayServices_Leaderboard_Show: " + e.getMessage(), e);
						}
					}
				});
	}

	static JSONObject PlayerToJSON(Player mPlayer) {

		// https://developers.google.com/android/reference/com/google/android/gms/games/Player

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (mPlayer.getBannerImageLandscapeUri() != null)
			map.put("bannerImageLandscapeUri", mPlayer.getBannerImageLandscapeUri().toString());
		if (mPlayer.getBannerImagePortraitUri() != null)
			map.put("bannerImagePortraitUri", mPlayer.getBannerImagePortraitUri().toString());
		if (mPlayer.getDisplayName() != null)
			map.put("displayName", mPlayer.getDisplayName());
		if (mPlayer.getHiResImageUri() != null)
			map.put("hiResImageUri", mPlayer.getHiResImageUri().toString());
		if (mPlayer.getIconImageUri() != null)
			map.put("iconImageUri", mPlayer.getIconImageUri().toString());
		// if(mPlayer.getLevelInfo().getCurrentXpTotal() != null)
		map.put("currentXpTotal", (double) mPlayer.getLevelInfo().getCurrentXpTotal());
		// if(mPlayer.getLevelInfo().getLastLevelUpTimestamp() != null)
		map.put("lastLevelUpTimestamp", (double) mPlayer.getLevelInfo().getLastLevelUpTimestamp());
		// if(mPlayer.getLevelInfo().getCurrentLevel().getLevelNumber() != null)
		map.put("currentLevelNumber", (double) mPlayer.getLevelInfo().getCurrentLevel().getLevelNumber());
		// if(mPlayer.getLevelInfo().getCurrentLevel().getMaxXp() != null)
		map.put("currentMaxXp", (double) mPlayer.getLevelInfo().getCurrentLevel().getMaxXp());
		// if(mPlayer.getLevelInfo().getCurrentLevel().getMinXp() != null)
		map.put("currentMinXp", (double) mPlayer.getLevelInfo().getCurrentLevel().getMinXp());
		// if(mPlayer.getLevelInfo().getNextLevel().getLevelNumber() != null)
		map.put("nextLevelNumber", (double) mPlayer.getLevelInfo().getNextLevel().getLevelNumber());
		// if(mPlayer.getLevelInfo().getNextLevel().getMaxXp() != null)
		map.put("nextMaxXp", (double) mPlayer.getLevelInfo().getNextLevel().getMaxXp());
		// if(mPlayer.getLevelInfo().getNextLevel().getMinXp() != null)
		map.put("nextMinXp", (double) mPlayer.getLevelInfo().getNextLevel().getMinXp());
		if (mPlayer.getPlayerId() != null)
			map.put("playerId", mPlayer.getPlayerId());
		// if(mPlayer.getRetrievedTimestamp() != null)
		map.put("retrievedTimestamp", (double) mPlayer.getRetrievedTimestamp());
		if (mPlayer.getTitle() != null)
			map.put("title", mPlayer.getTitle());

		if (mPlayer.hasHiResImage())
			map.put("hasHiResImage", (double) 1);
		else
			map.put("hasHiResImage", (double) 0);

		if (mPlayer.hasIconImage())
			map.put("hasIconImage", (double) 1);
		else
			map.put("hasIconImage", (double) 0);

		JSONObject obj = new JSONObject(map);

		return obj;

	}

	public double GooglePlayServices_Leaderboard_SubmitScore(final String leaderboardId, final double score,
			final String scoreTag) {

		final double ind = getAsyncInd();
		PlayGames.getLeaderboardsClient(GetActivity()).submitScoreImmediate(leaderboardId, (long) score, scoreTag)
				.addOnCompleteListener(new OnCompleteListener<ScoreSubmissionData>() {
					@Override
					public void onComplete(@NonNull Task<ScoreSubmissionData> task) {

						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_Leaderboard_SubmitScore");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {

							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
							RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboardId", leaderboardId);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "score", score);
							RunnerJNILib.DsMapAddString(dsMapIndex, "scoreTag", scoreTag);

							ScoreSubmissionData result = task.getResult();
							JSONObject reportJSON = BuildScoreReport(result);

							RunnerJNILib.DsMapAddString(dsMapIndex, "report", reportJSON.toString());
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
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

		try {
			for (LeaderboardScore leaderboardScore : leaderboardScoreBuffer) {
				scoresJSON.put(LeaderboardScoreToJSON(leaderboardScore));
			}
		} catch (Exception e) {
			Log.e("yoyo",
					"LeaderboardScoreBufferToJSON : failed to create LeaderboardScores json array - " + e.getMessage());
		}
		return scoresJSON;
	}

	static JSONObject LeaderboardScoreToJSON(LeaderboardScore mLeaderboardScore) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/LeaderboardScore
		JSONObject leaderboardScoreJSON = new JSONObject();

		try {
			if (mLeaderboardScore.getDisplayRank() != null)
				leaderboardScoreJSON.put("displayRank", mLeaderboardScore.getDisplayRank());
			if (mLeaderboardScore.getDisplayScore() != null)
				leaderboardScoreJSON.put("displayScore", mLeaderboardScore.getDisplayScore());
			// if(mLeaderboardScore.getRank() != null)
			leaderboardScoreJSON.put("rank", (double) mLeaderboardScore.getRank());
			// if(mLeaderboardScore.getRawScore() != null)
			leaderboardScoreJSON.put("rawScore", (double) mLeaderboardScore.getRawScore());
			if (PlayerToJSON(mLeaderboardScore.getScoreHolder()) != null)
				leaderboardScoreJSON.put("scoreHolder", PlayerToJSON(mLeaderboardScore.getScoreHolder()));
			if (mLeaderboardScore.getScoreHolderDisplayName() != null)
				leaderboardScoreJSON.put("scoreHolderDisplayName", mLeaderboardScore.getScoreHolderDisplayName());
			if (mLeaderboardScore.getScoreHolderHiResImageUri() != null)
				leaderboardScoreJSON.put("scoreHolderHiResImageUri",
						mLeaderboardScore.getScoreHolderHiResImageUri().toString());
			if (mLeaderboardScore.getScoreHolderIconImageUri() != null)
				leaderboardScoreJSON.put("scoreHolderIconImageUri",
						mLeaderboardScore.getScoreHolderIconImageUri().toString());
			if (mLeaderboardScore.getScoreTag() != null)
				leaderboardScoreJSON.put("scoreTag", mLeaderboardScore.getScoreTag());
			// if(mLeaderboardScore.getTimestampMillis() != null)
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
			if (mLeaderboard.getDisplayName() != null)
				leaderboardJSON.put("displayName", mLeaderboard.getDisplayName());
			if (mLeaderboard.getIconImageUri() != null)
				leaderboardJSON.put("iconImageUri", mLeaderboard.getIconImageUri().toString());
			if (mLeaderboard.getLeaderboardId() != null)
				leaderboardJSON.put("leaderboardId", mLeaderboard.getLeaderboardId());
			leaderboardJSON.put("scoreOrder", mLeaderboard.getScoreOrder());

		} catch (Exception e) {
			Log.e("yoyo", "LeaderboardToJSON : failed to create Leaderboard json object - " + e.getMessage());
		}

		return leaderboardJSON;
	}

	public double GooglePlayServices_Leaderboard_LoadPlayerCenteredScores(String leaderboardId, double span,
			double leaderboardCollection, double maxResults, double forceReload) {
		final double ind = getAsyncInd();

		PlayGames.getLeaderboardsClient(GetActivity())
				.loadPlayerCenteredScores(leaderboardId, (int) span, (int) leaderboardCollection, (int) maxResults,
						forceReload >= 0.5)
				.addOnCompleteListener(new OnCompleteListener<AnnotatedData<LeaderboardsClient.LeaderboardScores>>() {
					@Override
					public void onComplete(@NonNull Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task) {
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type",
								"GooglePlayServices_Leaderboard_LoadPlayerCenteredScores");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {

							AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
							LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

							LeaderboardScoreBuffer leaderboardScoreBuffer = leaderboardScores.getScores();
							JSONArray scoresJSON = LeaderboardScoreBufferToJSON(leaderboardScoreBuffer);

							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);

							JSONObject leaderboardJSON = LeaderboardToJSON(leaderboardScores.getLeaderboard());
							RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboard", leaderboardJSON.toString());
							RunnerJNILib.DsMapAddString(dsMapIndex, "data", scoresJSON.toString());

							leaderboardScores.release(); // avoid memory leaks
						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});

		return (ind);
	}

	public double GooglePlayServices_Leaderboard_LoadTopScores(String leaderboardId, double span,
			double leaderboardCollection, double maxResults, double forceReload) {
		final double ind = getAsyncInd();
		PlayGames.getLeaderboardsClient(GetActivity())
				.loadTopScores(leaderboardId, (int) span, (int) leaderboardCollection, (int) maxResults,
						forceReload >= 0.5)
				.addOnCompleteListener(new OnCompleteListener<AnnotatedData<LeaderboardsClient.LeaderboardScores>>() {
					@Override
					public void onComplete(@NonNull Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task) {

						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type",
								"GooglePlayServices_Leaderboard_LoadTopScores");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

						if (task.isSuccessful()) {

							AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
							LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

							LeaderboardScoreBuffer leaderboardScoreBuffer = leaderboardScores.getScores();
							JSONArray scoresJSON = LeaderboardScoreBufferToJSON(leaderboardScoreBuffer);

							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);

							JSONObject leaderboardJSON = LeaderboardToJSON(leaderboardScores.getLeaderboard());
							RunnerJNILib.DsMapAddString(dsMapIndex, "leaderboard", leaderboardJSON.toString());
							RunnerJNILib.DsMapAddString(dsMapIndex, "data", scoresJSON.toString());

							leaderboardScores.release();

						} else {
							Exception exception = task.getException();
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
						}

						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
					}
				});
		return (ind);
	}

	public double GooglePlayServices_UriToPath(String uriString) {
		double ind = getAsyncInd();

		(new Obj_UriToPath()).UriToPath(uriString, ind);

		return (ind);
	}

	private class Obj_UriToPath {
		public void UriToPath(final String uriString, final double ind) {
			if (Build.VERSION.SDK_INT >= 11)
				GetActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Uri uri = Uri.parse(uriString);
						try {
							ImageView mImageView = new ImageView(GetActivity());
							ImageManager mgr = ImageManager.create(GetActivity());
							mgr.loadImage(new OnImageLoadedListener() {
								@Override
								public void onImageLoaded(Uri uri, Drawable drawable, boolean isRequestedDrawable) {
									try {
										int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
										RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_UriToPath");
										RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);

										if (isRequestedDrawable) {
											Bitmap mBitmap = ((BitmapDrawable) drawable).getBitmap();

											ContextWrapper cw = new ContextWrapper(
													GetActivity().getApplicationContext());
											File directory = cw.getDir("profile", Context.MODE_PRIVATE);

											if (!directory.exists())
												directory.mkdir();

											File mypath = new File(directory,
													"thumbnail" + String.valueOf(ind) + ".png");

											String path = mypath.getPath();

											FileOutputStream fos = null;

											fos = new FileOutputStream(mypath);
											mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

											fos.close();

											RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
											RunnerJNILib.DsMapAddString(dsMapIndex, "path", path);

										} else
											RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);

										RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);

									} catch (Exception e) {
										// Log.e("yoyo", "URI2PATH failed: " +e.getMessage());
										int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
										RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_UriToPath");
										RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);
										RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
										RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
									}
								}
							}, uri);
						} catch (Exception e) {
							// Log.e("yoyo", "URI2PATH failed: " + e.getMessage());
							int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
							RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_UriToPath");
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", ind);
							RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
							RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
						}
					}
				});
		}
	}

	// ====================================
	// SavedGames
	// ====================================

	static JSONObject SnapshotMetadataToJSON(SnapshotMetadata snapshotMetadata) {
		JSONObject snapshotMetadataJSON = new JSONObject();

		try {
			snapshotMetadataJSON.put("coverImageAspectRatio", snapshotMetadata.getCoverImageAspectRatio());

			Uri coverImageUri = snapshotMetadata.getCoverImageUri();
			if (coverImageUri != null) {
				snapshotMetadataJSON.put("coverImageUri", coverImageUri.toString());
			}
			String description = snapshotMetadata.getDescription();
			if (description != null) {
				snapshotMetadataJSON.put("description", description);
			}
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
			if (uniqueName != null) {
				snapshotMetadataJSON.put("uniqueName", uniqueName);
			}

		} catch (Exception e) {
			Log.e("yoyo", "SnapshotMetadataToJSON : failed to create SnapshotMetadata json object - " + e.getMessage());
		}

		return snapshotMetadataJSON;
	}

	static JSONObject GameToJSON(Game game) {
		JSONObject gameJSON = new JSONObject();

		try {
			gameJSON.put("areSnapshotsEnabled", game.areSnapshotsEnabled() ? 1 : 0);
			gameJSON.put("achievementTotalCount", game.getAchievementTotalCount());
			gameJSON.put("applicationId", game.getApplicationId());
			gameJSON.put("description", game.getDescription());
			gameJSON.put("developerName", game.getDeveloperName());

			String displayName = game.getDisplayName();
			if (displayName != null) {
				gameJSON.put("displayName", displayName);
			}
			Uri featuredImageUri = game.getFeaturedImageUri();
			if (featuredImageUri != null) {
				gameJSON.put("featuredImageUri", featuredImageUri.toString());
			}

			gameJSON.put("gamepadSupport", game.hasGamepadSupport() ? 1 : 0);

			Uri hiResImageUri = game.getHiResImageUri();
			if (hiResImageUri != null) {
				gameJSON.put("hiResImageUri", hiResImageUri.toString());
			}
			Uri iconImageUri = game.getIconImageUri();
			if (iconImageUri != null) {
				gameJSON.put("iconImageUri", iconImageUri.toString());
			}

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
	private double ind_ShowSavedGamesUI;

	public double GooglePlayServices_SavedGames_ShowSavedGamesUI(String title, double buttonAdd, double buttonDelete,
			double max) {
		ind_ShowSavedGamesUI = getAsyncInd();

		boolean showAddButton = buttonAdd > 0.5;
		boolean showDeleteButton = buttonDelete > 0.5;
		int maxToShow = (int) max;

		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.getSelectSnapshotIntent(title, showAddButton, showDeleteButton, maxToShow)
				.addOnSuccessListener(intent -> {
					try {
						GetActivity().startActivityForResult(intent, RC_SAVED_GAMES);
					} catch (Exception e) {
						Log.e("yoyo", "GooglePlayServices_SavedGames_ShowSavedGamesUI: failed to show save games UI - "
								+ e.getMessage());
					}
				});

		return ind_ShowSavedGamesUI;
	}

	public double GooglePlayServices_SavedGames_CommitAndClose(final String name, final String desc, final String data,
			final String coverImagePath) {
		final double asyncIndex = getAsyncInd();

		Snapshot snapshot = mapSnapshot.get(name);
		if (snapshot == null) {
			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_CommitAndClose");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
			Log.i("yoyo",
					"GooglePlayServices_SavedGames_CommitAndClose : couldn't find snapshot with name '" + name + "'");

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);

		} else {

			try {
				byte[] dataBytes = data.getBytes("UTF_8");
				snapshot.getSnapshotContents().writeBytes(dataBytes);
			} catch (Exception e) {
				Log.i("yoyo",
						"GooglePlayServices_SavedGames_CommitAndClose: Exception while converting data to bytes - "
								+ e.getMessage());
			}

			SnapshotMetadataChange.Builder metadataChangeBuilder = new SnapshotMetadataChange.Builder();

			if (!desc.isEmpty()) {
				metadataChangeBuilder.setDescription(desc);
			}

			if (!coverImagePath.isEmpty()) {
				String localImgPath = GetActivity().getFilesDir() + "/" + coverImagePath;
				File imgFile = new File(localImgPath);

				if (imgFile.exists()) {
					Bitmap coverImageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
					if (coverImageBitmap != null) {
						metadataChangeBuilder.setCoverImage(coverImageBitmap);
					}
				}
			}

			SnapshotMetadataChange metadataChange = metadataChangeBuilder.build();
			SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());

			snapshotsClient.commitAndClose(snapshot, metadataChange).addOnCompleteListener(task -> {

				int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
				RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_CommitAndClose");
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

				if (task.isSuccessful()) {
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
					SnapshotMetadata snapshotMetadata = task.getResult();
					RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotMetadata",
							SnapshotMetadataToJSON(snapshotMetadata).toString());
					mapSnapshot.remove(name);
				} else {
					Exception exception = task.getException();
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
					RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
				}
				RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
			});

		}

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_CommitNew(final String name, final String desc, final String data,
			final String coverImagePath) {
		boolean createIfNotFound = true;
		double conflictPolicy = 1;
		final double asyncIndex = getAsyncInd();

		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.open(name, createIfNotFound, (int) conflictPolicy).addOnCompleteListener(task -> {

			// Task error Verfication
			try {
				task.getResult();
			} catch (Exception e) {
				// Log.e("yoyo", "GooglePlayServices_SavedGames_CommitNew: failed to open the
				// saved game - " + e.getMessage());
				int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
				RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_CommitNew");
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
				RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				return;
			}

			boolean wasSuccessful = task.isSuccessful();

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_CommitNew");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", wasSuccessful ? 1 : 0);

			if (wasSuccessful && !task.getResult().isConflict()) {
				DataOrConflict<Snapshot> dataOrConflict = task.getResult();
				Snapshot snapshot = dataOrConflict.getData();
				mapSnapshot.put(snapshot.getMetadata().getUniqueName(), snapshot);

				try {
					byte[] dataBytes = data.getBytes("UTF-8");
					snapshot.getSnapshotContents().writeBytes(dataBytes);
				} catch (Exception e) {
					Log.i("yoyo", "GooglePlayServices_SavedGames_CommitNew : Failed to write snapshot data - "
							+ e.getMessage());
				}

				SnapshotMetadataChange.Builder metadataChangeBuilder = new SnapshotMetadataChange.Builder();

				if (!desc.isEmpty()) {
					metadataChangeBuilder.setDescription(desc);
				}

				if (!coverImagePath.isEmpty()) {
					String localImgPath = GetActivity().getFilesDir() + "/" + coverImagePath;
					File imgFile = new File(localImgPath);

					if (imgFile.exists()) {
						Bitmap coverImageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
						if (coverImageBitmap != null) {
							metadataChangeBuilder.setCoverImage(coverImageBitmap);
						}
					}
				}

				SnapshotMetadataChange metadataChange = metadataChangeBuilder.build();
				snapshotsClient.commitAndClose(snapshot, metadataChange)
						.addOnCompleteListener(commitTask -> {
							boolean commitWasSuccessful = commitTask.isSuccessful();

							int dsMapIndexCommit = RunnerJNILib.jCreateDsMap(null, null, null);
							RunnerJNILib.DsMapAddString(dsMapIndexCommit, "type",
									"GooglePlayServices_SavedGames_CommitNew");
							RunnerJNILib.DsMapAddDouble(dsMapIndexCommit, "ind", asyncIndex);
							RunnerJNILib.DsMapAddDouble(dsMapIndexCommit, "success",
									commitWasSuccessful ? 1 : 0);

							if (commitWasSuccessful) {
								SnapshotMetadata snapshotMetadata = commitTask.getResult();
								RunnerJNILib.DsMapAddString(dsMapIndexCommit, "snapshotMetadata",
										SnapshotMetadataToJSON(snapshotMetadata).toString());
							} else {
								Exception exception = commitTask.getException();
								Log.i("yoyo",
										"GooglePlayServices_SavedGames_CommitNew: failed to commit and close the saved game - "
												+ exception.getMessage());
							}

							RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndexCommit, EVENT_OTHER_SOCIAL);
						});
			} else {
				Exception exception = task.getException();
				Log.i("yoyo",
						"GooglePlayServices_SavedGames_CommitNew: task failed or conflict - " + exception.getMessage());

			}

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Load(final double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());

		snapshotsClient.load(shouldForceReload).addOnCompleteListener(task -> {

			// Task error Verfication
			try {
				task.getResult();
			} catch (Exception e) {
				// Log.e("yoyo", "GooglePlayServices_SavedGames_Load: failed to load saved games
				// - " + e.getMessage());
				int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
				RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Load");
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
				RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				return;
			}

			boolean wasSuccessful = task.isSuccessful();

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Load");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", wasSuccessful ? 1 : 0);

			if (wasSuccessful) {
				AnnotatedData<SnapshotMetadataBuffer> annotatedData = task.getResult();
				SnapshotMetadataBuffer snapshotMetadataBuffer = annotatedData.get();

				JSONArray snapshots = new JSONArray();
				for (SnapshotMetadata snapshotMetadata : snapshotMetadataBuffer) {
					snapshots.put(SnapshotMetadataToJSON(snapshotMetadata));
				}

				snapshotMetadataBuffer.release();

				RunnerJNILib.DsMapAddString(dsMapIndex, "snapshots", snapshots.toString());
			} else {
				Exception exception = task.getException();
				Log.i("yoyo",
						"GooglePlayServices_SavedGames_Load : failed to load saved games - " + exception.getMessage());
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
				.addOnCompleteListener(task -> {

					// Task error Verfication
					try {
						task.getResult();
					} catch (Exception e) {
						// Log.e("yoyo", "GooglePlayServices_SavedGames_Open: failed to open saved game
						// - " + e.getMessage());
						int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
						RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Open");
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
						RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
						RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
						return;
					}

					boolean wasSuccessful = task.isSuccessful();

					int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
					RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Open");
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", wasSuccessful ? 1 : 0);

					if (wasSuccessful) {
						DataOrConflict<Snapshot> dataOrConflict = task.getResult();
						Snapshot snapshot = dataOrConflict.getData();
						mapSnapshot.put(snapshot.getMetadata().getUniqueName(), snapshot);

						SnapshotMetadata snapshotMetadata = snapshot.getMetadata();
						RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotMetadata",
								SnapshotMetadataToJSON(snapshotMetadata).toString());

						try {
							SnapshotContents snapshotContents = snapshot.getSnapshotContents();
							byte[] dataInBytes = snapshotContents.readFully();
							String dataString = new String(dataInBytes, "UTF-8");
							RunnerJNILib.DsMapAddString(dsMapIndex, "data", dataString);
						} catch (Exception e) {
							Log.i("yoyo",
									"GooglePlayServices_SavedGames_Open : Exception while reading snapshot data - "
											+ e.getMessage());
						}
					} else {
						Exception exception = task.getException();
						Log.i("yoyo", "GooglePlayServices_SavedGames_Open : failed to open saved game - "
								+ exception.getMessage());
					}

					RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
				});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Delete(final String name) {

		Snapshot snapshot = mapSnapshot.get(name);
		if (snapshot == null) {
			return -1;
		}

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		SnapshotMetadata snapshotMetadata = snapshot.getMetadata();

		snapshotsClient.delete(snapshotMetadata).addOnCompleteListener(task -> {

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_Delete");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

			if (task.isSuccessful()) {
				String snapshotID = task.getResult();
				RunnerJNILib.DsMapAddString(dsMapIndex, "snapshotID", snapshotID);
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
			} else {
				Exception exception = task.getException();
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
				RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
			}

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_DiscardAndClose(String name) {

		Snapshot snapshot = mapSnapshot.get(name);
		if (snapshot == null) {
			return -1;
		}

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.discardAndClose(snapshot).addOnCompleteListener(new OnCompleteListener<Void>() {
			@Override
			public void onComplete(@NonNull Task<Void> task) {

				int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
				RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_SavedGames_DiscardAndClose");
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

				if (task.isSuccessful()) {
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);
				} else {
					Exception exception = task.getException();
					RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
					RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
				}

				RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
			}
		});

		return asyncIndex;
	}

	// ====================================
	// PlayerStats
	// ====================================

	public double GooglePlayServices_PlayerStats_LoadPlayerStats(double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;

		PlayGames.getPlayerStatsClient(GetActivity()).loadPlayerStats(shouldForceReload).addOnCompleteListener(task -> {

			int dsMapIndex = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(dsMapIndex, "type", "GooglePlayServices_PlayerStats_LoadPlayerStats");
			RunnerJNILib.DsMapAddDouble(dsMapIndex, "ind", asyncIndex);

			if (task.isSuccessful()) {
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 1);

				AnnotatedData<PlayerStats> annotatedData = task.getResult();
				PlayerStats playerStats = annotatedData.get();

				RunnerJNILib.DsMapAddDouble(dsMapIndex, "AverageSessionLength", playerStats.getAverageSessionLength());
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "DaysSinceLastPlayed", playerStats.getDaysSinceLastPlayed());
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "NumberOfPurchases", playerStats.getNumberOfPurchases());
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "NumberOfSessions", playerStats.getNumberOfSessions());
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "SessionPercentile", playerStats.getSessionPercentile());
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "SpendPercentile", playerStats.getSpendPercentile());
			} else {
				Exception exception = task.getException();
				RunnerJNILib.DsMapAddDouble(dsMapIndex, "success", 0);
				RunnerJNILib.DsMapAddString(dsMapIndex, "error", exception.getMessage());
			}

			RunnerJNILib.CreateAsynEventWithDSMap(dsMapIndex, EVENT_OTHER_SOCIAL);
		});

		return asyncIndex;
	}
}
