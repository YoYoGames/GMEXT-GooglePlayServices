/// @description Reveal achievement

// This function will reveal a hidden achievement in the achievement board.
// it requires you to use the unique achievement id string.
play_services_achievements_reveal(Achievement2, function(_status)
{
	show_debug_message(_status);
});
