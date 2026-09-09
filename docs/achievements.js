/**
 * @struct PlayServicesAchievement
 * @desc The current state of an achievement for the signed-in player. Note that `current_steps` and
 * `total_steps` are only meaningful for an
 * ${constant.PlayServicesAchievementType}.Incremental achievement, as both of them are `0` for a
 * standard one.
 * @member {String} [achievement_id] The unique ID of the achievement.
 * @member {String} [name] The display name of the achievement.
 * @member {String} [description] The description of the achievement, which is typically the text
 * that explains how to unlock it.
 * @member {Enum.PlayServicesAchievementState} state The current state of the achievement.
 * @member {Enum.PlayServicesAchievementType} type Whether the achievement is standard or
 * incremental.
 * @member {Real} current_steps The number of steps that have been completed so far. This applies to
 * incremental achievements only.
 * @member {Real} total_steps The total number of steps that are required to unlock the achievement.
 * This applies to incremental achievements only.
 * @member {Real} last_updated_timestamp When the achievement was last updated, in milliseconds since
 * the epoch.
 * @member {Real} xp_value The XP that is awarded for unlocking the achievement.
 * @member {String} [revealed_image_uri] A URI for the revealed state image of the achievement. You
 * should convert it to a local path with ${function.play_services_uri_to_path} before loading it as
 * a sprite. This is only present once the achievement has been revealed.
 * @member {String} [unlocked_image_uri] A URI for the unlocked state image of the achievement. You
 * should convert it to a local path with ${function.play_services_uri_to_path} before loading it as
 * a sprite. This is only present once the achievement has been unlocked.
 * @struct_end
 */

/**
 * @function play_services_achievements_show
 * @desc This function shows the system Achievements UI overlay, which lists every achievement for
 * this game.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_achievements_increment
 * @desc This function increases the progress of an incremental achievement by a number of steps. It
 * has no effect beyond unlocking the achievement once `total_steps` has been reached, so calling it
 * again afterwards is harmless. You should use ${function.play_services_achievements_set_steps}
 * instead if you want to set an absolute step count rather than add to the current one.
 * @param {String} achievement_id The unique ID of the incremental achievement.
 * @param {Real} steps The number of steps to add.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the request.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_reveal
 * @desc This function changes the state of a hidden achievement to
 * ${constant.PlayServicesAchievementState}.Revealed for the signed-in player, without unlocking it.
 * @param {String} achievement_id The unique ID of the achievement.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the request.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_set_steps
 * @desc This function sets the progress of an incremental achievement to *at least* the given number
 * of steps, as it never decreases the progress that the player already has. You should use
 * ${function.play_services_achievements_increment} instead if you want to add to the current count
 * rather than set an absolute value.
 * @param {String} achievement_id The unique ID of the incremental achievement.
 * @param {Real} steps The absolute step count to set.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the request.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_achievements_unlock
 * @desc This function unlocks the given achievement for the signed-in player, setting its state to
 * ${constant.PlayServicesAchievementState}.Unlocked. For an incremental achievement, this completes
 * it immediately, whatever its current step count is.
 * @param {String} achievement_id The unique ID of the achievement.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the request.
 * @event_end
 * @example
 * ```gml
 * play_services_achievements_unlock("CgkI....", function(_status)
 * {
 *     if (_status.success)
 *         show_debug_message("Achievement unlocked");
 * });
 * ```
 * The code above unlocks the achievement with the given ID and writes a message to the output log
 * once Google Play has confirmed it.
 * @function_end
 */

/**
 * @function play_services_achievements_get_status
 * @desc This function loads the current state of every achievement in this game for the signed-in
 * player.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Array[Struct.PlayServicesAchievement]} achievements The current state of every
 * achievement. This is empty on failure.
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
 * The code above loads every achievement from the cache where possible, and writes the name and the
 * state of each one to the output log.
 * @function_end
 */

/**
 * @const PlayServicesAchievementState
 * @desc The unlock state of an achievement.
 * @member Unlocked The achievement has been unlocked.
 * @member Revealed The achievement is visible to the player, but it has not been unlocked yet.
 * @member Hidden The existence of the achievement is not shown to the player yet.
 * @const_end
 */

/**
 * @const PlayServicesAchievementType
 * @desc Whether an achievement unlocks all at once or tracks incremental progress.
 * @member Standard The achievement is unlocked in a single step, using
 * ${function.play_services_achievements_unlock} only.
 * @member Incremental The achievement tracks progress through `current_steps` and `total_steps`, so
 * ${function.play_services_achievements_increment} and
 * ${function.play_services_achievements_set_steps} apply to it.
 * @const_end
 */

/**
 * @module achievements
 * @title Achievements
 * @desc This module covers unlocking, revealing and tracking progress on achievements, along with
 * the system Achievements UI.
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
