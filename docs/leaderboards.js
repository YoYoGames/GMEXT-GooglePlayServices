/**
 * @struct PlayServicesScoreSubmission
 * @desc The outcome of a score submission for a single time span bucket, which is to say the daily,
 * the weekly or the all-time one.
 * @member {Real} raw_score The raw score value that ended up on the leaderboard for this time span,
 * which is the submitted score unless a better one already existed.
 * @member {String} [formatted_score] The `raw_score` member formatted for display, according to the
 * format that the leaderboard is configured with.
 * @member {String} [score_tag] The tag that is associated with the winning score, if one was
 * submitted along with it.
 * @member {Bool} new_best Whether the submitted score is a new best for this time span.
 * @struct_end
 */

/**
 * @struct PlayServicesScoreReportInfo
 * @desc The result of a score submission, broken down for each time span bucket, as a single
 * submitted score is evaluated against all three of them at once.
 * @member {Struct.PlayServicesScoreSubmission} daily The outcome for the daily bucket.
 * @member {Struct.PlayServicesScoreSubmission} weekly The outcome for the weekly bucket.
 * @member {Struct.PlayServicesScoreSubmission} all_time The outcome for the all-time bucket.
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboardVariant
 * @desc A single combination of a collection and a time span for a leaderboard.
 * @member {Enum.PlayServicesLeaderboardCollection} collection The collection that this variant
 * covers.
 * @member {Enum.PlayServicesLeaderboardTimeSpan} time_span The time span that this variant covers.
 * @member {Bool} has_player_info Whether this variant includes the rank and the score of the
 * signed-in player, even from outside the returned page. See
 * ${struct.PlayServicesLeaderboardScore}.score_holder.
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboard
 * @desc The metadata of a leaderboard, which is returned alongside a page of scores.
 * @member {String} [leaderboard_id] The unique ID of the leaderboard.
 * @member {String} [display_name] The display name of the leaderboard.
 * @member {Enum.PlayServicesLeaderboardScoreOrder} score_order Whether a smaller or a larger raw
 * score ranks better on this leaderboard.
 * @member {Array[Struct.PlayServicesLeaderboardVariant]} variants Every combination of a collection
 * and a time span that this leaderboard supports.
 * @struct_end
 */

/**
 * @struct PlayServicesLeaderboardScore
 * @desc A single ranked entry on a page of a leaderboard.
 * @member {String} [display_rank] The rank, formatted for display, which means localised and
 * formatted according to the configuration of the leaderboard.
 * @member {String} [display_score] The score, formatted for display.
 * @member {Real} raw_score The raw score value.
 * @member {String} [score_tag] The tag that was submitted alongside this score, if there was one.
 * @member {Real} timestamp_millis When this score was submitted, in milliseconds since the epoch.
 * @member {Struct.PlayServicesPlayerInfo} [score_holder] The player who holds this score. This is
 * only present when the requested variant has `has_player_info` set, for which see
 * ${struct.PlayServicesLeaderboardVariant}.
 * @struct_end
 */

/**
 * @function play_services_leaderboard_show_all
 * @desc This function shows the system Leaderboards UI overlay, which lists every leaderboard for
 * this game.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_leaderboard_show
 * @desc This function shows the system Leaderboard UI overlay for a single leaderboard.
 * @param {String} leaderboard_id The unique ID of the leaderboard to show.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @function_end
 */

/**
 * @function play_services_leaderboard_submit_score
 * @desc This function submits a score to a leaderboard, where it is evaluated against the daily, the
 * weekly and the all-time buckets at once. Only the highest score in each bucket is ever kept, so
 * submitting a score that is lower than the existing best of the player for a bucket has no effect
 * on that bucket. You should use ${function.play_services_leaderboard_submit_score_with_tag} if you
 * want to attach a tag to the submission.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Real} score The raw score to submit.
 * @param {Function} callback The function to call once the submission completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the submission completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the submission.
 * @member {Struct.PlayServicesScoreReportInfo} [report] The submission report for each time span.
 * This is only present on success.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_leaderboard_submit_score_with_tag
 * @desc This function submits a score to a leaderboard with a tag attached. It behaves exactly like
 * ${function.play_services_leaderboard_submit_score}, except that it also stamps `score_tag` onto
 * the winning entry so that you can read it back later, for example to record which game mode or
 * which character produced the score.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Real} score The raw score to submit.
 * @param {String} score_tag An arbitrary tag to associate with this score, of up to 64 characters.
 * @param {Function} callback The function to call once the submission completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the submission completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the submission.
 * @member {Struct.PlayServicesScoreReportInfo} [report] The submission report for each time span.
 * This is only present on success.
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
 * The code above submits a score tagged with the character that earned it, and checks the returned
 * report to see whether it beat the all-time best of the player.
 * @function_end
 */

