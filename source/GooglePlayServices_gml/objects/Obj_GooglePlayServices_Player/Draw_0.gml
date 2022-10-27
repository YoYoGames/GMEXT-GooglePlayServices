/// @description Draw player info

// Early exit if we are not signed in.


// We check if the sprite variable has a valid sprite and draw it.
if(sprite_exists(sprite))
{
	draw_sprite_stretched(sprite, 0, x, y, 150, 150);
}
else
{
	// If we don't have a valid sprite we draw a simple white frame.
	draw_rectangle(x, y, x+150, y+150, true);
}

// Here we set font properties to draw text.
draw_set_font(Font_YoYo_20);
draw_set_halign(fa_left);
draw_set_valign(fa_top);

// We proceed to draw the iaccount info.
draw_text(x + 160, y, "Player Info:");

var info = playerInfo;

// We check if 'info' is a valid struct
// NOTE: Data is retreived in callback Async Social 'GooglePlayServices_Player_Current'
if (!is_struct(info)) exit;

// We loop through all the properties inside the struct and draw them to screen.
var properties = variable_struct_get_names(info);
var count = array_length(properties);
for (var i = 0; i < count; i++)
{
	var property = properties[i];
	draw_text(x + 160, y + (i + 1)*30, property + ": " + string(info[$ property]));
}
