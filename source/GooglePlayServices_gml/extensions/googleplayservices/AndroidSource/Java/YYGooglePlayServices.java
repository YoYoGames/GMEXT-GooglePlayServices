package ${YYAndroidPackageName};

import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.annotation.SuppressLint;

import java.lang.Exception;
import java.lang.Void;
import java.lang.ref.WeakReference;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONException;
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

import com.google.android.gms.games.event.Event;
import com.google.android.gms.games.event.EventBuffer;

import com.google.android.gms.common.images.ImageManager;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.ConnectionResult;

public class YYGooglePlayServices extends RunnerSocial {

	// Activity handler
	private static Activity GetActivity() {
		return RunnerActivity.CurrentActivity;
	}

	// ====================================
	// Base
	// ====================================

	public YYGooglePlayServices() {
		PlayGamesSdk.initialize(GetActivity());
		snapshotHashMap = new HashMap<>();
	}

	public double GooglePlayServices_IsAvailable() {
		return (double) GoogleApiAvailability.getInstance()
				.isGooglePlayServicesAvailable(GetActivity()) == ConnectionResult.SUCCESS ? 1 : 0;
	}

	public double GooglePlayServices_SignIn() {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).signIn()
				.addOnCompleteListener((Task<AuthenticationResult> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_SignIn", asyncIndex);
					if (task.isSuccessful()) {
						AuthenticationResult result = task.getResult();
						map.put("isAuthenticated", result.isAuthenticated()).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	public double GooglePlayServices_IsAuthenticated() {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).isAuthenticated()
				.addOnCompleteListener((Task<AuthenticationResult> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_IsAuthenticated", asyncIndex);
					if (task.isSuccessful()) {
						AuthenticationResult result = task.getResult();
						map.put("isAuthenticated", result.isAuthenticated()).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_RequestServerSideAccess(String serverClientId, double forceRefreshToken) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getGamesSignInClient(GetActivity()).requestServerSideAccess(serverClientId, forceRefreshToken >= 0.5)
				.addOnCompleteListener((Task<String> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_RequestServerSideAccess", asyncIndex);
					if (task.isSuccessful()) {
						map.put("authCode", task.getResult()).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		int dsMapIndex;

		switch (requestCode) {

			case RC_SAVED_GAMES:

				if (data == null) {
					GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_ShowSavedGamesUI_OnExit",
							uiAsyncId);
					map.send();
				} else if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA)) {
					SnapshotMetadata snapshotMetadata = data
							.getParcelableExtra(SnapshotsClient.EXTRA_SNAPSHOT_METADATA);

					if (snapshotMetadata != null) {
						GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen",
								uiAsyncId);
						map.put("snapshotMetadata", snapshotMetadataToJSON(snapshotMetadata));
						map.send();

						GooglePlayServices_SavedGames_Open(snapshotMetadata.getUniqueName());
					}
				} else if (data.hasExtra(SnapshotsClient.EXTRA_SNAPSHOT_NEW)) {
					GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew",
							uiAsyncId);
					map.send();
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
					GMEventData map = GMEventData.create("GooglePlayServices_Player_Current", asyncIndex);
					if (task.isSuccessful()) {
						Player player = task.getResult();
						map.put("player", playerToJSON(player)).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	public void GooglePlayServices_Player_CurrentID() {
		final double asyncIndex = getAsyncInd();
		PlayGames.getPlayersClient(GetActivity()).getCurrentPlayerId()
				.addOnCompleteListener((Task<String> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Player_CurrentID", asyncIndex);
					if (task.isSuccessful()) {
						String playerID = task.getResult();
						map.put("playerID", playerID).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
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

	public double GooglePlayServices_Achievements_Increment(final String achievementId, double steps) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).incrementImmediate(achievementId, (int) steps)
				.addOnCompleteListener((Task<Boolean> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Achievements_Increment", asyncIndex);
					map.put("achievement_id", achievementId);
					if (task.isSuccessful()) {
						map.success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_Reveal(final String achievementId) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).revealImmediate(achievementId)
				.addOnCompleteListener((Task<Void> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Achievements_Reveal", asyncIndex);
					map.put("achievement_id", achievementId);
					if (task.isSuccessful()) {
						map.success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_SetSteps(final String achievementId, double steps) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).setStepsImmediate(achievementId, (int) steps)
				.addOnCompleteListener((Task<Boolean> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Achievements_SetSteps", asyncIndex);
					map.put("achievement_id", achievementId);
					if (task.isSuccessful()) {
						map.success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_Unlock(final String achievementId) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).unlockImmediate(achievementId)
				.addOnCompleteListener((Task<Void> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Achievements_Unlock", asyncIndex);
					map.put("achievement_id", achievementId);
					if (task.isSuccessful()) {
						map.success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Achievements_GetStatus(double forceReload) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getAchievementsClient(GetActivity()).load(forceReload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<AchievementBuffer>> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Achievements_GetStatus", asyncIndex);
					if (task.isSuccessful()) {
						AnnotatedData<AchievementBuffer> annotatedData = task.getResult();
						AchievementBuffer achievementBuffer = annotatedData.get();
						JSONArray achievementJSON = new JSONArray();
						if (achievementBuffer != null) {

							for (Achievement achievement : achievementBuffer) {
								achievementJSON.put(achievementToJSON(achievement));
							}
							achievementBuffer.release();
						}
						map.put("data", achievementJSON).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
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

	public double GooglePlayServices_Leaderboard_SubmitScore(final String leaderboardId, final double score,
			final String scoreTag) {

		final double asyncIndex = getAsyncInd();
		PlayGames.getLeaderboardsClient(GetActivity()).submitScoreImmediate(leaderboardId, (long) score, scoreTag)
				.addOnCompleteListener((Task<ScoreSubmissionData> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Leaderboard_SubmitScore", asyncIndex);
					if (task.isSuccessful()) {
						ScoreSubmissionData result = task.getResult();
						JSONObject reportJSON = scoreReportToJSON(result);
						map.put("leaderboardId", leaderboardId).put("score", score).put("scoreTag", scoreTag)
								.put("report", reportJSON).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double GooglePlayServices_Leaderboard_LoadPlayerCenteredScores(String leaderboardId, double span,
			double leaderboardCollection, double maxResults, double forceReload) {
		final double asyncIndex = getAsyncInd();

		PlayGames.getLeaderboardsClient(GetActivity())
				.loadPlayerCenteredScores(leaderboardId, (int) span, (int) leaderboardCollection, (int) maxResults,
						forceReload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<LeaderboardsClient.LeaderboardScores>> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_Leaderboard_LoadPlayerCenteredScores",
							asyncIndex);
					if (task.isSuccessful()) {
						AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
						LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

						if (leaderboardScores != null) {
							LeaderboardScoreBuffer leaderboardScoreBuffer = leaderboardScores.getScores();
							JSONArray scoresJSON = leaderboardScoreBufferToJSON(leaderboardScoreBuffer);
							leaderboardScoreBuffer.release(); // avoid memory leaks

							JSONObject leaderboardJSON = leaderboardToJSON(leaderboardScores.getLeaderboard());

							map.put("leaderboard", leaderboardJSON).put("data", scoresJSON).success();
						}
					} else {
						map.failure(task.getException());
					}
					map.send();
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
					GMEventData map = GMEventData.create("GooglePlayServices_Leaderboard_LoadTopScores", asyncIndex);
					if (task.isSuccessful()) {
						AnnotatedData<LeaderboardsClient.LeaderboardScores> annotatedData = task.getResult();
						LeaderboardsClient.LeaderboardScores leaderboardScores = annotatedData.get();

						if (leaderboardScores != null) {
							LeaderboardScoreBuffer leaderboardScoreBuffer = leaderboardScores.getScores();
							JSONArray scoresJSON = leaderboardScoreBufferToJSON(leaderboardScoreBuffer);
							leaderboardScoreBuffer.release(); // avoid memory leaks

							JSONObject leaderboardJSON = leaderboardToJSON(leaderboardScores.getLeaderboard());

							map.put("leaderboard", leaderboardJSON).put("data", scoresJSON).success();
						}
					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	// ====================================
	// Uri
	// ====================================

	private static final class UriImageListener
			implements ImageManager.OnImageLoadedListener {

		private static final Map<Double, UriImageListener> liveListeners = new ConcurrentHashMap<>();

		private final double asyncIndex;
		private final WeakReference<Activity> activityRef; // avoid leaks

		static void register(double ind, Activity act, Uri uri) {
			UriImageListener l = new UriImageListener(ind, act);
			liveListeners.put(Double.valueOf(ind), l); // keep reference
			ImageManager.create(act).loadImage(l, uri);
		}

		private UriImageListener(double asyncIndex, Activity act) {
			this.asyncIndex = asyncIndex;
			this.activityRef = new WeakReference<>(act);
		}

		@Override
		public void onImageLoaded(@NonNull Uri uri, Drawable drawable, boolean isRequestedDrawable) {

			runBackground(() -> {
				GMEventData map = GMEventData.create("GooglePlayServices_UriToPath", asyncIndex);

				if (isRequestedDrawable && drawable instanceof BitmapDrawable) {
					try {
						Activity act = Objects.requireNonNull(activityRef.get());
						Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();

						File out = new File(act.getCacheDir(), "thumbnail" + asyncIndex + ".png");
						try (FileOutputStream fos = new FileOutputStream(out)) {
							bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
						}
						map.put("path", out.getPath()).success();
					} catch (Exception e) {
						map.failure(e);
					}
				} else {
					map.failure(null);
				}

				map.send();
				liveListeners.remove(Double.valueOf(asyncIndex)); // release reference
			});
		}
	}

	public double GooglePlayServices_UriToPath(String uriString) {
		final double asyncIndex = getAsyncInd();
		runMain(() -> {
			try {
				Uri uri = Uri.parse(uriString);
				Activity act = GetActivity();
				if (act == null)
					throw new IllegalStateException("No Activity");

				UriImageListener.register(asyncIndex, act, uri);

			} catch (Exception ex) {
				GMEventData.create("GooglePlayServices_UriToPath", asyncIndex).failure(ex).send();
			}
		});
		return asyncIndex;
	}

	// ====================================
	// SavedGames
	// ====================================

	private static final int RC_SAVED_GAMES = 9009;

	private final HashMap<String, Snapshot> snapshotHashMap;
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

	public double GooglePlayServices_SavedGames_CommitAndClose(final String name, final String desc, final String data,
			final String coverImagePath) {

		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null)
			return -1;

		final double asyncIndex = getAsyncInd();
		runBackground(() -> {
			try {
				snapshot.getSnapshotContents().writeBytes(data.getBytes(StandardCharsets.UTF_8));
				SnapshotMetadataChange.Builder b = new SnapshotMetadataChange.Builder();
				fillMetadata(b, desc, coverImagePath);

				runMain(() -> commitSnapshot(snapshot, b.build(), "GooglePlayServices_SavedGames_CommitAndClose",
						asyncIndex));

			} catch (Exception ex) {
				runMain(() -> GMEventData.create("GooglePlayServices_SavedGames_CommitAndClose", asyncIndex)
						.failure(ex)
						.send());
			}
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_CommitNew(final String name, final String desc, final String data,
			final String coverImagePath) {
		final double asyncIndex = getAsyncInd();
		Activity act = GetActivity();
		PlayGames.getSnapshotsClient(act).open(name, /* create */ true, /* conflict policy */ 1)
				.addOnCompleteListener(act,
						(Task<DataOrConflict<Snapshot>> openTask) -> {
							GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_CommitNew", asyncIndex);
							if (!openTask.isSuccessful()) {
								map.failure(openTask.getException()).send();
								return;
							}

							DataOrConflict<Snapshot> dc = openTask.getResult();
							if (dc.isConflict()) {
								map.put("success", false).fail("Found conflict while commiting data").send();
								return;
							}

							final Snapshot snapshot = dc.getData();
							if (snapshot == null) { // should never happen, but be safe
								map.failure(null).send();
								return;
							}

							snapshotHashMap.put(snapshot.getMetadata().getUniqueName(), snapshot);

							/* heavy write -> background */
							runBackground(() -> {
								try {
									snapshot.getSnapshotContents().writeBytes(data.getBytes(StandardCharsets.UTF_8));

									SnapshotMetadataChange.Builder b = new SnapshotMetadataChange.Builder();
									fillMetadata(b, desc, coverImagePath);

									runMain(() -> commitSnapshot(snapshot, b.build(),
											"GooglePlayServices_SavedGames_CommitNew", asyncIndex));

								} catch (Exception ex) {
									map.failure(ex).send();
								}
							});
						});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Load(final double forceReload) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getSnapshotsClient(GetActivity()).load(forceReload >= 0.5)
				.addOnCompleteListener((Task<AnnotatedData<SnapshotMetadataBuffer>> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_Load", asyncIndex);
					if (task.isSuccessful()) {
						AnnotatedData<SnapshotMetadataBuffer> annotatedData = task.getResult();
						SnapshotMetadataBuffer snapshotMetadataBuffer = annotatedData.get();
						JSONArray snapshots = new JSONArray();
						if (snapshotMetadataBuffer != null) {
							for (SnapshotMetadata snapshotMetadata : snapshotMetadataBuffer) {
								snapshots.put(snapshotMetadataToJSON(snapshotMetadata));
							}
							snapshotMetadataBuffer.release();
						}
						map.put("snapshots", snapshots).success();
					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Open(final String name) {
		final double asyncIndex = getAsyncInd();
		PlayGames.getSnapshotsClient(GetActivity()).open(name, /* create */ false, /* conflict policy */ 1)
				.addOnCompleteListener((Task<DataOrConflict<Snapshot>> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_Open", asyncIndex);
					if (task.isSuccessful()) {
						DataOrConflict<Snapshot> dataOrConflict = task.getResult();

						if (!dataOrConflict.isConflict()) {
							Snapshot snapshot = dataOrConflict.getData();

							assert snapshot != null;
							SnapshotMetadata snapshotMetadata = snapshot.getMetadata();
							snapshotHashMap.put(snapshotMetadata.getUniqueName(), snapshot);
							map.put("snapshotMetadata", snapshotMetadataToJSON(snapshotMetadata));

							try {
								SnapshotContents snapshotContents = snapshot.getSnapshotContents();
								byte[] dataInBytes = snapshotContents.readFully();
								String dataString = new String(dataInBytes, StandardCharsets.UTF_8);
								map.put("data", dataString).success();

							} catch (Exception exception) {
								map.failure(exception);
							}
						} else {
							map.put("success", false).fail("Found conflict while commiting data");
						}
					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_Delete(final String name) {
		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null)
			return -1;

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		SnapshotMetadata snapshotMetadata = snapshot.getMetadata();
		snapshotsClient.delete(snapshotMetadata).addOnCompleteListener((Task<String> task) -> {
			GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_Delete", asyncIndex);
			if (task.isSuccessful()) {
				snapshotHashMap.remove(name);
				map.put("snapshotID", snapshotMetadata.getSnapshotId()).success();
			} else {
				map.failure(task.getException());
			}
			map.send();
		});

		return asyncIndex;
	}

	public double GooglePlayServices_SavedGames_DiscardAndClose(String name) {
		final Snapshot snapshot = snapshotHashMap.get(name);
		if (snapshot == null)
			return -1;

		final double asyncIndex = getAsyncInd();
		SnapshotsClient snapshotsClient = PlayGames.getSnapshotsClient(GetActivity());
		snapshotsClient.discardAndClose(snapshot).addOnCompleteListener((Task<Void> task) -> {
			GMEventData map = GMEventData.create("GooglePlayServices_SavedGames_DiscardAndClose", asyncIndex);
			if (task.isSuccessful()) {
				snapshotHashMap.remove(name);
				map.success();
			} else {
				map.failure(task.getException());
			}
			map.send();
		});

		return asyncIndex;
	}

	private void fillMetadata(@NonNull SnapshotMetadataChange.Builder b, @NonNull String desc,
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
			@NonNull String eventType, double asyncIndex) {

		// This might be called in the background so lets be safe
		Activity act = GetActivity();
		if (act == null)
			return;

		PlayGames.getSnapshotsClient(act)
				.commitAndClose(snap, change)
				.addOnCompleteListener(act, (Task<SnapshotMetadata> task) -> {
					GMEventData map = GMEventData.create(eventType, asyncIndex);
					if (task.isSuccessful()) {
						snapshotHashMap.remove(snap.getMetadata().getUniqueName());
						SnapshotMetadata snapshotMetadata = task.getResult();
						map.put("snapshotMetadata", snapshotMetadataToJSON(snapshotMetadata)).success();
					} else {
						map.failure(null);
					}
					map.send();
				});
	}

	// ====================================
	// PlayerStats
	// ====================================

	public double GooglePlayServices_PlayerStats_LoadPlayerStats(double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;

		PlayGames.getPlayerStatsClient(GetActivity()).loadPlayerStats(shouldForceReload)
				.addOnCompleteListener((Task<AnnotatedData<PlayerStats>> task) -> {
					GMEventData map = GMEventData.create("GooglePlayServices_PlayerStats_LoadPlayerStats", asyncIndex);
					if (task.isSuccessful()) {
						AnnotatedData<PlayerStats> annotatedData = task.getResult();
						PlayerStats playerStats = annotatedData.get();
						if (playerStats != null) {
							map.put("AverageSessionLength", playerStats.getAverageSessionLength())
									.put("DaysSinceLastPlayed", playerStats.getDaysSinceLastPlayed())
									.put("NumberOfPurchases", playerStats.getNumberOfPurchases())
									.put("NumberOfSessions", playerStats.getNumberOfSessions())
									.put("SessionPercentile", playerStats.getSessionPercentile())
									.put("SpendPercentile", playerStats.getSpendPercentile())
									.success();
						}
					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	// ====================================
	// Events (not yet connected to the GML side)
	// ====================================

	public double google_play_events_increment_event(String eventId, double incrementAmount) {
		PlayGames.getEventsClient(GetActivity()).increment(eventId, (int) incrementAmount);
		return 0;
	}

	public double google_play_events_load_events(double forceReload) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;

		PlayGames.getEventsClient(GetActivity()).load(shouldForceReload)
				.addOnCompleteListener((Task<AnnotatedData<EventBuffer>> task) -> {
					GMEventData map = GMEventData.create("gps_events_load_events", asyncIndex);

					if (task.isSuccessful()) {
						AnnotatedData<EventBuffer> annotatedData = task.getResult();
						EventBuffer eventBuffer = annotatedData.get();

						JSONArray events = new JSONArray();
						if (eventBuffer != null) {
							for (Event event : eventBuffer) {
								events.put(eventToJSON(event));
							}
							eventBuffer.release();
						}
						map.put("events", events);

					} else {
						map.failure(task.getException());
					}
					map.send();
				});
		return asyncIndex;
	}

	public double google_play_events_load_by_ids(double forceReload, String eventIds) {
		final double asyncIndex = getAsyncInd();
		boolean shouldForceReload = forceReload >= 0.5;

		String[] args = toEventIdArray(eventIds);
		PlayGames.getEventsClient(GetActivity()).loadByIds(shouldForceReload, args)
				.addOnCompleteListener((Task<AnnotatedData<EventBuffer>> task) -> {
					GMEventData map = GMEventData.create("gps_events_load_events", asyncIndex);

					if (task.isSuccessful()) {
						AnnotatedData<EventBuffer> annotatedData = task.getResult();
						EventBuffer eventBuffer = annotatedData.get();

						JSONArray events = new JSONArray();
						if (eventBuffer != null) {
							for (Event event : eventBuffer) {
								events.put(eventToJSON(event));
							}
							eventBuffer.release();
						}
						map.put("events", events);

					} else {
						map.failure(task.getException());
					}
					map.send();
				});

		return asyncIndex;
	}

	private static @NonNull String[] toEventIdArray(@NonNull String raw) {
		raw = raw.trim();

		// Quick check: is it a JSON array? Must start with '[' and end with ']'
		if (raw.length() >= 2 && raw.charAt(0) == '[' && raw.charAt(raw.length() - 1) == ']') {
			try {
				JSONArray json = new JSONArray(raw);
				String[] ids = new String[json.length()];
				for (int i = 0; i < json.length(); i++) {
					ids[i] = json.getString(i);
				}
				// Defensive: fall back to single-ID path if array was empty
				if (ids.length > 0)
					return ids;
			} catch (JSONException ignore) {
				// fall through – we’ll treat it as a single ID
			}
		}

		// Fallback: treat input as a single ID
		return new String[] { raw };
	}

	// ====================================
	// EventData Builder
	// ====================================

	static final class GMEventData {

		private static final int EVENT_OTHER_SOCIAL = 70; // same constant
		private static final String TAG = "yoyo";

		private final int id;

		/** Human-readable for debugging only. */
		private final double requestId;
		private final String requestType;

		/** Guard that guarantees the map is sent exactly once. */
		private final AtomicBoolean dispatched = new AtomicBoolean(false);

		private GMEventData(String type, double ind) {
			this.requestId = ind;
			this.requestType = type;

			id = RunnerJNILib.jCreateDsMap(null, null, null);
			RunnerJNILib.DsMapAddString(id, "type", type);
			RunnerJNILib.DsMapAddDouble(id, "ind", ind);
		}

		/* ---------- Static factory ---------- */

		public static GMEventData create(String type, double ind) {
			return new GMEventData(type, ind);
		}

		/* ---------- Fluent setters ---------- */

		GMEventData put(String key, String value) {
			RunnerJNILib.DsMapAddString(id, key, value);
			return this;
		}

		GMEventData put(String key, double value) {
			RunnerJNILib.DsMapAddDouble(id, key, value);
			return this;
		}

		GMEventData put(String key, boolean value) {
			RunnerJNILib.DsMapAddDouble(id, key, value ? 1 : 0);
			return this;
		}

		GMEventData put(String key, JSONObject value) {
			RunnerJNILib.DsMapAddString(id, key, value.toString());
			return this;
		}

		GMEventData put(String key, JSONArray value) {
			RunnerJNILib.DsMapAddString(id, key, value.toString());
			return this;
		}

		GMEventData success() {
			return put("success", 1);
		}

		GMEventData failure(@Nullable Throwable ex) {
			put("success", 0);
			put("error", ex != null ? ex.getLocalizedMessage() : "unknown");
			if (ex != null)
				Log.e(TAG, "Task failed", ex);
			return this;
		}

		GMEventData fail(String msg) {
			return failure(new RuntimeException(msg));
		}

		GMEventData fail() {
			return failure(null);
		}

		/* ---------- Final dispatch ---------- */

		void send() {
			if (dispatched.compareAndSet(false, true)) {
				RunnerJNILib.CreateAsynEventWithDSMap(id, EVENT_OTHER_SOCIAL);
			} else {
				Log.w(TAG, "GMEventData for type='" + requestType + "', ind=" + requestId
						+ " already dispatched--ignored");
			}
		}
	}

	// ====================================
	// Async Helpers
	// ====================================

	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	private final AtomicInteger asyncInd = new AtomicInteger();

	private int getAsyncInd() {
		return asyncInd.incrementAndGet();
	}

	private static void runBackground(Runnable r) {
		executorService.execute(r);
	}

	private static void runMain(Runnable r) {
		Activity a = GetActivity();
		if (a != null)
			a.runOnUiThread(r);
	}

	// ====================================
	// JSON Helpers
	// ====================================

	@NonNull
	private static JSONObject playerToJSON(Player player) {

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
			Log.e("yoyo", "playerToJSON : failed to create Player json object - " + e.getMessage());
		}

		return playJSON;
	}

	@NonNull
	private static JSONObject achievementToJSON(Achievement achievement) {

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
			Log.e("yoyo", "achievementToJSON : failed to create Achievement json object - " + e.getMessage());
		}

		return achievementJSON;
	}

	@NonNull
	private static JSONObject snapshotMetadataToJSON(SnapshotMetadata snapshotMetadata) {
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

			snapshotMetadataJSON.put("game", gameToJSON(snapshotMetadata.getGame()));
			snapshotMetadataJSON.put("hasChangePending", snapshotMetadata.hasChangePending() ? 1 : 0);
			snapshotMetadataJSON.put("lastModifiedTimestamp", snapshotMetadata.getLastModifiedTimestamp());
			snapshotMetadataJSON.put("owner", playerToJSON(snapshotMetadata.getOwner()));
			snapshotMetadataJSON.put("playedTime", snapshotMetadata.getPlayedTime());
			snapshotMetadataJSON.put("progressValue", snapshotMetadata.getProgressValue());

			String uniqueName = snapshotMetadata.getUniqueName();
			snapshotMetadataJSON.put("uniqueName", uniqueName);

		} catch (Exception e) {
			Log.e("yoyo", "snapshotMetadataToJSON : failed to create SnapshotMetadata json object - " + e.getMessage());
		}

		return snapshotMetadataJSON;
	}

	/** @noinspection ConstantValue */
	@NonNull
	private static JSONObject gameToJSON(Game game) {
		JSONObject gameJSON = new JSONObject();

		try {
			gameJSON.put("areSnapshotsEnabled", game.areSnapshotsEnabled() ? 1 : 0);
			gameJSON.put("achievementTotalCount", game.getAchievementTotalCount());
			gameJSON.put("applicationId", game.getApplicationId());
			gameJSON.put("description", game.getDescription());
			gameJSON.put("developerName", game.getDeveloperName());
			gameJSON.put("displayName", game.getDisplayName());

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

			String primaryCategory = game.getPrimaryCategory();
			if (primaryCategory != null) {
				gameJSON.put("primaryCategory", primaryCategory);
			}

			String secondaryCategory = game.getSecondaryCategory();
			if (secondaryCategory != null) {
				gameJSON.put("secondaryCategory", secondaryCategory);
			}

			gameJSON.put("themeColor", game.getThemeColor());

		} catch (Exception e) {
			Log.e("yoyo", "gameToJSON : failed to create Game json object - " + e.getMessage());
		}

		return gameJSON;
	}

	private static final Map<Integer, String> TIME_SPANS;
	static {
		Map<Integer, String> tmp = new LinkedHashMap<>(3); // keeps insertion order
		tmp.put(Integer.valueOf(LeaderboardVariant.TIME_SPAN_DAILY), "daily");
		tmp.put(Integer.valueOf(LeaderboardVariant.TIME_SPAN_WEEKLY), "weekly");
		tmp.put(Integer.valueOf(LeaderboardVariant.TIME_SPAN_ALL_TIME), "allTime");
		TIME_SPANS = Collections.unmodifiableMap(tmp);
	}

	@NonNull
	private static JSONObject scoreReportToJSON(ScoreSubmissionData data) {
		JSONObject report = new JSONObject();

		// Iterate once instead of copy/paste-3-times
		for (Map.Entry<Integer, String> e : TIME_SPANS.entrySet()) {
			ScoreSubmissionData.Result spanResult = data.getScoreResult(e.getKey());
			addPeriodJSON(report, e.getValue(), spanResult);
		}
		return report;
	}

	private static void addPeriodJSON(JSONObject root, String label, ScoreSubmissionData.Result r) {
		if (r == null)
			return;

		try {
			JSONObject periodJSON = new JSONObject();
			periodJSON.put("isNewBest", r.newBest ? 1 : 0);
			periodJSON.put("score", (double) r.rawScore);
			periodJSON.put("scoreTag", r.scoreTag);
			root.put(label, periodJSON);
		} catch (Exception e) {
			Log.e("yoyo", "addPeriodJSON: JSON error", e);
		}
	}

	@NonNull
	private static JSONArray leaderboardScoreBufferToJSON(LeaderboardScoreBuffer leaderboardScoreBuffer) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/LeaderboardScore
		JSONArray scoresJSON = new JSONArray();

		for (LeaderboardScore leaderboardScore : leaderboardScoreBuffer) {
			scoresJSON.put(leaderboardScoreToJSON(leaderboardScore));
		}

		return scoresJSON;
	}

	@NonNull
	private static JSONObject leaderboardScoreToJSON(LeaderboardScore leaderboardScore) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/LeaderboardScore
		JSONObject leaderboardScoreJSON = new JSONObject();

		try {
			leaderboardScoreJSON.put("displayRank", leaderboardScore.getDisplayRank());
			leaderboardScoreJSON.put("displayScore", leaderboardScore.getDisplayScore());
			leaderboardScoreJSON.put("rank", (double) leaderboardScore.getRank());
			leaderboardScoreJSON.put("rawScore", (double) leaderboardScore.getRawScore());

			Player scoreHolder = leaderboardScore.getScoreHolder();
			if (scoreHolder != null) {
				leaderboardScoreJSON.put("scoreHolder", playerToJSON(scoreHolder));
			}

			leaderboardScoreJSON.put("scoreHolderDisplayName", leaderboardScore.getScoreHolderDisplayName());
			leaderboardScoreJSON.put("scoreHolderHiResImageUri",
					leaderboardScore.getScoreHolderHiResImageUri().toString());

			leaderboardScoreJSON.put("scoreHolderIconImageUri",
					leaderboardScore.getScoreHolderIconImageUri().toString());

			leaderboardScoreJSON.put("scoreTag", leaderboardScore.getScoreTag());
			leaderboardScoreJSON.put("timestampMillis", (double) leaderboardScore.getTimestampMillis());

		} catch (Exception e) {
			Log.e("yoyo", "leaderboardScoreToJSON : failed to create LeaderboardScore json object - " + e.getMessage());
		}
		return leaderboardScoreJSON;
	}

	@NonNull
	private static JSONObject leaderboardToJSON(Leaderboard leaderboard) {
		// https://developers.google.com/android/reference/com/google/android/gms/games/leaderboard/Leaderboard
		JSONObject leaderboardJSON = new JSONObject();
		try {
			leaderboardJSON.put("displayName", leaderboard.getDisplayName());
			leaderboardJSON.put("iconImageUri", leaderboard.getIconImageUri().toString());
			leaderboardJSON.put("leaderboardId", leaderboard.getLeaderboardId());
			leaderboardJSON.put("scoreOrder", leaderboard.getScoreOrder());

		} catch (Exception e) {
			Log.e("yoyo", "leaderboardToJSON : failed to create Leaderboard json object - " + e.getMessage());
		}
		return leaderboardJSON;
	}

	@SuppressLint("VisibleForTests")
	@NonNull
	private static JSONObject eventToJSON(Event event) {
		JSONObject eventJSON = new JSONObject();
		try {
			eventJSON.put("event_id", event.getEventId());
			eventJSON.put("description", event.getDescription());
			eventJSON.put("formatted_value", event.getFormattedValue());
			eventJSON.put("icon_image_uri", event.getIconImageUri());
			eventJSON.put("name", event.getName());
			eventJSON.put("player", event.getPlayer());
			eventJSON.put("value", event.getValue());
			eventJSON.put("is_visible", event.isVisible());
		} catch (Exception e) {
			Log.e("yoyo", "eventToJSON : failed to create Event json object - " + e.getMessage());
		}

		return eventJSON;
	}
}