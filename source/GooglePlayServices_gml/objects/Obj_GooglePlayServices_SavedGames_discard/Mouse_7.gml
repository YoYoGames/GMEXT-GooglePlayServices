/// @description Discard changes

// Early exit if locked
if (locked) exit;

// Discard the changes and close save slot with a specific name id.
// This method requires the unique name identifier of the slot and closes it without
// commiting changes. Note that this function triggers a Social Async event callback
// of the same name "gpgs_SavedGames_DiscardAndClose".
gpgs_saved_games_discard_and_close(Obj_GooglePlayServices_SavedGames.opened_uniqueName);
