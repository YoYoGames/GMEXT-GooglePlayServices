
if(mouse_check_button_pressed(mb_left))
if(point_in_rectangle(mouse_x,mouse_y,x,y,x+150,y+150))
{
	play_services_player_load(player_info.player_id, true, function(result) {
	    if (result.success) {
	        show_debug_message("Player: " + result.player.display_name);
	    } else {
	        show_debug_message("Error: " + result.error);
	    }
	});

	play_services_player_profile_show(player_info.player_id);
}

