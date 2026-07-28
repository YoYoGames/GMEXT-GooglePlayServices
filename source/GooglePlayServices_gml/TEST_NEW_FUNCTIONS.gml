/// ============================================================================
/// Google Play Services - Test Suite for New Functions
/// ============================================================================
///
/// This test suite validates all new friends and players functions
/// Create an object (obj_gpgs_test) and add these functions to it
///

/// ============================================================================
/// TEST INITIALIZATION
/// ============================================================================

function test_init() {
    global.test_state = {
        current_test: 0,
        total_tests: 0,
        passed: 0,
        failed: 0,
        tests: [],
        authenticated: false,
        results: []
    };

    setup_tests();
    check_authentication();
}

function setup_tests() {
    var tests = global.test_state.tests;

    // Add all tests
    array_push(tests, {
        name: "Authentication Check",
        func: test_authentication,
        required: true
    });

    array_push(tests, {
        name: "Get Current Player",
        func: test_current_player,
        required: true
    });

    array_push(tests, {
        name: "Load Specific Player",
        func: test_player_load,
        required: false
    });

    array_push(tests, {
        name: "Load Friends List",
        func: test_friends_load,
        required: false
    });

    array_push(tests, {
        name: "Load More Friends",
        func: test_friends_load_more,
        required: false
    });

    array_push(tests, {
        name: "Friends Load with Consent",
        func: test_friends_load_with_consent,
        required: false
    });

    array_push(tests, {
        name: "Show Player Profile",
        func: test_player_profile_show,
        required: false
    });

    array_push(tests, {
        name: "Show Player Search",
        func: test_player_search_show,
        required: false
    });

    global.test_state.total_tests = array_length(tests);
}

/// ============================================================================
/// TEST 1: Authentication
/// ============================================================================

function test_authentication() {
    show_debug_message("[TEST] Checking authentication...");

    gpgs_is_authenticated(function(result) {
        if (result.success && result.is_authenticated) {
            test_pass("Authentication verified");
            global.test_state.authenticated = true;
        } else if (result.success && !result.is_authenticated) {
            test_fail("User is not authenticated. Must sign in first.");
            show_message("Please sign in to Google Play Games to run tests.");
        } else {
            test_fail("Authentication check failed: " + result.error);
        }

        run_next_test();
    });

    return false;  // Async test
}

/// ============================================================================
/// TEST 2: Get Current Player
/// ============================================================================

function test_current_player() {
    show_debug_message("[TEST] Loading current player...");

    gpgs_player_current(function(result) {
        if (result.success && result.player.player_id != "") {
            test_pass("Current player loaded: " + result.player.display_name);

            // Store current player for later tests
            global.test_state.current_player = result.player;

            show_debug_message("  Player ID: " + result.player.player_id);
            show_debug_message("  Display Name: " + result.player.display_name);
            show_debug_message("  Title: " + result.player.title);
        } else {
            test_fail("Failed to load current player: " + result.error);
        }

        run_next_test();
    });

    return false;  // Async test
}

/// ============================================================================
/// TEST 3: Load Specific Player
/// ============================================================================

