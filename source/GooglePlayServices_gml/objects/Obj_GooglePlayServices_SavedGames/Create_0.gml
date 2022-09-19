/// @description Initialize variables

// Opened slot name/description
opened_uniqueName = ""
opened_description = ""

// Dialog async identifier
dialog_ind = -1;

// This is an helper function for setting the application in slot edit
// mode this function will lock and unlock the respective buttons.
function setSlotEditMode(enable)
{
	// In slot edit mode the Save/Delete/Discard buttons should be unlocked
	with (Obj_GooglePlayServices_SavedGames_Save) locked = !enable;
	with (Obj_GooglePlayServices_SavedGames_Delete) locked = !enable;
	with (Obj_GooglePlayServices_SavedGames_Discard) locked = !enable;
	
	// The other buttons should be locked
	with (Obj_GooglePlayServices_SavedGames_New) locked = enable;
	with (Obj_GooglePlayServices_SavedGames_Show) locked = enable;
	with (Obj_GooglePlayServices_SavedGames_Slot)
	{
		locked = enable;
	}
}

// Load the current saved games.
// The boolean flagged passed onto the function will force the load.
GooglePlayServices_SavedGames_Load(true);

