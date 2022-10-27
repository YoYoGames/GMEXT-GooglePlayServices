
draw_set_font(Font_YoYo_20)
draw_set_halign(fa_left)
draw_set_valign(fa_left)

draw_text(x,y,"Player Stats:")
var array = variable_struct_get_names(struct)
for(var a  = 0 ; a < array_length(array) ; a++)
{
	draw_text(x,y+a*30+30,array[a] + ": " + string(variable_struct_get(struct,array[a])))
}
