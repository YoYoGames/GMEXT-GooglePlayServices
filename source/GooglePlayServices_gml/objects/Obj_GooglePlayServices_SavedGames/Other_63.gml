/// @description Handle dialog callback

// Early exit if sync identification doesn't match
if (async_load[?"id"] != dialog_ind) exit;

// Early exit if status if false (Cancel button was pressed)
if (!async_load[?"status"]) exit;

// Early exit if result is empty string.
if (async_load[?"result"] == "") exit;

// Temporary path to save the saved game icon.
var path = "save_thumbnail.png"

sprite_save_w(Spr_GooglePlayServices_SavedGames, 0, path);
			
// Create initial position/image data to save
var objsArray = [];	
for (var i = 0 ; i < 3; i++)
{
	var objData = {};
	objData.x = 700 + i*125;
	objData.y = 250;
	objData.image_index = 0;
		
	array_push(objsArray, objData);
}

// Pack the data into a struct with object data and default icon index
var data = {};
data.objs = objsArray;
data.icon_index = 0;

// Convert the struct into a string to be saved
var jsonData = json_stringify(data);

// Generate a unique slot identifier name
// This is not the perfect way for generating unique identifiers
// Use your own unique id generation logic.
var name = "slotUniqueName_" + string(irandom_range(0, 99999)) // This should be unique

// Get the description from the dialog result
var description = async_load[? "result"];

// Create a new save game commit, this function allows the developer to commit a new data slot to be saved.
// TYhe user can pass in a: 
// - name: an unique slot identification id
// - description: this is a tag that appears when showing saved games using 'GooglePlayServices_SavedGames_ShowSavedGamesUI'
// - jsonData: The data being saved to the slot (this must be a string and should probably be a json)
// - path: the path for the save game thumbnail image.
// This function triggers a Social Async callback (check: "GooglePlayServices_SavedGames_CommitNew")
GooglePlayServices_SavedGames_CommitNew(name, description, jsonData, path);

