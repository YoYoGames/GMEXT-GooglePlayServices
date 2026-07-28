/// @description Draw the current opened slot

// Here we set font properties to draw text.
draw_set_valign(fa_middle);
draw_set_halign(fa_center);
draw_set_font(Font_YoYo_30);

// Now we draw the opened slot description for debug.
draw_text(x, y, "[" + opened_description + "]");