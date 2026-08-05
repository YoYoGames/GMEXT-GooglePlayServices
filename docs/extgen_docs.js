/**
 * @function_partial play_services_is_available
 * @returns {Bool}
 * @function_end
 */

/**
 * @function_partial play_services_sign_in
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_is_authenticated
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_request_server_side_access
 * @param {String} server_client_id
 * @param {Bool} force_refresh_token
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_current
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_current_id
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_stats_load
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_load
 * @param {String} player_id
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_friends_load
 * @param {Bool} force_reload
 * @param {Real} max_results
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_friends_load_more
 * @param {Real} page_size
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_friends_load_with_consent
 * @param {Bool} force_reload
 * @param {Real} max_results
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_profile_show
 * @param {String} player_id
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_player_search_show
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_show
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_increment
 * @param {String} achievement_id
 * @param {Real} steps
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_reveal
 * @param {String} achievement_id
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_set_steps
 * @param {String} achievement_id
 * @param {Real} steps
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_unlock
 * @param {String} achievement_id
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_achievements_get_status
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_show_all
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_show
 * @param {String} leaderboard_id
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_submit_score
 * @param {String} leaderboard_id
 * @param {Real} score
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_submit_score_with_tag
 * @param {String} leaderboard_id
 * @param {Real} score
 * @param {String} score_tag
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_load_player_centered_scores
 * @param {String} leaderboard_id
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection
 * @param {Real} max_results
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_leaderboard_load_top_scores
 * @param {String} leaderboard_id
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection
 * @param {Real} max_results
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_uri_to_path
 * @param {String} uri
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_show_saved_games_ui
 * @param {String} title
 * @param {Bool} button_add
 * @param {Bool} button_delete
 * @param {Real} max_results
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_commit_and_close
 * @param {Struct.PlayServicesSavedGameCommitOptions} options
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_load
 * @param {Bool} force_reload
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_open
 * @param {String} name
 * @param {Bool} create_if_not_found
 * @param {Enum.PlayServicesSavedGamesConflictPolicy} conflict_policy
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_delete
 * @param {String} name
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @function_partial play_services_saved_games_resolve_conflict
 * @param {String} conflict_id
 * @param {Bool} use_local
 * @param {Function} callback
 * @returns {Enum.PlayServicesError}
 * @function_end
 */

/**
 * @struct_partial PlayServicesSavedGameCommitOptions
 * @member {String} name
 * @member {String} data
 * @member {String} desc
 * @member {Real} played_time_millis
 * @member {Real} progress_value
 * @member {String} cover_image_path
 * @struct_end
 */

/**
 * @struct_partial PlayServicesResult
 * @member {Bool} success
 * @member {String} error
 * @struct_end
 */

/**
 * @struct_partial PlayServicesPlayerInfo
 * @member {String} [player_id]
 * @member {String} [display_name]
 * @member {String} [title]
 * @member {String} [icon_image_uri]
 * @member {String} [hi_res_image_uri]
 * @struct_end
 */

/**
 * @struct_partial PlayServicesPlayerStatsInfo
 * @member {Real} average_session_length
 * @member {Real} days_since_last_played
 * @member {Real} number_of_purchases
 * @member {Real} number_of_sessions
 * @member {Real} session_percentile
 * @member {Real} spend_percentile
 * @member {Real} churn_probability
 * @member {Real} high_spender_probability
 * @member {Real} spend_probability
 * @member {Real} total_spend_next_28_days
 * @struct_end
 */

/**
 * @struct_partial PlayServicesAchievement
 * @member {String} [achievement_id]
 * @member {String} [name]
 * @member {String} [description]
 * @member {Enum.PlayServicesAchievementState} state
 * @member {Enum.PlayServicesAchievementType} type
 * @member {Real} current_steps
 * @member {Real} total_steps
 * @member {Real} last_updated_timestamp
 * @member {Real} xp_value
 * @member {String} [revealed_image_uri]
 * @member {String} [unlocked_image_uri]
 * @struct_end
 */

