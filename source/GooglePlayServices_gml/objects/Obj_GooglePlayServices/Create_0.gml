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
	with (Obj_GooglePlayServices_GotoAchievements) locked = !enable;
	with (Obj_GooglePlayServices_GotoLeaderboards) locked = !enable;
	with (Obj_GooglePlayServices_GotoSavedGames) locked = !enable;
}

setSignedInMode(false);
gpgs_is_authenticated(function(success,authenticated,error){
	show_debug_message($"is authenticated: {{success,authenticated,error}}")
		if(success)
		if(authenticated)
		{
			setSignedInMode(true)
			instance_create_depth(30,100,0,Obj_GooglePlayServices_PlayerStats)
			instance_create_depth(450,110,0,Obj_GooglePlayServices_Player)
		}
		else 
			gpgs_sign_in(function(success,sign_in,error){
					show_debug_message({success,sign_in,error})
				});
	})
