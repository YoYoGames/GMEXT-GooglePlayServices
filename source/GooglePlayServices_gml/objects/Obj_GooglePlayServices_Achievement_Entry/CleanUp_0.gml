/// @description Release the dynamically loaded achievement sprite.

if (sprite_exists(sprite))
{
    sprite_delete(sprite);
    sprite = noone;
}
