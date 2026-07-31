show_debug_message("play_services_friends_load_with_consent called")
play_services_friends_load_with_consent(true, 8*3, function(status, players, has_more) {
    if (status.success) {
        show_debug_message("Friends loaded: " + string(array_length(players)));

		for(var a = 0 ; a < array_length(players) ; a++)
		{
			var b = a mod 8
			var c = a div 8
			var friend = players[a]
			show_debug_message($"Friend: {friend}")
			instance_create_depth(200+b*150,300+c*150, depth, obj_googlePlayServices_friend, {data: friend})
		}
    } else {
        show_debug_message("Friends load failed: " + status.error);
    }
});
