instance_destroy(Obj_GooglePlayServices_Achievement_Entry);

gpgs_achievements_get_status(true, function(_result)
{
    show_debug_message(_result);

    if (!_result.success)
    {
        show_debug_message(_result.error);
        return;
    }

    var _achievements = _result.achievements;

    array_sort(_achievements, function(_achievement1, _achievement2)
    {
        if (_achievement1.name < _achievement2.name)
            return -1;

        if (_achievement1.name > _achievement2.name)
            return 1;

        return 0;
    });

    for (var i = 0; i < array_length(_achievements); ++i)
    {
        var _achievement = _achievements[i];

        show_debug_message(_achievement);

        instance_create_depth(
            150 + i * 300,
            room_height / 2 - 50,
            0,
            Obj_GooglePlayServices_Achievement_Entry,
            {
                data: _achievement
            }
        );
    }
});