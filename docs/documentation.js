
/**
 * @module home
 * @title GooglePlayServices
 *
 * @section Extension's Features
 * @desc
 *
 * * Sign in with Google Play Games Services and check/request authentication state
 * * Read the current player's profile, stats, and friends list (with consent handling)
 * * Show the Player Search and Player Profile Compare system UIs
 * * Unlock/reveal/increment achievements and show the Achievements system UI
 * * Submit and load leaderboard scores, and show the Leaderboards system UI
 * * Save and load player progress with the Saved Games service, including conflict resolution
 * * Convert a Play Games image URI into a local file path for use with ${function.sprite_add}
 *
 * @section_end
 *
 * @section Introduction
 *
 * @desc
 *
 * This extension wraps Google Play Games Services **v2** for **Android**. Every `play_services_*`
 * function returns a ${constant.PlayServicesError} synchronously - `Ok` means the call was accepted
 * and, if it takes a `callback`, that callback will fire once with the real result; any other value
 * means the call was rejected before ever reaching Google Play (not authenticated, no activity, or an
 * invalid argument) and the callback is never touched.
 *
 * Every callback fires exactly once, `callback.call(status, data...)`: `status` is always a
 * ${struct.PlayServicesResult} carrying just success/error, and the real payload rides as further,
 * separate positional arguments - never bundled into a single per-function result struct. Check
 * `status.success` before reading anything else.
 *
 * Most functions require the player to be signed in first - call ${function.play_services_sign_in} (or
 * rely on the automatic sign-in attempt made at game start) before calling anything else. A handful of
 * functions launch a native system UI (achievements, leaderboards, saved games picker, player search,
 * player profile compare) instead of returning data directly.
 *
 * @section_end
 *
 * @section Guides
 * @desc Guides for the GooglePlayServices extension.
 * @reference page.google_setup
 * @reference page.extension_setup
 * @reference page.getting_started
 * @section_end
 *
 * @section Modules
 * @desc The following are the available modules for the GooglePlayServices extension:
 *
 * @reference module.general
 * @reference module.player
 * @reference module.friends
 * @reference module.achievements
 * @reference module.leaderboards
 * @reference module.savedgames
 * @reference module.utilities
 *
 * @section_end
 *
 * @module_end
 */