/**
 * @function play_services_leaderboard_load_player_centered_scores
 * @desc This function loads the page of scores that is centred on the rank of the signed-in player.
 * If that player has no score on this leaderboard then the top page is loaded instead, exactly as
 * ${function.play_services_leaderboard_load_top_scores} would.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span The time span to load scores for.
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection The collection to load
 * scores for.
 * @param {Real} max_results The maximum number of scores to return, which is clamped to the range
 * [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_LEADERBOARD_RESULTS].
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Struct.PlayServicesLeaderboard} [leaderboard] The metadata of the leaderboard. This is
 * only present on success.
 * @member {Array[Struct.PlayServicesLeaderboardScore]} scores The loaded page of scores. This is
 * empty on failure.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_leaderboard_load_top_scores
 * @desc This function loads the top ranked page of scores for a leaderboard.
 * @param {String} leaderboard_id The unique ID of the leaderboard.
 * @param {Enum.PlayServicesLeaderboardTimeSpan} span The time span to load scores for.
 * @param {Enum.PlayServicesLeaderboardCollection} leaderboard_collection The collection to load
 * scores for.
 * @param {Real} max_results The maximum number of scores to return, which is clamped to the range
 * [${constant.macros}.PLAY_SERVICES_MIN_PAGE_SIZE, ${constant.macros}.PLAY_SERVICES_MAX_LEADERBOARD_RESULTS].
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load.
 * @member {Struct.PlayServicesLeaderboard} [leaderboard] The metadata of the leaderboard. This is
 * only present on success.
 * @member {Array[Struct.PlayServicesLeaderboardScore]} scores The loaded page of scores. This is
 * empty on failure.
 * @event_end
 * @example
 * ```gml
 * play_services_leaderboard_load_top_scores(leaderboard_id, PlayServicesLeaderboardTimeSpan.AllTime,
 *     PlayServicesLeaderboardCollection.Public, PLAY_SERVICES_MAX_LEADERBOARD_RESULTS, false,
 *     function(_status, _leaderboard = undefined, _scores = [])
 *     {
 *         if (!_status.success) return;
 *
 *         for (var i = 0; i < array_length(_scores); i++)
 *             show_debug_message($"#{_scores[i].display_rank}: {_scores[i].display_score}");
 *     });
 * ```
 * The code above loads a full page of public all-time scores and writes the rank and the score of
 * each entry to the output log, using the display strings that Google Play has already formatted.
 * @function_end
 */

/**
 * @const PlayServicesLeaderboardTimeSpan
 * @desc The time span that a leaderboard variant covers.
 * @member Daily The scores for the current day. This resets daily, at 11:59 PM Pacific time.
 * @member Weekly The scores for the current week. This resets weekly, on Sunday at 11:59 PM Pacific
 * time.
 * @member AllTime Every score that has ever been submitted. This never resets.
 * @const_end
 */

/**
 * @const PlayServicesLeaderboardCollection
 * @desc The set of players that the scores of a leaderboard variant are drawn from.
 * @member Public The scores of every player who shares their gameplay activity publicly.
 * @member Friends The scores of the players in the friends list of the signed-in player. See
 * ${module.friends}.
 * @const_end
 */

/**
 * @const PlayServicesLeaderboardScoreOrder
 * @desc How the raw scores on a leaderboard are ranked.
 * @member SmallerIsBetter A lower raw score ranks higher, as it would on a completion time
 * leaderboard.
 * @member LargerIsBetter A higher raw score ranks higher, as it would on a points leaderboard.
 * @const_end
 */

/**
 * @module leaderboards
 * @title Leaderboards
 * @desc This module covers submitting and loading leaderboard scores, along with the system
 * Leaderboards UI.
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
