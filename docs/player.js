// FUNCTIONS

/**
 * @func GooglePlayServices_Player_Current
 * @desc This function queries the Google Play server for information on the current signed-in player.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Player_Current"`
 * @member {boolean} success Whether or not the function request succeeded
 * @member {string} player The information of the player as a json formatted string of ${struct.PlayerJSON}. This string can be parsed into a struct with the function ${function.json_parse}.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Player_Current()
 * ```
 * The code sample above will trigger a player data fetch and the callback can be caught inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_Player_Current")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     var playerInfo = json_parse(async_load[? "player"]);
 * 
 *     // ### Available members #### (some are optional, check documentation)
 *     // bannerImageLandscapeUri
 *     // bannerImagePortraitUri
 *     // displayName
 *     // hiResImageUri
 *     // iconImageUri
 *     // currentXpTotal
 *     // lastLevelUpTimestamp
 *     // currentLevelNumber
 *     // currentMaxXp
 *     // currentMinXp
 *     // nextLevelNumber
 *     // nextMaxXp
 *     // nextMinXp
 *     // playerId
 *     // retrievedTimestamp
 *     // title
 * 
 * }
 * ```
 * The code above matches the response against the correct event type and parses the player data string into a struct (${struct.PlayerJSON}) using the function ${function.json_parse}. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Player_Current() {}

/** 
 * @func GooglePlayServices_Player_CurrentID
 * @desc This function queries the Google Play server for information on the current player's unique id.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Player_CurrentID"`
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} playerID The unique identifier of the current player.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Player_CurrentID()
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_Player_CurrentID")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     var playerId = async_load[? "playerID"];
 * }
 * ```
 * The code above matches the response against the correct event type and cache the playerID into a variable.
 * @func_end
 */
function GooglePlayServices_Player_CurrentID() {}

/** 
 * @func GooglePlayServices_PlayerStats_LoadPlayerStats
 * @desc This function queries the Google Play server for a lot of statistical values regarding the logged in player.
 * @returns {real}
 * 
 * @event social 
 * @member {string} type The string `"GooglePlayServices_PlayerStats_LoadPlayerStats"`.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {real} AverageSessionLength The average session length of the player in minutes.
 * @member {real} DaysSinceLastPlayed The approximate number of days since the player last played.
 * @member {real} NumberOfPurchases The approximate number of in-app purchases for the player.
 * @member {real} NumberOfSessions The approximate number of sessions of the player.
 * @member {real} SessionPercentile The approximation of sessions percentile for the player.
 * @member {real} SpendPercentile The approximate spend percentile of the player.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_PlayerStats_LoadPlayerStats()
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_PlayerStats_LoadPlayerStats")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     // Do something with the data
 * }
 * ```
 * The code above matches the response against the correct event type and bails out if the task was not successful.
 * @func_end
 */
function GooglePlayServices_PlayerStats_LoadPlayerStats() {}

// STRUCTS

/** 
 * @struct PlayerJSON
 * @desc Represents a player and its associated metadata.
 * @member {string} [bannerImageLandscapeUri] The URI for loading this player's landscape banner image (see GooglePlayServices_UriToPath). Not present if the player has no landscape banner image image.
 * @member {string} [bannerImagePortraitUri] The URI for loading this player's portrait banner image (see GooglePlayServices_UriToPath). Not present if the player has no portrait banner image.
 * @member {string} displayName The display name for this player.
 * @member {string} [hiResImageUri] The URI for loading this player's hi-res profile image (see GooglePlayServices_UriToPath). Not present if the player has no high-resolution image.
 * @member {string} [iconImageUri] The URI for loading this player's icon-size profile image (see GooglePlayServices_UriToPath). Not present if the player has no icon image.
 * @member {real} currentXpTotal The player's current XP value.
 * @member {real} lastLevelUpTimestamp The timestamp of the player's last level-up.
 * @member {real} currentLevelNumber The number for the current level.
 * @member {real} currentMaxXp The maximum XP value represented by the current level, exclusive.
 * @member {real} currentMinXp The minimum XP value needed to attain the current level, inclusive.
 * @member {real} [nextLevelNumber] The number for the next level. Not present if the player is already at the maximum level.
 * @member {real} [nextMaxXp] The maximum XP value represented by the next level, exclusive. Not present if the player is already at the maximum level.
 * @member {real} [nextMinXp] The minimum XP value needed to attain the next level, inclusive. Not present if the player is already at the maximum level.
 * @member {string} playerId The ID of this player.
 * @member {real} retrievedTimestamp The timestamp at which this player record was last updated locally.
 * @member {string} title The title of the player.
 * @member {boolean} hasHiResImage Indicates whether this player has a hi-res profile image to display.
 * @member {boolean} hasIconImage Indicates whether this player has an icon-size profile image to display.
 * @struct_end
 */

// MODULES

/**
 * @module player
 * @title Player
 * @desc Being able to access player information allows for a much better gaming experience. The game can be tailored to the gamer in new and creative ways. This functions will allow you to gain that kind of costumization.
 * 
 * @section_func
 * @desc The following functions are provided for working with player data:
 * @reference GooglePlayServices_Player*
 * @section_end
 * 
 * @section_struct
 * @desc The following structures are used by the API to expose data to the developer:
 * @reference PlayerJSON
 * @section_end
 */

