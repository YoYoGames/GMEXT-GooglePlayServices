/// @description Unlock achievement

// This function will unlock a new achievement in the achievement board and mark it as cleared
// it requires you to use the unique achievement id string.
play_services_achievements_unlock(Achievement3, function(_status)
{
	show_debug_message(_status);
});
