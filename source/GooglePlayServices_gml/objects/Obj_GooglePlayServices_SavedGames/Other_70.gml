/// @description Sloat loading/editing

switch(async_load[? "type"])
{
	// @triggered by the button (+) button on the ShowSavedGamesUI
	case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew":
		// At this point we want to create a new slot so we request the user for a new
		// description name and handle slot creation under the Async Dialog event.
		dialog_ind = get_string_async("Description: ","Slot #0");
		break;
	
	// @triggered by the button (Select) button on the ShowSavedGamesUI
	case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen":
		
		// At point we just requested a slot to be opened.
		// The selected slot (snapshot) metadata is returned inside the 'snapshotMetadata' key
		// amough other properties we have access to the 'uniqueName' identifier that we need
		// to call the function 'GooglePlayServices_SavedGames_Open' to query the saved data.
		var snapshotMeta = json_parse(async_load[? "snapshotMetadata"]);
		var uniqueName = snapshotMeta.uniqueName;
		
		// This function uses the unique identifier name of the slot to query its data.
		// Note that this will trigger an Async Social event - "GooglePlayServices_SavedGames_Open"
		GooglePlayServices_SavedGames_Open(uniqueName);	
		break;
	
	// @triggered by closing the ShowSavedGamesUI
	case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnExit":
		// At this point we just closed the ShowSavedGames google play UI.
		// We use this event to reload all the saves slots to properly display them
		// since we could have deleted a slot through the 'ShowSavedGamesUI'.
		GooglePlayServices_SavedGames_Load(true);
		break;
	
	// @triggered by GooglePlayServices_SavedGames_CommitNew()
	case "GooglePlayServices_SavedGames_CommitNew":
		
		// Early exit if the callback "success" flag is not true.
		if (!async_load[? "success"]) exit;

		// Upon creating a new game save slot we refresh the current UI
		// by destroying all previous slots and reloading the data.
		instance_destroy(Obj_GooglePlayServices_SavedGames_Slot);
		GooglePlayServices_SavedGames_Load(true);
		break;

	// @triggered by GooglePlayServices_SavedGames_Load()
	case "GooglePlayServices_SavedGames_Load":
		
		// Early exit if the callback "success" flag is not true.
		if(!async_load[?"success"]) exit
		
		// At this point we just loaded all the game save data we are ready to refresh the UI
		
		// We destroy all previously used Slots and DataObj
		instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);
		instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);
		instance_destroy(Obj_GooglePlayServices_SavedGames_Slot);
		
		// We loop through all the queried snapshots and recreate the new slot data.
		var snapshots = json_parse(async_load[?"snapshots"]);
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
		break;

	// @triggered by GooglePlayServices_SavedGames_Open()
	case "GooglePlayServices_SavedGames_Open":
		
		// Early exit if the callback "success" flag is not true.
		if (!async_load[? "success"])
		{
			GooglePlayServices_SavedGames_Load(true);
			return;
		}
		
		// Ad this point we successfully queried the save data slot so we get access
		// to its 'metadata' and 'data' to 
		var snapshotMeta = json_parse(async_load[? "snapshotMetadata"]);			
		opened_uniqueName = snapshotMeta.uniqueName;
		opened_description = snapshotMeta.description;
		
		// We set edit mode to true (this is an helper function defined on the Create event)
		setSlotEditMode(true);
		
		// We can now parse the data from the callback, the saved data will be in the async_load
		// dictionary on a key named "data".
		var data = json_parse(async_load[? "data"]);
		
		// We destroy previously exiting Icon and DataObj if existing.
		instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);
		instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);
		
		// We create a new icon with the 'image_index' set to the store icon_index (inside the saved data)
		var ins = instance_create_depth(room_width - 120, 150, depth, Obj_GooglePlayServices_SavedGames_Icon);
		ins.image_index = data.icon_index;
				
		// We now looped through all object related data (inside the saved data) and created the respective
		// 'DataObj' with the corresponding position and image_index. 
		var objects = data.objs;
		var count = array_length(objects);
		for (var i = 0; i < count; i++) {
			var properties = objects[i];
			
			var ins = instance_create_depth(properties.x, properties.y, depth, Obj_GooglePlayServices_SavedGames_DataObj);
			ins.image_index = properties.image_index;
		}
		break;
	
	// @triggered by GooglePlayServices_SavedGames_Delete()
	case "GooglePlayServices_SavedGames_Delete":
	// @triggered by GooglePlayServices_SavedGames_CommitAndClose()
	case "GooglePlayServices_SavedGames_CommitAndClose":

		// Early exit if the callback "success" flag is not true.
		if(!async_load[? "success"]) exit;

		// At this point we successfully commited data or deleted a slot.
		// We will proceed to refresh the UI but since Google Play Services can have some delay
		// we should place the reload call in an alarm to ensure the action has completly finished.
		//
		// NOTE: This shouldn't be necessary but it's a bug from Google side
		alarm[0] = 90;
		opened_uniqueName = "";
		opened_description = "";
		break;
	
	// @triggered by GooglePlayServices_SavedGames_DiscardAndClose()
	case "GooglePlayServices_SavedGames_DiscardAndClose":
	
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
		
	break

}

