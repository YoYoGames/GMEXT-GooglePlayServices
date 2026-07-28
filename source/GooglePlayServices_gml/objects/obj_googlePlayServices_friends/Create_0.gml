
play_services_friends_load(true, 20, function(result) {
    if (result.success) {
        show_debug_message("Friends loaded: " + string(array_length(result.players)));
    }
});



//play_services_friends_load_more(function(result) {
//    if (result.success) {
//        show_debug_message("More friends: " + string(array_length(result.players)));
//    }
//});