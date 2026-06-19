/// @description Open saved game

// Early exit if locked
if (locked) exit;

// This function call will open a save slot and retrieve its information
// It requires the unique name id to be provided and triggers an Async Social
// event callback with the same name "gpgs_SavedGames_Open"
gpgs_saved_games_open(uniqueName);	
//Or if you want handle by your self use gpgs_SavedGames_Open_Conflict(). gpgs_SavedGames_Open() uses RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED internally
//gpgs_SavedGames_Open_Conflict(uniqueName,GOOGLE_PLAY_RESOLUTION_POLICY_MANUAL)
