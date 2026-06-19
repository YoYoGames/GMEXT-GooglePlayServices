/// @description Open saved game

// Early exit if locked
if (locked) exit;

// This function call will open a save slot and retrieve its information
// It requires the unique name id to be provided and triggers an Async Social
// event callback with the same name "gpgs_SavedGames_Open"
gpgs_saved_games_open(uniqueName,function(success,snapshot_metadata,error){
		with(Obj_GooglePlayServices_SavedGames)
		{
			// Early exit if the callback "success" flag is not true.
			if (!success)
			{
				with(Obj_GooglePlayServices_SavedGames)
					gpgs_saved_games_load(true,callback_saved_games_load);
			
				return;
			}
		
			// Ad this point we successfully queried the save data slot so we get access
			// to its 'metadata' and 'data' to 
			var snapshotMeta = json_parse(snapshot_metadata);			
			opened_uniqueName = snapshotMeta.uniqueName;
			opened_description = snapshotMeta.description;
		
			// We set edit mode to true (this is an helper function defined on the Create event)
			setSlotEditMode(true);
		
			// We can now parse the data from the callback, the saved data will be in the async_load
			// dictionary on a key named "data".
			var data = json_parse(snapshot_data);
		
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
		}
	});	

//Or if you want handle by your self use gpgs_SavedGames_Open_Conflict(). gpgs_SavedGames_Open() uses RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED internally
//gpgs_SavedGames_Open_Conflict(uniqueName,GOOGLE_PLAY_RESOLUTION_POLICY_MANUAL)
