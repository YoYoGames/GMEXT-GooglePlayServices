/// @description Create save slot

// Early exit if locked
if (locked) exit;

// The intent of the object is to create a new save slot.
// So it checks if the number of existing slots exceeds the maximum allowded
// NOTE: this max value can be any value.
if(instance_number(Obj_GooglePlayServices_SavedGames_Slot) < 3)
{
	// After checking that the slot number didn't reach it's maximum value
	// We proceed to asking the user for the description of the new slot
	// This async call will be handled in the 'Obj_GooglePlayServices_SavedData' Async Dialog Event
	var dialog_ind = get_string_async("Description: ", "Slot #0");
	Obj_GooglePlayServices_SavedGames.dialog_ind = dialog_ind;
}

