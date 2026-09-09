/**
 * @struct PlayServicesSavedGameCommitOptions
 * @desc The content and the metadata to write when a saved game slot is committed with
 * ${function.play_services_saved_games_commit_and_close}.
 * @member {String} name The unique identifier of the save slot. It must match a slot that has
 * already been opened in this session with ${function.play_services_saved_games_open}.
 * @member {String} data The save data to write, as a string. A JSON encoded string is a common
 * choice for structured data.
 * @member {String} desc The description to display for this save slot in the system Saved Games UI.
 * Leave it empty to keep the existing description of the slot unchanged.
 * @member {Real} played_time_millis The total played time to record for this save, in milliseconds.
 * Pass a negative value to leave the existing value of the slot unchanged.
 * @member {Real} progress_value The progress value to record for this save, which is an arbitrary
 * number of your own that is used to compare saves, for example in a conflict resolution heuristic.
 * Pass a negative value to leave the existing value of the slot unchanged.
 * @member {String} cover_image_path A local file path to an image to use as the cover image of the
 * slot, for example a screenshot saved with `surface_save`. Leave it empty to keep the existing
 * cover image of the slot.
 * @struct_end
 */

/**
 * @struct PlayServicesSnapshotMetadata
 * @desc The metadata of a saved game slot, without its actual save data.
 * @member {String} [unique_name] The unique identifier of the slot.
 * @member {String} [description] The description of the slot.
 * @member {String} [device_name] The name of the device that last wrote to the slot, if it is known.
 * @member {Real} last_modified_timestamp When the slot was last modified, in milliseconds since the
 * epoch.
 * @member {Real} played_time The total played time that is recorded for the slot, in milliseconds.
 * @member {Real} progress_value The progress value that is recorded for the slot.
 * @member {Bool} has_change_pending Whether the slot has local changes that have not been uploaded
 * to the server yet.
 * @member {String} [cover_image_uri] A URI for the cover image of the slot. You should convert it to
 * a local path with ${function.play_services_uri_to_path} before loading it as a sprite. This is
 * only present if a cover image was set.
 * @struct_end
 */

/**
 * @struct PlayServicesSnapshotOpenInfo
 * @desc The result of opening a saved game slot. Exactly one of two groups of members is populated,
 * and `is_conflict` says which. When the slot opened cleanly, `snapshot_metadata` and `data` are
 * populated. When it opened into a conflict that needs
 * ${function.play_services_saved_games_resolve_conflict}, `conflict_id`,
 * `snapshot_metadata_local`, `data_local`, `snapshot_metadata_remote` and `data_remote` are
 * populated instead.
 * [[Note: Only ${constant.PlayServicesSavedGamesConflictPolicy}.Manual can ever produce a conflict
 * result. Every other policy value makes Google Play resolve the conflict automatically on the
 * server, so the conflict members of this struct are only ever populated for a game that opened the
 * slot with `Manual`.]]
 * @member {Bool} is_conflict Whether this open resulted in an unresolved conflict.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata] The metadata of the opened slot.
 * This is only present when `is_conflict` is `false`.
 * @member {String} [data] The save data of the opened slot. This is only present when `is_conflict`
 * is `false`.
 * @member {String} [conflict_id] The ID to pass to
 * ${function.play_services_saved_games_resolve_conflict}. This is only present when `is_conflict` is
 * `true`.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_local] The local side of the
 * conflict, which is the one on the device. This is only present when `is_conflict` is `true`.
 * @member {String} [data_local] The save data of the local side. This is only present when
 * `is_conflict` is `true`.
 * @member {Struct.PlayServicesSnapshotMetadata} [snapshot_metadata_remote] The remote side of the
 * conflict, which is the one on the server. This is only present when `is_conflict` is `true`.
 * @member {String} [data_remote] The save data of the remote side. This is only present when
 * `is_conflict` is `true`.
 * @struct_end
 */

/**
 * @function play_services_saved_games_show_saved_games_ui
 * @desc This function shows the system Saved Games UI overlay, which lets the player pick an
 * existing slot, create a new one, or, if you allow it, delete a slot.
 * @param {String} title The title text to display on the overlay.
 * @param {Bool} button_add Whether to show a button for creating a new save slot.
 * @param {Bool} button_delete Whether to show a button for deleting a save slot.
 * @param {Real} max_results The maximum number of existing save slots to list.
 * @param {Function} callback The function to call once the UI is dismissed.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the UI was launched, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the overlay is dismissed, whichever way the player closed it.
 * @member {Struct.PlayServicesResult} status The outcome of the overlay. The `success` member is
 * `false` only if launching the picker itself failed, in which case `result` is
 * ${constant.PlayServicesSavedGamesUIResult}.Error.
 * @member {Enum.PlayServicesSavedGamesUIResult} result Which way the overlay was closed.
 * @member {Struct.PlayServicesSnapshotMetadata} [metadata] The metadata of the selected slot. This
 * is only present when `result` is ${constant.PlayServicesSavedGamesUIResult}.Selected, and you open
 * that slot with ${function.play_services_saved_games_open} using `metadata.unique_name`.
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
 * The code above shows the saved games picker with both the add and the delete buttons enabled, and
 * opens whichever slot the player selected.
 * @function_end
 */

/**
 * @function play_services_saved_games_commit_and_close
 * @desc This function writes data to a save slot and then closes it, releasing the handle that
 * ${function.play_services_saved_games_open} opened.
 * @param {Struct.PlayServicesSavedGameCommitOptions} options The slot name, the data and the
 * metadata to write. The `options.name` member must refer to a slot that was opened in this session.
 * @param {Function} callback The function to call once the commit completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if `options.name` does not refer to a slot that was
 * opened in this session.
 * @event callback
 * @desc Called once, when the commit completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the commit.
 * @member {Struct.PlayServicesSnapshotMetadata} [metadata] The server confirmed metadata of the
 * committed slot. This is only present on success.
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
 * The code above writes the save data and a new description to an already open slot, leaving the
 * played time, the progress value and the cover image of that slot as they were.
 * @function_end
 */

