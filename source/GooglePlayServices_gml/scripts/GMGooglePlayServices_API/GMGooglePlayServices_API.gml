// ##### extgen :: Auto-generated file do not edit!! #####

// #####################################################################
// # Macros
// #####################################################################

#macro PlayServicesMaxFriendsPageSize 25

#macro PlayServicesMaxLeaderboardResults 25

#macro PlayServicesMinPageSize 1

// #####################################################################
// # Enums
// #####################################################################

enum PlayServicesAchievementState
{
    Unlocked = 0,
    Revealed = 1,
    Hidden = 2
}

enum PlayServicesAchievementType
{
    Standard = 0,
    Incremental = 1
}

enum PlayServicesLeaderboardTimeSpan
{
    Daily = 0,
    Weekly = 1,
    AllTime = 2
}

enum PlayServicesLeaderboardCollection
{
    Public = 0,
    Friends = 3
}

enum PlayServicesLeaderboardScoreOrder
{
    SmallerIsBetter = 0,
    LargerIsBetter = 1
}

enum PlayServicesSavedGamesConflictPolicy
{
    Manual = -1,
    LongestPlaytime = 1,
    LastKnownGood = 2,
    MostRecentlyModified = 3,
    HighestProgress = 4
}

enum PlayServicesSavedGamesUIResult
{
    Cancelled = 0,
    Selected = 1,
    CreatedNew = 2,
    Error = -1
}

enum PlayServicesError
{
    Ok = 0,
    NotAuthenticated = -1,
    ActivityNull = -2,
    InvalidArgument = -3
}

// #####################################################################
// # Constructors
// #####################################################################

/**
 * @returns {Struct.PlayServicesSavedGameCommitOptions}
 */
function PlayServicesSavedGameCommitOptions() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1866852405;

    self.name = undefined;
    self.data = undefined;
    self.desc = undefined;
    self.played_time_millis = undefined;
    self.progress_value = undefined;
    self.cover_image_path = undefined;

}

/**
 * @returns {Struct.PlayServicesResult}
 */
function PlayServicesResult() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3714327798;

    self.success = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.PlayServicesPlayerInfo}
 */
function PlayServicesPlayerInfo() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1540281070;

    self.player_id = undefined;
    self.display_name = undefined;
    self.title = undefined;
    self.icon_image_uri = undefined;
    self.hi_res_image_uri = undefined;

}

/**
 * @returns {Struct.PlayServicesPlayerStatsInfo}
 */
function PlayServicesPlayerStatsInfo() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 22285045;

    self.average_session_length = undefined;
    self.days_since_last_played = undefined;
    self.number_of_purchases = undefined;
    self.number_of_sessions = undefined;
    self.session_percentile = undefined;
    self.spend_percentile = undefined;
    self.churn_probability = undefined;
    self.high_spender_probability = undefined;
    self.spend_probability = undefined;
    self.total_spend_next_28_days = undefined;

}

/**
 * @returns {Struct.PlayServicesAchievement}
 */
function PlayServicesAchievement() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3379023530;

    self.achievement_id = undefined;
    self.name = undefined;
    self.description = undefined;
    self.state = undefined;
    self.type = undefined;
    self.current_steps = undefined;
    self.total_steps = undefined;
    self.last_updated_timestamp = undefined;
    self.xp_value = undefined;
    self.revealed_image_uri = undefined;
    self.unlocked_image_uri = undefined;

}

/**
 * @returns {Struct.PlayServicesScoreSubmission}
 */
function PlayServicesScoreSubmission() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 998548871;

    self.raw_score = undefined;
    self.formatted_score = undefined;
    self.score_tag = undefined;
    self.new_best = undefined;

}

/**
 * @returns {Struct.PlayServicesLeaderboardVariant}
 */
function PlayServicesLeaderboardVariant() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 4230728379;

    self.collection = undefined;
    self.time_span = undefined;
    self.has_player_info = undefined;

}

/**
 * @returns {Struct.PlayServicesSnapshotMetadata}
 */
function PlayServicesSnapshotMetadata() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 238308842;

    self.unique_name = undefined;
    self.description = undefined;
    self.device_name = undefined;
    self.last_modified_timestamp = undefined;
    self.played_time = undefined;
    self.progress_value = undefined;
    self.has_change_pending = undefined;
    self.cover_image_uri = undefined;

}

/**
 * @returns {Struct.PlayServicesLeaderboardScore}
 */
function PlayServicesLeaderboardScore() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 858218960;

    self.display_rank = undefined;
    self.display_score = undefined;
    self.raw_score = undefined;
    self.score_tag = undefined;
    self.timestamp_millis = undefined;
    self.score_holder = undefined;

}

/**
 * @returns {Struct.PlayServicesScoreReportInfo}
 */
function PlayServicesScoreReportInfo() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1510410541;

    self.daily = undefined;
    self.weekly = undefined;
    self.all_time = undefined;

}

/**
 * @returns {Struct.PlayServicesLeaderboard}
 */
function PlayServicesLeaderboard() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3043143370;

    self.leaderboard_id = undefined;
    self.display_name = undefined;
    self.score_order = undefined;
    self.variants = undefined;

}

/**
 * @returns {Struct.PlayServicesSnapshotOpenInfo}
 */
