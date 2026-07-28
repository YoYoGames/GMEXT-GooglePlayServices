/// @description Initialize variables

// ############### LEADERBOARDS ###############

#macro Leaderboard1 "CgkI9bH92usSEAIQBA"
#macro Leaderboard2 "CgkI9bH92usSEAIQBQ"

// ############### ACHIEVEMENTS ###############

// Incremental achievement
#macro Achievement1 "CgkI9bH92usSEAIQAQ"

// Hidden achievement
#macro Achievement2 "CgkI9bH92usSEAIQAg"

// Achievement locked (unlock example)
#macro Achievement3 "CgkI9bH92usSEAIQAw"


// Code start here!!
randomize();

// This is a helper function that will enabled and disable the
// room buttons according to the player being signed in to google play.
function setSignedInMode(enable)
{	
	with (obj_play_services_go_to_achievements) locked = !enable;
	with (obj_play_services_go_to_leaderboards) locked = !enable;
	with (obj_play_services_go_to_saved_games) locked = !enable;
}

setSignedInMode(false);
play_services_is_authenticated(function(_result){
	show_debug_message(_result);
	if(_result.success)
	{
		if(_result.is_authenticated)
		{
			setSignedInMode(true);
			instance_create_depth(30,100,0,obj_play_services_player_stats);
			instance_create_depth(450,110,0,obj_play_services_player);
		}
		else
		{
			play_services_sign_in(function(_signin_result){
				show_debug_message(_signin_result);
			});
		}
	}
});
