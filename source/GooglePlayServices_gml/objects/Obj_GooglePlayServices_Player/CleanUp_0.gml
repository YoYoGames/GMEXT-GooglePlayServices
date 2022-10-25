/// @description Delete sprite

// We need to check if the sprite exists and delete it
// otherwise we will have memory leaks.
if(sprite_exists(sprite))
{
	sprite_delete(sprite)
	sprite = noone
}
