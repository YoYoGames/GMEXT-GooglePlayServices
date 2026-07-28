/// @description Delete dynamically loaded cover sprite

if (sprite_exists(cover_sprite))
{
    sprite_delete(cover_sprite);
    cover_sprite = noone;
}
