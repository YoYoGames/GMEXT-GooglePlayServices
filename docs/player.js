
/**
 * @struct PlayServicesPlayerInfo
 * @desc A player's public Play Games Services profile. String fields are absent, not empty strings,
 * when Google has no value for them - check presence before use.
 * @member {String} [player_id] The player's unique Play Games Services ID.
 * @member {String} [display_name] The player's display name.
 * @member {String} [title] The player's in-game title (e.g. an experience-level title), if any.
 * @member {String} [icon_image_uri] A URI for the player's icon-size profile image. Convert to a
 * local path with ${function.play_services_uri_to_path} before loading it as a sprite.
 * @member {String} [hi_res_image_uri] A URI for the player's hi-res profile image. Convert to a local
 * path with ${function.play_services_uri_to_path} before loading it as a sprite.
 * @struct_end
 */

/**
 * @struct PlayServicesPlayerStatsInfo
 * @desc Aggregate play statistics for a player, as estimated by Google Play Games Services. Any
 * field can come back as `-1` (Google's `UNSET_VALUE`) when there isn't enough data to calculate it -
 * check for `-1` before trusting a value.
 * [[Note: `churn_probability`, `high_spender_probability`, `spend_probability`, and
 * `total_spend_next_28_days` mirror Google's own deprecated `PlayerStats` getters, which now always
 * return `-1` regardless of the player's real data - do not rely on these four for real predictions.]]
 * @member {Real} average_session_length The average session length, in minutes.
 * @member {Real} days_since_last_played The approximate number of days since the player last played.
 * @member {Real} number_of_purchases The approximate number of in-app purchases made by the player.
 * @member {Real} number_of_sessions The approximate number of sessions the player has had.
 * @member {Real} session_percentile The player's session-count percentile versus this game's player
 * base, `0`-`1` (higher means more sessions played).
 * @member {Real} spend_percentile The player's spend percentile versus this game's player base,
 * `0`-`1` (higher means more spent).
 * @member {Real} churn_probability Always `-1` - see the note above.
 * @member {Real} high_spender_probability Always `-1` - see the note above.
 * @member {Real} spend_probability Always `-1` - see the note above.
 * @member {Real} total_spend_next_28_days Always `-1` - see the note above.
 * @struct_end
 */

/**
 * @function play_services_player_current
 * @desc Loads the currently signed-in player's own profile.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Struct.PlayServicesPlayerInfo} [player] The current player's profile. Only present on
 * success.
 * @event_end
 * @example
 * ```gml
 * play_services_player_current(function(_status, _player = undefined)
 * {
 *     if (_status.success)
 *         show_debug_message($"Signed in as {_player.display_name}");
 * });
 * ```
 * @function_end
 */

/**
 * @function play_services_player_current_id
 * @desc Loads just the currently signed-in player's unique ID, without the rest of the profile.
 * Cheaper than ${function.play_services_player_current} when only the ID is needed.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {String} [player_id] The current player's unique ID. Only present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_player_stats_load
 * @desc Loads aggregate play statistics for the currently signed-in player.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server. Prefer `false` for most calls to benefit from caching.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Struct.PlayServicesPlayerStatsInfo} [stats] The player's stats. Only present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_player_load
 * @desc Loads another player's public profile by ID (e.g. a friend or a leaderboard/achievement
 * score holder's ID).
 * @param {String} player_id The unique ID of the player to load.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Struct.PlayServicesPlayerInfo} [player] The requested player's profile. Only present on
 * success.
 * @event_end
 * @function_end
 */

/**
 * @module player
 * @title Player
 * @desc Loading the current player's own profile and stats, and looking up other players by ID. See
 * ${module.friends} for the friends list and the player-search/profile-compare system UIs.
 *
 * @section_func
 * @ref play_services_player_current
 * @ref play_services_player_current_id
 * @ref play_services_player_stats_load
 * @ref play_services_player_load
 * @section_end
 *
 * @section_struct
 * @ref PlayServicesPlayerInfo
 * @ref PlayServicesPlayerStatsInfo
 * @section_end
 *
 * @module_end
 */