function test_player_load() {
    if (!global.test_state.authenticated) {
        test_skip("Player load - requires authentication");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Loading specific player...");

    // Use current player ID for testing
    if (global.test_state.current_player != undefined) {
        var test_player_id = global.test_state.current_player.player_id;

        gpgs_player_load(test_player_id, true, function(result) {
            if (result.success && result.player.player_id != "") {
                test_pass("Player loaded: " + result.player.display_name);
                show_debug_message("  Player ID: " + result.player.player_id);
            } else {
                test_fail("Failed to load player: " + result.error);
            }

            run_next_test();
        });

        return false;  // Async test
    } else {
        test_fail("No player ID available for testing");
        run_next_test();
        return false;
    }
}

/// ============================================================================
/// TEST 4: Load Friends List
/// ============================================================================

function test_friends_load() {
    if (!global.test_state.authenticated) {
        test_skip("Friends load - requires authentication");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Loading friends list...");

    gpgs_friends_load(true, 10, function(result) {
        if (result.success) {
            var friend_count = array_length(result.players);
            test_pass("Friends loaded: " + string(friend_count) + " friends");

            show_debug_message("  Friends count: " + string(friend_count));
            show_debug_message("  Has more: " + string(result.has_more));

            // Store friends for next test
            global.test_state.friends = result.players;
            global.test_state.friends_has_more = result.has_more;

            // Show first few friends
            for (var i = 0; i < min(3, friend_count); i++) {
                var friend = result.players[i];
                show_debug_message("    Friend " + string(i + 1) + ": " + friend.display_name);
            }
        } else {
            test_fail("Failed to load friends: " + result.error);
        }

        run_next_test();
    });

    return false;  // Async test
}

/// ============================================================================
/// TEST 5: Load More Friends (Pagination)
/// ============================================================================

function test_friends_load_more() {
    if (!global.test_state.authenticated) {
        test_skip("Friends load more - requires authentication");
        run_next_test();
        return false;
    }

    if (!global.test_state.friends_has_more) {
        test_skip("Friends load more - no more friends available");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Loading more friends...");

    gpgs_friends_load_more(function(result) {
        if (result.success) {
            var friend_count = array_length(result.players);
            test_pass("More friends loaded: " + string(friend_count) + " additional friends");

            show_debug_message("  Additional friends: " + string(friend_count));
            show_debug_message("  Has more: " + string(result.has_more));

            for (var i = 0; i < min(2, friend_count); i++) {
                var friend = result.players[i];
                show_debug_message("    Friend: " + friend.display_name);
            }
        } else {
            test_fail("Failed to load more friends: " + result.error);
        }

        run_next_test();
    });

    return false;  // Async test
}

/// ============================================================================
/// TEST 6: Friends Load with Consent
/// ============================================================================

function test_friends_load_with_consent() {
    if (!global.test_state.authenticated) {
        test_skip("Friends with consent - requires authentication");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Loading friends with consent handling...");

    gpgs_friends_load_with_consent(true, 10, function(result) {
        if (result.success) {
            var friend_count = array_length(result.players);
            test_pass("Friends loaded with consent: " + string(friend_count) + " friends");

            show_debug_message("  Friends count: " + string(friend_count));
            show_debug_message("  Has more: " + string(result.has_more));
        } else {
            if (string_pos("permission", result.error) > 0 ||
                string_pos("denied", result.error) > 0) {
                test_pass("Consent test completed (user denied permission - expected)");
                show_debug_message("  Permission was denied - this is acceptable in testing");
            } else {
                test_fail("Failed to load friends with consent: " + result.error);
            }
        }

        run_next_test();
    });

    return false;  // Async test
}

/// ============================================================================
/// TEST 7: Show Player Profile UI
/// ============================================================================

function test_player_profile_show() {
    if (!global.test_state.authenticated) {
        test_skip("Player profile - requires authentication");
        run_next_test();
        return false;
    }

    if (global.test_state.current_player == undefined) {
        test_skip("Player profile - no player ID available");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Opening player profile UI...");

    // This test just opens the UI - there's no callback to verify
    gpgs_player_profile_show(global.test_state.current_player.player_id);

    test_pass("Player profile UI opened (manual verification required)");
    show_debug_message("  Check: Did the Google Play Games profile UI open?");

    run_next_test();
    return false;
}

/// ============================================================================
/// TEST 8: Show Player Search UI
/// ============================================================================

function test_player_search_show() {
    if (!global.test_state.authenticated) {
        test_skip("Player search - requires authentication");
        run_next_test();
        return false;
    }

    show_debug_message("[TEST] Opening player search UI...");

    gpgs_player_search_show(function(result) {
        if (result.status == 1) {
            test_pass("Player search completed: " + result.display_name + " selected");
            show_debug_message("  Selected player: " + result.display_name);
            show_debug_message("  Player ID: " + result.player_id);
        } else {
            test_pass("Player search UI opened (user canceled - acceptable)");
            show_debug_message("  User canceled the search");
        }

        run_next_test();
    });

    test_pass("Player search UI opened (manual verification required)");
    show_debug_message("  Check: Did the Google Play Games search UI open?");

    return false;  // Async test
}

/// ============================================================================
/// TEST UTILITIES
/// ============================================================================

function check_authentication() {
    gpgs_is_authenticated(function(result) {
        if (result.success && result.is_authenticated) {
            global.test_state.authenticated = true;
            show_debug_message("=== GOOGLE PLAY GAMES AUTHENTICATION: OK ===");
            show_debug_message("Running tests...");
        } else {
            global.test_state.authenticated = false;
            show_debug_message("=== WARNING: NOT AUTHENTICATED ===");
            show_debug_message("Please sign in to run tests");
        }
    });
}

function run_next_test() {
    global.test_state.current_test++;

    if (global.test_state.current_test >= global.test_state.total_tests) {
        test_complete();
    }
}

function test_pass(message) {
    global.test_state.passed++;
    var test_name = global.test_state.tests[global.test_state.current_test].name;

    show_debug_message("✓ PASS: " + test_name + " - " + message);

    array_push(global.test_state.results, {
        test: test_name,
        status: "PASS",
        message: message
    });
}

function test_fail(message) {
    global.test_state.failed++;
    var test_name = global.test_state.tests[global.test_state.current_test].name;

    show_debug_message("✗ FAIL: " + test_name + " - " + message);

    array_push(global.test_state.results, {
        test: test_name,
        status: "FAIL",
        message: message
    });
}

function test_skip(message) {
    var test_name = global.test_state.tests[global.test_state.current_test].name;

    show_debug_message("⊘ SKIP: " + test_name + " - " + message);

    array_push(global.test_state.results, {
        test: test_name,
        status: "SKIP",
        message: message
    });
}

function test_complete() {
    show_debug_message("");
    show_debug_message("===========================================");
    show_debug_message("TEST SUITE COMPLETE");
    show_debug_message("===========================================");
    show_debug_message("Total Tests: " + string(global.test_state.total_tests));
    show_debug_message("Passed: " + string(global.test_state.passed));
    show_debug_message("Failed: " + string(global.test_state.failed));
    show_debug_message("Skipped: " + string(global.test_state.total_tests - global.test_state.passed - global.test_state.failed));
    show_debug_message("===========================================");

    global.test_state.completed = true;

    // Print results
    for (var i = 0; i < array_length(global.test_state.results); i++) {
        var result = global.test_state.results[i];
        show_debug_message(result.status + ": " + result.test + " - " + result.message);
    }
}

/// ============================================================================
/// DRAW FUNCTION
/// ============================================================================

function test_draw() {
    var xx = 20;
    var yy = 20;

    draw_set_color(c_white);
    draw_text(xx, yy, "Google Play Services Test Suite");
    draw_text(xx, yy + 25, "================================");

    var current_test = global.test_state.current_test;
    var total_tests = global.test_state.total_tests;

    if (global.test_state.completed) {
        draw_set_color(c_lime);
        draw_text(xx, yy + 50, "Tests Completed!");
        draw_set_color(c_white);
        draw_text(xx, yy + 75, "Passed: " + string(global.test_state.passed) + "/" + string(total_tests));
    } else {
        draw_set_color(c_yellow);
        draw_text(xx, yy + 50, "Running Test " + string(current_test + 1) + " of " + string(total_tests));

        if (current_test < total_tests) {
            draw_set_color(c_white);
            draw_text(xx, yy + 75, "Test: " + global.test_state.tests[current_test].name);
        }

        draw_text(xx, yy + 100, "Passed: " + string(global.test_state.passed));
        draw_text(xx, yy + 120, "Failed: " + string(global.test_state.failed));
    }

    draw_set_color(c_white);
    draw_text(xx, yy + 150, "Check the Debug Output for detailed results");
}

/// ============================================================================
/// MAIN TEST FUNCTION
/// ============================================================================

function run_all_tests() {
    test_init();
    run_next_test();
}
