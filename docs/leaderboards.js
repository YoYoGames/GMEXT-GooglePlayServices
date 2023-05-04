
/**
 * @func GooglePlayServices_Leaderboard_LoadPlayerCenteredScores
 * @desc Asynchronously load the player-centered page of scores for a given leaderboard. If the player does not have a score on this leaderboard, this call will return the first page instead.
 * @param {string} leaderboardId The unique identifier of the leaderboard.
 * @param {constant.LeaderboardTimeSpan} span The time span to retrieve data for.
 * @param {Constant.LeaderboardCollection} collection he collection to retrieve scores for
 * @param {real} maxResults The maximum number of scores to fetch per page. Must be between 1 and 25.
 * @param {boolean} forceReload If true, this call will clear any locally cached data and attempt to fetch the latest data from the server. This would commonly be used for something like a user-initiated refresh. Normally, this should be set to false to gain advantages of data caching.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Leaderboard_LoadPlayerCenteredScores"`
 * @member {string} data A json formatted string of an array of ${struct.LeaderboardEntryJSON}. This string can be parsed into an array with the function ${function.json_parse}.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Leaderboard_LoadPlayerCenteredScores(Leaderboard1, Leaderboard_TIME_SPAN_ALL_TIME, Leaderboard_COLLECTION_PUBLIC, 5, true)
 * ```
 * The code sample above will start a query for player centered leaderboard entries. The results can be caught inside an ${event.social}.
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Leaderboard_LoadPlayerCenteredScores")
 * {
 *     var array = json_parse(async_load[?"data"])
 *     for(var a = 0 ; a < array_length(array) ; a ++)
 *     {
 *         var struct = array[a]
 *         var ins = instance_create_depth(800,200+a*75,00,Obj_GooglePlayServices_Leaderboard_Entry)
 *         ins.displayRank = struct.displayRank;
 *         ins.displayScore = struct.displayScore;
 *         ins.rank = struct.rank;
 *         ins.rawScore = struct.rawScore;
 *         ins.scoreHolder = struct.scoreHolder;
 *         ins.scoreHolderDisplayName = struct.scoreHolderDisplayName;
 *         ins.scoreHolderHiResImageUri = struct.scoreHolderHiResImageUri;
 *         ins.scoreHolderIconImageUri = struct.scoreHolderIconImageUri;
 * 
 *         // This is an options parameter and is only present if a scoreTag was provided.
 *         ins.scoreTag = struct[$ "scoreTag"];
 * 
 *         ins.timestampMillis = struct.timestampMillis;
 *     }
 * }
 * ```
 * The code above shows a way of reading the returned data using the function ${function.json_parse}. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Leaderboard_LoadPlayerCenteredScores() {}

/**
 * @func GooglePlayServices_Leaderboard_LoadTopScores
 * @desc Asynchronously load the top page of scores for a given leaderboard.
 * @param {string} leaderboardId The unique identifier of the leaderboard.
 * @param {constant.LeaderboardTimeSpan} span The time span to retrieve data for.
 * @param {Constant.LeaderboardCollection} collection he collection to retrieve scores for
 * @param {real} maxResults The maximum number of scores to fetch per page. Must be between 1 and 25.
 * @param {boolean} forceReload If true, this call will clear any locally cached data and attempt to fetch the latest data from the server. This would commonly be used for something like a user-initiated refresh. Normally, this should be set to false to gain advantages of data caching.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Leaderboard_LoadTopScores"`
 * @member {string} data A json formatted string of an array of ${struct.LeaderboardEntryJSON}. This string can be parsed into an array with the function ${function.json_parse}.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Leaderboard_LoadTopScores(Leaderboard1, Leaderboard_TIME_SPAN_ALL_TIME, Leaderboard_COLLECTION_PUBLIC, 5, true)
 * ```
 * The code sample above will start a query for top scores leaderboard entries. The results can be caught inside a ${event.social}.
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Leaderboard_LoadTopScores")
 * {
 *     var array = json_parse(async_load[?"data"])
 *     for(var a = 0 ; a < array_length(array) ; a ++)
 *     {
 *         var struct = array[a]
 *         var ins = instance_create_depth(800,200+a*75,00,Obj_GooglePlayServices_Leaderboard_Entry)
 *         ins.displayRank = struct.displayRank;
 *         ins.displayScore = struct.displayScore;
 *         ins.rank = struct.rank;
 *         ins.rawScore = struct.rawScore;
 *         ins.scoreHolder = struct.scoreHolder;
 *         ins.scoreHolderDisplayName = struct.scoreHolderDisplayName;
 *         ins.scoreHolderHiResImageUri = struct.scoreHolderHiResImageUri;
 *         ins.scoreHolderIconImageUri = struct.scoreHolderIconImageUri;
 * 
 *         // This is an options parameter and is only present if a scoreTag was provided.
 *         ins.scoreTag = struct[$ "scoreTag"];
 * 
 *         ins.timestampMillis = struct.timestampMillis;
 *     }
 * }
 * ```
 * The code above shows a way of reading the returned data using the function ${function.json_parse}. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Leaderboard_LoadTopScores() {}

/**
 * @func GooglePlayServices_Leaderboard_Show
 * @desc This function will call the Google Play Services overlay for a specific leaderboard.
 * @param {string} leaderboardId The unique identifier of the leaderboard.
 * 
 * @example
 * ```gml
 * GooglePlayServices_Leaderboard_Show(leaderboardId);
 * ```
 * The code above will trigger the leaderboard overlay of the given leaderboard.
 * @func_end
 */
function GooglePlayServices_Leaderboard_Show() {}

