
if(mouse_check_button_pressed(mb_left))
if(point_in_rectangle(mouse_x,mouse_y,x-50,y-50,x+50,y+50))
{
	play_services_player_load(data.player_id, true, function(result) {
	    if (result.success) {
	        show_debug_message("Player: " + result.player.display_name);
	    } else {
	        show_debug_message("Error: " + result.error);
	    }
	});

	play_services_player_profile_show(data.player_id);
}

