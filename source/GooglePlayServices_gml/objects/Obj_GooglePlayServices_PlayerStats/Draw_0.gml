draw_set_font(Font_YoYo_20);
draw_set_halign(fa_left);
draw_set_valign(fa_top);

draw_text(x, y, "Player Stats:");

if (!is_struct(player_stats))
{
    draw_text(x, y + 30, "Loading...");
    exit;
}

var names = variable_struct_get_names(player_stats);

for (var i = 0; i < array_length(names); ++i)
{
    var key = names[i];
    var value = variable_struct_get(player_stats, key);

    draw_text(
        x,
        y + i * 30 + 30,
        key + ": " + string(value)
    );
}
