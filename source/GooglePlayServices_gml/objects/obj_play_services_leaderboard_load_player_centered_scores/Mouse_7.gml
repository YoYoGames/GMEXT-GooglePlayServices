
instance_destroy(obj_play_services_leaderboard_entry);

play_services_leaderboard_load_player_centered_scores(
    Leaderboard1,
    PlayServicesLeaderboardTimeSpan.AllTime,
    PlayServicesLeaderboardCollection.Public,
    5,
    true,
    function(_status, _leaderboard, _scores)
    {
        show_debug_message(_status);

        if (!_status.success)
        {
            show_debug_message(_status.error);
            return;
        }

        show_debug_message(_leaderboard);

        for (var i = 0; i < array_length(_scores); ++i)
        {
            var _score = _scores[i];
			
			show_debug_message(_score)
			
            instance_create_depth(
                800,
                200 + i * 75,
                0,
                obj_play_services_leaderboard_entry,
                {
                    data: _score
                }
            );
        }
    }
);
