// ##### extgen :: Auto-generated file do not edit!! #####

// #####################################################################
// # Macros
// #####################################################################

// #####################################################################
// # Enums
// #####################################################################

enum GPGSAchievementState
{
    Unlocked = 0,
    Revealed = 1,
    Hidden = 2
}

enum GPGSAchievementType
{
    Standard = 0,
    Incremental = 1
}

enum GPGSLeaderboardTimeSpan
{
    Daily = 0,
    Weekly = 1,
    AllTime = 2
}

enum GPGSLeaderboardCollection
{
    Public = 0,
    Friends = 3
}

enum GPGSLeaderboardScoreOrder
{
    SmallerIsBetter = 0,
    LargerIsBetter = 1
}

enum GPGSSavedGamesConflictPolicy
{
    Manual = -1,
    LongestPlaytime = 1,
    LastKnownGood = 2,
    MostRecentlyModified = 3,
    HighestProgress = 4
}

enum GPGSSavedGamesDisplayLimit
{
    None = -1
}

enum GPGSSavedGamesUIResult
{
    Cancelled = 0,
    Selected = 1,
    CreatedNew = 2,
    Deleted = 3,
    Error = -1
}

// #####################################################################
// # Constructors
// #####################################################################

/**
 * @returns {Struct.GPGSSavedGameCommitOptions} 
 */
function GPGSSavedGameCommitOptions() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore 
     */
    static __uid = 2822368498;

    self.name = undefined;
    self.data = undefined;
    self.desc = undefined;
    self.played_time_millis = undefined;
    self.progress_value = undefined;
    self.cover_image_path = undefined;

}

// #####################################################################
// # Codecs
// #####################################################################

/**
 * @func __GPGSSavedGameCommitOptions_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSavedGameCommitOptions} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore 
 */
