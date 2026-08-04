
/**
 * @struct PlayServicesScoreSubmission
 * @desc The outcome of a score submission for a single time-span bucket (daily/weekly/all-time).
 * @member {Real} raw_score The raw score value that ended up on the leaderboard for this time span
 * (the submitted score, unless a better one already existed).
 * @member {String} [formatted_score] `raw_score` formatted for display, per the leaderboard's
 * configured format.
 * @member {String} [score_tag] The tag associated with the winning score, if one was submitted with
 * it.
 * @member {Bool} new_best Whether the submitted score is a new best for this time span.
 * @struct_end
 */

/**
 * @struct PlayServicesScoreReportInfo
 * @desc The result of a score submission, broken down per time-span bucket - a single submitted score
 * is evaluated against all three simultaneously.
 * @member {Struct.PlayServicesScoreSubmission} daily The daily bucket's outcome.
 * @member {Struct.PlayServicesScoreSubmission} weekly The weekly bucket's outcome.
 * @member {Struct.PlayServicesScoreSubmission} all_time The all-time bucket's outcome.
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboardVariant
 * @desc One collection/time-span combination of a leaderboard.
 * @member {Enum.PlayServicesLeaderboardCollection} collection Which collection this variant covers.
 * @member {Enum.PlayServicesLeaderboardTimeSpan} time_span Which time span this variant covers.
 * @member {Bool} has_player_info Whether this variant includes the signed-in player's own rank/score,
 * even outside the returned page (see ${struct.PlayServicesLeaderboardScore}.score_holder).
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboard
 * @desc A leaderboard's own metadata, returned alongside a page of scores.
 * @member {String} [leaderboard_id] The leaderboard's unique ID.
 * @member {String} [display_name] The leaderboard's display name.
 * @member {Enum.PlayServicesLeaderboardScoreOrder} score_order Whether smaller or larger raw scores
 * rank better on this leaderboard.
 * @member {Array[Struct.PlayServicesLeaderboardVariant]} variants Every collection/time-span variant
 * this leaderboard supports.
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboardScore
 * @desc A single ranked entry on a leaderboard page.
 * @member {String} [display_rank] The rank, formatted for display (localized/formatted per the
 * leaderboard's configuration).
 * @member {String} [display_score] The score, formatted for display.
 * @member {Real} raw_score The raw score value.
 * @member {String} [score_tag] The tag submitted alongside this score, if any.
 * @member {Real} timestamp_millis When this score was submitted, in milliseconds since epoch.
 * @member {Struct.PlayServicesPlayerInfo} [score_holder] The player who holds this score. Only
 * present when the requested variant has `has_player_info` set (see
 * ${struct.PlayServicesLeaderboardVariant}).
 * @struct_end
 */

/**
 * @function play_services_leaderboard_show_all
 * @desc Shows the system Leaderboards UI overlay, listing every leaderboard for this game.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_leaderboard_show
 * @desc Shows the system Leaderboard UI overlay for a single leaderboard.
 * @param {String} leaderboard_id The unique ID of the leaderboard to show.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_leaderboard_submit_score
 * @desc Submits a score to a leaderboard, evaluated against the daily/weekly/all-time buckets
 * simultaneously. Only the highest score per bucket is ever kept - submitting a lower score than the
 * player's existing best for a bucket has no effect on that bucket. Use
 * ${function.play_services_leaderboard_submit_score_with_tag} to attach a tag to the submission.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Real} score The raw score to submit.
 * @param {Function} callback The function to call once the submission completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the submission completes or fails.
 * @member {Struct.PlayServicesResult} status The submission's outcome.
 * @member {Struct.PlayServicesScoreReportInfo} [report] The per-time-span submission report. Only
 * present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_leaderboard_submit_score_with_tag
 * @desc Submits a score to a leaderboard with an attached tag, same as
 * ${function.play_services_leaderboard_submit_score} but stamping `score_tag` onto the winning entry
 * for later retrieval (e.g. to encode which game mode or character produced the score).
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Real} score The raw score to submit.
 * @param {String} score_tag An arbitrary tag to associate with this score, up to 64 characters.
 * @param {Function} callback The function to call once the submission completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the submission completes or fails.
 * @member {Struct.PlayServicesResult} status The submission's outcome.
 * @member {Struct.PlayServicesScoreReportInfo} [report] The per-time-span submission report. Only
 * present on success.
 * @event_end
 * @example
 * ```gml
 * play_services_leaderboard_submit_score_with_tag(leaderboard_id, 12345, "archer",
 *     function(_status, _report = undefined)
 *     {
 *         if (_status.success && _report.all_time.new_best)
 *             show_debug_message("New all-time best!");
 *     });
 * ```
 * @function_end
 */