function PlayServicesSnapshotOpenInfo() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 204936095;

    self.is_conflict = undefined;
    self.snapshot_metadata = undefined;
    self.data = undefined;
    self.conflict_id = undefined;
    self.snapshot_metadata_local = undefined;
    self.data_local = undefined;
    self.snapshot_metadata_remote = undefined;
    self.data_remote = undefined;

}

// #####################################################################
// # Codecs
// #####################################################################

/**
 * @func __PlayServicesSavedGameCommitOptions_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesSavedGameCommitOptions} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesSavedGameCommitOptions_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
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
 * @func __PlayServicesSavedGameCommitOptions_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesSavedGameCommitOptions}
 * @ignore
 */
function __PlayServicesSavedGameCommitOptions_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesSavedGameCommitOptions();
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

/**
 * @func __PlayServicesResult_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesResult} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesResult_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __PlayServicesResult_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesResult}
 * @ignore
 */
function __PlayServicesResult_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesResult();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __PlayServicesPlayerInfo_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesPlayerInfo} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesPlayerInfo_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: player_id, type: optional<String>
        if (is_undefined(self.player_id))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.player_id)) show_error($"{_where} :: self.player_id expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.player_id));
            buffer_write(_buffer, buffer_string, self.player_id);
        }

        // field: display_name, type: optional<String>
        if (is_undefined(self.display_name))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.display_name)) show_error($"{_where} :: self.display_name expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.display_name));
            buffer_write(_buffer, buffer_string, self.display_name);
        }

        // field: title, type: optional<String>
        if (is_undefined(self.title))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.title)) show_error($"{_where} :: self.title expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.title));
            buffer_write(_buffer, buffer_string, self.title);
        }

        // field: icon_image_uri, type: optional<String>
        if (is_undefined(self.icon_image_uri))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.icon_image_uri)) show_error($"{_where} :: self.icon_image_uri expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.icon_image_uri));
            buffer_write(_buffer, buffer_string, self.icon_image_uri);
        }

        // field: hi_res_image_uri, type: optional<String>
        if (is_undefined(self.hi_res_image_uri))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.hi_res_image_uri)) show_error($"{_where} :: self.hi_res_image_uri expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.hi_res_image_uri));
            buffer_write(_buffer, buffer_string, self.hi_res_image_uri);
        }

    }
}

/**
 * @func __PlayServicesPlayerInfo_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesPlayerInfo}
 * @ignore
 */
function __PlayServicesPlayerInfo_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesPlayerInfo();
    with (_inst)
    {
        // field: player_id, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.player_id = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.player_id = undefined;
        }

        // field: display_name, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.display_name = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.display_name = undefined;
        }

        // field: title, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.title = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.title = undefined;
        }

        // field: icon_image_uri, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.icon_image_uri = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.icon_image_uri = undefined;
        }

        // field: hi_res_image_uri, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.hi_res_image_uri = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.hi_res_image_uri = undefined;
        }

    }

    return _inst;
}

/**
 * @func __PlayServicesPlayerStatsInfo_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesPlayerStatsInfo} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesPlayerStatsInfo_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: average_session_length, type: Float64
        if (!is_numeric(self.average_session_length)) show_error($"{_where} :: self.average_session_length expected number", true);
        buffer_write(_buffer, buffer_f64, self.average_session_length);

        // field: days_since_last_played, type: Float64
        if (!is_numeric(self.days_since_last_played)) show_error($"{_where} :: self.days_since_last_played expected number", true);
        buffer_write(_buffer, buffer_f64, self.days_since_last_played);

        // field: number_of_purchases, type: Float64
        if (!is_numeric(self.number_of_purchases)) show_error($"{_where} :: self.number_of_purchases expected number", true);
        buffer_write(_buffer, buffer_f64, self.number_of_purchases);

        // field: number_of_sessions, type: Float64
        if (!is_numeric(self.number_of_sessions)) show_error($"{_where} :: self.number_of_sessions expected number", true);
        buffer_write(_buffer, buffer_f64, self.number_of_sessions);

        // field: session_percentile, type: Float64
        if (!is_numeric(self.session_percentile)) show_error($"{_where} :: self.session_percentile expected number", true);
        buffer_write(_buffer, buffer_f64, self.session_percentile);

        // field: spend_percentile, type: Float64
        if (!is_numeric(self.spend_percentile)) show_error($"{_where} :: self.spend_percentile expected number", true);
        buffer_write(_buffer, buffer_f64, self.spend_percentile);

        // field: churn_probability, type: Float64
        if (!is_numeric(self.churn_probability)) show_error($"{_where} :: self.churn_probability expected number", true);
        buffer_write(_buffer, buffer_f64, self.churn_probability);

        // field: high_spender_probability, type: Float64
        if (!is_numeric(self.high_spender_probability)) show_error($"{_where} :: self.high_spender_probability expected number", true);
        buffer_write(_buffer, buffer_f64, self.high_spender_probability);

        // field: spend_probability, type: Float64
        if (!is_numeric(self.spend_probability)) show_error($"{_where} :: self.spend_probability expected number", true);
        buffer_write(_buffer, buffer_f64, self.spend_probability);

        // field: total_spend_next_28_days, type: Float64
        if (!is_numeric(self.total_spend_next_28_days)) show_error($"{_where} :: self.total_spend_next_28_days expected number", true);
        buffer_write(_buffer, buffer_f64, self.total_spend_next_28_days);

    }
}

/**
 * @func __PlayServicesPlayerStatsInfo_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesPlayerStatsInfo}
 * @ignore
 */
