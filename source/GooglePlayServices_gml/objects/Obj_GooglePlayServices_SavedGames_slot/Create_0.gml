/// @description Initialize save slot

event_inherited();

cover_sprite = noone;
cover_image_uri = "";
cover_image_requested = false;
callback_owner = id;
unique_name = "";
description = "";

image_xscale = 5;

uri_to_path_callback = function(_success, _path, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    if (!instance_exists(callback_owner))
        return;

    with (callback_owner)
    {
        if (sprite_exists(cover_sprite))
            sprite_delete(cover_sprite);

        cover_sprite = sprite_add(_path, 1, false, false, 0, 0);
    }
};
