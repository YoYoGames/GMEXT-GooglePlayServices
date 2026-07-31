player_stats = {};

play_services_player_stats_load(true, function(_status, _stats)
{
    show_debug_message("play_services_player_stats_load");
    show_debug_message(_status);

    if (_status.success)
    {
        player_stats = _stats;
        show_debug_message(player_stats);
    }
    else
    {
        show_debug_message(_status.error);
        player_stats = {};
    }
});