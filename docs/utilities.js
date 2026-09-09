
/**
 * @function play_services_uri_to_path
 * @desc This function downloads a Play Games image URI, of the kind that is returned in
 * `icon_image_uri`, `hi_res_image_uri`, `cover_image_uri` and so on throughout this extension, and
 * converts it into a local file path that is suitable for loading with ${function.sprite_add}. The
 * image is fetched over the network if it is not already cached, and it is written to a temporary
 * PNG file.
 * @param {String} uri The image URI to resolve, as returned by another function in this extension.
 * @param {Function} callback The function to call once the download and the conversion complete.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted, or
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Called once, when the download completes, fails, or times out after 30 seconds.
 * @member {Struct.PlayServicesResult} status The outcome of the conversion.
 * @member {String} [path] The local file path of the downloaded image. This is only present on
 * success.
 * @event_end
 * @example
 * ```gml
 * play_services_uri_to_path(_player.icon_image_uri, function(_status, _path = undefined)
 * {
 *     if (_status.success)
 *         icon_sprite = sprite_add(_path, 1, false, false, 0, 0);
 * });
 * ```
 * The code above downloads the profile image of a player and loads the resulting file as a sprite
 * that the game can then draw.
 * @function_end
 */

/**
 * @module utilities
 * @title Utilities
 * @desc This module holds the helper functions for working with the data that the other modules
 * return.
 *
 * @section_func
 * @ref play_services_uri_to_path
 * @section_end
 *
 * @module_end
 */
