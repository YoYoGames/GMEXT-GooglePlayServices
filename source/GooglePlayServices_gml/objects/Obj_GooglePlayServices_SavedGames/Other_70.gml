/// @description Sloat loading/editing

switch(async_load[? "type"])
{
	// @triggered by the button (+) button on the ShowSavedGamesUI
	case GOOGLE_PLAY_EV_SAVEDGAMES_UI_NEW:
		// At this point we want to create a new slot so we request the user for a new
		// description name and handle slot creation under the Async Dialog event.
		dialog_ind = get_string_async("Description: ","Slot #0");
		break;
	
	// @triggered by the button (Select) button on the ShowSavedGamesUI
	case GOOGLE_PLAY_EV_SAVEDGAMES_UI_OPEN:
		
		// At point we just requested a slot to be opened.
		// The selected slot (snapshot) metadata is returned inside the 'snapshotMetadata' key
		// amough other properties we have access to the 'uniqueName' identifier that we need
		// to call the function 'gpgs_SavedGames_Open' to query the saved data.
		var snapshotMeta = json_parse(async_load[? "snapshotMetadata"]);
		var uniqueName = snapshotMeta.uniqueName;
		
		// This function uses the unique identifier name of the slot to query its data.
		// Note that this will trigger an Async Social event - "gpgs_SavedGames_Open"
		gpgs_saved_games_open(uniqueName);	
		break;
	
	// @triggered by closing the ShowSavedGamesUI
	case GOOGLE_PLAY_EV_SAVEDGAMES_UI_EXIT:
		// At this point we just closed the ShowSavedGames google play UI.
		// We use this event to reload all the saves slots to properly display them
		// since we could have deleted a slot through the 'ShowSavedGamesUI'.
		with(Obj_GooglePlayServices_SavedGames)
			gpgs_saved_games_load(true,callback_saved_games_load);
		break;
	



		
	case GOOGLE_PLAY_EV_SAVEDGAMES_RESOLVE_CONFLICT:
	break

	case GOOGLE_PLAY_EV_SAVEDGAMES_OPEN_CONFLICT:
		
		if(async_load[?"isConflict"])
		{
			show_message_async("isConflict")
			
			var conflictId = async_load[?"conflictId"]
			var snapshotMetadataLocal = async_load[?"snapshotMetadataLocal"]
			var dataLocal = async_load[?"dataLocal"]
			var snapshotMetadataRemote = async_load[?"snapshotMetadataRemote"]
			var dataRemote = async_load[?"dataRemote"]
			
			//take the choose using previosly information... in this exmaple we will select GOOGLE_PLAY_SNAPSHOT_REMOTE
			var resolution = GOOGLE_PLAY_SNAPSHOT_REMOTE //or GOOGLE_PLAY_SNAPSHOT_LOCAL
			
			gpgs_saved_games_resolve_conflict(conflictId,resolution)
			
			break
		}
		else
		{
			show_message_async("NOT Conflict")
			//break //contining like we do in GOOGLE_PLAY_EV_SAVEDGAMES_OPEN		
		}

}

