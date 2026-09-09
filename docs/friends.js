/**
 * @function play_services_friends_load
 * @desc This function loads the first page of the friends of the signed-in player who also play this
 * game, without ever prompting for the Play Games friends list permission. Only those players who
 * have already granted that permission, or who do not require it, are returned. You should use
 * ${function.play_services_friends_load_with_consent} instead if you want the player to be prompted
 * for consent when it is needed.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Real} max_results The maximum number of friends to return in this page, which is clamped
 * to the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails. The `needs_consent` member is always `false`
 * for this function, as it never prompts and only reports what is already visible.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. This is empty
 * on failure.
 * @member {Bool} has_more Whether a further page is available through
 * ${function.play_services_friends_load_more}.
 * @member {Bool} needs_consent This is always `false` for this function.
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
 * The code above loads a full page of friends from the cache where possible, and writes the display
 * name of each one to the output log.
 * @function_end
 */

/**
 * @function play_services_friends_load_more
 * @desc This function loads the next page of friends, following an earlier call to
 * ${function.play_services_friends_load} or ${function.play_services_friends_load_with_consent} in
 * this session.
 * @param {Real} page_size The maximum number of friends to return in this page, which is clamped to
 * the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if no page of friends has been loaded yet in this
 * session.
 * @event callback
 * @desc Called once, when the load completes or fails. The `needs_consent` member is always `false`
 * for this function.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. This is empty
 * on failure.
 * @member {Bool} has_more Whether a further page is still available.
 * @member {Bool} needs_consent This is always `false` for this function.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_friends_load_with_consent
 * @desc This function loads the first page of friends in the same way that
 * ${function.play_services_friends_load} does, except that if the player has not yet granted the
 * Play Games friends list permission then a system consent dialog is shown to ask them for it before
 * the call completes.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Real} max_results The maximum number of friends to return in this page, which is clamped
 * to the range [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE].
 * @param {Function} callback The function to call once the load, and any consent prompt, completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load, along with any consent prompt that was shown in between,
 * completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Array[Struct.PlayServicesPlayerInfo]} players The loaded page of friends. This is empty
 * on failure.
 * @member {Bool} has_more Whether a further page is available through
 * ${function.play_services_friends_load_more}.
 * @member {Bool} needs_consent Whether the player denied or dismissed the consent dialog, which lets
 * you tell that case apart from a genuine error and offer your own way to ask again. It is `false`
 * for every other outcome, including success.
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
 *             // the player denied the consent dialog, so offer a way to try again
 *         }
 *     });
 * ```
 * The code above loads the friends list, prompting the player for consent if that is required, and
 * handles a denied prompt separately from a real failure.
 * @function_end
 */

/**
 * @function play_services_player_profile_show
 * @desc This function shows the system UI that compares the profile of the current player against
 * that of another player, placing their achievements, statistics and so on side by side. It does not
 * report a result back through a callback, as the player simply dismisses the UI and control returns
 * to the game.
 * @param {String} player_id The unique ID of the player to compare against.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_player_search_show
 * @desc This function shows the system Player Search UI, which lets the player search for and pick
 * another Play Games player.
 * @param {Function} callback The function to call once the UI is dismissed.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the UI is dismissed, whether a player was picked, the search was
 * cancelled, or the UI failed to launch.
 * @member {Struct.PlayServicesResult} status The outcome of the search. The `success` member is
 * `false` if the player cancelled the search or if no player was selected.
 * @member {Struct.PlayServicesPlayerInfo} [player] The selected player. This is only present on
 * success.
 * @event_end
 * @function_end
 */

/**
 * @module friends
 * @title Friends
 * @desc This module covers loading the friends list of the current player, with optional consent
 * handling, along with the system UIs for searching for a player and for comparing profiles.
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
