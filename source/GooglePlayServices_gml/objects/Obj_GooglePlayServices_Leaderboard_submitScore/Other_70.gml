
if(async_load[?"type"] == GOOGLE_PLAY_EV_LEADERBOARDS_SUBMIT_SCORE)
if(async_load[?"leaderboardId"] == leaderboard_id)
	show_debug_message("GooglePlayServices_Leaderboard_SubmitScore: " + json_encode(async_load))
