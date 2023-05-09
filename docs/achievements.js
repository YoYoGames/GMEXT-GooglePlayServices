// FUNCTIONS

/**
 * @func GooglePlayServices_Achievements_GetStatus
 * @desc This function requests the Google Play Services API to query the status of the achievements for the current player.
 * @param {boolean} forceReload Should we force reload the achievements status.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Achievements_GetStatus"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {string} data A json formatted string of an array of ${struct.AchievementStatusJSON}. This string can be parsed into a struct with the function ${function.json_parse}.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Achievements_GetStatus(achievementId);
 * ```
 * The code sample above will initialize the status querie for all the available achievements. The result can then be caught inside an ${event.social}.
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Achievements_GetStatus")
 * {
 *     var array = json_parse(async_load[?"data"])
 * 
 *     array_sort(array, function(_ach1, _ach2) { return _ach1.name < _ach2.name ? -1 : 1 });
 *     for(var a = 0 ; a < array_length(array) ; a++)
 *     {
 *         var struct = array[a];
 * 
 *         show_debug_message(struct);
 * 
 *         var ins = instance_create_depth(150+a*300,room_height/2-50,0,Obj_GooglePlayServices_Achievement_Entry)
 *         ins.ID = struct.id
 *         ins.description = struct.description
 *         ins.lastUpdatedTimestamp = struct.lastUpdatedTimestamp
 *         ins.name = struct.name
 *         ins.revealedImage = struct.revealedImage
 *         ins.state = struct.state
 *         ins.typeAchievement = struct.typeAchievement
 *         ins.unlockedImage = struct.unlockedImage
 *         ins.xpValue = struct.xpValue
 * 
 *         if(ins.typeAchievement == Achievement_TYPE_INCREMENTAL)
 *         {
 *             ins.currentSteps = struct.currentSteps
 *             ins.formattedCurrentSteps = struct.formattedCurrentSteps
 *             ins.formattedTotalSteps = struct.formattedTotalSteps
 *             ins.totalSteps = struct.totalSteps
 *         }
 *     }
 * }
 * ```
 * The code above shows a way of reading the returned data using the function ${function.json_parse}. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Achievements_GetStatus() {}

/** 
 * @func GooglePlayServices_Achievements_Increment
 * @desc This function requests the Google Play Services API to increment the achievement progress by a given amount of steps. Incremental achievements require a specific amount of steps before they are set as complete. You can also set the steps to a specific value using ${function.GooglePlayServices_Achievements_SetSteps}.
 * @param {string} achievementId The unique identifier of the achievement
 * @param {real} steps The amount of steps to increment by
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Achievements_Increment"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} achievement_id The unique name of the achievement
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Achievements_Increment(achievementId, 1);
 * ```
 * The code sample above will increment the number of steps for the specified acheivement by 1. The result can be caught inside an ${event.social} as follows:
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Achievements_Increment")
 * if(async_load[?"success"])
 * {    
 *     //Done, let's continue
 * }
 * ```
 * The code above checks if the task was successful. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Achievements_Increment() {}

/** 
 * @func GooglePlayServices_Achievements_Reveal
 * @desc This function requests the Google Play Services API to change the state of a given achievement to **revealed** (${constant.AchievementState}) for the currently signed in player.
 * @param {string} achievementId The unique identifier of the achievement
 * @returns {real}
 * 
 * @event social 
 * @member {string} type The string `"GooglePlayServices_Achievements_Reveal"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} achievement_id The unique name of the achievement
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Achievements_Reveal(achievementId);
 * ```
 * The code sample above will set the state of the specified acheivement to revealed. The result can be caught inside an ${event.social} as follows:
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Achievements_Reveal")
 * if(async_load[?"success"])
 * {    
 *     //Done, let's continue
 * }
 * ```
 * The code above checks if the task was successful. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Achievements_Reveal() {}

/** 
 * @func GooglePlayServices_Achievements_SetSteps
 * @desc This function requests the Google Play Services API to set the achievement progress to a given amount of steps. Incremental achievements require a specific amount of steps before they are set as complete. You can also increment the steps by a given amount using ${function.GooglePlayServices_Achievements_Increment}.
 * @returns {real}
 * 
 * @event social 
 * @member {string} type The string `"GooglePlayServices_Achievements_SetSteps"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} achievement_id The unique name of the achievement
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Achievements_SetSteps(achievementId,1);
 * ```
 * The code sample above will set the number of steps for the specified acheivement to 1. The result can be caught inside an ${event.social} as follows:
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Achievements_SetSteps")
 * if(async_load[?"success"])
 * {    
 *     //Done, let's continue
 * }
 * ```
 * The code above checks if the task was successful. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Achievements_SetSteps() {}

/** 
 * @func GooglePlayServices_Achievements_Show
 * @desc This function will call the Google Play Services achievement overlay with all your achievement information.
 * @example
 * ```gml
 * GooglePlayServices_Achievements_Show()
 * ```
 * The code above will trigger the achievement overlay.
 * @func_end
 */
