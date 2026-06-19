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

callback_saved_games_load = function(success,str_snapshots,error)
	{
		// Early exit if the callback "success" flag is not true.
		if(!success) exit
		
		// At this point we just loaded all the game save data we are ready to refresh the UI
		
		// We destroy all previously used Slots and DataObj
		instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);
		instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);
		instance_destroy(Obj_GooglePlayServices_SavedGames_Slot);
		
		// We loop through all the queried snapshots and recreate the new slot data.
		var snapshots = json_parse(str_snapshots);
		var count = array_length(snapshots);
		for(var i = 0; i < count; i++)
		{
			// Snapshots contain the metadata of the queried saved games all properties can
			// be consulted on the providede documentation.
			var snapshot = snapshots[i];
			
			var ins = instance_create_depth(300, 250 + 85*i, depth, Obj_GooglePlayServices_SavedGames_Slot)
			if(variable_struct_exists(snapshot,"coverImageUri"))
				ins.coverImageUri = snapshot.coverImageUri;
			ins.uniqueName = snapshot.uniqueName;
			ins.description = snapshot.description;
			ins.text = snapshot.description;
		}
		
		// We just finished a reload so we are not editing any slot
		// we can set the edit mode to false.
		// This is a helper function defined inside the Create event.
		setSlotEditMode(false);
	}

// Load the current saved games.
// The boolean flagged passed onto the function will force the load.
gpgs_saved_games_load(true,callback_saved_games_load);

