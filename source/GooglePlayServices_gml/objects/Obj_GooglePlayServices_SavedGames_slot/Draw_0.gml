/// @description Draw save slot

event_inherited();

if (sprite_exists(cover_sprite))
{
    draw_sprite_stretched(
        cover_sprite,
        0,
        bbox_left - 40,
        y - 40,
        80,
        80
    );
}
else if (!cover_image_requested && cover_image_uri != "")
{
    cover_image_requested = true;
    gpgs_uri_to_path(cover_image_uri, uri_to_path_callback);
}
