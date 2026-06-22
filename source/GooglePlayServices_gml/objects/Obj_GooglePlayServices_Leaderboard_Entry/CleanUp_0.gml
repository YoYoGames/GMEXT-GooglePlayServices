/// @description Release the dynamically loaded player sprite

if (sprite_exists(entry_sprite))
{
    sprite_delete(entry_sprite);
    entry_sprite = noone;
}
