/// @description Close the local editor without committing

if (locked) exit;

// The current extension API has no discard-and-close native function.
// This only closes the local editor. Reopening the slot reloads persisted data.
with (obj_play_services_saved_games)
{
    close_local_slot();
    play_services_saved_games_load(true, callback_saved_games_load);
}
