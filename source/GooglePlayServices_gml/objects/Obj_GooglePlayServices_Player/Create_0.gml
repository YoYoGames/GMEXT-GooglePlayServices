/// @description Initialize player information

player_info = undefined;
player_sprite = noone;
callback_owner = id;

gpgs_uri_to_path_callback = function(_success, _path, _error)
{
    show_debug_message("gpgs_uri_to_path_callback");
    show_debug_message({
        success: _success,
        path: _path,
        error: _error
    });

    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    if (!instance_exists(callback_owner))
        return;

    with (callback_owner)
    {
        if (sprite_exists(player_sprite))
            sprite_delete(player_sprite);

        player_sprite = sprite_add(
            _path,
            1,
            false,
            false,
            0,
            0
        );

        show_debug_message({
            player_sprite: player_sprite,
            sprite_exists: sprite_exists(player_sprite)
        });
    }
};

function player_info_reset()
{
    player_info = undefined;

    if (sprite_exists(player_sprite))
    {
        sprite_delete(player_sprite);
        player_sprite = noone;
    }
}

gpgs_player_current(function(_result)
{
    show_debug_message(_result);

    if (!_result.success)
    {
        show_debug_message(_result.error);
        player_info_reset();
        return;
    }

    player_info = _result.player;

    if (!is_struct(player_info))
    {
        player_info_reset();
        return;
    }

    var _image_uri = "";

    if (struct_exists(player_info, "hi_res_image_uri")
        && player_info.hi_res_image_uri != "")
    {
        _image_uri = player_info.hi_res_image_uri;
    }
    else if (struct_exists(player_info, "icon_image_uri")
        && player_info.icon_image_uri != "")
    {
        _image_uri = player_info.icon_image_uri;
    }

    if (_image_uri != "")
    {
        gpgs_uri_to_path(
            _image_uri,
            gpgs_uri_to_path_callback
        );
    }
    else
    {
        show_debug_message("The player has no usable image URI.");
    }
});
