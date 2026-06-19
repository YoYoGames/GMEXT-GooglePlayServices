/// @description Initialize variables

// ############### LEADERBOARDS ###############

#macro Leaderboard1 "CggI47itmU0QAhAD"
#macro Leaderboard2 "CggI47itmU0QAhAE"

// ############### ACHIEVEMENTS ###############

// Incremental achievement
#macro Achievement1 "CggI47itmU0QAhAC"

// Hidden achievement
#macro Achievement2 "CggI47itmU0QAhAF"

// Achievement locked (unlock example)
#macro Achievement3 "CggI47itmU0QAhAB"


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
			//instance_create_depth(30,100,0,Obj_GooglePlayServices_PlayerStats)
			//instance_create_depth(450,110,0,Obj_GooglePlayServices_Player)
			
		}
		else 
			gpgs_sign_in(function(success,sign_in,error){
					show_debug_message({success,sign_in,error})
				});
	})
