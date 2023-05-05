
/**
 * @func GooglePlayServices_SavedGames_CommitAndClose
 * @desc This function requests the Google Play Services API to commit data to a given save slot. The unique identifier of the slot needs to exist meaning that this function will only update already existing files (see ${function.GooglePlayServices_SavedGames_CommitNew} for creating new ones) but the `description`, `data` and `coverPath` can be changed if needed.
 * @param {string} name The unique identifier of the save game slot.
 * @param {string} description The description of the current save slot.
 * @param {string} data A string containing the data you want to save (note you can use json formatted string to store data).
 * @param {string} coverPath The path to the image to be used as a save slot cover image.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_SavedGames_CommitAndClose"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_CommitAndClose("slot_1", "Random Description....", json_stringify(struct), coverPath);
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_CommitAndClose")
 * {
 *     if(async_load[? "success"])
 *       show_debug_message("Saved")
 *     else
 *       show_debug_message("Not saved :(")
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_CommitAndClose() {}

/**
 * @func GooglePlayServices_SavedGames_CommitNew
 * @desc This function requests the Google Play Services API to commit data to a given save slot. This function will create a new slot, if you want to update an already existing slot use the ${function.GooglePlayServices_SavedGames_CommitAndClose} function.
 * @param {string} name The unique identifier of the save game slot.
 * @param {string} description The description of the current save slot.
 * @param {string} data A string containing the data you want to save (note you can use json formatted string to store data).
 * @param {string} coverPath The path to the image to be used as a save slot cover image.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_SavedGames_CommitAndClose"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} [snapshotMetadata] A json formatted string of ${struct.SnapshotMetadataJSON}. This string can be parsed into a struct with the function json_parse. Only available if the task succeeds.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_CommitNew("slot_1", "Random Description....", json_stringify(struct), coverPath);
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_CommitNew")
 * {
 *     if(async_load[? "success"])
 *       show_debug_message("Created");
 * 
 *       var snapshots = json_parse(async_load[?"snapshotMetadata"]);
 *       // Do something with the snapshots
 *     else
 *       show_debug_message("Not created :(")
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_CommitNew() {}

/**
 * @func GooglePlayServices_SavedGames_Delete
 * @desc This function requests the Google Play Services API to delete a given save slot given its unique identifier.
 * @param {string} name The unique identifier of the save game slot.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string "GooglePlayServices_SavedGames_Delete"
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_Delete("slot_1");
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_Delete")
 * {
 *     if(async_load[? "success"])
 *       show_debug_message("Deleted")
 *     else
 *       show_debug_message("Not Deleted :(")
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_Delete() {}

/**
 * @func GooglePlayServices_SavedGames_DiscardAndClose
 * @desc This function requests the Google Play Services API to discard changes and close the currently opened save slot.
 * @param {string} name The unique identifier of the save game slot.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_SavedGames_DiscardAndClose"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_DiscardAndClose("slot_1")
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_DiscardAndClose")
 * {
 *     if(async_load[? "success"])
 *       show_debug_message("Closed (discarding)")
 *     else
 *       show_debug_message("Not closed :(")
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_DiscardAndClose() {}

/**
 * @func GooglePlayServices_SavedGames_Load
 * @desc This function requests the Google Play Services API to query all the save slot metadata of the currently signed-in user.
 * @param {boolean} forceReload Whether or not the current local cache should be cleared and a new fetch should be performed from the server.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_SavedGames_Load"`
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} [snapshots] A json formatted string of an array of ${struct.SnapshotMetadataJSON}. This string can be parsed into a struct with the function json_parse. Only available if the task succeeds.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_Load(true)
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_Load")
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     var snapshots = json_parse(async_load[?"snapshots"]);
 *     //do something with snapshots
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_Load() {}

/**
 * @func GooglePlayServices_SavedGames_Open
 * @desc This function requests the Google Play Services API to open a given save slot given its unique name identifier.
 * @param {string} name The unique identifier of the save game slot.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string "GooglePlayServices_SavedGames_Open"
 * @member {real} ind The id of the request this callback refers to.
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {string} [data] The string previously saved to the Google Play Services. Only available if the task succeeds.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_Open("slot_1")
 * ```
 * The code sample above save the identifier that can be used inside an ${event.social}
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_SavedGames_Open") 
 * if (!async_load[? "success"])
 * {
 *     GooglePlayServices_SavedGames_Load(true);
 * 
 *     var data = async_load[? "data"];
 *     // do something with the data
 *     return;
 * }
 * ```
 * The code above matches the response against the correct event type and logs the success of the task. For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_Open() {}

/**
 * @func GooglePlayServices_SavedGames_ShowSavedGamesUI
 * @desc This function requests Google Play Services API to open the saved games UI overlay, giving you the ability to see, add (optional) and delete (optional) the save slots.
 * 
 * [[NOTE: This function will open an overlay the user can interact with, during this time there are some Async Social events that can be trigger depending on the actions performed by the user. Theses events are showed in the tables below.]]
 * @param {string} title The text to be used as the title of the overlay.
 * @param {boolean} add Whether or not the **Add** slot button should be displayed.
 * @param {boolean} delete Whether or not the **Delete** slot button should be displayed.
 * @param {boolean} max Sets the maximum amount of slots allowed. This can be useful to limit the number of saves you want the player to be able to create.
 * @returns {real}
 * 
 * @event social
 * @desc Triggered whenever an existing slot is selected
 * @member {string} type The string `"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen"`.
 * @member {real} ind The id of the request this callback refers to.
 * @member {string} [snapshotMetadata] A json formatted string of ${struct.SnapshotMetadataJSON}. This string can be parsed into a struct with the function json_parse.
 * @event_end
 * 
 * @event social
 * @desc Triggered whenever a new slot is created.
 * @member {string} type The string `"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew"`.
 * @member {real} ind The id of the request this callback refers to.
 * @event_end
 * 
 * @event social
 * @desc Triggered when the dialog is closed.
 * @member {string} type The string `"GooglePlayServices_SavedGames_ShowSavedGamesUI_OnClose"`.
 * @member {real} ind The id of the request this callback refers to.
 * @event_end
 * 
 * @example
 * ```gml
 * GooglePlayServices_SavedGames_ShowSavedGamesUI("Save Point", true, true, 3)
 * ```
 * The code above will trigger the save games overlay with options to create/delete save slots and with a maximum number of 3 slots. From now on the triggered events can be caught inside an ${event.social}, as follows:
 * ```gml
 * switch(async_load[? "type"])
 * {
 *     case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnNew":
 *         dialog_ind = get_string_async("Description: ","Slot #0");
 *         break;
 * 
 *     case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnOpen":
 *         var snapshotMeta = json_parse(async_load[? "snapshotMetadata"]);
 *         var uniqueName = snapshotMeta.uniqueName;
 *         GooglePlayServices_SavedGames_Open(uniqueName);    
 *         break;
 * 
 *     case "GooglePlayServices_SavedGames_ShowSavedGamesUI_OnExit":
 *         GooglePlayServices_SavedGames_Load(true);
 *         break;
 * }
 * ```
 * The code above matches the response against the correct event type and acts accordingly by requesting the user a description for the new slot (if a new slot was created), opens a existinf slot (using ${function.GooglePlayServices_SavedGames_Open}, if the user selects an existing one) or reloads the slot data (using ${function.GooglePlayServices_SavedGames_Load}, if the user closes the overaly). For a full usage sample check the provided demo.
 * @func_end
 */
