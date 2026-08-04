
/**
 * @struct PlayServicesSavedGameCommitOptions
 * @desc The metadata and content to write when committing a saved-game slot via
 * ${function.play_services_saved_games_commit_and_close}.
 * @member {String} name The unique identifier of the save slot. Must match a slot already opened this
 * session with ${function.play_services_saved_games_open}.
 * @member {String} data The save data to write, as a string (a JSON-encoded string is a common
 * choice for structured data).
 * @member {String} desc The description to display for this save slot in the system Saved Games UI.
 * Leave empty to keep the slot's existing description unchanged.
 * @member {Real} played_time_millis The total played time to record for this save, in milliseconds.
 * Pass a negative value to leave the slot's existing value unchanged.
 * @member {Real} progress_value The progress value to record for this save (an arbitrary,
 * developer-defined number used to compare saves, e.g. for conflict resolution heuristics). Pass a
 * negative value to leave the slot's existing value unchanged.
 * @member {String} cover_image_path A local file path to an image to use as the slot's cover image
 * (e.g. a screenshot saved via `surface_save`). Leave empty to keep the slot's existing cover image.
 * @struct_end
 */

/**
 * @struct PlayServicesSnapshotMetadata
 * @desc A saved-game slot's metadata, without its actual save data.
 * @member {String} [unique_name] The slot's unique identifier.
 * @member {String} [description] The slot's description.
 * @member {String} [device_name] The name of the device that last wrote this slot, if known.
 * @member {Real} last_modified_timestamp When this slot was last modified, in milliseconds since
 * epoch.
 * @member {Real} played_time The total played time recorded for this slot, in milliseconds.
 * @member {Real} progress_value The progress value recorded for this slot.
 * @member {Bool} has_change_pending Whether this slot has local changes not yet uploaded to the
 * server.
 * @member {String} [cover_image_uri] A URI for the slot's cover image. Convert to a local path with
 * ${function.play_services_uri_to_path} before loading it as a sprite. Only present if a cover image
 * was set.
 * @struct_end
 */

/**
 * @struct PlayServicesSnapshotOpenInfo
 * @desc The result of opening a saved-game slot. Exactly one of two field groups is populated,
 * selected by `is_conflict`: `snapshot_metadata`/`data` when the slot opened cleanly; `conflict_id`/
 * `snapshot_metadata_local`/`data_local`/`snapshot_metadata_remote`/`data_remote` when it opened into
 * a conflict that needs ${function.play_services_saved_games_resolve_conflict}. Only
 * ${constant.PlayServicesSavedGamesConflictPolicy}.Manual can ever produce a conflict result - every
 * other policy value makes Google Play auto-resolve on the server, so this struct's conflict fields
 * only ever populate for callers using `Manual`.
 * @member {Bool} is_conflict Whether this open resulted in an unresolved conflict.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata] The opened slot's metadata. Only
 * present when `!is_conflict`.
 * @member {String} [data] The opened slot's save data. Only present when `!is_conflict`.
 * @member {String} [conflict_id] The ID to pass to
 * ${function.play_services_saved_games_resolve_conflict}. Only present when `is_conflict`.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_local] The local (device) side of
 * the conflict. Only present when `is_conflict`.
 * @member {String} [data_local] The local side's save data. Only present when `is_conflict`.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_remote] The remote (server) side of
 * the conflict. Only present when `is_conflict`.
 * @member {String} [data_remote] The remote side's save data. Only present when `is_conflict`.
 * @struct_end
 */

