
instance_destroy(Obj_GooglePlayServices_Leaderboard_Entry);

gpgs_leaderboard_load_player_centered_scores(
    Leaderboard1,
    GPGSLeaderboardTimeSpan.AllTime,
    GPGSLeaderboardCollection.Public,
    5,
    true,
    function(_result)
    {
        show_debug_message(_result);

        if (!_result.success)
        {
            show_debug_message(_result.error);
            return;
        }

        var _leaderboard = _result.leaderboard;
        var _scores = _result.scores;

        show_debug_message(_leaderboard);

        for (var i = 0; i < array_length(_scores); ++i)
        {
            var _score = _scores[i];
			
			show_debug_message(_score)
			
            instance_create_depth(
                800,
                200 + i * 75,
                0,
                Obj_GooglePlayServices_Leaderboard_Entry,
                {
                    data: _score
                }
            );
        }
    }
);