function __PlayServicesPlayerStatsInfo_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesPlayerStatsInfo();
    with (_inst)
    {
        // field: average_session_length, type: Float64
        self.average_session_length = buffer_read(_buffer, buffer_f64);

        // field: days_since_last_played, type: Float64
        self.days_since_last_played = buffer_read(_buffer, buffer_f64);

        // field: number_of_purchases, type: Float64
        self.number_of_purchases = buffer_read(_buffer, buffer_f64);

        // field: number_of_sessions, type: Float64
        self.number_of_sessions = buffer_read(_buffer, buffer_f64);

        // field: session_percentile, type: Float64
        self.session_percentile = buffer_read(_buffer, buffer_f64);

        // field: spend_percentile, type: Float64
        self.spend_percentile = buffer_read(_buffer, buffer_f64);

        // field: churn_probability, type: Float64
        self.churn_probability = buffer_read(_buffer, buffer_f64);

        // field: high_spender_probability, type: Float64
        self.high_spender_probability = buffer_read(_buffer, buffer_f64);

        // field: spend_probability, type: Float64
        self.spend_probability = buffer_read(_buffer, buffer_f64);

        // field: total_spend_next_28_days, type: Float64
        self.total_spend_next_28_days = buffer_read(_buffer, buffer_f64);

    }

    return _inst;
}

/**
 * @func __PlayServicesAchievement_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesAchievement} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesAchievement_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: achievement_id, type: optional<String>
        if (is_undefined(self.achievement_id))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.achievement_id)) show_error($"{_where} :: self.achievement_id expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.achievement_id));
            buffer_write(_buffer, buffer_string, self.achievement_id);
        }

        // field: name, type: optional<String>
        if (is_undefined(self.name))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.name)) show_error($"{_where} :: self.name expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.name));
            buffer_write(_buffer, buffer_string, self.name);
        }

        // field: description, type: optional<String>
        if (is_undefined(self.description))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.description)) show_error($"{_where} :: self.description expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.description));
            buffer_write(_buffer, buffer_string, self.description);
        }

        // field: state, type: enum PlayServicesAchievementState

        if (!is_numeric(self.state)) show_error($"{_where} :: self.state expected number", true);
        buffer_write(_buffer, buffer_s32, self.state);

        // field: type, type: enum PlayServicesAchievementType

        if (!is_numeric(self.type)) show_error($"{_where} :: self.type expected number", true);
        buffer_write(_buffer, buffer_s32, self.type);

        // field: current_steps, type: Float64
        if (!is_numeric(self.current_steps)) show_error($"{_where} :: self.current_steps expected number", true);
        buffer_write(_buffer, buffer_f64, self.current_steps);

        // field: total_steps, type: Float64
        if (!is_numeric(self.total_steps)) show_error($"{_where} :: self.total_steps expected number", true);
        buffer_write(_buffer, buffer_f64, self.total_steps);

        // field: last_updated_timestamp, type: Float64
        if (!is_numeric(self.last_updated_timestamp)) show_error($"{_where} :: self.last_updated_timestamp expected number", true);
        buffer_write(_buffer, buffer_f64, self.last_updated_timestamp);

        // field: xp_value, type: Float64
        if (!is_numeric(self.xp_value)) show_error($"{_where} :: self.xp_value expected number", true);
        buffer_write(_buffer, buffer_f64, self.xp_value);

        // field: revealed_image_uri, type: optional<String>
        if (is_undefined(self.revealed_image_uri))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.revealed_image_uri)) show_error($"{_where} :: self.revealed_image_uri expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.revealed_image_uri));
            buffer_write(_buffer, buffer_string, self.revealed_image_uri);
        }

        // field: unlocked_image_uri, type: optional<String>
        if (is_undefined(self.unlocked_image_uri))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.unlocked_image_uri)) show_error($"{_where} :: self.unlocked_image_uri expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.unlocked_image_uri));
            buffer_write(_buffer, buffer_string, self.unlocked_image_uri);
        }

    }
}

/**
 * @func __PlayServicesAchievement_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesAchievement}
 * @ignore
 */
function __PlayServicesAchievement_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesAchievement();
    with (_inst)
    {
        // field: achievement_id, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.achievement_id = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.achievement_id = undefined;
        }

        // field: name, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.name = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.name = undefined;
        }

        // field: description, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.description = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.description = undefined;
        }

        // field: state, type: enum PlayServicesAchievementState
        self.state = buffer_read(_buffer, buffer_s32);

        // field: type, type: enum PlayServicesAchievementType
        self.type = buffer_read(_buffer, buffer_s32);

        // field: current_steps, type: Float64
        self.current_steps = buffer_read(_buffer, buffer_f64);

        // field: total_steps, type: Float64
        self.total_steps = buffer_read(_buffer, buffer_f64);

        // field: last_updated_timestamp, type: Float64
        self.last_updated_timestamp = buffer_read(_buffer, buffer_f64);

        // field: xp_value, type: Float64
        self.xp_value = buffer_read(_buffer, buffer_f64);

        // field: revealed_image_uri, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.revealed_image_uri = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.revealed_image_uri = undefined;
        }

        // field: unlocked_image_uri, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.unlocked_image_uri = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.unlocked_image_uri = undefined;
        }

    }

    return _inst;
}

