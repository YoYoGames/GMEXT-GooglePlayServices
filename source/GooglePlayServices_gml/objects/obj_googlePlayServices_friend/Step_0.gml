
if(mouse_check_button_pressed(mb_left))
if(point_in_rectangle(mouse_x,mouse_y,x-50,y-50,x+50,y+50))
{
	play_services_player_load(data.player_id, true, function(_status, _player) {
	    if (_status.success) {
	        show_debug_message("Player: " + string(_player.display_name));
	    } else {
	        show_debug_message("Error: " + _status.error);
	    }
	});

	play_services_player_profile_show(data.player_id);
}

