/// @description Create save slot

// Early exit if locked
if (locked) exit;

// The intent of the object is to create a new save slot.
// So it checks if th	e number of existing slots exceeds the maximum allowded
// NOTE: this max value can be any value.
if(instance_number(obj_play_services_saved_games_slot) < 3)
{
	// After checking that the slot number didn't reach it's maximum value
	// We proceed to asking the user for the description of the new slot
	// This async call will be handled in the 'obj_play_services_saved_data' Async Dialog Event
	var dialog_ind = get_string_async("Description: ", "Slot #0");
	obj_play_services_saved_games.dialog_ind = dialog_ind;
}

