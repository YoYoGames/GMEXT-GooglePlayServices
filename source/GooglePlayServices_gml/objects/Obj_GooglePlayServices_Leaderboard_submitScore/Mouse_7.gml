/// @description Show leaderboard

var newScore = irandom_range(1, 100000);

show_message_async("Your Score: " + string(newScore));

// This function submits a new value to the leaderboard with a given id and allows to set a tag.
// This id is the leaderboard unique identifier string.
GooglePlayServices_Leaderboard_SubmitScore(leaderboard_id, newScore, "test");

