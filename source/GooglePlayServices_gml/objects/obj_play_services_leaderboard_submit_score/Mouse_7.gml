/// @description Show leaderboard

var newScore = irandom_range(1, 100000);

show_message_async("Your Score: " + string(newScore));

// This function submits a new value to the leaderboard with a given id and allows to set a tag.
// This id is the leaderboard unique identifier string.
play_services_leaderboard_submit_score_with_tag(leaderboard_id, newScore, "test", function(_status, _report){
	show_debug_message(_status);
	show_debug_message(_report);
});