/**
 * @func __PlayServicesScoreSubmission_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesScoreSubmission} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesScoreSubmission_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: raw_score, type: Float64
        if (!is_numeric(self.raw_score)) show_error($"{_where} :: self.raw_score expected number", true);
        buffer_write(_buffer, buffer_f64, self.raw_score);

        // field: formatted_score, type: optional<String>
        if (is_undefined(self.formatted_score))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.formatted_score)) show_error($"{_where} :: self.formatted_score expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.formatted_score));
            buffer_write(_buffer, buffer_string, self.formatted_score);
        }

        // field: score_tag, type: optional<String>
        if (is_undefined(self.score_tag))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.score_tag)) show_error($"{_where} :: self.score_tag expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.score_tag));
            buffer_write(_buffer, buffer_string, self.score_tag);
        }

        // field: new_best, type: Bool
        if (!is_bool(self.new_best)) show_error($"{_where} :: self.new_best expected bool", true);
        buffer_write(_buffer, buffer_bool, self.new_best);

    }
}

/**
 * @func __PlayServicesScoreSubmission_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesScoreSubmission}
 * @ignore
 */
function __PlayServicesScoreSubmission_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesScoreSubmission();
    with (_inst)
    {
        // field: raw_score, type: Float64
        self.raw_score = buffer_read(_buffer, buffer_f64);

        // field: formatted_score, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.formatted_score = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.formatted_score = undefined;
        }

        // field: score_tag, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.score_tag = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.score_tag = undefined;
        }

        // field: new_best, type: Bool
        self.new_best = buffer_read(_buffer, buffer_bool);

    }

    return _inst;
}

/**
 * @func __PlayServicesLeaderboardVariant_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesLeaderboardVariant} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesLeaderboardVariant_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: collection, type: enum PlayServicesLeaderboardCollection

        if (!is_numeric(self.collection)) show_error($"{_where} :: self.collection expected number", true);
        buffer_write(_buffer, buffer_s32, self.collection);

        // field: time_span, type: enum PlayServicesLeaderboardTimeSpan

        if (!is_numeric(self.time_span)) show_error($"{_where} :: self.time_span expected number", true);
        buffer_write(_buffer, buffer_s32, self.time_span);

        // field: has_player_info, type: Bool
        if (!is_bool(self.has_player_info)) show_error($"{_where} :: self.has_player_info expected bool", true);
        buffer_write(_buffer, buffer_bool, self.has_player_info);

    }
}

/**
 * @func __PlayServicesLeaderboardVariant_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesLeaderboardVariant}
 * @ignore
 */
function __PlayServicesLeaderboardVariant_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesLeaderboardVariant();
    with (_inst)
    {
        // field: collection, type: enum PlayServicesLeaderboardCollection
        self.collection = buffer_read(_buffer, buffer_s32);

        // field: time_span, type: enum PlayServicesLeaderboardTimeSpan
        self.time_span = buffer_read(_buffer, buffer_s32);

        // field: has_player_info, type: Bool
        self.has_player_info = buffer_read(_buffer, buffer_bool);

    }

    return _inst;
}

/**
 * @func __PlayServicesSnapshotMetadata_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesSnapshotMetadata} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesSnapshotMetadata_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: unique_name, type: optional<String>
        if (is_undefined(self.unique_name))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.unique_name)) show_error($"{_where} :: self.unique_name expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.unique_name));
            buffer_write(_buffer, buffer_string, self.unique_name);
        }

        // field: description, type: optional<String>
        if (is_undefined(self.description))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.description)) show_error($"{_where} :: self.description expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.description));
            buffer_write(_buffer, buffer_string, self.description);
        }

        // field: device_name, type: optional<String>
        if (is_undefined(self.device_name))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.device_name)) show_error($"{_where} :: self.device_name expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.device_name));
            buffer_write(_buffer, buffer_string, self.device_name);
        }

        // field: last_modified_timestamp, type: Float64
        if (!is_numeric(self.last_modified_timestamp)) show_error($"{_where} :: self.last_modified_timestamp expected number", true);
        buffer_write(_buffer, buffer_f64, self.last_modified_timestamp);

        // field: played_time, type: Float64
        if (!is_numeric(self.played_time)) show_error($"{_where} :: self.played_time expected number", true);
        buffer_write(_buffer, buffer_f64, self.played_time);

        // field: progress_value, type: Float64
        if (!is_numeric(self.progress_value)) show_error($"{_where} :: self.progress_value expected number", true);
        buffer_write(_buffer, buffer_f64, self.progress_value);

        // field: has_change_pending, type: Bool
        if (!is_bool(self.has_change_pending)) show_error($"{_where} :: self.has_change_pending expected bool", true);
        buffer_write(_buffer, buffer_bool, self.has_change_pending);

        // field: cover_image_uri, type: optional<String>
        if (is_undefined(self.cover_image_uri))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.cover_image_uri)) show_error($"{_where} :: self.cover_image_uri expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.cover_image_uri));
            buffer_write(_buffer, buffer_string, self.cover_image_uri);
        }

    }
}

/**
 * @func __PlayServicesSnapshotMetadata_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesSnapshotMetadata}
 * @ignore
 */
