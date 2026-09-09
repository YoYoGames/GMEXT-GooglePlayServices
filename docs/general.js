/**
 * @struct PlayServicesResult
 * @desc The uniform outcome record that is given as the **first** argument of every asynchronous
 * callback in this extension. It carries nothing but the success or the failure of the call, as the
 * real payload always arrives as further, separate arguments to `callback.call(status, data...)`
 * rather than being bundled into this struct. You should always check `success` before trusting
 * anything else that the callback was given.
 * @member {Bool} success Whether the operation succeeded.
 * @member {String} error The error message on failure. This is an empty string on success.
 * @struct_end
 */

/**
 * @function play_services_is_available
 * @desc This function checks whether Google Play Services is installed and up to date on the device
 * that the game is running on. You should call it before using any other function in this extension.
 * @returns {Bool} `true` if Google Play Services is available.
 * @example
 * ```gml
 * if (play_services_is_available())
 *     play_services_sign_in(sign_in_callback);
 * ```
 * The code above checks that Google Play Services is present on the device, and only starts a
 * sign-in attempt if it is.
 * @function_end
 */

/**
 * @function play_services_sign_in
 * @desc This function asks Play Games Services to sign the player in.
 * [[Note: A sign-in attempt is made automatically when the game starts, so you only need to call
 * this function yourself if that automatic attempt failed, or if you want to prompt the player again
 * after they have signed out.]]
 * @param {Function} callback The function to call once the sign-in attempt completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Called once, when the sign-in attempt completes.
 * @member {Struct.PlayServicesResult} status The outcome of the sign-in request. Note that
 * `status.success` can be `true` while `is_authenticated` is `false`, as the request itself
 * completed and it was the player who chose not to authenticate.
 * @member {Bool} is_authenticated Whether the player is authenticated after this attempt.
 * @event_end
 * @example
 * ```gml
 * play_services_sign_in(function(_status, _is_authenticated)
 * {
 *     if (_status.success && _is_authenticated)
 *         show_debug_message("Signed in to Google Play Games");
 * });
 * ```
 * The code above starts a sign-in attempt and writes a message to the output log once the player is
 * signed in.
 * @function_end
 */

/**
 * @function play_services_is_authenticated
 * @desc This function asks Google Play Games Services for the current sign-in and authentication
 * status of the player.
 * @param {Function} callback The function to call once the query completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Called once, when the query completes.
 * @member {Struct.PlayServicesResult} status The outcome of the query.
 * @member {Bool} is_authenticated Whether the player is currently authenticated.
 * @event_end
 * @example
 * ```gml
 * play_services_is_authenticated(function(_status, _is_authenticated)
 * {
 *     if (_status.success && !_is_authenticated)
 *         play_services_sign_in(sign_in_callback);
 * });
 * ```
 * The code above checks the current authentication status and, if the player is not yet
 * authenticated, prompts them to sign in.
 * @function_end
 */

/**
 * @function play_services_request_server_side_access
 * @desc This function requests server-side access to Play Games Services for the player that is
 * currently signed in, which is what a game backend needs in order to authenticate that player
 * independently. It gives you back an authorization code that your server can exchange for an access
 * token, and, if `force_refresh_token` is `true`, for a refresh token as well.
 *
 * A refresh token lets your server keep requesting new access tokens while the player is not
 * actively playing. Note that refresh tokens are only issued for players who have auto sign-in
 * enabled.
 * @param {String} server_client_id The OAuth 2.0 web client ID of the server that performs the
 * authorization code exchange.
 * @param {Bool} force_refresh_token Whether to also request a refresh token when the authorization
 * code is exchanged.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the request.
 * @member {String} [auth_code] The authorization code that your server is to exchange. This is only
 * present on success.
 * @event_end
 * @example
 * ```gml
 * play_services_request_server_side_access("your-server-client-id.apps.googleusercontent.com", false,
 *     function(_status, _auth_code = undefined)
 *     {
 *         if (_status.success)
 *         {
 *             // send _auth_code to your backend to exchange for an access token
 *         }
 *     });
 * ```
 * The code above requests an authorization code without a refresh token, which the game would then
 * pass on to its own backend.
 * @function_end
 */

/**
 * @const PlayServicesError
 * @desc The synchronous return code that every `play_services_*` function reports before the
 * underlying Google Play Games call is ever attempted. A value of `Ok` means that the call was
 * accepted and that, for a function that takes a `callback`, that callback will be called once the
 * real asynchronous result is known. Any other value means that the call was rejected up front and
 * that the callback is never called at all for that invocation.
 * @member Ok The call was accepted.
 * @member NotAuthenticated The player is not authenticated. You should call
 * ${function.play_services_sign_in} or ${function.play_services_is_authenticated} first.
 * @member ActivityNull The game's activity is not available yet, which means that the call was made
 * too early in the application's lifecycle.
 * @member InvalidArgument An argument was not valid for the current state, for example a saved game
 * slot name that has not been opened, or a page of friends that was requested before the first page
 * was loaded.
 * @const_end
 */

/**
 * @const macros
 * @desc The shared limits that the Google Play Games Services API enforces. A value outside one of
 * these ranges is silently clamped rather than rejected, with a warning written to the native log.
 * @member PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE The maximum number of friends that
 * ${function.play_services_friends_load}, ${function.play_services_friends_load_more} and
 * ${function.play_services_friends_load_with_consent} return per page.
 * @member PLAY_SERVICES_MAX_LEADERBOARD_RESULTS The maximum number of scores that
 * ${function.play_services_leaderboard_load_player_centered_scores} and
 * ${function.play_services_leaderboard_load_top_scores} return per page.
 * @member PLAY_SERVICES_MIN_PAGE_SIZE The minimum page size that any of the paged loading functions
 * above will accept.
 * @const_end
 */

/**
 * @module general
 * @title General
 * @desc This module covers availability, sign-in and authentication, and server-side access. It also
 * holds the shared ${struct.PlayServicesResult} record and the ${constant.PlayServicesError} codes
 * that the functions of every other module use.
 *
 * @section_func
 * @desc Availability and authentication.
 * @ref play_services_is_available
 * @ref play_services_sign_in
 * @ref play_services_is_authenticated
 * @ref play_services_request_server_side_access
 * @section_end
 *
 * @section_struct
 * @desc The shared result record that is used across every module.
 * @ref PlayServicesResult
 * @section_end
 *
 * @section_const
 * @ref PlayServicesError
 * @ref macros
 * @section_end
 *
 * @module_end
 */
