
if(async_load[?"type"] == "GooglePlayServices_Leaderboard_SubmitScore")
if(async_load[?"leaderboardId"] == leaderboard_id)
	show_debug_message("GooglePlayServices_Leaderboard_SubmitScore: " + json_encode(async_load))