/**
 * @function play_services_leaderboard_load_player_centered_scores
 * @desc Loads the page of scores centered on the signed-in player's own rank. If the player has no
 * score on this leaderboard, this loads the top page instead (same as
 * ${function.play_services_leaderboard_load_top_scores}).
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span Which time span to load scores for.
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection Which collection to load
 * scores for.
 * @param {Real} max_results The maximum number of scores to return, clamped to
 * `[${constant.macros}.PlayServicesMinPageSize, ${constant.macros}.PlayServicesMaxLeaderboardResults]`.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Struct.PlayServicesLeaderboard} [leaderboard] The leaderboard's own metadata. Only present
 * on success.
 * @member {Array[Struct.PlayServicesLeaderboardScore]} scores The loaded page of scores. Empty on
 * failure.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_leaderboard_load_top_scores
 * @desc Loads the top-ranked page of scores for a leaderboard.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span Which time span to load scores for.
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection Which collection to load
 * scores for.
 * @param {Real} max_results The maximum number of scores to return, clamped to
 * `[${constant.macros}.PlayServicesMinPageSize, ${constant.macros}.PlayServicesMaxLeaderboardResults]`.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome.
 * @member {Struct.PlayServicesLeaderboard} [leaderboard] The leaderboard's own metadata. Only present
 * on success.
 * @member {Array[Struct.PlayServicesLeaderboardScore]} scores The loaded page of scores. Empty on
 * failure.
 * @event_end
 * @example
 * ```gml
 * play_services_leaderboard_load_top_scores(leaderboard_id, PlayServicesLeaderboardTimeSpan.AllTime,
 *     PlayServicesLeaderboardCollection.Public, PlayServicesMaxLeaderboardResults, false,
 *     function(_status, _leaderboard = undefined, _scores = [])
 *     {
 *         if (!_status.success) return;
 *
 *         for (var i = 0; i < array_length(_scores); i++)
 *             show_debug_message($"#{_scores[i].display_rank}: {_scores[i].display_score}");
 *     });
 * ```
 * @function_end
 */

/**
 * @const PlayServicesLeaderboardTimeSpan
 * @desc The time span a leaderboard variant covers.
 * @member Daily Scores for the current day. Resets daily at 11:59 PM Pacific time.
 * @member Weekly Scores for the current week. Resets weekly, Sunday 11:59 PM Pacific time.
 * @member AllTime All scores ever submitted. Never resets.
 * @const_end
 */

/**
 * @const PlayServicesLeaderboardCollection
 * @desc Which set of players a leaderboard variant's scores are drawn from.
 * @member Public Scores from every player sharing their gameplay activity publicly.
 * @member Friends Scores from players in the signed-in player's friends list (see ${module.friends}).
 * @const_end
 */

/**
 * @const PlayServicesLeaderboardScoreOrder
 * @desc How raw scores are ranked on a leaderboard.
 * @member SmallerIsBetter A lower raw score ranks higher (e.g. a completion-time leaderboard).
 * @member LargerIsBetter A higher raw score ranks higher (e.g. a points leaderboard).
 * @const_end
 */

/**
 * @module leaderboards
 * @title Leaderboards
 * @desc Submitting and loading leaderboard scores, plus the system Leaderboards UI.
 *
 * @section_func
 * @ref play_services_leaderboard_show_all
 * @ref play_services_leaderboard_show
 * @ref play_services_leaderboard_submit_score
 * @ref play_services_leaderboard_submit_score_with_tag
 * @ref play_services_leaderboard_load_player_centered_scores
 * @ref play_services_leaderboard_load_top_scores
 * @section_end
 *
 * @section_struct
 * @ref PlayServicesScoreSubmission
 * @ref PlayServicesScoreReportInfo
 * @ref PlayServicesLeaderboardVariant
 * @ref PlayServicesLeaderboard
 * @ref PlayServicesLeaderboardScore
 * @section_end
 *
 * @section_const
 * @ref PlayServicesLeaderboardTimeSpan
 * @ref PlayServicesLeaderboardCollection
 * @ref PlayServicesLeaderboardScoreOrder
 * @section_end
 *
 * @module_end
 */
