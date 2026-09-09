/**
 * @struct PlayServicesPlayerInfo
 * @desc The public Play Games Services profile of a player. Note that a string member is absent
 * rather than an empty string when Google holds no value for it, so you should check that it is
 * present before using it.
 * @member {String} [player_id] The unique Play Games Services ID of the player.
 * @member {String} [display_name] The display name of the player.
 * @member {String} [title] The in-game title of the player, for example an experience level title,
 * if they have one.
 * @member {String} [icon_image_uri] A URI for the icon sized profile image of the player. You should
 * convert it to a local path with ${function.play_services_uri_to_path} before loading it as a
 * sprite.
 * @member {String} [hi_res_image_uri] A URI for the high resolution profile image of the player. You
 * should convert it to a local path with ${function.play_services_uri_to_path} before loading it as
 * a sprite.
 * @struct_end
 */

/**
 * @struct PlayServicesPlayerStatsInfo
 * @desc The aggregate play statistics for a player, as estimated by Google Play Games Services. Any
 * of these members can come back as `-1`, which is Google's `UNSET_VALUE`, when there is not enough
 * data to calculate it, so you should check for `-1` before trusting a value.
 * [[Note: The `churn_probability`, `high_spender_probability`, `spend_probability` and
 * `total_spend_next_28_days` members mirror Google's own deprecated `PlayerStats` getters, which now
 * always return `-1` no matter what the real data for the player is. You should not rely on these
 * four for any real prediction.]]
 * @member {Real} average_session_length The average session length, in minutes.
 * @member {Real} days_since_last_played The approximate number of days since the player last played.
 * @member {Real} number_of_purchases The approximate number of in-app purchases that the player has
 * made.
 * @member {Real} number_of_sessions The approximate number of sessions that the player has had.
 * @member {Real} session_percentile The session count percentile of the player against the player
 * base of this game, from `0` to `1`, where a higher value means more sessions played.
 * @member {Real} spend_percentile The spend percentile of the player against the player base of this
 * game, from `0` to `1`, where a higher value means more spent.
 * @member {Real} churn_probability This is always `-1`. See the note above.
 * @member {Real} high_spender_probability This is always `-1`. See the note above.
 * @member {Real} spend_probability This is always `-1`. See the note above.
 * @member {Real} total_spend_next_28_days This is always `-1`. See the note above.
 * @struct_end
 */

/**
 * @function play_services_player_current
 * @desc This function loads the profile of the player that is currently signed in.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Struct.PlayServicesPlayerInfo} [player] The profile of the current player. This is only
 * present on success.
 * @event_end
 * @example
 * ```gml
 * play_services_player_current(function(_status, _player = undefined)
 * {
 *     if (_status.success)
 *         show_debug_message($"Signed in as {_player.display_name}");
 * });
 * ```
 * The code above loads the profile of the signed-in player and writes their display name to the
 * output log.
 * @function_end
 */

/**
 * @function play_services_player_current_id
 * @desc This function loads only the unique ID of the player that is currently signed in, without
 * the rest of their profile. It is cheaper than ${function.play_services_player_current}, so you
 * should prefer it when the ID is all that you need.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {String} [player_id] The unique ID of the current player. This is only present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_player_stats_load
 * @desc This function loads the aggregate play statistics for the player that is currently signed
 * in.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * You should pass `false` for most calls, so that the call can benefit from caching.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Struct.PlayServicesPlayerStatsInfo} [stats] The statistics for the player. This is only
 * present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_player_load
 * @desc This function loads the public profile of another player from their ID, for example that of
 * a friend, or that of the holder of a leaderboard score or an achievement.
 * @param {String} player_id The unique ID of the player to load.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Struct.PlayServicesPlayerInfo} [player] The profile of the requested player. This is only
 * present on success.
 * @event_end
 * @function_end
 */

/**
 * @module player
 * @title Player
 * @desc This module covers loading the profile and the statistics of the current player, and looking
 * up other players by their ID. See ${module.friends} for the friends list and for the system UIs
 * that search for a player and compare profiles.
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
