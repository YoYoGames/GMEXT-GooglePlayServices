
/**
 * @func GooglePlayServices_UriToPath
 * @desc Some of the functions callbacks in this API return URIs (unique resource identifiers). However if you need to open these files or load them as images (using the ${function.sprite_add} function) it is necessary to convert these URIs into paths. This function requests the Google Play Services API for the path to a given URI.
 * @param {string} uri The URI to get the path from.
 * @returns {real}
 * 
 * @event social
 * @member {string} type The string `"GooglePlayServices_UriToPath"`
 * @member {boolean} success Whether or not the function request succeeded.
 * @member {real} ind The id of the request this callback refers to.
 * @member {string} path The path to the resource.
 * @event_end
 * 
 * @example
 * ```gml
 * var request = GooglePlayServices_UriToPath(uri)
 * ```
 * The code sample above save the identifier that can be used inside ${event.social}.
 * ```gml
 * if(async_load[? "type"] == "GooglePlayServices_UriToPath")
 * if(async_load[?"ind"] == request)
 * {
 *     if(!async_load[?"success"])
 *        exit
 * 
 *     sprite = sprite_add(async_load[?"path"], 0, 0, 0, 0, 0);
 * }
 * ```
 * The code above matches the response against the correct event type and request identifier (ind) . And loads the resolved path as a sprite using the ${function.sprite_add} function.
 * @func_end
 */
function GooglePlayServices_UriToPath() {}


/**
 * @module utilities
 * @title Utilities
 * @desc This modules provides the user with some utility functions.
 * 
 * @section_func
 * @desc The following functions are provided for helping with development:
 * @reference GooglePlayServices_UriToPath
 * @section_end
 * 
 * @module_end
 */