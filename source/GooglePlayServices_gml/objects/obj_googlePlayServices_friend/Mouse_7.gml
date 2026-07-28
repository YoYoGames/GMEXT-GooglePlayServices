
play_services_player_load("player_id_123", true, function(result) {
    if (result.success) {
        show_debug_message("Player: " + result.player.display_name);
    } else {
        show_debug_message("Error: " + result.error);
    }
});



play_services_player_profile_show("friend_id_123");

