/// @description Open saved game

// Early exit if locked
if (locked) exit;

// This function call will open a save slot and retrieve its information
// It requires the unique name id to be provided and triggers an Async Social
// event callback with the same name "GooglePlayServices_SavedGames_Open"
GooglePlayServices_SavedGames_Open(uniqueName)
