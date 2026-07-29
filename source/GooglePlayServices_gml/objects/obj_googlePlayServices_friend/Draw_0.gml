
event_inherited();

draw_set_valign(fa_center)
draw_set_halign(fa_middle)
draw_set_font(fnt_gm_20)
draw_text(x,y-50-20,data.display_name)
if (sprite_exists(player_sprite))
    draw_sprite_stretched(player_sprite,0,x-50,y-50,100,100);
else
    draw_rectangle(x-50,y-50,x + 100,y + 100,true);
