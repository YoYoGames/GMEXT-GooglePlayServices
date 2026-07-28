/// @description Release the dynamically loaded player sprite

if (sprite_exists(player_sprite))
{
    sprite_delete(player_sprite);
    player_sprite = noone;
}