function __PlayServicesSnapshotMetadata_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesSnapshotMetadata();
    with (_inst)
    {
        // field: unique_name, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.unique_name = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.unique_name = undefined;
        }

        // field: description, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.description = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.description = undefined;
        }

        // field: device_name, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.device_name = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.device_name = undefined;
        }

        // field: last_modified_timestamp, type: Float64
        self.last_modified_timestamp = buffer_read(_buffer, buffer_f64);

        // field: played_time, type: Float64
        self.played_time = buffer_read(_buffer, buffer_f64);

        // field: progress_value, type: Float64
        self.progress_value = buffer_read(_buffer, buffer_f64);

        // field: has_change_pending, type: Bool
        self.has_change_pending = buffer_read(_buffer, buffer_bool);

        // field: cover_image_uri, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.cover_image_uri = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.cover_image_uri = undefined;
        }

    }

    return _inst;
}

/**
 * @func __PlayServicesLeaderboardScore_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesLeaderboardScore} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesLeaderboardScore_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: display_rank, type: optional<String>
        if (is_undefined(self.display_rank))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.display_rank)) show_error($"{_where} :: self.display_rank expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.display_rank));
            buffer_write(_buffer, buffer_string, self.display_rank);
        }

        // field: display_score, type: optional<String>
        if (is_undefined(self.display_score))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.display_score)) show_error($"{_where} :: self.display_score expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.display_score));
            buffer_write(_buffer, buffer_string, self.display_score);
        }

        // field: raw_score, type: Float64
        if (!is_numeric(self.raw_score)) show_error($"{_where} :: self.raw_score expected number", true);
        buffer_write(_buffer, buffer_f64, self.raw_score);

        // field: score_tag, type: optional<String>
        if (is_undefined(self.score_tag))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.score_tag)) show_error($"{_where} :: self.score_tag expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.score_tag));
            buffer_write(_buffer, buffer_string, self.score_tag);
        }

        // field: timestamp_millis, type: Float64
        if (!is_numeric(self.timestamp_millis)) show_error($"{_where} :: self.timestamp_millis expected number", true);
        buffer_write(_buffer, buffer_f64, self.timestamp_millis);

        // field: score_holder, type: optional<struct PlayServicesPlayerInfo>
        if (is_undefined(self.score_holder))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (self.score_holder.__uid != 1540281070) show_error($"{_where} :: self.score_holder expected PlayServicesPlayerInfo", true);
            __PlayServicesPlayerInfo_encode(self.score_holder, _buffer, buffer_tell(_buffer), _where);
        }

    }
}

/**
 * @func __PlayServicesLeaderboardScore_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesLeaderboardScore}
 * @ignore
 */
function __PlayServicesLeaderboardScore_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesLeaderboardScore();
    with (_inst)
    {
        // field: display_rank, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.display_rank = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.display_rank = undefined;
        }

        // field: display_score, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.display_score = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.display_score = undefined;
        }

        // field: raw_score, type: Float64
        self.raw_score = buffer_read(_buffer, buffer_f64);

        // field: score_tag, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.score_tag = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.score_tag = undefined;
        }

        // field: timestamp_millis, type: Float64
        self.timestamp_millis = buffer_read(_buffer, buffer_f64);

        // field: score_holder, type: optional<struct PlayServicesPlayerInfo>
        if (buffer_read(_buffer, buffer_bool))
        {
            self.score_holder = __PlayServicesPlayerInfo_decode(_buffer, buffer_tell(_buffer));
        }
        else
        {
            self.score_holder = undefined;
        }

    }

    return _inst;
}

/**
 * @func __PlayServicesScoreReportInfo_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesScoreReportInfo} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesScoreReportInfo_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: daily, type: struct PlayServicesScoreSubmission
        if (self.daily.__uid != 998548871) show_error($"{_where} :: self.daily expected PlayServicesScoreSubmission", true);
        __PlayServicesScoreSubmission_encode(self.daily, _buffer, buffer_tell(_buffer), _where);

        // field: weekly, type: struct PlayServicesScoreSubmission
        if (self.weekly.__uid != 998548871) show_error($"{_where} :: self.weekly expected PlayServicesScoreSubmission", true);
        __PlayServicesScoreSubmission_encode(self.weekly, _buffer, buffer_tell(_buffer), _where);

        // field: all_time, type: struct PlayServicesScoreSubmission
        if (self.all_time.__uid != 998548871) show_error($"{_where} :: self.all_time expected PlayServicesScoreSubmission", true);
        __PlayServicesScoreSubmission_encode(self.all_time, _buffer, buffer_tell(_buffer), _where);

    }
}

/**
 * @func __PlayServicesScoreReportInfo_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesScoreReportInfo}
 * @ignore
 */
