# Google Play Services - New Functions Reference

## New Functions Added (Friends & Players)

### 1. Load a Specific Player
```gml
gpgs_player_load(player_id, force_reload, callback)
```

**Parameters:**
- `player_id` (string) - The target player's unique ID
- `force_reload` (bool) - If true, refresh from server; if false, use cached data
- `callback` (function) - Callback function that receives `GPGSPlayer` result

**Result Object:**
```gml
{
    success: bool,
    player: {
        player_id: string,
        display_name: string,
        title: string,
        icon_image_uri: string,
        hi_res_image_uri: string
    },
    error: string
}
```

**Example:**
```gml
gpgs_player_load("player_id_123", true, function(result) {
    if (result.success) {
        show_debug_message("Player: " + result.player.display_name);
    } else {
        show_debug_message("Error: " + result.error);
    }
});
```

---

### 2. Load Current Player's Friends
```gml
gpgs_friends_load(force_reload, max_results, callback)
```

**Parameters:**
- `force_reload` (bool) - If true, refresh from server
- `max_results` (real) - Number of friends to load (1-25, automatically clamped)
- `callback` (function) - Callback function that receives `GPGSPlayerList` result

**Result Object:**
```gml
{
    success: bool,
    players: [GPGSPlayerInfo array],  // Array of friend info
    has_more: bool,                    // True if more friends available
    error: string
}
```

**Example:**
```gml
gpgs_friends_load(true, 20, function(result) {
    if (result.success) {
        show_debug_message("Friends loaded: " + string(array_length(result.players)));
    }
});
```

---

### 3. Load More Friends (Pagination)
```gml
gpgs_friends_load_more(callback)
```

**Parameters:**
- `callback` (function) - Callback function that receives `GPGSPlayerList` result

**Important:**
- Must call `gpgs_friends_load()` first to initialize pagination
- Uses the same page size as the initial `gpgs_friends_load()` call
- Returns next page of results

**Example:**
```gml
gpgs_friends_load_more(function(result) {
    if (result.success) {
        show_debug_message("More friends: " + string(array_length(result.players)));
    }
});
```

---

### 4. Load Friends with Consent Permission
```gml
gpgs_friends_load_with_consent(force_reload, max_results, callback)
```

**Parameters:**
- `force_reload` (bool) - If true, refresh from server
- `max_results` (real) - Number of friends to load (1-25, automatically clamped)
- `callback` (function) - Callback function that receives `GPGSPlayerList` result

**Behavior:**
- Attempts to load friends immediately
- If friends permission not granted, shows Google's native consent dialog
- After user grants/denies permission, callback is invoked
- On permission granted, automatically retries `loadFriends()`

**Result Object:**
```gml
{
    success: bool,
    players: [GPGSPlayerInfo array],
    has_more: bool,
    error: string
}
```

**Possible Errors:**
- `"User denied friends access permission."` - User declined consent
- Network errors
- Authentication errors

**Example:**
```gml
gpgs_friends_load_with_consent(true, 20, function(result) {
    if (result.success) {
        show_debug_message("Friends loaded with permission");
    } else {
        show_debug_message("Friends: " + result.error);
    }
});
```

---

### 5. Show Player Profile UI
```gml
gpgs_player_profile_show(player_id)
```

**Parameters:**
- `player_id` (string) - The target player's ID to display

**Behavior:**
- Opens native Google Play Games profile UI
- Shows player stats, achievements, and friend options
- No callback - just opens the UI
- User can manage friend status from the UI

**Example:**
```gml
gpgs_player_profile_show("friend_id_123");
// Opens Google Play Games profile for that player
```

---

### 6. Show Player Search UI
```gml
gpgs_player_search_show(callback)
```

**Parameters:**
- `callback` (function) - Callback that receives search result

**Behavior:**
- Opens native Google Play Games player search dialog
- User can search for and select a player
- Callback is invoked when user selects a player or cancels
- Returns selected player's information

**Result Object:**
```gml
{
    status: real,          // 1 = success, 0 = canceled/failed
    player_id: string,     // Selected player's ID
    display_name: string,  // Selected player's name
    icon_image_url: string,    // Small icon URL
    hi_res_image_url: string   // High-res portrait URL
}
```

**Example:**
```gml
gpgs_player_search_show(function(result) {
    if (result.status == 1) {
        show_debug_message("Selected: " + result.display_name);
    } else {
        show_debug_message("Search canceled");
    }
});
```

---

## Data Structures

### GPGSPlayerInfo
```gml
{
    player_id: string,           // Unique player identifier
    display_name: string,        // Player's display name
    title: string,               // Player's title (can be empty)
    icon_image_uri: string,      // Small icon image URL
    hi_res_image_uri: string     // High-res portrait image URL
}
```

### GPGSPlayer (Single Player Result)
```gml
{
    success: bool,
    player: GPGSPlayerInfo,
    error: string
}
```

### GPGSPlayerList (Friends List Result)
```gml
{
    success: bool,
    players: [GPGSPlayerInfo[]],
    has_more: bool,
    error: string
}
```

---

## Authentication Requirements

All functions **require** the user to be authenticated with Google Play Games:
- Call `gpgs_sign_in()` first
- Or call `gpgs_is_authenticated()` to check status

If not authenticated, functions will fail with error:
- `"Google Play Games user is not authenticated."`

---

## Function Summary Table

| Function | Returns | UI | Async | Notes |
|----------|---------|----|----|-------|
| `gpgs_player_load()` | `GPGSPlayer` | No | Yes | Single player data |
| `gpgs_friends_load()` | `GPGSPlayerList` | No | Yes | Friends list (paginated) |
| `gpgs_friends_load_more()` | `GPGSPlayerList` | No | Yes | Next page of friends |
| `gpgs_friends_load_with_consent()` | `GPGSPlayerList` | Yes* | Yes | Shows consent dialog if needed |
| `gpgs_player_profile_show()` | None | Yes | No | Native profile UI |
| `gpgs_player_search_show()` | Custom | Yes | No | Native search UI |

*Consent dialog is native Google UI, not from extension

---

## Error Handling Best Practices

```gml
gpgs_friends_load(true, 20, function(result) {
    if (result.success) {
        // Handle success
        process_friends(result.players);
        
        if (result.has_more) {
            // More friends available, can call load_more()
        }
    } else {
        // Handle specific errors
        if (string_pos("authenticated", result.error) > 0) {
            show_message("Please sign in to Google Play Games");
        } else if (string_pos("permission", result.error) > 0) {
            show_message("Friends permission required");
        } else {
            show_message("Error: " + result.error);
        }
    }
});
```
