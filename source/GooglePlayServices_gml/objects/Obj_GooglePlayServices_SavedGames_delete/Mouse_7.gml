/// @description Delete opened save slot

if (locked) exit;

var _name = Obj_GooglePlayServices_SavedGames.opened_unique_name;

gpgs_saved_games_delete(_name, function(_success, _deleted_name, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    with (Obj_GooglePlayServices_SavedGames)
    {
        close_local_slot();
        gpgs_saved_games_load(true, callback_saved_games_load);
    }
});
