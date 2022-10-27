/// @description Draw slot

event_inherited();

// If there is a sprite to draw draw the sprite next to
// the save slot button.
if(sprite_exists(sprite))
{
	draw_sprite_stretched(sprite, 0, bbox_left-40, y-40, 80, 80);
}
// Else fi there is an 'coverImageUri' we need to convert it to a path
else if (!is_undefined(coverImageUri)) {
	// The function call below will take in a URI (unique resource identifier)
	// and query it's path. The function returns an Async call id, that you can match
	// using the Async Social Event callback of type "GooglePlayServices_UriToPath".
	UriToPath_ind = GooglePlayServices_UriToPath(coverImageUri);
	
	coverImageUri = undefined;
}
