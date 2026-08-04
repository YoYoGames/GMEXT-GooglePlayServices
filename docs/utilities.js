
/**
 * @function play_services_uri_to_path
 * @desc Downloads and converts a Play Games image URI (as returned in `icon_image_uri`,
 * `hi_res_image_uri`, `cover_image_uri`, etc. throughout this extension) into a local file path,
 * suitable for loading with ${function.sprite_add}. The image is fetched from the network if not
 * already cached and written to a temporary PNG file.
 * @param {String} uri The image URI to resolve, as returned by another function in this extension.
 * @param {Function} callback The function to call once the download/conversion completes.
 * @returns {Enum.PlayServicesError} ${constant.PlayServicesError}.Ok if the request was accepted,
 * ${constant.PlayServicesError}.ActivityNull otherwise.
 * @event callback
 * @desc Fires once, when the download completes, fails, or times out (after 30 seconds).
 * @member {Struct.PlayServicesResult} status The conversion's outcome.
 * @member {String} [path] The local file path to the downloaded image. Only present on success.
 * @event_end
 * @example
 * ```gml
 * play_services_uri_to_path(_player.icon_image_uri, function(_status, _path = undefined)
 * {
 *     if (_status.success)
 *         icon_sprite = sprite_add(_path, 1, false, false, 0, 0);
 * });
 * ```
 * @function_end
 */

/**
 * @module utilities
 * @title Utilities
 * @desc Helper functions for working with data returned by other modules.
 *
 * @section_func
 * @ref play_services_uri_to_path
 * @section_end
 *
 * @module_end
 */
