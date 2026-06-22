/// @description Close the local editor without committing

if (locked) exit;

// The current extension API has no discard-and-close native function.
// This only closes the local editor. Reopening the slot reloads persisted data.
with (Obj_GooglePlayServices_SavedGames)
{
    close_local_slot();
    gpgs_saved_games_load(true, callback_saved_games_load);
}