/**
 * @function play_services_saved_games_load
 * @desc This function loads the metadata for every save slot that belongs to the signed-in player.
 * @param {Bool} force_reload Whether to bypass the local cache and fetch fresh data from the server.
 * @param {Function} callback The function to call once the load completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the load completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the load. Note that `success` is `true`
 * with an empty `snapshots` array both for an account that genuinely has no saves and for some
 * offline edge cases that Google documents in its own API. If you need to tell those two apart then
 * you should also check connectivity independently.
 * @member {Array[Struct.PlayServicesSnapshotMetadata]} snapshots The metadata of every save slot.
 * This is empty on failure, and also for an account that genuinely has no saves.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_saved_games_open
 * @desc This function opens a save slot by name, creating it first if you ask it to. On success the
 * slot is held open until it is committed with
 * ${function.play_services_saved_games_commit_and_close} or dropped with
 * ${function.play_services_saved_games_delete}. Opening a name that is already open is safe, and it
 * simply replaces the handle that is being held.
 * @param {String} name The unique identifier of the save slot.
 * @param {Bool} create_if_not_found Whether to create the slot when it does not already exist,
 * instead of failing.
 * @param {Enum.PlayServicesSavedGamesConflictPolicy} conflict_policy How to resolve a conflict if
 * this slot has local and server data that have diverged. Only
 * ${constant.PlayServicesSavedGamesConflictPolicy}.Manual can ever produce an unresolved conflict in
 * the callback, as every other value is resolved automatically on the server, so you should only
 * pass `Manual` when you are prepared to call
 * ${function.play_services_saved_games_resolve_conflict} yourself.
 * @param {Function} callback The function to call once the open completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull
 * otherwise.
 * @event callback
 * @desc Called once, when the open completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the open.
 * @member {Struct.PlayServicesSnapshotOpenInfo} [info] The opened slot, or the conflict that is to
 * be resolved. This is only present on success.
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
 * The code above opens a save slot, creating it if it does not exist yet, and parses the save data
 * that comes back once the slot has opened cleanly.
 * @function_end
 */

/**
 * @function play_services_saved_games_delete
 * @desc This function deletes a save slot. The `name` argument must refer to a slot that was opened
 * in this session with ${function.play_services_saved_games_open}.
 * @param {String} name The unique identifier of the save slot.
 * @param {Function} callback The function to call once the deletion completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if `name` does not refer to a slot that was opened
 * in this session.
 * @event callback
 * @desc Called once, when the deletion completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the deletion.
 * @event_end
 * @function_end
 */

/**
 * @function play_services_saved_games_resolve_conflict
 * @desc This function resolves a conflict that ${function.play_services_saved_games_open} reported
 * earlier, having been called with ${constant.PlayServicesSavedGamesConflictPolicy}.Manual, by
 * picking one of the two sides as the winner.
 * [[Note: Resolving a conflict can itself produce a fresh conflict, as it can in Google's own API.
 * You should always check `info.is_conflict` on the result and be prepared to call this function
 * again with the new `conflict_id`, rather than assuming that a single call always settles it.]]
 * @param {String} conflict_id The conflict ID from the ${struct.PlayServicesSnapshotOpenInfo} that
 * triggered this.
 * @param {Bool} use_local Whether to keep the local side, which is the one on the device. Pass
 * `false` to keep the remote side, which is the one on the server.
 * @param {Function} callback The function to call once the resolution completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.NotAuthenticated / ${constant.PlayServicesError}.ActivityNull, or
 * ${constant.PlayServicesError}.InvalidArgument if there is no pending conflict that matches
 * `use_local`.
 * @event callback
 * @desc Called once, when the resolution completes or fails.
 * @member {Struct.PlayServicesResult} status The outcome of the resolution.
 * @member {Struct.PlayServicesSnapshotOpenInfo} [info] The resolved slot, or a fresh conflict that
 * is to be resolved in turn, for which see the note above. This is only present on success.
 * @event_end
 * @function_end
 */

/**
 * @const PlayServicesSavedGamesConflictPolicy
 * @desc How ${function.play_services_saved_games_open} resolves a slot whose local and server data
 * have diverged. Every value except `Manual` is resolved automatically on the server, without a
 * conflict ever being surfaced to the game.
 * @member Manual The game resolves the conflict itself with
 * ${function.play_services_saved_games_resolve_conflict}. This is the only value that can produce an
 * unresolved conflict result.
 * @member LongestPlaytime Whichever side has the greater `played_time_millis` is kept.
 * @member LastKnownGood The last version that the server confirmed as consistent is kept.
 * @member MostRecentlyModified Whichever side has the more recent modification timestamp is kept.
 * @member HighestProgress Whichever side has the greater `progress_value` is kept.
 * @const_end
 */

/**
 * @const PlayServicesSavedGamesUIResult
 * @desc How the ${function.play_services_saved_games_show_saved_games_ui} overlay was closed.
 * @member Cancelled The player closed the overlay without selecting or creating a slot.
 * @member Selected The player picked an existing slot, which is given by the `metadata` member of
 * the callback.
 * @member CreatedNew The player created a new slot with the add button of the overlay.
 * @member Error The overlay itself failed to launch.
 * @const_end
 */

/**
 * @module savedgames
 * @title Saved Games
 * @desc This module covers saving and loading the progress of the player on Google's servers,
 * synchronised across every device that the player signs in on. It also covers conflict resolution
 * and the system Saved Games UI.
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