/**
 * @function play_services_saved_games_show_saved_games_ui
 * @desc Shows the system Saved Games UI overlay, letting the player pick an existing slot, create a
 * new one, or (optionally) delete a slot.
 * @param {String} title The title text to display on the overlay.
 * @param {Bool} button_add Whether to show a button for creating a new save slot.
 * @param {Bool} button_delete Whether to show a button for deleting a save slot.
 * @param {Real} max_results The maximum number of existing save slots to list.
 * @param {Function} callback The function to call once the UI is dismissed.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the overlay is dismissed - whichever way the player closed it.
 * @member {Struct.PlayServicesResult} status The overlay's outcome. `success` is `false` only if
 * launching the picker itself failed (`result` is then ${constant.PlayServicesSavedGamesUIResult}.Error).
 * @member {Enum.PlayServicesSavedGamesUIResult} result Which way the overlay was closed.
 * @member {Struct.PlayServicesSnapshotMetadata} [metadata] The selected slot's metadata. Only present
 * when `result` is ${constant.PlayServicesSavedGamesUIResult}.Selected - open it with
 * ${function.play_services_saved_games_open} using `metadata.unique_name`.
 * @event_end
 * @example
 * ```gml
 * play_services_saved_games_show_saved_games_ui("Load or create a save", true, true, 5,
 *     function(_status, _result, _metadata = undefined)
 *     {
 *         if (_result == PlayServicesSavedGamesUIResult.Selected)
 *             play_services_saved_games_open(_metadata.unique_name, false,
 *                 PlayServicesSavedGamesConflictPolicy.MostRecentlyModified, open_callback);
 *     });
 * ```
 * @function_end
 */

/**
 * @function play_services_saved_games_commit_and_close
 * @desc Writes data to a save slot and closes it, releasing the handle opened by
 * ${function.play_services_saved_games_open}.
 * @param {Struct.PlayServicesSavedGameCommitOptions} options The slot name, data, and metadata to
 * write. `options.name` must refer to a slot opened this session.
 * @param {Function} callback The function to call once the commit completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated/${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if `options.name` doesn't refer to a slot opened this
 * session.
 * @event callback
 * @desc Fires once, when the commit completes or fails.
 * @member {Struct.PlayServicesResult} status The commit's outcome.
 * @member {Struct.PlayServicesSnapshotMetadata} [metadata] The committed slot's server-confirmed
 * metadata. Only present on success.
 * @event_end
 * @example
 * ```gml
 * var _options = new PlayServicesSavedGameCommitOptions();
 * _options.name = "slot_1";
 * _options.data = json_stringify(save_struct);
 * _options.desc = "Level 3, 00:12:34";
 * _options.played_time_millis = -1; // leave unchanged
 * _options.progress_value = -1;     // leave unchanged
 * _options.cover_image_path = "";   // leave unchanged
 *
 * play_services_saved_games_commit_and_close(_options, function(_status, _metadata = undefined)
 * {
 *     if (_status.success)
 *         show_debug_message("Saved");
 * });
 * ```
 * @function_end
 */

/**
 * @function play_services_saved_games_load
 * @desc Loads the metadata for every save slot belonging to the signed-in player.
 * @param {Bool} force_reload If `true`, bypasses the local cache and fetches fresh data from the
 * server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The load's outcome. `success` is `true` with an empty
 * `snapshots` array both for a genuinely empty account and for some documented offline edge cases in
 * Google's own API - if you need to tell "no saves" apart from "couldn't check", also verify
 * connectivity independently.
 * @member {Array[Struct.PlayServicesSnapshotMetadata]} snapshots Every save slot's metadata. Empty on
 * failure or on a genuinely empty account.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_saved_games_open
 * @desc Opens a save slot by name, creating it first if requested. On success, the slot is held open
 * until committed with ${function.play_services_saved_games_commit_and_close} or dropped with
 * ${function.play_services_saved_games_delete}. Re-opening an already-open name safely replaces the
 * held handle.
 * @param {String} name The unique identifier of the save slot.
 * @param {Bool} create_if_not_found If `true`, creates the slot when it doesn't already exist instead
 * of failing.
 * @param {Enum.PlayServicesSavedGamesConflictPolicy} conflict_policy How to resolve a conflict if
 * this slot has divergent local/server data. Only ${constant.PlayServicesSavedGamesConflictPolicy}.Manual
 * can ever produce an unresolved conflict in the callback - every other value auto-resolves on the
 * server, so pass `Manual` only when you're prepared to call
 * ${function.play_services_saved_games_resolve_conflict} yourself.
 * @param {Function} callback The function to call once the open completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated or ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Fires once, when the open completes or fails.
 * @member {Struct.PlayServicesResult} status The open's outcome.
 * @member {Struct.PlayServicesSnapshotOpenInfo} [info] The opened slot, or the conflict to resolve.
 * Only present on success.
 * @event_end
 * @example
 * ```gml
 * play_services_saved_games_open("slot_1", true, PlayServicesSavedGamesConflictPolicy.MostRecentlyModified,
 *     function(_status, _info = undefined)
 *     {
 *         if (!_status.success) return;
 *
 *         if (_info.is_conflict)
 *         {
 *             // Only reachable when conflict_policy was Manual.
 *             play_services_saved_games_resolve_conflict(_info.conflict_id, true, resolve_callback);
 *         }
 *         else
 *         {
 *             var _save = _info.data != "" ? json_parse(_info.data) : undefined;
 *         }
 *     });
 * ```
 * @function_end
 */

