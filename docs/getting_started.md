@title Getting Started

# Getting Started

This guide walks you through the recommended order in which to call the functions of the
GooglePlayServices extension, from checking availability through to your first authenticated calls.
You should read ${page.google_setup} first if you have not yet set up Google Play Games Services for
your app, and ${page.extension_setup} for filling in the Application ID of the extension.

## Prerequisites

* A Google Play Games Services setup for your app, with the leaderboards and the achievements
  already created (${page.google_setup}).
* The Application ID of the extension filled in (${page.extension_setup}).
* An Android build, as this extension has no iOS or desktop implementation.

## 1. Check availability

```gml
if (!play_services_is_available())
{
    show_debug_message("Google Play Services is not available on this device.");
    exit;
}
```

The code above checks that Google Play Services is present on the device before the game attempts
anything else.

## 2. Sign in

A sign-in attempt is made automatically when the game starts, so you should only call
${function.play_services_sign_in} yourself if you need to prompt the player again, for example
because the automatic attempt failed or because the player signed out:

```gml
play_services_is_authenticated(function(_status, _is_authenticated)
{
    if (_status.success && !_is_authenticated)
    {
        play_services_sign_in(function(_sign_in_status, _signed_in)
        {
            if (_signed_in)
                show_debug_message("Signed in to Google Play Games");
        });
    }
});
```

The code above queries the current authentication state and only prompts the player when they are
not already signed in.

## 3. Handling callbacks

Every asynchronous function in this extension gives you a synchronous ${constant.PlayServicesError}
return value first, and then delivers its real outcome through a callback whose **first argument is
always a** ${struct.PlayServicesResult}. You should check `status.success` before touching anything
else that the callback was given, as only `status.error` is meaningful on a failure:

```gml
var _error = play_services_player_current(function(_status, _player = undefined)
{
    if (!_status.success)
    {
        show_debug_message($"Failed: {_status.error}");
        return;
    }

    show_debug_message($"Signed in as {_player.display_name}");
});

if (_error != PlayServicesError.Ok)
{
    // the callback above will never be called, as the call was rejected outright (not signed
    // in, no activity, and so on)
}
```

The code above handles both halves of that shape, which means the failure that arrives through the
callback and the failure that is reported by the return value alone.

## 4. Main usage

Once the player is signed in, every module follows the same shape. You call a function and you get a
${struct.PlayServicesResult} back through its callback:

```gml
// Player
play_services_player_current(player_current_callback);

// Achievements
play_services_achievements_unlock("CgkI...", achievement_callback);
play_services_achievements_show(); // system UI, no callback

// Leaderboards
play_services_leaderboard_submit_score_with_tag(leaderboard_id, score, "archer", submit_callback);
play_services_leaderboard_load_top_scores(leaderboard_id, PlayServicesLeaderboardTimeSpan.AllTime,
    PlayServicesLeaderboardCollection.Public, PLAY_SERVICES_MAX_LEADERBOARD_RESULTS, false, scores_callback);

// Friends
play_services_friends_load_with_consent(false, PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE, friends_callback);

// Saved Games
play_services_saved_games_open("slot_1", true, PlayServicesSavedGamesConflictPolicy.MostRecentlyModified,
    open_callback);
```

See ${module.player}, ${module.friends}, ${module.achievements}, ${module.leaderboards} and
${module.savedgames} for the full function and struct reference of each one.

## 5. Cleanup

A save slot that was opened with ${function.play_services_saved_games_open} stays held until you
either commit it with ${function.play_services_saved_games_commit_and_close} or delete it with
${function.play_services_saved_games_delete}, so you should not leave slots open indefinitely across
scene or room changes. No other module in this extension holds a resource that needs to be cleaned
up explicitly.

## Testing notes

* The demo project that is bundled with this extension is a reference demo, so it needs your own
  `.keystore` and your own Google Services setup in order to run. See ${page.extension_setup}.
* An achievements, leaderboards or saved games call that reaches the network can fail with
  ${constant.PlayServicesError}.NotAuthenticated if sign-in has not completed yet, so you should
  always check ${function.play_services_is_authenticated}, or wait for the callback of
  ${function.play_services_sign_in}, before exercising the rest of the API in a test scene.
* ${function.play_services_saved_games_open},
  ${function.play_services_saved_games_delete} and
  ${function.play_services_saved_games_commit_and_close} only work against a slot that was opened
  earlier in the same session, as restarting the game clears which slots are considered to be open.
