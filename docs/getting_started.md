@title Getting Started

# Getting Started

This guide walks through the recommended call order for the GooglePlayServices extension, from
checking availability through your first authenticated calls. See ${page.google_setup} first if you
haven't yet set up Google Play Games Services for your app, and ${page.extension_setup} for filling in
the extension's Application ID.

## Prerequisites

* A Google Play Games Services setup for your app, with leaderboards/achievements already created
  (${page.google_setup}).
* The extension's Application ID filled in (${page.extension_setup}).
* **Android only** - this extension has no iOS or desktop implementation.

## 1. Check availability

```gml
if (!play_services_is_available())
{
    show_debug_message("Google Play Services is not available on this device.");
    exit;
}
```

## 2. Sign in

A sign-in attempt is made automatically when the game starts. Call
${function.play_services_sign_in} yourself only if you need to re-prompt (e.g. the automatic attempt
failed, or the player signed out):

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

## 3. Handling callbacks

Every async function in this extension checks its synchronous ${constant.PlayServicesError} return
value first, then delivers its real outcome through a callback whose **first argument is always a**
${struct.PlayServicesResult}. Check `status.success` before touching anything else the callback
receives - on failure, only `status.error` is meaningful:

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
    // callback above will never fire - the call was rejected outright (not signed in, no activity, ...)
}
```

## 4. Main usage

Once signed in, every module follows the same shape - call a function, get a
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
    PlayServicesLeaderboardCollection.Public, PlayServicesMaxLeaderboardResults, false, scores_callback);

// Friends
play_services_friends_load_with_consent(false, PlayServicesMaxFriendsPageSize, friends_callback);

// Saved Games
play_services_saved_games_open("slot_1", true, PlayServicesSavedGamesConflictPolicy.MostRecentlyModified,
    open_callback);
```

See ${module.player}, ${module.friends}, ${module.achievements}, ${module.leaderboards}, and
${module.savedgames} for the full function/struct reference of each.

## 5. Cleanup

A save slot opened with ${function.play_services_saved_games_open} stays held until you either commit
it with ${function.play_services_saved_games_commit_and_close} or delete it with
${function.play_services_saved_games_delete} - don't leave slots open indefinitely across scene/room
changes. No other module in this extension holds a resource that needs explicit cleanup.

## Testing notes

* The demo project bundled with this extension is a reference demo - it needs your own `.keystore` and
  Google Services setup to run, per ${page.extension_setup}.
* Achievements/leaderboards/saved-games calls that hit the network can fail with
  ${constant.PlayServicesError}.NotAuthenticated if sign-in hasn't completed yet - always check
  ${function.play_services_is_authenticated} (or wait for ${function.play_services_sign_in}'s callback)
  before exercising the rest of the API in a test scene.
* ${function.play_services_saved_games_open}/${function.play_services_saved_games_delete}/
  ${function.play_services_saved_games_commit_and_close} only work against a slot opened earlier in the
  same session - restarting the game clears which slots are considered "open".
