/// @description Delete game slot

// Early exit if locked
if (locked) exit;

// Delete the game slot with a specific name id.
// This method requires the unique name identifier of the slot and deletes it from the
// Google Play Services. Note that this function triggers a Social Async event callback
// of the same name "gpgs_SavedGames_Delete".
gpgs_saved_games_delete(Obj_GooglePlayServices_SavedGames.opened_uniqueName);