function __PlayServicesScoreReportInfo_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesScoreReportInfo();
    with (_inst)
    {
        // field: daily, type: struct PlayServicesScoreSubmission
        self.daily = __PlayServicesScoreSubmission_decode(_buffer, buffer_tell(_buffer));

        // field: weekly, type: struct PlayServicesScoreSubmission
        self.weekly = __PlayServicesScoreSubmission_decode(_buffer, buffer_tell(_buffer));

        // field: all_time, type: struct PlayServicesScoreSubmission
        self.all_time = __PlayServicesScoreSubmission_decode(_buffer, buffer_tell(_buffer));

    }

    return _inst;
}

/**
 * @func __PlayServicesLeaderboard_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesLeaderboard} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesLeaderboard_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: leaderboard_id, type: optional<String>
        if (is_undefined(self.leaderboard_id))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.leaderboard_id)) show_error($"{_where} :: self.leaderboard_id expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.leaderboard_id));
            buffer_write(_buffer, buffer_string, self.leaderboard_id);
        }

        // field: display_name, type: optional<String>
        if (is_undefined(self.display_name))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.display_name)) show_error($"{_where} :: self.display_name expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.display_name));
            buffer_write(_buffer, buffer_string, self.display_name);
        }

        // field: score_order, type: enum PlayServicesLeaderboardScoreOrder

        if (!is_numeric(self.score_order)) show_error($"{_where} :: self.score_order expected number", true);
        buffer_write(_buffer, buffer_s32, self.score_order);

        // field: variants, type: struct PlayServicesLeaderboardVariant[]
        if (!is_array(self.variants)) show_error($"{_where} :: self.variants expected array", true);
        var __length__ = array_length(self.variants);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.variants[_i].__uid != 4230728379) show_error($"{_where} :: self.variants[_i] expected PlayServicesLeaderboardVariant", true);
            __PlayServicesLeaderboardVariant_encode(self.variants[_i], _buffer, buffer_tell(_buffer), _where);
        }

    }
}

/**
 * @func __PlayServicesLeaderboard_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesLeaderboard}
 * @ignore
 */
function __PlayServicesLeaderboard_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesLeaderboard();
    with (_inst)
    {
        // field: leaderboard_id, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.leaderboard_id = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.leaderboard_id = undefined;
        }

        // field: display_name, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.display_name = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.display_name = undefined;
        }

        // field: score_order, type: enum PlayServicesLeaderboardScoreOrder
        self.score_order = buffer_read(_buffer, buffer_s32);

        // field: variants, type: struct PlayServicesLeaderboardVariant[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.variants = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.variants[_i] = __PlayServicesLeaderboardVariant_decode(_buffer, buffer_tell(_buffer));
        }

    }

    return _inst;
}

/**
 * @func __PlayServicesSnapshotOpenInfo_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.PlayServicesSnapshotOpenInfo} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __PlayServicesSnapshotOpenInfo_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: is_conflict, type: Bool
        if (!is_bool(self.is_conflict)) show_error($"{_where} :: self.is_conflict expected bool", true);
        buffer_write(_buffer, buffer_bool, self.is_conflict);

        // field: snapshot_metadata, type: optional<struct PlayServicesSnapshotMetadata>
        if (is_undefined(self.snapshot_metadata))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (self.snapshot_metadata.__uid != 238308842) show_error($"{_where} :: self.snapshot_metadata expected PlayServicesSnapshotMetadata", true);
            __PlayServicesSnapshotMetadata_encode(self.snapshot_metadata, _buffer, buffer_tell(_buffer), _where);
        }

        // field: data, type: optional<String>
        if (is_undefined(self.data))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.data)) show_error($"{_where} :: self.data expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.data));
            buffer_write(_buffer, buffer_string, self.data);
        }

        // field: conflict_id, type: optional<String>
        if (is_undefined(self.conflict_id))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.conflict_id)) show_error($"{_where} :: self.conflict_id expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.conflict_id));
            buffer_write(_buffer, buffer_string, self.conflict_id);
        }

        // field: snapshot_metadata_local, type: optional<struct PlayServicesSnapshotMetadata>
        if (is_undefined(self.snapshot_metadata_local))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (self.snapshot_metadata_local.__uid != 238308842) show_error($"{_where} :: self.snapshot_metadata_local expected PlayServicesSnapshotMetadata", true);
            __PlayServicesSnapshotMetadata_encode(self.snapshot_metadata_local, _buffer, buffer_tell(_buffer), _where);
        }

        // field: data_local, type: optional<String>
        if (is_undefined(self.data_local))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.data_local)) show_error($"{_where} :: self.data_local expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.data_local));
            buffer_write(_buffer, buffer_string, self.data_local);
        }

        // field: snapshot_metadata_remote, type: optional<struct PlayServicesSnapshotMetadata>
        if (is_undefined(self.snapshot_metadata_remote))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (self.snapshot_metadata_remote.__uid != 238308842) show_error($"{_where} :: self.snapshot_metadata_remote expected PlayServicesSnapshotMetadata", true);
            __PlayServicesSnapshotMetadata_encode(self.snapshot_metadata_remote, _buffer, buffer_tell(_buffer), _where);
        }

        // field: data_remote, type: optional<String>
        if (is_undefined(self.data_remote))
        {
            buffer_write(_buffer, buffer_bool, false);
        }
        else
        {
            buffer_write(_buffer, buffer_bool, true);
            if (!is_string(self.data_remote)) show_error($"{_where} :: self.data_remote expected string", true);
            buffer_write(_buffer, buffer_u32, string_byte_length(self.data_remote));
            buffer_write(_buffer, buffer_string, self.data_remote);
        }

    }
}

/**
 * @func __PlayServicesSnapshotOpenInfo_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.PlayServicesSnapshotOpenInfo}
 * @ignore
 */
