
// FUNCTIONS

/**
 * @func GooglePlayServices_IsAuthenticated
 * @desc Queries the servers for the current authentication status.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_IsAuthenticated"`
 * @member {boolean} success Whether or not the function request succeeded
 * @member {boolean} isAuthenticated Whether or not player is authenticated
 * @event_end
 * @example
 * ```gml
 * GooglePlayServices_IsAuthenticated()
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_IsAuthenticated")
 * {
 *     if(!async_load[?"success"])
 *      exit
 *
 *     if(async_load[?"isAuthenticated"]);
 *     {
 *         show_debug_message("GoolePlayServices Player Authenticated")
 *     }
 *     else
 *     {
 *         GooglePlayServices_SignIn()
 *     }
 * }
 * ```
 * The code above matches the response against the correct event type and, if the player is not authenticated yet, it calls ${function.GooglePlayServices_SignIn} to initiate the sign in process.
 * @func_end
 */
function GooglePlayServices_IsAuthenticated() {}

/** 
 * @func GooglePlayServices_IsAvailable
 * @desc This function returns whether or not the user has GooglePlayServices installed into their devices. This is required for using any of the extension functions.
 * @returns {boolean}
 * @example
 * ```gml
 * if(GooglePlayServices_IsAvailable())
 * {
 *     show_debug_message("GooglePlayServices Available")
 *     //Do something
 * }
 * ```
 * The code sample above will check if the GooglePlayServices are installed into the device after a positive result from this call you can call any of the function from the extension.
 * @func_end
 */
function GooglePlayServices_IsAvailable() {}

/** 
 * @func GooglePlayServices_RequestServerSideAccess
 * @desc Requests server-side access to Play Games Services for the currently signed-in player, this is necessary for 3rd party apps that need GooglePlayServices authentication.
 * 
 * When requested, an authorization code is returned that can be used by your server to exchange for an access token (and conditionally a refresh token when `forceRefreshToken` is `true`). The access token may then be used by your server to access the Play Games Services web APIs. This is commonly used to complete a sign-in flow by verifying the Play Games Services player ID.
 * 
 * If `forceRefreshToken` is `true`, when exchanging the authorization code, a refresh token will be returned in addition to the access token. The refresh token allows your server to request additional access tokens, allowing your server to continue accesses Play Games Services while the user is not actively playing your game. Refresh tokens are only generated for players that have auto sign-in setting enabled.
 * @param {string} serverClientId The client ID of the server that will perform the authorization code flow exchange.
 * @param {boolean} forceRefreshToken If `true`, when the returned authorization code is exchanged, a refresh token will be included in addition to an access token.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_RequestServerSideAccess"`
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} authCode The authorization code
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_RequestServerSideAccess()
 * ```
 * The code sample above requests for an authorization code that will allow server side access, the code can be caught inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_RequestServerSideAccess")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     var authorizationCode = async_load[?"authCode"];
 *     // Use the code to request a accessToken (with optional refreshToken)
 * 
 * }
 * ```
 * The code above matches the response against the correct event type and in case of success caches the authorization code that can be later used on 3rd party libraries.
 * 
 * @func_end
 */
function GooglePlayServices_RequestServerSideAccess() {}

/** 
 * @func GooglePlayServices_SignIn
 * @desc Manually requests that your game sign in with Play Games Services.
 * 
 * [[NOTE: A sign-in attempt will be made automatically when your game starts. Games will only need to manually request to sign in if the automatic sign-in attempt failed.]]
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_SignIn"`
 * @member {boolean} success Whether or not the function request succeeded
 * @member {boolean} isAuthenticated Whether or not player is authenticated
 * @event_end
 * @example
 * ```gml
 * GooglePlayServices_SignIn()
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SignIn")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     if(async_load[?"isAuthenticated"]);
 *     {
 *         show_debug_message("GoolePlayServices Player Authenticated")
 *     }
 *     else
 *     {
 *         show_debug_message("Lets continue without GooglePlayGameServices")
 *     }
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task.
 * @func_end
 */
function GooglePlayServices_SignIn() {}

// STRUCTS

/** 
 * @struct GameJSON
 * @desc This is the GameJSON is a json formatted string representing a game and its associated metadata.
 * @member {boolean} areSnapshotsEnabled Whether or not this game supports snapshots.
 * @member {number} achievementTotalCount The number of achievements registered for this game.
 * @member {string} applicationId The application ID for this game.
 * @member {string} description The description of this game.
 * @member {string} developerName The name of the developer of this game.
 * @member {string} displayName The display name for this game.
 * @member {string} [featuredImageUri] An image URI that can be used to load the game's featured (banner) image from Google Play (see ${function.GooglePlayServices_UriToPath}). Not present if the game has no featured image.
 * @member {string} [hiResImageUri] An image URI that can be used to load the game's hi-res image (see ${function.GooglePlayServices_UriToPath}). Not present if the game has no high-res image.
 * @member {string} [iconImageUri] An image URI that can be used to load the game's icon (see ${function.GooglePlayServices_UriToPath}). Not present if the game has no icon image.
 * @member {number} leaderboardCount The number of leaderboards registered for this game.
 * @member {string} primaryCategory The primary category of the game.
 * @member {string} secondaryCategory The secondary category of the game.
 * @member {string} themeColor The theme color for this game.
 * @member {boolean} gamepadSupport Whether or not this game is marked as supporting gamepads.
 * @struct_end
 */

// MODULES

/**
 * @module general
 * @title General
 * @desc Play Games Services sign-in provides you with a player's gaming identity, which is a platform-level, gaming-specific identity for Android players. This identity helps build a relationship between your game and the player. Players are more willing to use this identity to sign in than with alternate centralized systems.
 * @section_func
 * @desc The following functions are provided for general tasks:
 * @reference GooglePlayServices_IsAuthenticated
 * @reference GooglePlayServices_IsAvailable
 * @reference GooglePlayServices_RequestServerSideAccess
 * @reference GooglePlayServices_SignIn
 * @section_end
 * 
 * @section_struct
 * @desc The following structures are provided as response for some GooglePlayServices API calls:
 * @reference GameJSON
 * @section_end
 */