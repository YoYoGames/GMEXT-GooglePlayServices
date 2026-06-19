/// @description Show leaderboard

var newScore = irandom_range(1, 100000);

show_message_async("Your Score: " + string(newScore));

// This function submits a new value to the leaderboard with a given id and allows to set a tag.
// This id is the leaderboard unique identifier string.
gpgs_leaderboard_submit_score(leaderboard_id, newScore, "test",function(success,leaderboard_id,_score,score_tag,score_report,error){
				show_debug_message({success,leaderboard_id,_score,score_tag,score_report,error})
			});

