/// @description Initialize variables

randomize();

// This is a helper function that will enabled and disable the
// room buttons according to the player being signed in to google play.
function setSignedInMode(enable)
{
	with (Obj_GooglePlayServices_Revoke) locked = !enable;
	
	with (Obj_GooglePlayServices_GotoAchievements) locked = !enable;
	with (Obj_GooglePlayServices_GotoLeaderboards) locked = !enable;
	with (Obj_GooglePlayServices_GotoSavedGames) locked = !enable;
	
	with (Obj_GooglePlayServices_VideoRecording) locked = !enable;
}

// This alarm will delay the check for signin until all buttons are created.
alarm[0] = 2;