/// @description Save slot

// Early exit if locked
if (locked) exit;

// We create a temporary path for the thumbnail to be saved to.
var path = "save_thumbnail.png"

// We have this thumbnail to the given path
var sprite = Obj_GooglePlayServices_SavedGames_Icon.sprite_index;
var subimage = Obj_GooglePlayServices_SavedGames_Icon.image_index;
sprite_save_w(sprite, subimage, path);

// Now we need to handle the DataObjs for that we loop through them and
// store position and image_index information into a struct and push
// that struct into an array.
var objsArray = [];
with(Obj_GooglePlayServices_SavedGames_DataObj)
{
	var objData = {};
	objData.x = x;
	objData.y = y;
	objData.image_index = image_index;
		
	array_push(objsArray, objData);
}

// We pack the final game data using the icon information and the objects information.
var data = {};
data.objs = objsArray;
data.icon_index = Obj_GooglePlayServices_SavedGames_Icon.image_index;

// We need to convert the data to string
var jsonData = json_stringify(data);

//We get the currently opened slot uniqueName and description
var name = Obj_GooglePlayServices_SavedGames.opened_uniqueName;
var description = Obj_GooglePlayServices_SavedGames.opened_description;

// Finally we can commit the changes and close the slot using the function
// below the requires the 'name' (unique id) the 'description' tag, the 'jsonData'
// in string format (it can be any data as long as it is a string) and the path to the
// thumbnail being used for the save.
// This function call will trigger an Social Async event callback of the same name
// "GooglePlayServices_SavedGames_CommitAndClose"

GooglePlayServices_SavedGames_CommitAndClose(name, description, jsonData, path);