/**
 * @struct_partial PlayServicesScoreSubmission
 * @member {Real} raw_score
 * @member {String} [formatted_score]
 * @member {String} [score_tag]
 * @member {Bool} new_best
 * @struct_end
 */

/**
 * @struct_partial PlayServicesLeaderboardVariant
 * @member {Enum.PlayServicesLeaderboardCollection} collection
 * @member {Enum.PlayServicesLeaderboardTimeSpan} time_span
 * @member {Bool} has_player_info
 * @struct_end
 */

/**
 * @struct_partial PlayServicesSnapshotMetadata
 * @member {String} [unique_name]
 * @member {String} [description]
 * @member {String} [device_name]
 * @member {Real} last_modified_timestamp
 * @member {Real} played_time
 * @member {Real} progress_value
 * @member {Bool} has_change_pending
 * @member {String} [cover_image_uri]
 * @struct_end
 */

/**
 * @struct_partial PlayServicesLeaderboardScore
 * @member {String} [display_rank]
 * @member {String} [display_score]
 * @member {Real} raw_score
 * @member {String} [score_tag]
 * @member {Real} timestamp_millis
 * @member {Struct.PlayServicesPlayerInfo} [score_holder]
 * @struct_end
 */

/**
 * @struct_partial PlayServicesScoreReportInfo
 * @member {Struct.PlayServicesScoreSubmission} daily
 * @member {Struct.PlayServicesScoreSubmission} weekly
 * @member {Struct.PlayServicesScoreSubmission} all_time
 * @struct_end
 */

/**
 * @struct_partial PlayServicesLeaderboard
 * @member {String} [leaderboard_id]
 * @member {String} [display_name]
 * @member {Enum.PlayServicesLeaderboardScoreOrder} score_order
 * @member {Array[Struct.PlayServicesLeaderboardVariant]} variants
 * @struct_end
 */

/**
 * @struct_partial PlayServicesSnapshotOpenInfo
 * @member {Bool} is_conflict
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata]
 * @member {String} [data]
 * @member {String} [conflict_id]
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_local]
 * @member {String} [data_local]
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_remote]
 * @member {String} [data_remote]
 * @struct_end
 */

/**
 * @enum_partial PlayServicesAchievementState
 * @member Unlocked
 * @member Revealed
 * @member Hidden
 * @enum_end
 */

/**
 * @enum_partial PlayServicesAchievementType
 * @member Standard
 * @member Incremental
 * @enum_end
 */

/**
 * @enum_partial PlayServicesLeaderboardTimeSpan
 * @member Daily
 * @member Weekly
 * @member AllTime
 * @enum_end
 */

/**
 * @enum_partial PlayServicesLeaderboardCollection
 * @member Public
 * @member Friends
 * @enum_end
 */

/**
 * @enum_partial PlayServicesLeaderboardScoreOrder
 * @member SmallerIsBetter
 * @member LargerIsBetter
 * @enum_end
 */

/**
 * @enum_partial PlayServicesSavedGamesConflictPolicy
 * @member Manual
 * @member LongestPlaytime
 * @member LastKnownGood
 * @member MostRecentlyModified
 * @member HighestProgress
 * @enum_end
 */

/**
 * @enum_partial PlayServicesSavedGamesUIResult
 * @member Cancelled
 * @member Selected
 * @member CreatedNew
 * @member Error
 * @enum_end
 */

/**
 * @enum_partial PlayServicesError
 * @member Ok
 * @member NotAuthenticated
 * @member ActivityNull
 * @member InvalidArgument
 * @enum_end
 */

/**
 * @const_partial macros
 * @member PLAY_SERVICES_MAX_FRIENDS_PAGE_SIZE (value: '25')
 * @member PLAY_SERVICES_MAX_LEADERBOARD_RESULTS (value: '25')
 * @member PLAY_SERVICES_MIN_PAGE_SIZE (value: '1')
 * @const_end
 */

