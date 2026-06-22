/// @description Draw leaderboard entry

if (sprite_exists(entry_sprite))
{
    draw_sprite_stretched(
        entry_sprite,
        0,
        x - 30,
        y - 30,
        60,
        60
    );
}
else
{
    draw_rectangle(
        x - 30,
        y - 30,
        x + 30,
        y + 30,
        true
    );
}

draw_set_valign(fa_middle);

draw_set_halign(fa_right);
draw_text(x - 70, y, display_rank);

draw_set_halign(fa_left);
draw_text(x + 70, y - 15, score_holder_name);
draw_text(x + 70, y + 15, display_score);
