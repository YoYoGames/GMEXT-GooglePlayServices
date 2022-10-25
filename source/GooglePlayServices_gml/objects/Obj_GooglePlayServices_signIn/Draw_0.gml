
event_inherited();

draw_set_font(Font_YoYo_20)
draw_set_valign(fa_top)
draw_set_halign(fa_left)
draw_set_color(c_white)

draw_text(bbox_left,bbox_top-30,"GooglePlayServices_IsAvailable: " + string(GooglePlayServices_IsAvailable()))