function GooglePlayServices_SavedGames_ShowSavedGamesUI() {}


/**
 * @struct SnapshotMetadataJSON
 * @desc Is a json formatted string that represents a snapshot and its associated metadata.
 * @property {real} [coverImageAspectRatio] The aspect ratio of the cover image for this snapshot, if any.
 * @property {string} [coverImageUri] An image URI that can be used to load the snapshot's cover image (see GooglePlayServices_UriToPath). Not present if the metadata has no cover image.
 * @property {string} description The description of this snapshot.
 * @property {real} [deviceName] The name of the device that wrote this snapshot, if known.
 * @property {struct.GameJSON} game A struct of GameJSON.
 * @property {real} lastModifiedTimestamp The last time this snapshot was modified, in millis since epoch.
 * @property {struct.PlayerJSON} owner A struct of PlayerJSON.
 * @property {real} playedTime The played time of this snapshot in milliseconds.
 * @property {real} [progressValue] The progress value for this snapshot.
 * @property {string} uniqueName The unique identifier of this snapshot.
 * @property {boolean} hasChangePending Indicates whether or not this snapshot has any changes pending that have not been uploaded to the server.
 * @struct_end
 */

/**
 * @module savedgames
 * @title SavedGames
 * @desc The Saved Games service gives you a convenient way to save your players' game progression to Google's servers. Your game can retrieve the saved game data to allow returning players to continue a game at their last save point from any device.
 * 
 * The Saved Games service makes it possible to synchronize a player's game data across multiple devices. For example, if you have a game that runs on Android, you can use the Saved Games service to allow a player to start a game on their Android phone, and then continue playing on a tablet without losing any of their progress. This service can also be used to ensure that a player's game play continues from where it left off even if their device is lost, destroyed, or traded in for a newer model.
 * 
 * @section_func
 * @desc The following functions are provided for working with saved games:
 * @reference GooglePlayServices_SavedGames_*
 * @section_end
 * 
 * @section_struct
 * @desc The following structurs are used as output values from the function calls to the GooglePlayServices API:
 * @reference SnapshotMetadataJSON
 * @section_end
 * 
 * @module_end
 */