

show_debug_message("play_services_friends_load_with_consent called")
play_services_friends_load_with_consent(true, 8*3, function(result) {
    if (result.success) {
        show_debug_message("Friends loaded: " + string(array_length(result.players)));
		
		for(var a = 0 ; a < array_length(result.players) ; a++)
		{
			var b = a mod 8
			var c = a div 8
			var friend = result.players[a]
			show_debug_message($"Friend: {friend}")
			instance_create_depth(200+b*150,300+c*150, depth, obj_googlePlayServices_friend, {data: friend})
		}
    } else {
        show_debug_message("Friends load failed: " + result.error);
    }
});



///// Quick diagnostic test
//function test_friends_load_diagnostic() {
//    show_debug_message("=== FRIENDS LOAD DIAGNOSTIC ===");
    
//    // Step 1: Check authentication
//    show_debug_message("Step 1: Checking authentication...");
//    play_services_is_authenticated(function(auth_result) {
//        show_debug_message("Auth callback reached!");
//        show_debug_message("  Success: " + string(auth_result.success));
//        show_debug_message("  Is Authenticated: " + string(auth_result.is_authenticated));
        
//        if (!auth_result.is_authenticated) {
//            show_debug_message("ERROR: Not authenticated! Signing in...");
//            play_services_sign_in(function(signin_result) {
//                show_debug_message("Sign in callback reached!");
//                show_debug_message("  Success: " + string(signin_result.success));
//                show_debug_message("  Is Authenticated: " + string(signin_result.is_authenticated));
                
//                if (signin_result.is_authenticated) {
//                    attempt_friends_load();
//                }
//            });
//        } else {
//            show_debug_message("Authentication OK, loading friends...");
//            attempt_friends_load();
//        }
//    });
//}

//function attempt_friends_load() {
//    show_debug_message("Step 2: Calling play_services_friends_load...");
    
//    play_services_friends_load(true, 20, function(result) {
//        show_debug_message("*** FRIENDS LOAD CALLBACK REACHED ***");
//        show_debug_message("  Success: " + string(result.success));
//        show_debug_message("  Friend count: " + string(array_length(result.players)));
//        show_debug_message("  Has more: " + string(result.has_more));
//        show_debug_message("  Error: " + result.error);
        
//        if (result.success) {
//            for (var i = 0; i < array_length(result.players); i++) {
//                var friend = result.players[i];
//                show_debug_message("    Friend " + string(i) + ": " + friend.display_name);
//            }
//        }
//    });
    
//    show_debug_message("play_services_friends_load() call completed");
//}

//// Call this in your test
//test_friends_load_diagnostic();







//show_debug_message("play_services_friends_load called")
//play_services_friends_load(true, 20, function(result) {
//    if (result.success) {
//        show_debug_message("Friends loaded: " + string(array_length(result.players)));
		
//		for(var a = 0 ; a < array_length(result.players) ; a++)
//		{
//			var friend = result.players[a]
//			instance_create_depth(room_width/2,200+a*100,depth,obj_googlePlayServices_friend,{data: friend})
//		}
//    }
//});



////play_services_friends_load_more(function(result) {
////    if (result.success) {
////        show_debug_message("More friends: " + string(array_length(result.players)));
////    }
////});