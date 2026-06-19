/**
 * @function_partial gpgs_is_available
 * @returns {Bool} 
 * @function_end 
 */

/**
 * @function_partial gpgs_sign_in
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_is_authenticated
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_request_server_side_access
 * @param {String} server_client_id
 * @param {Bool} force_refresh_token
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_player_current
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_player_current_id
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_player_stats_load
 * @param {Bool} force_reload
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_show
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_increment
 * @param {String} achievement_id
 * @param {Real} steps
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_reveal
 * @param {String} achievement_id
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_set_steps
 * @param {String} achievement_id
 * @param {Real} steps
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_unlock
 * @param {String} achievement_id
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_achievements_get_status
 * @param {Bool} force_reload
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_leaderboard_show_all
 * @function_end 
 */

/**
 * @function_partial gpgs_leaderboard_show
 * @param {String} leaderboard_id
 * @function_end 
 */

/**
 * @function_partial gpgs_leaderboard_submit_score
 * @param {String} leaderboard_id
 * @param {Real} score
 * @param {String} score_tag
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_leaderboard_load_player_centered_scores
 * @param {String} leaderboard_id
 * @param {Enum.GPGSLeaderboardTimeSpan} span
 * @param {Enum.GPGSLeaderboardCollection} leaderboard_collection
 * @param {Real} max_results
 * @param {Bool} force_reload
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_leaderboard_load_top_scores
 * @param {String} leaderboard_id
 * @param {Enum.GPGSLeaderboardTimeSpan} span
 * @param {Enum.GPGSLeaderboardCollection} leaderboard_collection
 * @param {Real} max_results
 * @param {Bool} force_reload
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_uri_to_path
 * @param {String} uri
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_show_saved_games_ui
 * @param {String} title
 * @param {Bool} button_add
 * @param {Bool} button_delete
 * @param {Real} max_results
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial __gpgs_saved_games_commit_and_close
 * @param {String} options_json
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial __gpgs_saved_games_commit_new
 * @param {String} options_json
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_load
 * @param {Bool} force_reload
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_open
 * @param {String} name
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_open_conflict
 * @param {String} name
 * @param {Enum.GPGSSavedGamesConflictPolicy} conflict_policy
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_delete
 * @param {String} name
 * @param {Function} callback
 * @function_end 
 */

/**
 * @function_partial gpgs_saved_games_resolve_conflict
 * @param {String} conflict_id
 * @param {Bool} use_local
 * @param {Function} callback
 * @function_end 
 */

/**
 * @enum_partial GPGSAchievementState
 * @member Unlocked
 * @member Revealed
 * @member Hidden
 * @enum_end 
 */

/**
 * @enum_partial GPGSAchievementType
 * @member Standard
 * @member Incremental
 * @enum_end 
 */

/**
 * @enum_partial GPGSLeaderboardTimeSpan
 * @member Daily
 * @member Weekly
 * @member AllTime
 * @enum_end 
 */

/**
 * @enum_partial GPGSLeaderboardCollection
 * @member Public
 * @member Friends
 * @enum_end 
 */

/**
 * @enum_partial GPGSLeaderboardScoreOrder
 * @member SmallerIsBetter
 * @member LargerIsBetter
 * @enum_end 
 */

/**
 * @enum_partial GPGSSavedGamesConflictPolicy
 * @member Manual
 * @member LongestPlaytime
 * @member LastKnownGood
 * @member MostRecentlyModified
 * @member HighestProgress
 * @enum_end 
 */

/**
 * @enum_partial GPGSSavedGamesDisplayLimit
 * @member None
 * @enum_end 
 */

/**
 * @enum_partial GPGSSavedGamesUIResult
 * @member Cancelled
 * @member Selected
 * @member CreatedNew
 * @member Deleted
 * @member Error
 * @enum_end 
 */

/**
 * @const_partial macros
 * @const_end 
 */

