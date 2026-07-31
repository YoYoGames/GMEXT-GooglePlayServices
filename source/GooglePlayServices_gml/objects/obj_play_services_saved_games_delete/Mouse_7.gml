/// @description Delete opened save slot

if (locked) exit;

var _name = obj_play_services_saved_games.opened_unique_name;

play_services_saved_games_delete(_name, function(_status)
{
    if (!_status.success)
    {
        show_debug_message(_status.error);
        return;
    }

    with (obj_play_services_saved_games)
    {
        close_local_slot();
        play_services_saved_games_load(true, callback_saved_games_load);
    }
});
