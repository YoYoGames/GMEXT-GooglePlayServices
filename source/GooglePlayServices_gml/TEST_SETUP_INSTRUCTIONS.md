# Test Suite Setup Instructions

## Files Created

### 1. **NEW_FUNCTIONS_REFERENCE.md**
Complete documentation of all 6 new functions with:
- Function signatures
- Parameters explained
- Return values
- Example code
- Error handling

### 2. **TEST_NEW_FUNCTIONS.gml**
Complete test suite with 8 test cases:
1. Authentication check
2. Get current player
3. Load specific player
4. Load friends list
5. Load more friends (pagination)
6. Friends load with consent
7. Show player profile UI
8. Show player search UI

### 3. This File
Setup and usage instructions

---

## How to Run Tests

### Step 1: Create a Test Object

Create a new game object in GameMaker called `obj_gpgs_test`:

```gml
// Create Event
run_all_tests();

// Step Event
// (tests run automatically via callbacks)

// Draw Event
test_draw();
```

### Step 2: Add Test Functions

Include `TEST_NEW_FUNCTIONS.gml` in your test object or in a script that the object can access.

### Step 3: Prerequisites

Before running tests, ensure:

✅ **User is signed in to Google Play Games**
- Test will check authentication automatically
- If not signed in, run `gpgs_sign_in()` first
- Or add a sign-in button to your test scene

✅ **Extension is properly installed**
- All Google Play Services functions available
- `GMGooglePlayServices` extension added to project

✅ **Network connection available**
- Tests make real API calls to Google Play Games
- Requires active internet connection

### Step 4: Run the Room

Place `obj_gpgs_test` in a room and run the game:

```gml
// Room Creation Code
instance_create_layer(0, 0, "Instances", obj_gpgs_test);
```

---

## What the Tests Do

### Test 1: Authentication Check
- Verifies user is signed in to Google Play Games
- **Required** - all other tests depend on this
- If fails: Tests are skipped

### Test 2: Get Current Player
- Loads the current signed-in player's information
- Stores player data for use in other tests
- **Required** - needed by other tests

### Test 3: Load Specific Player
- Loads the current player's info by ID
- Uses `gpgs_player_load()`
- Verifies player data structure

### Test 4: Load Friends List
- Loads first page of friends (10 friends)
- Uses `gpgs_friends_load()`
- Stores data for pagination test
- Shows sample friend names

### Test 5: Load More Friends
- Tests pagination with `gpgs_friends_load_more()`
- Only runs if more friends available
- Verifies additional friends loaded correctly

### Test 6: Friends with Consent
- Tests `gpgs_friends_load_with_consent()`
- Handles permission dialogs automatically
- If user denies permission: Test passes (expected behavior)

### Test 7: Show Player Profile UI
- Opens native Google Play Games profile UI
- Manual verification required (check screen)
- Cannot programmatically verify UI opened

### Test 8: Show Player Search UI
- Opens native Google Play Games player search UI
- Manual verification required
- Callback receives selected player (if any)

---

## Test Output

### Debug Messages
Check the **Debug Output** panel in GameMaker for:
- `✓ PASS:` - Test passed
- `✗ FAIL:` - Test failed
- `⊘ SKIP:` - Test skipped

### Example Output
```
[TEST] Checking authentication...
✓ PASS: Authentication Check - Authentication verified

[TEST] Loading current player...
✓ PASS: Get Current Player - Current player loaded: PlayerName
  Player ID: G123456789abc
  Display Name: PlayerName
  Title: 

[TEST] Loading friends list...
✓ PASS: Load Friends List - Friends loaded: 15 friends
  Friends count: 15
  Has more: true
    Friend 1: Friend Name 1
    Friend 2: Friend Name 2
    Friend 3: Friend Name 3
```

### Final Summary
```
===========================================
TEST SUITE COMPLETE
===========================================
Total Tests: 8
Passed: 8
Failed: 0
Skipped: 0
===========================================
```

---

## Common Issues

### Issue: "User is not authenticated"
**Solution:** Call `gpgs_sign_in()` before running tests
```gml
gpgs_sign_in(function(result) {
    if (result.success && result.is_authenticated) {
        run_all_tests();
    }
});
```

### Issue: Tests don't start
**Solution:** Check that:
1. Extension is properly linked
2. Object is in the room
3. `run_all_tests()` is called in Create event

### Issue: Player search/profile UI doesn't appear
**Solution:** 
- Make sure you have an active internet connection
- Ensure user is authenticated
- Check that Android app has Google Play Games permissions

### Issue: Friends permission dialog appears
**Solution:**
- This is expected for `gpgs_friends_load_with_consent()`
- Test waits for user to accept/deny permission
- Either accept the permission or deny it - test passes either way

---

## Test Results Interpretation

### All Tests Pass ✅
- All functions working correctly
- Extension fully functional
- Ready for production use

### Some Tests Fail ❌
- Check error message in Debug Output
- Verify authentication
- Check network connection
- See "Common Issues" section above

### Tests Skip ⊘
- Normal - tests skip if conditions not met
- Example: "Load More Friends" skips if user has <20 friends
- Not considered failures

---

## Next Steps After Testing

Once tests pass:

1. **Integrate into your game:**
   ```gml
   // Your game's social menu
   function open_friends_list() {
       gpgs_friends_load(false, 20, function(result) {
           if (result.success) {
               display_friends(result.players);
           }
       });
   }
   ```

2. **Use the example files:**
   - `FRIENDS_PLAYERS_EXAMPLES.gml` - Basic usage patterns
   - `FRIENDS_UI_PATTERNS.gml` - UI components
   - `PROFILE_SEARCH_EXAMPLES.gml` - Profile/search usage

3. **Reference the documentation:**
   - `NEW_FUNCTIONS_REFERENCE.md` - Function reference
   - `QUICK_REFERENCE.md` - Cheat sheet

---

## Test Code Structure

The test suite uses a callback-based approach to handle async operations:

```
test_init()
  ↓
setup_tests() - Register all test functions
  ↓
check_authentication() - Verify user is signed in
  ↓
run_next_test()
  ↓
Each test function...
  - Shows debug message
  - Calls Google Play Services function
  - Callback invokes test_pass(), test_fail(), or test_skip()
  - Calls run_next_test()
  ↓
test_complete() - Print final summary
```

---

## Modifying the Test Suite

To add a new test:

1. Create a test function:
```gml
function test_my_feature() {
    gpgs_my_function(function(result) {
        if (result.success) {
            test_pass("My feature works");
        } else {
            test_fail("Error: " + result.error);
        }
        run_next_test();
    });
    return false;
}
```

2. Add to setup_tests():
```gml
array_push(tests, {
    name: "My Feature",
    func: test_my_feature,
    required: false
});
```

3. The test framework handles the rest!

---

## Support

If tests fail:

1. Check Debug Output for error messages
2. Verify authentication
3. Check network connection
4. Review `NEW_FUNCTIONS_REFERENCE.md` for function details
5. See example files for usage patterns
