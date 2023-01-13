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
GooglePlayServices_IsAuthenticated()