/**
 * @func GooglePlayServices_Leaderboard_ShowAll
 * @desc This function will call the general Google Play Services leaderboards overlay. Here the user will have access to all the existing leaderboards of the current application.
 * 
 * @example
 * ```gml
 * GooglePlayServices_Leaderboard_ShowAll()
 * ```
 * The code above will trigger the leaderboard overlay of all the available leaderboards for this game.
 * @func_end
 */
function GooglePlayServices_Leaderboard_ShowAll() {}

/**
 * @func GooglePlayServices_Leaderboard_SubmitScore
 * @desc This function requests the Google Play Services API to submit a score to the given leaderboard.
 * @param {string} leaderboardId The unique identifier of the leaderboard.
 * @param {real} score The value to be submitted to the leaderboard (remember that only the highest score value is displayed in the leaderboard).
 * @param {string} scoreTag A tag that will be added to the value being submitted to the leaderboard (note that this value is required, if you don't want to set a tag use an empty string).
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_Leaderboard_SubmitScore"`
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} leaderboardId The unique name of the leaderboard.
 * @member {real} score The submitted score.
 * @member {string} scoreTag The tag for the current submission.
 * @member {string} report A json formatted string of ${struct.LearderboardReportJSON}. This string can be parsed into a struct with the function ${function.json_parse}. Only available if the task succeeds.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_Leaderboard_SubmitScore(leaderId, 100, "archer");
 * ```
 * The code sample above will submit a new score to the leaderboard with a specific tag (&quot;archer&quot;). The result can be caught inside a ${event.social} as follows:
 * ```gml
 * if(async_load[?"type"] == "GooglePlayServices_Leaderboard_SubmitScore")
 * if(async_load[?"success")
 * {    
 *     //Done, let's continue
 * }
 * ```
 * The code above checks if the task was successful. This sample is taken from the demo project check the project for more context.
 * @func_end
 */
function GooglePlayServices_Leaderboard_SubmitScore() {}


/**
 * @const LeaderboardCollection
 * @desc These constants represent kinds of leaderboard collections.
 * @member Leaderboard_COLLECTION_SOCIAL These leaderboards contain the scores of players in the viewing player's friends list.
 * @member Leaderboard_COLLECTION_PUBLIC Public leaderboards contain the scores of players who are sharing their gameplay activity publicly.
 * @const_end
 */


/**
 * @const LeaderboardTimeSpan
 * @desc These constants represent the various types of time span that can be used.
 * @member Leaderboard_TIME_SPAN_DAILY Refers to the scores of the day. Scores are reset every day. The reset occurs at 11:59PM PST.
 * @member Leaderboard_TIME_SPAN_WEEKLY Refers to the scores of the week. Scores are reset once per week. The reset occurs at 11:59PM PST on Sunday.
 * @member Leaderboard_TIME_SPAN_ALL_TIME Refers to all the scores. Scores are never reset.
 * @const_end
 */


/**
 * @struct LeaderboardEntryJSON
 * @desc Represents a score and its associated metadata.
 * @member {string} displayRank A formatted string to display for this rank. This handles appropriate localization and formatting.
 * @member {string} displayScore A formatted string to display for this score. The details of the formatting are specified by the developer in their dev console.
 * @member {number} rank The rank returned from the server for this score. Note that this may not be exact and that multiple scores can have identical ranks. Lower ranks indicate a better score, with rank 1 being the best score on the board.
 * @member {number} rawScore The raw score value.
 * @member {struct.PlayerJSON} scoreHolder A struct of PlayerJSON.
 * @member {string} scoreHolderDisplayName The display name of the player that scored this particular score.
 * @member {string} [scoreHolderHiResImageUri] The URI of the hi-res image to display for the player who scored this score (you can later convert the uri to a local path using the ${function.GooglePlayServices_UriToPath}).
 * @member {string} [scoreHolderIconImageUri] The URI of the icon image to display for the player who scored this score (you can later convert the uri to a local path using the ${function.GooglePlayServices_UriToPath}).
 * @struct_end
 */

/**
 * @struct LearderboardReportJSON
 * @desc Represents a leaderboard report and its associated metadata.
 * @member {struct.LearderboardReportEntryJSON} allTime The all time result report.
 * @member {struct.LearderboardReportEntryJSON} weekly The weekly result report.
 * @member {struct.LearderboardReportEntryJSON} daily The daily result report
 * @struct_end
 */

/**
 * @struct LearderboardReportEntryJSON
 * @desc Represents a leaderboard report entry.
 * @member {boolean} isNewBest Whether or not this score is a new best.
 * @member {real} score The score submitted to the server.
 * @member {string} scoreTag The tag used the submittion of the achievement.
 * @struct_end
 */


/**
 * @module leaderboards
 * @title Leaderboards
 * @desc Leaderboards can be a fun way to drive competition among your players, both for your most hardcore fans (who will be fighting for the top spot in a public leaderboard) and for your more casual players (who will be interested in comparing their progress to their friends').
 * 
 * @section_func
 * @desc The following functions are provided for working with leaderboards:
 * @reference GooglePlayServices_Leaderboard_*
 * @section_end
 * 
 * @section_const
 * @desc The following constants are provided to be used as input arguments or output values:
 * @reference LeaderboardCollection
 * @reference LeaderboardTimeSpan
 * @section_end
 *
 * @section_struct
 * @desc The following structures are used as output values from the function calls to the GooglePlayServices API:
 * @reference LeaderboardEntryJSON
 * @reference LeaderboardReportJSON
 * @reference LeaderboardReportEntryJSON
 * @section_end
 * 
 * @module_end
 */