function GooglePlayServices_Achievements_Show() {}


/** 
 * @func GooglePlayServices_Achievements_Unlock
 * @desc This function requests the Google Play Services API to unlock (${constant.AchievementState}) the given achievement for the currently signed in player.
 * @param {string} achievementId The unique identifier of the achievement
 * @returns {real}
 * 
 * @event social 
 * @member {string} type The string `"GooglePlayServices_Achievements_Unlock"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} achievement_id The unique name of the achievement
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Achievements_Unlock(achievementId);
 * ```
 * The code sample above will set the state of the specified acheivement to unlocked. The result can be caught inside an ${event.social} as follows:
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Achievements_Unlock")
 * if(async_load[?"success"])
 * {    
 *     //Done, let's continue
 * }
 * ```
 * The code above checks if the task was successful. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Achievements_Unlock() {}


// CONSTANTS

/** 
 * @const AchievementState
 * @desc These constants specify the achievement state.
 * @member Achievement_STATE_HIDDEN Indicates a hidden achievement.
 * @member Achievement_STATE_REVEALED Indicates a revealed achievement.
 * @member Achievement_STATE_UNLOCKED Indicates an unlocked achievement.
 * @const_end
 */

/** 
 * @const AchievementType
 * @desc These constants specify the type on an achievement.
 * @member Achievement_TYPE_INCREMENTAL Indicates an incremental achievement.
 * @member Achievement_TYPE_STANDARD Indicates a standard achievement.
 * @const_end
 */

// STRUCTS

/** 
 * @struct AchievementStatusJSON
 * @desc Represents an achievement and its associated metadata.
 * @member {string} id The ID of this achievement.
 * @member {string} name The name of this achievement.
 * @member {string} description The description for this achievement.
 * @member {constant.AchievementState} state The AchievementState of the achievement.
 * @member {constant.AchievementType} typeAchievement The AchievementType of the achievement.
 * @member {real} xpValue The XP value of this achievement.
 * @member {real} lastUpdatedTimestamp The timestamp (in millseconds since epoch) at which this achievement was last updated.
 * @member {string} [revealedImage] A URI string that can be used to load the achievement's revealed image icon (see ${function.GooglePlayServices_UriToPath}). Not present if the achievement has no revealed image.
 * @member {string} [unlockedImage] A URI string that can be used to load the achievement's unlocked image icon (see ${function.GooglePlayServices_UriToPath}). Not present if the achievement has no revealed image.
 * @member {real} currentSteps The number of steps this user has gone toward unlocking this achievement. Only available for incremental achievement types (${constant.AchievementType}).
 * @member {string} [formattedCurrentSteps] The number of steps this user has gone toward unlocking this achievement (formatted for the user's locale). Only available for incremental achievement types (${constant.AchievementType}).
 * @member {real} [totalSteps] The total number of steps necessary to unlock this achievement. Only available for incremental achievement types (${constant.AchievementType}).
 * @member {string} [formattedTotalSteps] The total number of steps necessary to unlock this achievement, formatted for the user's locale. Only available for incremental achievement types (${constant.AchievementType}).
 * @struct_end
 */

// MODULES

/**
 * @module achievements
 * @title Achievements
 * @desc Achievements can be a great way to increase your users' engagement within your game. You can implement achievements in your game to encourage players to experiment with features they might not normally use, or to approach your game with entirely different play styles. Achievements can also be a fun way for players to compare their progress with each other and engage in light-hearted competition.
 * 
 * @section_func
 * @desc The following functions are provided for working with achievements:
 * @reference GooglePlayServices_Achievements_*
 * @section_end
 * 
 * @section_const
 * @desc The following constants are provided to be used as input arguments or output values:
 * @reference AchievementState
 * @reference AchievementType
 * @section_end
 * 
 * @section_struct
 * @desc The following structurs are used as output values from the function calls to the GooglePlayServices API:
 * @reference AchievementStatusJSON
 * @section_end
 * 
 * @module_end
 */