/**
 * @function play_services_saved_games_delete
 * @desc Deletes a save slot. `name` must refer to a slot opened this session with
 * ${function.play_services_saved_games_open}.
 * @param {String} name The unique identifier of the save slot.
 * @param {Function} callback The function to call once the delete completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated/${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if `name` doesn't refer to a slot opened this session.
 * @event callback
 * @desc Fires once, when the delete completes or fails.
 * @member {Struct.PlayServicesResult} status The delete's outcome.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_saved_games_resolve_conflict
 * @desc Resolves a conflict previously reported by ${function.play_services_saved_games_open} (with
 * ${constant.PlayServicesSavedGamesConflictPolicy}.Manual) by picking one side as the winner.
 * [[Note: Resolving a conflict can itself produce a fresh conflict, per Google's own API - always
 * check `info.is_conflict` on the result and be prepared to call this function again with the new
 * `conflict_id` rather than assuming one call always settles it.]]
 * @param {String} conflict_id The conflict ID from the triggering
 * ${struct.PlayServicesSnapshotOpenInfo}.
 * @param {Bool} use_local `true` to keep the local (device) side, `false` to keep the remote (server)
 * side.
 * @param {Function} callback The function to call once the resolution completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated/${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if there is no pending conflict matching `use_local`.
 * @event callback
 * @desc Fires once, when the resolution completes or fails.
 * @member {Struct.PlayServicesResult} status The resolution's outcome.
 * @member {Struct.PlayServicesSnapshotOpenInfo} [info] The resolved slot, or a fresh conflict to
 * resolve again (see the note above). Only present on success.
 * @event_end
 * @function_end
 */

/**
 * @const PlayServicesSavedGamesConflictPolicy
 * @desc How ${function.play_services_saved_games_open} resolves a slot with divergent local/server
 * data. Every value except `Manual` auto-resolves on the server without ever surfacing a conflict.
 * @member Manual The game resolves the conflict itself via
 * ${function.play_services_saved_games_resolve_conflict} - the only value that can produce an
 * unresolved conflict result.
 * @member LongestPlaytime Keeps whichever side has the greater `played_time_millis`.
 * @member LastKnownGood Keeps the last version the server confirmed as consistent.
 * @member MostRecentlyModified Keeps whichever side has the more recent modification timestamp.
 * @member HighestProgress Keeps whichever side has the greater `progress_value`.
 * @const_end
 */

/**
 * @const PlayServicesSavedGamesUIResult
 * @desc How the ${function.play_services_saved_games_show_saved_games_ui} overlay was closed.
 * @member Cancelled The player closed the overlay without selecting or creating a slot.
 * @member Selected The player picked an existing slot - see the callback's `metadata` member.
 * @member CreatedNew The player created a new slot via the overlay's add button.
 * @member Error The overlay itself failed to launch.
 * @const_end
 */

/**
 * @module savedgames
 * @title Saved Games
 * @desc Saving and loading player progress to Google's servers, synchronized across every device the
 * player signs into, including conflict resolution and the system Saved Games UI.
 *
 * @section_func
 * @ref play_services_saved_games_show_saved_games_ui
 * @ref play_services_saved_games_commit_and_close
 * @ref play_services_saved_games_load
 * @ref play_services_saved_games_open
 * @ref play_services_saved_games_delete
 * @ref play_services_saved_games_resolve_conflict
 * @section_end
 *
 * @section_struct
 * @ref PlayServicesSavedGameCommitOptions
 * @ref PlayServicesSnapshotMetadata
 * @ref PlayServicesSnapshotOpenInfo
 * @section_end
 *
 * @section_const
 * @ref PlayServicesSavedGamesConflictPolicy
 * @ref PlayServicesSavedGamesUIResult
 * @section_end
 *
 * @module_end
 */
