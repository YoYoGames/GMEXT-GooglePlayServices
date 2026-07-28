/// @description Draw player information

if (sprite_exists(player_sprite))
{
    draw_sprite_stretched(
        player_sprite,
        0,
        x,
        y,
        150,
        150
    );
}
else
{
    draw_rectangle(
        x,
        y,
        x + 150,
        y + 150,
        true
    );
}

draw_set_font(Font_YoYo_20);
draw_set_halign(fa_left);
draw_set_valign(fa_top);

draw_text(x + 160, y, "Player Info:");

if (!is_struct(player_info))
{
    draw_text(x + 160, y + 30, "Loading...");
    exit;
}

var _properties = variable_struct_get_names(player_info);

for (var i = 0; i < array_length(_properties); ++i)
{
    var _property = _properties[i];
    var _value = variable_struct_get(player_info, _property);

    draw_text(
        x + 160,
        y + (i + 1) * 30,
        _property + ": " + string(_value)
    );
}
