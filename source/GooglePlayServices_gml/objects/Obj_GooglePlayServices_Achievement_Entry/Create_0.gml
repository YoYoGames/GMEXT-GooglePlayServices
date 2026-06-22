/// @description Initialize achievement entry from the creation struct.

sprite = noone;

// `data` is provided by instance_create_depth(..., { data: _achievement }).
if (!variable_instance_exists(id, "data") || !is_struct(data))
{
    show_debug_message("Achievement entry created without valid data.");
    instance_destroy();
    exit;
}

achievement_id = data.achievement_id;
achievement_name = data.name;
achievement_description = data.description;

state = data.state;
achievement_type = data.type;

current_steps = data.current_steps;
total_steps = data.total_steps;

last_updated_timestamp = data.last_updated_timestamp;
xp_value = data.xp_value;

revealed_image_uri = data.revealed_image_uri;
unlocked_image_uri = data.unlocked_image_uri;

gpgs_uri_to_path_callback = function(_success, _path, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    if (_path == "")
        return;

    if (sprite_exists(sprite))
        sprite_delete(sprite);

    sprite = sprite_add(_path, 0, false, false, 0, 0);
};

switch (state)
{
    case GPGSAchievementState.Revealed:
        if (revealed_image_uri != "")
            gpgs_uri_to_path(
                revealed_image_uri,
                gpgs_uri_to_path_callback
            );
    break;

    case GPGSAchievementState.Unlocked:
        if (unlocked_image_uri != "")
            gpgs_uri_to_path(
                unlocked_image_uri,
                gpgs_uri_to_path_callback
            );
    break;
}
