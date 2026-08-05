
/**
 * @struct PlayServicesResult
 * @desc The uniform success/failure envelope delivered as the first argument to every async callback
 * in this extension. The real payload (if any) always rides as further, separate positional arguments
 * to `callback.call(status, data...)` - never bundled into this struct.
 * @member {Bool} success Whether the operation succeeded.
 * @member {String} error The error message on failure; an empty string on success.
 * @struct_end
 */

/**
 * @function play_services_is_available
 * @desc Checks whether Google Play Services is installed and up to date on the current device. Call
 * this before using any other function in this extension.
 * @returns {Bool} `true` if Google Play Services is available.
 * @example
 * ```gml
 * if (play_services_is_available())
 *     play_services_sign_in(sign_in_callback);
 * ```
 * @function_end
 */

/**
 * @function play_services_sign_in
 * @desc Manually requests that the game sign in with Play Games Services.
 * [[Note: A sign-in attempt is made automatically when the game starts. Games only need to call this
 * manually if the automatic sign-in attempt failed, or to re-prompt after a manual sign-out.]]
 * @param {Function} callback The function to call once the sign-in attempt completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Fires once, when the sign-in attempt completes.
 * @member {Struct.PlayServicesResult} status The sign-in request's outcome. Note `status.success` can
 * be `true` even when `is_authenticated` is `false` - the request itself completed, but the player
 * chose not to authenticate.
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
 * @function_end
 */

/**
 * @function play_services_is_authenticated
 * @desc Queries Google Play Games Services for the current sign-in/authentication status.
 * @param {Function} callback The function to call once the query completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Fires once, when the query completes.
 * @member {Struct.PlayServicesResult} status The query's outcome.
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
 * @function_end
 */

/**
 * @function play_services_request_server_side_access
 * @desc Requests server-side access to Play Games Services for the currently signed-in player -
 * necessary for a game backend that needs to authenticate the player independently. Returns an
 * authorization code your server can exchange for an access token (and, if `force_refresh_token` is
 * `true`, a refresh token as well).
 *
 * A refresh token lets your server keep requesting new access tokens while the player isn't actively
 * playing; refresh tokens are only issued for players who have auto sign-in enabled.
 * @param {String} server_client_id The OAuth 2.0 web client ID of the server that performs the
 * authorization code exchange.
 * @param {Bool} force_refresh_token Whether to also request a refresh token when the authorization
 * code is exchanged.
 * @param {Function} callback The function to call once the request completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the request completes or fails.
 * @member {Struct.PlayServicesResult} status The request's outcome.
 * @member {String} [auth_code] The authorization code, to be exchanged by your server. Only present
 * on success.
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
 * @function_end
 */

/**
 * @const PlayServicesError
 * @desc The synchronous pre-flight return code every `play_services_*` function reports before the
 * underlying Google Play Games call is ever attempted. `Ok` means the call was accepted and, for
 * functions that take a `callback`, that callback will fire once the real async result is known;
 * anything else means the callback never fires at all for that invocation.
 * @member Ok The call was accepted.
 * @member NotAuthenticated The player is not authenticated. Call ${function.play_services_sign_in} or
 * ${function.play_services_is_authenticated} first.
 * @member ActivityNull The game's activity is not available yet (too early in the app lifecycle).
 * @member InvalidArgument An argument was invalid for the current state (e.g. a saved-game slot name
 * that hasn't been opened, or a friends page requested before the first page was loaded).
 * @const_end
 */

/**
 * @const macros
 * @desc Shared limits enforced by the Google Play Games Services API. Values outside these ranges are
 * silently clamped (with a logcat warning), not rejected.
 * @member PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE The maximum number of friends returned per page by
 * ${function.play_services_friends_load}/${function.play_services_friends_load_more}/
 * ${function.play_services_friends_load_with_consent}.
 * @member PLAY_SERVICES_MAX_LEADERBOARD_RESULTS The maximum number of scores returned per page by
 * ${function.play_services_leaderboard_load_player_centered_scores}/
 * ${function.play_services_leaderboard_load_top_scores}.
 * @member PLAY_SERVICES_MIN_PAGE_SIZE The minimum page size accepted by any of the paged-loading
 * functions above.
 * @const_end
 */

/**
 * @module general
 * @title General
 * @desc Availability, sign-in/authentication, and server-side access - plus the shared
 * ${struct.PlayServicesResult} envelope and ${constant.PlayServicesError} codes every other module's
 * functions use.
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
 * @desc The shared result envelope used across every module.
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
