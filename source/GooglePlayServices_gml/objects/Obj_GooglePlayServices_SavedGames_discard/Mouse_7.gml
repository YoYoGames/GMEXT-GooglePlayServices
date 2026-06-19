/// @description Discard changes

// Early exit if locked
if (locked) exit;

// Discard the changes and close save slot with a specific name id.
// This method requires the unique name identifier of the slot and closes it without
// commiting changes. Note that this function triggers a Social Async event callback
// of the same name "gpgs_SavedGames_DiscardAndClose".
gpgs_saved_games_discard_and_close(Obj_GooglePlayServices_SavedGames.opened_uniqueName,function(){
	with(Obj_GooglePlayServices_SavedGames)
	{
		// Early exit if the callback "success" flag is not true.
		if (!async_load[? "success"]) exit;

		// At this point we just discarded the current changes.
	
		// We can now clean the objects used during slot edit mode
		instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj)	
		instance_destroy(Obj_GooglePlayServices_SavedGames_Icon)
		
		// We clear the local variables for opened slot
		opened_uniqueName = "";
		opened_description = "";
		
		// We set the slot edit mode to false
		// This is a helper function defined inside the Create event.
		setSlotEditMode(false);
	}
});