function __PlayServicesSnapshotOpenInfo_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new PlayServicesSnapshotOpenInfo();
    with (_inst)
    {
        // field: is_conflict, type: Bool
        self.is_conflict = buffer_read(_buffer, buffer_bool);

        // field: snapshot_metadata, type: optional<struct PlayServicesSnapshotMetadata>
        if (buffer_read(_buffer, buffer_bool))
        {
            self.snapshot_metadata = __PlayServicesSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));
        }
        else
        {
            self.snapshot_metadata = undefined;
        }

        // field: data, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.data = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.data = undefined;
        }

        // field: conflict_id, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.conflict_id = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.conflict_id = undefined;
        }

        // field: snapshot_metadata_local, type: optional<struct PlayServicesSnapshotMetadata>
        if (buffer_read(_buffer, buffer_bool))
        {
            self.snapshot_metadata_local = __PlayServicesSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));
        }
        else
        {
            self.snapshot_metadata_local = undefined;
        }

        // field: data_local, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.data_local = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.data_local = undefined;
        }

        // field: snapshot_metadata_remote, type: optional<struct PlayServicesSnapshotMetadata>
        if (buffer_read(_buffer, buffer_bool))
        {
            self.snapshot_metadata_remote = __PlayServicesSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));
        }
        else
        {
            self.snapshot_metadata_remote = undefined;
        }

        // field: data_remote, type: optional<String>
        if (buffer_read(_buffer, buffer_bool))
        {
            buffer_read(_buffer, buffer_u32);
            self.data_remote = buffer_read(_buffer, buffer_string);
        }
        else
        {
            self.data_remote = undefined;
        }

    }

    return _inst;
}

// #####################################################################
// # Functions
// #####################################################################

// Skipping function play_services_is_available (no wrapper is required)


