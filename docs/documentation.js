/**
 * @module home
 * @title GooglePlayServices
 *
 * @section Extension's Features
 * @desc
 *
 * * Sign in with Google Play Games Services and check or request the authentication state
 * * Read the profile, the statistics and the friends list of the current player, with consent
 *   handling
 * * Show the Player Search and the Player Profile Compare system UIs
 * * Unlock, reveal and increment achievements, and show the Achievements system UI
 * * Submit and load leaderboard scores, and show the Leaderboards system UI
 * * Save and load the progress of the player with the Saved Games service, including conflict
 *   resolution
 * * Convert a Play Games image URI into a local file path for use with ${function.sprite_add}
 *
 * @section_end
 *
 * @section Introduction
 *
 * @desc
 *
 * This extension wraps Google Play Games Services **v2** for **Android**. Every `play_services_*`
 * function **returns** a ${constant.PlayServicesError} straight away. A value of `Ok` means that the
 * call was accepted and that, if the function takes a `callback`, that callback will be called once
 * with the real result. Any other value means that the call was rejected before it ever reached
 * Google Play, because the player is not authenticated, because the activity is not available, or
 * because an argument was not valid, and in that case the callback is never called at all.
 *
 * Every callback is called exactly once, as `callback.call(status, data...)`. The `status` argument
 * is always a ${struct.PlayServicesResult}, which carries nothing but the success or the failure of
 * the call, and the real payload arrives as further, separate arguments rather than being bundled
 * into a result struct of its own. You should always check `status.success` before reading anything
 * else.
 *
 * Most functions require the player to be signed in first, so you should call
 * ${function.play_services_sign_in}, or rely on the automatic sign-in attempt that is made when the
 * game starts, before calling anything else. A handful of functions launch a native system UI, which
 * is the case for achievements, leaderboards, the saved games picker, player search and player
 * profile compare, instead of returning data directly.
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
