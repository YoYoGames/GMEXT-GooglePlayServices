
/**
 * @struct PlayServicesAchievement
 * @desc An achievement's current state for the signed-in player. `current_steps`/`total_steps` are
 * only meaningful for ${constant.PlayServicesAchievementType}.Incremental achievements - both are `0`
 * for a Standard achievement.
 * @member {String} [achievement_id] The achievement's unique ID.
 * @member {String} [name] The achievement's display name.
 * @member {String} [description] The achievement's description (typically the "how to unlock" text).
 * @member {Enum.PlayServicesAchievementState} state The achievement's current state.
 * @member {Enum.PlayServicesAchievementType} type Whether this achievement is standard or
 * incremental.
 * @member {Real} current_steps The number of steps completed so far. Incremental achievements only.
 * @member {Real} total_steps The total number of steps required to unlock. Incremental achievements
 * only.
 * @member {Real} last_updated_timestamp When this achievement was last updated, in milliseconds since
 * epoch.
 * @member {Real} xp_value The XP awarded for unlocking this achievement.
 * @member {String} [revealed_image_uri] A URI for the achievement's revealed-state image. Convert to
 * a local path with ${function.play_services_uri_to_path} before loading it as a sprite. Only present
 * once the achievement has been revealed.
 * @member {String} [unlocked_image_uri] A URI for the achievement's unlocked-state image. Convert to
 * a local path with ${function.play_services_uri_to_path} before loading it as a sprite. Only present
 * once the achievement has been unlocked.
 * @struct_end
 */

/**
 * @function play_services_achievements_show
 * @desc Shows the system Achievements UI overlay, listing every achievement for this game.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_achievements_increment
 * @desc Increments an incremental achievement's progress by a number of steps. Has no effect beyond
 * unlocking the achievement once `total_steps` is reached - calling it again afterward is harmless.
 * Use ${function.play_services_achievements_set_steps} instead if you want to set an absolute step
 * count rather than add to it.
 * @param {String} achievement_id The unique ID of the (incremental) achievement.
 * @param {Real} steps The number of steps to add.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The request's outcome.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_reveal
 * @desc Changes a hidden achievement's state to ${constant.PlayServicesAchievementState}.Revealed for
 * the signed-in player, without unlocking it.
 * @param {String} achievement_id The unique ID of the achievement.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The request's outcome.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_set_steps
 * @desc Sets an incremental achievement's progress to *at least* the given number of steps - it never
 * decreases progress the player already has. Use
 * ${function.play_services_achievements_increment} instead if you want to add to the current count
 * rather than set an absolute value.
 * @param {String} achievement_id The unique ID of the (incremental) achievement.
 * @param {Real} steps The absolute step count to set.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The request's outcome.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_unlock
 * @desc Unlocks (${constant.PlayServicesAchievementState}.Unlocked) the given achievement for the
 * signed-in player. For an incremental achievement, this immediately completes it regardless of its
 * current step count.
 * @param {String} achievement_id The unique ID of the achievement.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The request's outcome.
 * @event_end
 * @example
 * ```gml
 * play_services_achievements_unlock("CgkI....", function(_status)
 * {
 *     if (_status.success)
 *         show_debug_message("Achievement unlocked");
 * });
 * ```
 * @function_end
 */

/**
 * @function play_services_achievements_get_status
 * @desc Loads the current state of every achievement for this game, for the signed-in player.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Array[Struct.PlayServicesAchievement]} achievements Every achievement's current state.
 * Empty on failure.
 * @event_end
 * @example
 * ```gml
 * play_services_achievements_get_status(false, function(_status, _achievements)
 * {
 *     if (!_status.success) return;
 *
 *     for (var i = 0; i < array_length(_achievements); i++)
 *     {
 *         var _achievement = _achievements[i];
 *         show_debug_message($"{_achievement.name}: {_achievement.state}");
 *     }
 * });
 * ```
 * @function_end
 */

/**
 * @const PlayServicesAchievementState
 * @desc An achievement's unlock state.
 * @member Unlocked The achievement has been unlocked.
 * @member Revealed The achievement is visible to the player but not yet unlocked.
 * @member Hidden The achievement's existence is not yet shown to the player.
 * @const_end
 */

/**
 * @const PlayServicesAchievementType
 * @desc Whether an achievement unlocks all at once or tracks incremental progress.
 * @member Standard Unlocked in a single step - ${function.play_services_achievements_unlock} only.
 * @member Incremental Tracks progress via `current_steps`/`total_steps` -
 * ${function.play_services_achievements_increment}/${function.play_services_achievements_set_steps}
 * apply.
 * @const_end
 */

/**
 * @module achievements
 * @title Achievements
 * @desc Unlocking, revealing, and tracking progress on achievements, plus the system Achievements UI.
 *
 * @section_func
 * @ref play_services_achievements_show
 * @ref play_services_achievements_increment
 * @ref play_services_achievements_reveal
 * @ref play_services_achievements_set_steps
 * @ref play_services_achievements_unlock
 * @ref play_services_achievements_get_status
 * @section_end
 *
 * @section_struct
 * @ref PlayServicesAchievement
 * @section_end
 *
 * @section_const
 * @ref PlayServicesAchievementState
 * @ref PlayServicesAchievementType
 * @section_end
 *
 * @module_end
 */