/**
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_sign_in(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_sign_in(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_is_authenticated(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_is_authenticated(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _server_client_id
 * @param {Bool} _force_refresh_token
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_request_server_side_access(_server_client_id, _force_refresh_token, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_request_server_side_access(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_current(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_current(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_current_id(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_current_id(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_stats_load(_force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_stats_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _player_id
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_load(_player_id, _force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _player_id, type: String
    if (!is_string(_player_id)) show_error($"{_GMFUNCTION_} :: _player_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_player_id));
    buffer_write(__args_buffer, buffer_string, _player_id);

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Bool} _force_reload
 * @param {Real} _max_results
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_friends_load(_force_reload, _max_results, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _max_results, type: Float64
    if (!is_numeric(_max_results)) show_error($"{_GMFUNCTION_} :: _max_results expected number", true);
    buffer_write(__args_buffer, buffer_f64, _max_results);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_friends_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Real} _page_size
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_friends_load_more(_page_size, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _page_size, type: Float64
    if (!is_numeric(_page_size)) show_error($"{_GMFUNCTION_} :: _page_size expected number", true);
    buffer_write(__args_buffer, buffer_f64, _page_size);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_friends_load_more(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Bool} _force_reload
 * @param {Real} _max_results
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_friends_load_with_consent(_force_reload, _max_results, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _max_results, type: Float64
    if (!is_numeric(_max_results)) show_error($"{_GMFUNCTION_} :: _max_results expected number", true);
    buffer_write(__args_buffer, buffer_f64, _max_results);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_friends_load_with_consent(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _player_id
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_profile_show(_player_id)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_profile_show(_player_id, buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_player_search_show(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_player_search_show(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_show()
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_show(buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_increment(_achievement_id, _steps, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_increment(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_reveal(_achievement_id, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_reveal(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_set_steps(_achievement_id, _steps, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_set_steps(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_unlock(_achievement_id, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _achievement_id, type: String
    if (!is_string(_achievement_id)) show_error($"{_GMFUNCTION_} :: _achievement_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_achievement_id));
    buffer_write(__args_buffer, buffer_string, _achievement_id);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_unlock(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_achievements_get_status(_force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_achievements_get_status(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_show_all()
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_show_all(buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _leaderboard_id
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_show(_leaderboard_id)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_show(_leaderboard_id, buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _leaderboard_id
 * @param {Real} _score
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_submit_score(_leaderboard_id, _score, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _score, type: Float64
    if (!is_numeric(_score)) show_error($"{_GMFUNCTION_} :: _score expected number", true);
    buffer_write(__args_buffer, buffer_f64, _score);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_submit_score(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _leaderboard_id
 * @param {Real} _score
 * @param {String} _score_tag
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_submit_score_with_tag(_leaderboard_id, _score, _score_tag, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_submit_score_with_tag(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _leaderboard_id
 * @param {Enum.PlayServicesLeaderboardTimeSpan} _span
 * @param {Enum.PlayServicesLeaderboardCollection} _leaderboard_collection
 * @param {Real} _max_results
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_load_player_centered_scores(_leaderboard_id, _span, _leaderboard_collection, _max_results, _force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _span, type: enum PlayServicesLeaderboardTimeSpan

    if (!is_numeric(_span)) show_error($"{_GMFUNCTION_} :: _span expected number", true);
    buffer_write(__args_buffer, buffer_s32, _span);

    // param: _leaderboard_collection, type: enum PlayServicesLeaderboardCollection

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_load_player_centered_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _leaderboard_id
 * @param {Enum.PlayServicesLeaderboardTimeSpan} _span
 * @param {Enum.PlayServicesLeaderboardCollection} _leaderboard_collection
 * @param {Real} _max_results
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_leaderboard_load_top_scores(_leaderboard_id, _span, _leaderboard_collection, _max_results, _force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _leaderboard_id, type: String
    if (!is_string(_leaderboard_id)) show_error($"{_GMFUNCTION_} :: _leaderboard_id expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_leaderboard_id));
    buffer_write(__args_buffer, buffer_string, _leaderboard_id);

    // param: _span, type: enum PlayServicesLeaderboardTimeSpan

    if (!is_numeric(_span)) show_error($"{_GMFUNCTION_} :: _span expected number", true);
    buffer_write(__args_buffer, buffer_s32, _span);

    // param: _leaderboard_collection, type: enum PlayServicesLeaderboardCollection

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_leaderboard_load_top_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _uri
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_uri_to_path(_uri, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _uri, type: String
    if (!is_string(_uri)) show_error($"{_GMFUNCTION_} :: _uri expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_uri));
    buffer_write(__args_buffer, buffer_string, _uri);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_uri_to_path(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _title
 * @param {Bool} _button_add
 * @param {Bool} _button_delete
 * @param {Real} _max_results
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_show_saved_games_ui(_title, _button_add, _button_delete, _max_results, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_show_saved_games_ui(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Struct.PlayServicesSavedGameCommitOptions} _options
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_commit_and_close(_options, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct PlayServicesSavedGameCommitOptions
    if (_options.__uid != 1866852405) show_error($"{_GMFUNCTION_} :: _options expected PlayServicesSavedGameCommitOptions", true);
    __PlayServicesSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_commit_and_close(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Struct.PlayServicesSavedGameCommitOptions} _options
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_commit_new(_options, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct PlayServicesSavedGameCommitOptions
    if (_options.__uid != 1866852405) show_error($"{_GMFUNCTION_} :: _options expected PlayServicesSavedGameCommitOptions", true);
    __PlayServicesSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_commit_new(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_load(_force_reload, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _force_reload, type: Bool
    if (!is_bool(_force_reload)) show_error($"{_GMFUNCTION_} :: _force_reload expected bool", true);
    buffer_write(__args_buffer, buffer_bool, _force_reload);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_open(_name, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_open(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _name
 * @param {Enum.PlayServicesSavedGamesConflictPolicy} _conflict_policy
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_open_conflict(_name, _conflict_policy, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _conflict_policy, type: enum PlayServicesSavedGamesConflictPolicy

    if (!is_numeric(_conflict_policy)) show_error($"{_GMFUNCTION_} :: _conflict_policy expected number", true);
    buffer_write(__args_buffer, buffer_s32, _conflict_policy);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_open_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_delete(_name, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _name, type: String
    if (!is_string(_name)) show_error($"{_GMFUNCTION_} :: _name expected string", true);
    buffer_write(__args_buffer, buffer_u32, string_byte_length(_name));
    buffer_write(__args_buffer, buffer_string, _name);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_delete(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/**
 * @param {String} _conflict_id
 * @param {Bool} _use_local
 * @param {Function} _callback
 * @returns {Enum.PlayServicesError}
 */
function play_services_saved_games_resolve_conflict(_conflict_id, _use_local, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __ret_buffer = __ext_core_get_ret_buffer();

    var __return_value__ = __play_services_saved_games_resolve_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer), buffer_get_address(__ret_buffer), buffer_get_size(__ret_buffer));

    var __result__ = undefined;
    __result__ = buffer_read(__ret_buffer, buffer_s32);
    return __result__;
}

/// @ignore
function __GMGooglePlayServices_get_decoders()
{
    static __decoders__ = [
        __PlayServicesSavedGameCommitOptions_decode,
        __PlayServicesResult_decode,
        __PlayServicesPlayerInfo_decode,
        __PlayServicesPlayerStatsInfo_decode,
        __PlayServicesAchievement_decode,
        __PlayServicesScoreSubmission_decode,
        __PlayServicesLeaderboardVariant_decode,
        __PlayServicesSnapshotMetadata_decode,
        __PlayServicesLeaderboardScore_decode,
        __PlayServicesScoreReportInfo_decode,
        __PlayServicesLeaderboard_decode,
        __PlayServicesSnapshotOpenInfo_decode
    ];
    return __decoders__;
}
/// @ignore
function __GMGooglePlayServices_get_dispatcher()
{
    static __dispatcher__ = new __GMNativeFunctionDispatcher(__GMGooglePlayServices_invocation_handler, __GMGooglePlayServices_get_decoders());
    return __dispatcher__;
}
/// @ignore
function __GMGooglePlayServices_is_available()
{
    static __available__ = extension_exists("GMGooglePlayServices");
    return __available__;
}
