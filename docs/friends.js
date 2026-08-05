
/**
 * @function play_services_friends_load
 * @desc Loads the first page of the currently signed-in player's friends who also play this game,
 * without prompting for the Play Games friends-list permission (only players who have already granted
 * it, or don't require it, are returned). Use ${function.play_services_friends_load_with_consent}
 * instead if you want to prompt the player for consent when needed.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Real} max_results The maximum number of friends to return in this page, clamped to
 * the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails. `needs_consent` is always `false` for this
 * function - it never prompts, it only reports what's already visible.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. Empty on
 * failure.
 * @member {Bool} has_more Whether a further page is available via
 * ${function.play_services_friends_load_more}.
 * @member {Bool} needs_consent Always `false` for this function.
 * @event_end
 * @example
 * ```gml
 * play_services_friends_load(false, PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE,
 *     function(_status, _players, _has_more, _needs_consent)
 *     {
 *         if (_status.success)
 *         {
 *             for (var i = 0; i < array_length(_players); i++)
 *                 show_debug_message(_players[i].display_name);
 *         }
 *     });
 * ```
 * @function_end
 */

/**
 * @function play_services_friends_load_more
 * @desc Loads the next page of friends following a prior
 * ${function.play_services_friends_load}/${function.play_services_friends_load_with_consent} call in
 * this session.
 * @param {Real} page_size The maximum number of friends to return in this page, clamped to
 * the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated/${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if no friends page has been loaded yet this session.
 * @event callback
 * @desc Fires once, when the load completes or fails. `needs_consent` is always `false` for this
 * function.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. Empty on
 * failure.
 * @member {Bool} has_more Whether a further page is still available.
 * @member {Bool} needs_consent Always `false` for this function.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_friends_load_with_consent
 * @desc Loads the first page of friends like ${function.play_services_friends_load}, but if the
 * player hasn't yet granted the Play Games friends-list permission, this function prompts them for it
 * via a system consent dialog before completing.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Real} max_results The maximum number of friends to return in this page, clamped to
 * the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load (and any consent prompt) completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load (and any consent prompt shown in between) completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. Empty on
 * failure.
 * @member {Bool} has_more Whether a further page is available via
 * ${function.play_services_friends_load_more}.
 * @member {Bool} needs_consent `true` only when the player denied or dismissed the consent dialog -
 * distinguishes "show your own re-request UI" from a genuine error. `false` for every other outcome,
 * including success.
 * @event_end
 * @example
 * ```gml
 * play_services_friends_load_with_consent(false, PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE,
 *     function(_status, _players, _has_more, _needs_consent)
 *     {
 *         if (_status.success)
 *         {
 *             // got the friends list, possibly after the player granted consent
 *         }
 *         else if (_needs_consent)
 *         {
 *             // player denied the consent dialog - offer a way to try again
 *         }
 *     });
 * ```
 * @function_end
 */

/**
 * @function play_services_player_profile_show
 * @desc Shows the system UI comparing the current player's profile against another player's
 * (achievements, stats, etc. side by side). This function doesn't report a result back through a
 * callback - the UI is dismissed by the player and control simply returns to the game.
 * @param {String} player_id The unique ID of the player to compare against.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_player_search_show
 * @desc Shows the system Player Search UI, letting the player search for and pick another Play Games
 * player.
 * @param {Function} callback The function to call once the UI is dismissed.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the UI is dismissed - whether a player was picked, the search was
 * cancelled, or it failed to launch.
 * @member {Struct.PlayServicesResult} status The search's outcome. `success` is `false` if the
 * player cancelled the search or none was selected.
 * @member {Struct.PlayServicesPlayerInfo} [player] The selected player. Only present on success.
 * @event_end
 * @function_end
 */

/**
 * @module friends
 * @title Friends
 * @desc Loading the current player's friends list (with optional consent handling), and the system
 * UIs for searching for a player and comparing profiles.
 *
 * @section_func
 * @ref play_services_friends_load
 * @ref play_services_friends_load_more
 * @ref play_services_friends_load_with_consent
 * @ref play_services_player_profile_show
 * @ref play_services_player_search_show
 * @section_end
 *
 * @module_end
 */