function __GPGSSavedGameCommitOptions_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: name, type: String
        if (!is_string(self.name)) show_error($"{_where} :: self.name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.name));
        buffer_write(_buffer, buffer_string, self.name);

        // field: data, type: String
        if (!is_string(self.data)) show_error($"{_where} :: self.data expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.data));
        buffer_write(_buffer, buffer_string, self.data);

        // field: desc, type: String
        if (!is_string(self.desc)) show_error($"{_where} :: self.desc expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.desc));
        buffer_write(_buffer, buffer_string, self.desc);

        // field: played_time_millis, type: Float64
        if (!is_numeric(self.played_time_millis)) show_error($"{_where} :: self.played_time_millis expected number", true);
        buffer_write(_buffer, buffer_f64, self.played_time_millis);

        // field: progress_value, type: Float64
        if (!is_numeric(self.progress_value)) show_error($"{_where} :: self.progress_value expected number", true);
        buffer_write(_buffer, buffer_f64, self.progress_value);

        // field: cover_image_path, type: String
        if (!is_string(self.cover_image_path)) show_error($"{_where} :: self.cover_image_path expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.cover_image_path));
        buffer_write(_buffer, buffer_string, self.cover_image_path);

    }
}

/**
 * @func __GPGSSavedGameCommitOptions_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSavedGameCommitOptions} 
 * @ignore 
 */
function __GPGSSavedGameCommitOptions_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSavedGameCommitOptions();
    with (_inst)
    {
        // field: name, type: String
        buffer_read(_buffer, buffer_u32);
        self.name = buffer_read(_buffer, buffer_string);

        // field: data, type: String
        buffer_read(_buffer, buffer_u32);
        self.data = buffer_read(_buffer, buffer_string);

        // field: desc, type: String
        buffer_read(_buffer, buffer_u32);
        self.desc = buffer_read(_buffer, buffer_string);

        // field: played_time_millis, type: Float64
        self.played_time_millis = buffer_read(_buffer, buffer_f64);

        // field: progress_value, type: Float64
        self.progress_value = buffer_read(_buffer, buffer_f64);

        // field: cover_image_path, type: String
        buffer_read(_buffer, buffer_u32);
        self.cover_image_path = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

// #####################################################################
// # Functions
// #####################################################################

// Skipping function gpgs_is_available (no wrapper is required)


/**
 * @param {Function} _callback
 */
function gpgs_sign_in(_callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_sign_in(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Function} _callback
 */
function gpgs_is_authenticated(_callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_is_authenticated(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _server_client_id
 * @param {Bool} _force_refresh_token
 * @param {Function} _callback
 */
function gpgs_request_server_side_access(_server_client_id, _force_refresh_token, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _server_client_id, type: String
    if (!is_string(_server_client_id)) show_error($"{_GMFUNCTION_} :: _server_client_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_server_client_id));
    buffer_write(__args_buffer, buffer_string, _server_client_id);

    // param: _force_refresh_token, type: Bool
    if (!is_bool(_force_refresh_token)) show_error($"{_GMFUNCTION_} :: _force_refresh_token expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_refresh_token);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_request_server_side_access(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Function} _callback
 */
function gpgs_player_current(_callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_player_current(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Function} _callback
 */
function gpgs_player_current_id(_callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_player_current_id(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_player_stats_load(_force_reload, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_player_stats_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

// Skipping function gpgs_achievements_show (no wrapper is required)


/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 */
function gpgs_achievements_increment(_achievement_id, _steps, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _steps, type: Float64
    if (!is_numeric(_steps)) show_error($"{_GMFUNCTION_} :: _steps expected number", true);
    buffer_write(__args_buffer, buffer_f64, _steps);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_achievements_increment(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 */
function gpgs_achievements_reveal(_achievement_id, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_achievements_reveal(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 */
function gpgs_achievements_set_steps(_achievement_id, _steps, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _steps, type: Float64
    if (!is_numeric(_steps)) show_error($"{_GMFUNCTION_} :: _steps expected number", true);
    buffer_write(__args_buffer, buffer_f64, _steps);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_achievements_set_steps(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 */
function gpgs_achievements_unlock(_achievement_id, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_achievements_unlock(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_achievements_get_status(_force_reload, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_achievements_get_status(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

// Skipping function gpgs_leaderboard_show_all (no wrapper is required)


// Skipping function gpgs_leaderboard_show (no wrapper is required)


/**
 * @param {String} _leaderboard_id
 * @param {Real} _score
 * @param {String} _score_tag
 * @param {Function} _callback
 */
function gpgs_leaderboard_submit_score(_leaderboard_id, _score, _score_tag, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _score, type: Float64
    if (!is_numeric(_score)) show_error($"{_GMFUNCTION_} :: _score expected number", true);
    buffer_write(__args_buffer, buffer_f64, _score);

    // param: _score_tag, type: String
    if (!is_string(_score_tag)) show_error($"{_GMFUNCTION_} :: _score_tag expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_score_tag));
    buffer_write(__args_buffer, buffer_string, _score_tag);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_leaderboard_submit_score(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _leaderboard_id
 * @param {Enum.GPGSLeaderboardTimeSpan} _span
 * @param {Enum.GPGSLeaderboardCollection} _leaderboard_collection
 * @param {Real} _max_results
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_leaderboard_load_player_centered_scores(_leaderboard_id, _span, _leaderboard_collection, _max_results, _force_reload, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _span, type: enum GPGSLeaderboardTimeSpan

    if (!is_numeric(_span)) show_error($"{_GMFUNCTION_} :: _span expected number", true);
    buffer_write(__args_buffer, buffer_s32, _span);

    // param: _leaderboard_collection, type: enum GPGSLeaderboardCollection

    if (!is_numeric(_leaderboard_collection)) show_error($"{_GMFUNCTION_} :: _leaderboard_collection expected number", true);
    buffer_write(__args_buffer, buffer_s32, _leaderboard_collection);

    // param: _max_results, type: Float64
    if (!is_numeric(_max_results)) show_error($"{_GMFUNCTION_} :: _max_results expected number", true);
    buffer_write(__args_buffer, buffer_f64, _max_results);

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_leaderboard_load_player_centered_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _leaderboard_id
 * @param {Enum.GPGSLeaderboardTimeSpan} _span
 * @param {Enum.GPGSLeaderboardCollection} _leaderboard_collection
 * @param {Real} _max_results
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_leaderboard_load_top_scores(_leaderboard_id, _span, _leaderboard_collection, _max_results, _force_reload, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _span, type: enum GPGSLeaderboardTimeSpan

    if (!is_numeric(_span)) show_error($"{_GMFUNCTION_} :: _span expected number", true);
    buffer_write(__args_buffer, buffer_s32, _span);

    // param: _leaderboard_collection, type: enum GPGSLeaderboardCollection

    if (!is_numeric(_leaderboard_collection)) show_error($"{_GMFUNCTION_} :: _leaderboard_collection expected number", true);
    buffer_write(__args_buffer, buffer_s32, _leaderboard_collection);

    // param: _max_results, type: Float64
    if (!is_numeric(_max_results)) show_error($"{_GMFUNCTION_} :: _max_results expected number", true);
    buffer_write(__args_buffer, buffer_f64, _max_results);

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_leaderboard_load_top_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _uri
 * @param {Function} _callback
 */
function gpgs_uri_to_path(_uri, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _uri, type: String
    if (!is_string(_uri)) show_error($"{_GMFUNCTION_} :: _uri expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_uri));
    buffer_write(__args_buffer, buffer_string, _uri);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_uri_to_path(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _title
 * @param {Bool} _button_add
 * @param {Bool} _button_delete
 * @param {Real} _max_results
 * @param {Function} _callback
 */
function gpgs_saved_games_show_saved_games_ui(_title, _button_add, _button_delete, _max_results, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _title, type: String
    if (!is_string(_title)) show_error($"{_GMFUNCTION_} :: _title expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_title));
    buffer_write(__args_buffer, buffer_string, _title);

    // param: _button_add, type: Bool
    if (!is_bool(_button_add)) show_error($"{_GMFUNCTION_} :: _button_add expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _button_add);

    // param: _button_delete, type: Bool
    if (!is_bool(_button_delete)) show_error($"{_GMFUNCTION_} :: _button_delete expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _button_delete);

    // param: _max_results, type: Float64
    if (!is_numeric(_max_results)) show_error($"{_GMFUNCTION_} :: _max_results expected number", true);
    buffer_write(__args_buffer, buffer_f64, _max_results);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_show_saved_games_ui(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Struct.GPGSSavedGameCommitOptions} _options
 * @param {Function} _callback
 */
function gpgs_saved_games_commit_and_close(_options, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct GPGSSavedGameCommitOptions
    
    __GPGSSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_commit_and_close(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Struct.GPGSSavedGameCommitOptions} _options
 * @param {Function} _callback
 */
function gpgs_saved_games_commit_new(_options, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct GPGSSavedGameCommitOptions
    
    __GPGSSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_commit_new(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_saved_games_load(_force_reload, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 */
function gpgs_saved_games_open(_name, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_open(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _name
 * @param {Enum.GPGSSavedGamesConflictPolicy} _conflict_policy
 * @param {Function} _callback
 */
function gpgs_saved_games_open_conflict(_name, _conflict_policy, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _conflict_policy, type: enum GPGSSavedGamesConflictPolicy

    if (!is_numeric(_conflict_policy)) show_error($"{_GMFUNCTION_} :: _conflict_policy expected number", true);
    buffer_write(__args_buffer, buffer_s32, _conflict_policy);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_open_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 */
function gpgs_saved_games_delete(_name, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_delete(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/**
 * @param {String} _conflict_id
 * @param {Bool} _use_local
 * @param {Function} _callback
 */
function gpgs_saved_games_resolve_conflict(_conflict_id, _use_local, _callback)
{
    static __dispatcher = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _conflict_id, type: String
    if (!is_string(_conflict_id)) show_error($"{_GMFUNCTION_} :: _conflict_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_conflict_id));
    buffer_write(__args_buffer, buffer_string, _conflict_id);

    // param: _use_local, type: Bool
    if (!is_bool(_use_local)) show_error($"{_GMFUNCTION_} :: _use_local expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _use_local);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var _return_value = __gpgs_saved_games_resolve_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return _return_value;
}

/// @ignore
function __GMGooglePlayServices_get_decoders()
{
    static __decoders = [
        __GPGSSavedGameCommitOptions_decode
    ];
    return __decoders;
}
/// @ignore
function __GMGooglePlayServices_get_dispatcher()
{
    static __dispatcher = new __GMNativeFunctionDispatcher(__GMGooglePlayServices_invocation_handler, __GMGooglePlayServices_get_decoders());
    return __dispatcher;
}
