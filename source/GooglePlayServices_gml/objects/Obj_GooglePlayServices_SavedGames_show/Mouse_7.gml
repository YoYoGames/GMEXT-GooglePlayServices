/// @description Show SavedGamesUI

// Earlt exit on locked
if (locked) exit;

// This function call will show the Google Play Services, Saved Games UI popup.
// You can:
// - give it a title
// - allow add
// - allow delete
// - provide a max number of allowed save slots
GooglePlayServices_SavedGames_ShowSavedGamesUI("YoYo Games SavedGames", true, true, 3);
