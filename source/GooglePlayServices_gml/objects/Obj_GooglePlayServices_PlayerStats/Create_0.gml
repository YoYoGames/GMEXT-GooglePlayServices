player_stats = {};

gpgs_player_stats_load(true, function(_result)
{
    show_debug_message("gpgs_player_stats_load");
    show_debug_message(_result);

    if (_result.success)
    {
        player_stats = _result.stats;
        show_debug_message(player_stats);
    }
    else
    {
        show_debug_message(_result.error);
        player_stats = {};
    }
});