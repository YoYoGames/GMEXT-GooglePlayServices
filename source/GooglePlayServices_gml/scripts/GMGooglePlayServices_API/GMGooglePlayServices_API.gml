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

/**
 * @returns {Struct.GPGSAuthResult}
 */
function GPGSAuthResult() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1752374075;

    self.success = undefined;
    self.is_authenticated = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSPlayerInfo}
 */
function GPGSPlayerInfo() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1960719841;

    self.player_id = undefined;
    self.display_name = undefined;
    self.title = undefined;
    self.icon_image_uri = undefined;
    self.hi_res_image_uri = undefined;

}

/**
 * @returns {Struct.GPGSPlayerStats}
 */
function GPGSPlayerStats() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 2590306968;

    self.success = undefined;
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
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSAchievement}
 */
function GPGSAchievement() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 649865523;

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
 * @returns {Struct.GPGSScoreResult}
 */
function GPGSScoreResult() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 4103454823;

    self.raw_score = undefined;
    self.formatted_score = undefined;
    self.score_tag = undefined;
    self.new_best = undefined;

}

/**
 * @returns {Struct.GPGSLeaderboardVariant}
 */
function GPGSLeaderboardVariant() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 4077043484;

    self.collection = undefined;
    self.time_span = undefined;
    self.has_player_info = undefined;

}

/**
 * @returns {Struct.GPGSSnapshotMetadata}
 */
function GPGSSnapshotMetadata() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 2582870709;

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
 * @returns {Struct.GPGSTaskResult}
 */
function GPGSTaskResult() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1050131728;

    self.success = undefined;
    self.value = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSPlayer}
 */
function GPGSPlayer() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1590072345;

    self.success = undefined;
    self.player = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSPlayerList}
 */
function GPGSPlayerList() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1927280005;

    self.success = undefined;
    self.players = undefined;
    self.has_more = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSLeaderboardScore}
 */
function GPGSLeaderboardScore() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 627234391;

    self.display_rank = undefined;
    self.display_score = undefined;
    self.raw_score = undefined;
    self.score_tag = undefined;
    self.timestamp_millis = undefined;
    self.score_holder = undefined;

}

/**
 * @returns {Struct.GPGSAchievementList}
 */
function GPGSAchievementList() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 600409755;

    self.success = undefined;
    self.achievements = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSScoreReport}
 */
function GPGSScoreReport() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3963189564;

    self.success = undefined;
    self.leaderboard_id = undefined;
    self.score = undefined;
    self.score_tag = undefined;
    self.daily = undefined;
    self.weekly = undefined;
    self.all_time = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSLeaderboard}
 */
function GPGSLeaderboard() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3807895483;

    self.leaderboard_id = undefined;
    self.display_name = undefined;
    self.score_order = undefined;
    self.variants = undefined;

}

/**
 * @returns {Struct.GPGSSnapshotMetadataList}
 */
function GPGSSnapshotMetadataList() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 270634129;

    self.success = undefined;
    self.snapshots = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSSnapshotOpenResult}
 */
function GPGSSnapshotOpenResult() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 639618593;

    self.is_conflict = undefined;
    self.snapshot_metadata = undefined;
    self.data = undefined;
    self.conflict_id = undefined;
    self.snapshot_metadata_local = undefined;
    self.data_local = undefined;
    self.snapshot_metadata_remote = undefined;
    self.data_remote = undefined;

}

/**
 * @returns {Struct.GPGSSavedGamesUIEvent}
 */
function GPGSSavedGamesUIEvent() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 1789697340;

    self.result = undefined;
    self.snapshot_metadata = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSLeaderboardScores}
 */
function GPGSLeaderboardScores() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 4271315628;

    self.success = undefined;
    self.leaderboard = undefined;
    self.scores = undefined;
    self.error = undefined;

}

/**
 * @returns {Struct.GPGSSnapshot}
 */
function GPGSSnapshot() constructor
{
    /**
     * Internally generated hash for quick validation
     * @ignore
     */
    static __uid = 3110322928;

    self.success = undefined;
    self.result = undefined;
    self.error = undefined;

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

/**
 * @func __GPGSAuthResult_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSAuthResult} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSAuthResult_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: is_authenticated, type: Bool
        if (!is_bool(self.is_authenticated)) show_error($"{_where} :: self.is_authenticated expected bool", true);
        buffer_write(_buffer, buffer_bool, self.is_authenticated);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSAuthResult_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSAuthResult}
 * @ignore
 */
function __GPGSAuthResult_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSAuthResult();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: is_authenticated, type: Bool
        self.is_authenticated = buffer_read(_buffer, buffer_bool);

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSPlayerInfo_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSPlayerInfo} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSPlayerInfo_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: player_id, type: String
        if (!is_string(self.player_id)) show_error($"{_where} :: self.player_id expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.player_id));
        buffer_write(_buffer, buffer_string, self.player_id);

        // field: display_name, type: String
        if (!is_string(self.display_name)) show_error($"{_where} :: self.display_name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.display_name));
        buffer_write(_buffer, buffer_string, self.display_name);

        // field: title, type: String
        if (!is_string(self.title)) show_error($"{_where} :: self.title expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.title));
        buffer_write(_buffer, buffer_string, self.title);

        // field: icon_image_uri, type: String
        if (!is_string(self.icon_image_uri)) show_error($"{_where} :: self.icon_image_uri expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.icon_image_uri));
        buffer_write(_buffer, buffer_string, self.icon_image_uri);

        // field: hi_res_image_uri, type: String
        if (!is_string(self.hi_res_image_uri)) show_error($"{_where} :: self.hi_res_image_uri expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.hi_res_image_uri));
        buffer_write(_buffer, buffer_string, self.hi_res_image_uri);

    }
}

/**
 * @func __GPGSPlayerInfo_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSPlayerInfo}
 * @ignore
 */
function __GPGSPlayerInfo_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSPlayerInfo();
    with (_inst)
    {
        // field: player_id, type: String
        buffer_read(_buffer, buffer_u32);
        self.player_id = buffer_read(_buffer, buffer_string);

        // field: display_name, type: String
        buffer_read(_buffer, buffer_u32);
        self.display_name = buffer_read(_buffer, buffer_string);

        // field: title, type: String
        buffer_read(_buffer, buffer_u32);
        self.title = buffer_read(_buffer, buffer_string);

        // field: icon_image_uri, type: String
        buffer_read(_buffer, buffer_u32);
        self.icon_image_uri = buffer_read(_buffer, buffer_string);

        // field: hi_res_image_uri, type: String
        buffer_read(_buffer, buffer_u32);
        self.hi_res_image_uri = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSPlayerStats_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSPlayerStats} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSPlayerStats_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

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

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSPlayerStats_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSPlayerStats}
 * @ignore
 */
function __GPGSPlayerStats_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSPlayerStats();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

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

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSAchievement_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSAchievement} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSAchievement_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: achievement_id, type: String
        if (!is_string(self.achievement_id)) show_error($"{_where} :: self.achievement_id expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.achievement_id));
        buffer_write(_buffer, buffer_string, self.achievement_id);

        // field: name, type: String
        if (!is_string(self.name)) show_error($"{_where} :: self.name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.name));
        buffer_write(_buffer, buffer_string, self.name);

        // field: description, type: String
        if (!is_string(self.description)) show_error($"{_where} :: self.description expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.description));
        buffer_write(_buffer, buffer_string, self.description);

        // field: state, type: Float64
        if (!is_numeric(self.state)) show_error($"{_where} :: self.state expected number", true);
        buffer_write(_buffer, buffer_f64, self.state);

        // field: type, type: Float64
        if (!is_numeric(self.type)) show_error($"{_where} :: self.type expected number", true);
        buffer_write(_buffer, buffer_f64, self.type);

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

        // field: revealed_image_uri, type: String
        if (!is_string(self.revealed_image_uri)) show_error($"{_where} :: self.revealed_image_uri expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.revealed_image_uri));
        buffer_write(_buffer, buffer_string, self.revealed_image_uri);

        // field: unlocked_image_uri, type: String
        if (!is_string(self.unlocked_image_uri)) show_error($"{_where} :: self.unlocked_image_uri expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.unlocked_image_uri));
        buffer_write(_buffer, buffer_string, self.unlocked_image_uri);

    }
}

/**
 * @func __GPGSAchievement_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSAchievement}
 * @ignore
 */
function __GPGSAchievement_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSAchievement();
    with (_inst)
    {
        // field: achievement_id, type: String
        buffer_read(_buffer, buffer_u32);
        self.achievement_id = buffer_read(_buffer, buffer_string);

        // field: name, type: String
        buffer_read(_buffer, buffer_u32);
        self.name = buffer_read(_buffer, buffer_string);

        // field: description, type: String
        buffer_read(_buffer, buffer_u32);
        self.description = buffer_read(_buffer, buffer_string);

        // field: state, type: Float64
        self.state = buffer_read(_buffer, buffer_f64);

        // field: type, type: Float64
        self.type = buffer_read(_buffer, buffer_f64);

        // field: current_steps, type: Float64
        self.current_steps = buffer_read(_buffer, buffer_f64);

        // field: total_steps, type: Float64
        self.total_steps = buffer_read(_buffer, buffer_f64);

        // field: last_updated_timestamp, type: Float64
        self.last_updated_timestamp = buffer_read(_buffer, buffer_f64);

        // field: xp_value, type: Float64
        self.xp_value = buffer_read(_buffer, buffer_f64);

        // field: revealed_image_uri, type: String
        buffer_read(_buffer, buffer_u32);
        self.revealed_image_uri = buffer_read(_buffer, buffer_string);

        // field: unlocked_image_uri, type: String
        buffer_read(_buffer, buffer_u32);
        self.unlocked_image_uri = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSScoreResult_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSScoreResult} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSScoreResult_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: raw_score, type: Float64
        if (!is_numeric(self.raw_score)) show_error($"{_where} :: self.raw_score expected number", true);
        buffer_write(_buffer, buffer_f64, self.raw_score);

        // field: formatted_score, type: String
        if (!is_string(self.formatted_score)) show_error($"{_where} :: self.formatted_score expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.formatted_score));
        buffer_write(_buffer, buffer_string, self.formatted_score);

        // field: score_tag, type: String
        if (!is_string(self.score_tag)) show_error($"{_where} :: self.score_tag expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.score_tag));
        buffer_write(_buffer, buffer_string, self.score_tag);

        // field: new_best, type: Bool
        if (!is_bool(self.new_best)) show_error($"{_where} :: self.new_best expected bool", true);
        buffer_write(_buffer, buffer_bool, self.new_best);

    }
}

/**
 * @func __GPGSScoreResult_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSScoreResult}
 * @ignore
 */
function __GPGSScoreResult_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSScoreResult();
    with (_inst)
    {
        // field: raw_score, type: Float64
        self.raw_score = buffer_read(_buffer, buffer_f64);

        // field: formatted_score, type: String
        buffer_read(_buffer, buffer_u32);
        self.formatted_score = buffer_read(_buffer, buffer_string);

        // field: score_tag, type: String
        buffer_read(_buffer, buffer_u32);
        self.score_tag = buffer_read(_buffer, buffer_string);

        // field: new_best, type: Bool
        self.new_best = buffer_read(_buffer, buffer_bool);

    }

    return _inst;
}

/**
 * @func __GPGSLeaderboardVariant_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSLeaderboardVariant} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSLeaderboardVariant_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: collection, type: Float64
        if (!is_numeric(self.collection)) show_error($"{_where} :: self.collection expected number", true);
        buffer_write(_buffer, buffer_f64, self.collection);

        // field: time_span, type: Float64
        if (!is_numeric(self.time_span)) show_error($"{_where} :: self.time_span expected number", true);
        buffer_write(_buffer, buffer_f64, self.time_span);

        // field: has_player_info, type: Bool
        if (!is_bool(self.has_player_info)) show_error($"{_where} :: self.has_player_info expected bool", true);
        buffer_write(_buffer, buffer_bool, self.has_player_info);

    }
}

/**
 * @func __GPGSLeaderboardVariant_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSLeaderboardVariant}
 * @ignore
 */
function __GPGSLeaderboardVariant_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSLeaderboardVariant();
    with (_inst)
    {
        // field: collection, type: Float64
        self.collection = buffer_read(_buffer, buffer_f64);

        // field: time_span, type: Float64
        self.time_span = buffer_read(_buffer, buffer_f64);

        // field: has_player_info, type: Bool
        self.has_player_info = buffer_read(_buffer, buffer_bool);

    }

    return _inst;
}

/**
 * @func __GPGSSnapshotMetadata_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSnapshotMetadata} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSSnapshotMetadata_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: unique_name, type: String
        if (!is_string(self.unique_name)) show_error($"{_where} :: self.unique_name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.unique_name));
        buffer_write(_buffer, buffer_string, self.unique_name);

        // field: description, type: String
        if (!is_string(self.description)) show_error($"{_where} :: self.description expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.description));
        buffer_write(_buffer, buffer_string, self.description);

        // field: device_name, type: String
        if (!is_string(self.device_name)) show_error($"{_where} :: self.device_name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.device_name));
        buffer_write(_buffer, buffer_string, self.device_name);

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

        // field: cover_image_uri, type: String
        if (!is_string(self.cover_image_uri)) show_error($"{_where} :: self.cover_image_uri expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.cover_image_uri));
        buffer_write(_buffer, buffer_string, self.cover_image_uri);

    }
}

/**
 * @func __GPGSSnapshotMetadata_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSnapshotMetadata}
 * @ignore
 */
function __GPGSSnapshotMetadata_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSnapshotMetadata();
    with (_inst)
    {
        // field: unique_name, type: String
        buffer_read(_buffer, buffer_u32);
        self.unique_name = buffer_read(_buffer, buffer_string);

        // field: description, type: String
        buffer_read(_buffer, buffer_u32);
        self.description = buffer_read(_buffer, buffer_string);

        // field: device_name, type: String
        buffer_read(_buffer, buffer_u32);
        self.device_name = buffer_read(_buffer, buffer_string);

        // field: last_modified_timestamp, type: Float64
        self.last_modified_timestamp = buffer_read(_buffer, buffer_f64);

        // field: played_time, type: Float64
        self.played_time = buffer_read(_buffer, buffer_f64);

        // field: progress_value, type: Float64
        self.progress_value = buffer_read(_buffer, buffer_f64);

        // field: has_change_pending, type: Bool
        self.has_change_pending = buffer_read(_buffer, buffer_bool);

        // field: cover_image_uri, type: String
        buffer_read(_buffer, buffer_u32);
        self.cover_image_uri = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSTaskResult_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSTaskResult} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSTaskResult_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: value, type: String
        if (!is_string(self.value)) show_error($"{_where} :: self.value expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.value));
        buffer_write(_buffer, buffer_string, self.value);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSTaskResult_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSTaskResult}
 * @ignore
 */
function __GPGSTaskResult_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSTaskResult();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: value, type: String
        buffer_read(_buffer, buffer_u32);
        self.value = buffer_read(_buffer, buffer_string);

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSPlayer_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSPlayer} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSPlayer_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: player, type: struct GPGSPlayerInfo
        if (self.player.__uid != 1960719841) show_error($"{_where} :: self.player expected GPGSPlayerInfo", true);
        __GPGSPlayerInfo_encode(self.player, _buffer, buffer_tell(_buffer), _where);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSPlayer_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSPlayer}
 * @ignore
 */
function __GPGSPlayer_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSPlayer();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: player, type: struct GPGSPlayerInfo
        self.player = __GPGSPlayerInfo_decode(_buffer, buffer_tell(_buffer));

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSPlayerList_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSPlayerList} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSPlayerList_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: players, type: struct GPGSPlayerInfo[]
        if (!is_array(self.players)) show_error($"{_where} :: self.players expected array", true);
        var __length__ = array_length(self.players);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.players[_i].__uid != 1960719841) show_error($"{_where} :: self.players[_i] expected GPGSPlayerInfo", true);
            __GPGSPlayerInfo_encode(self.players[_i], _buffer, buffer_tell(_buffer), _where);
        }

        // field: has_more, type: Bool
        if (!is_bool(self.has_more)) show_error($"{_where} :: self.has_more expected bool", true);
        buffer_write(_buffer, buffer_bool, self.has_more);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSPlayerList_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSPlayerList}
 * @ignore
 */
function __GPGSPlayerList_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSPlayerList();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: players, type: struct GPGSPlayerInfo[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.players = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.players[_i] = __GPGSPlayerInfo_decode(_buffer, buffer_tell(_buffer));
        }

        // field: has_more, type: Bool
        self.has_more = buffer_read(_buffer, buffer_bool);

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSLeaderboardScore_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSLeaderboardScore} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSLeaderboardScore_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: display_rank, type: String
        if (!is_string(self.display_rank)) show_error($"{_where} :: self.display_rank expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.display_rank));
        buffer_write(_buffer, buffer_string, self.display_rank);

        // field: display_score, type: String
        if (!is_string(self.display_score)) show_error($"{_where} :: self.display_score expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.display_score));
        buffer_write(_buffer, buffer_string, self.display_score);

        // field: raw_score, type: Float64
        if (!is_numeric(self.raw_score)) show_error($"{_where} :: self.raw_score expected number", true);
        buffer_write(_buffer, buffer_f64, self.raw_score);

        // field: score_tag, type: String
        if (!is_string(self.score_tag)) show_error($"{_where} :: self.score_tag expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.score_tag));
        buffer_write(_buffer, buffer_string, self.score_tag);

        // field: timestamp_millis, type: Float64
        if (!is_numeric(self.timestamp_millis)) show_error($"{_where} :: self.timestamp_millis expected number", true);
        buffer_write(_buffer, buffer_f64, self.timestamp_millis);

        // field: score_holder, type: struct GPGSPlayerInfo
        if (self.score_holder.__uid != 1960719841) show_error($"{_where} :: self.score_holder expected GPGSPlayerInfo", true);
        __GPGSPlayerInfo_encode(self.score_holder, _buffer, buffer_tell(_buffer), _where);

    }
}

/**
 * @func __GPGSLeaderboardScore_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSLeaderboardScore}
 * @ignore
 */
function __GPGSLeaderboardScore_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSLeaderboardScore();
    with (_inst)
    {
        // field: display_rank, type: String
        buffer_read(_buffer, buffer_u32);
        self.display_rank = buffer_read(_buffer, buffer_string);

        // field: display_score, type: String
        buffer_read(_buffer, buffer_u32);
        self.display_score = buffer_read(_buffer, buffer_string);

        // field: raw_score, type: Float64
        self.raw_score = buffer_read(_buffer, buffer_f64);

        // field: score_tag, type: String
        buffer_read(_buffer, buffer_u32);
        self.score_tag = buffer_read(_buffer, buffer_string);

        // field: timestamp_millis, type: Float64
        self.timestamp_millis = buffer_read(_buffer, buffer_f64);

        // field: score_holder, type: struct GPGSPlayerInfo
        self.score_holder = __GPGSPlayerInfo_decode(_buffer, buffer_tell(_buffer));

    }

    return _inst;
}

/**
 * @func __GPGSAchievementList_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSAchievementList} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSAchievementList_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: achievements, type: struct GPGSAchievement[]
        if (!is_array(self.achievements)) show_error($"{_where} :: self.achievements expected array", true);
        var __length__ = array_length(self.achievements);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.achievements[_i].__uid != 649865523) show_error($"{_where} :: self.achievements[_i] expected GPGSAchievement", true);
            __GPGSAchievement_encode(self.achievements[_i], _buffer, buffer_tell(_buffer), _where);
        }

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSAchievementList_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSAchievementList}
 * @ignore
 */
function __GPGSAchievementList_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSAchievementList();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: achievements, type: struct GPGSAchievement[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.achievements = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.achievements[_i] = __GPGSAchievement_decode(_buffer, buffer_tell(_buffer));
        }

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSScoreReport_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSScoreReport} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSScoreReport_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: leaderboard_id, type: String
        if (!is_string(self.leaderboard_id)) show_error($"{_where} :: self.leaderboard_id expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.leaderboard_id));
        buffer_write(_buffer, buffer_string, self.leaderboard_id);

        // field: score, type: Float64
        if (!is_numeric(self.score)) show_error($"{_where} :: self.score expected number", true);
        buffer_write(_buffer, buffer_f64, self.score);

        // field: score_tag, type: String
        if (!is_string(self.score_tag)) show_error($"{_where} :: self.score_tag expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.score_tag));
        buffer_write(_buffer, buffer_string, self.score_tag);

        // field: daily, type: struct GPGSScoreResult
        if (self.daily.__uid != 4103454823) show_error($"{_where} :: self.daily expected GPGSScoreResult", true);
        __GPGSScoreResult_encode(self.daily, _buffer, buffer_tell(_buffer), _where);

        // field: weekly, type: struct GPGSScoreResult
        if (self.weekly.__uid != 4103454823) show_error($"{_where} :: self.weekly expected GPGSScoreResult", true);
        __GPGSScoreResult_encode(self.weekly, _buffer, buffer_tell(_buffer), _where);

        // field: all_time, type: struct GPGSScoreResult
        if (self.all_time.__uid != 4103454823) show_error($"{_where} :: self.all_time expected GPGSScoreResult", true);
        __GPGSScoreResult_encode(self.all_time, _buffer, buffer_tell(_buffer), _where);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSScoreReport_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSScoreReport}
 * @ignore
 */
function __GPGSScoreReport_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSScoreReport();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: leaderboard_id, type: String
        buffer_read(_buffer, buffer_u32);
        self.leaderboard_id = buffer_read(_buffer, buffer_string);

        // field: score, type: Float64
        self.score = buffer_read(_buffer, buffer_f64);

        // field: score_tag, type: String
        buffer_read(_buffer, buffer_u32);
        self.score_tag = buffer_read(_buffer, buffer_string);

        // field: daily, type: struct GPGSScoreResult
        self.daily = __GPGSScoreResult_decode(_buffer, buffer_tell(_buffer));

        // field: weekly, type: struct GPGSScoreResult
        self.weekly = __GPGSScoreResult_decode(_buffer, buffer_tell(_buffer));

        // field: all_time, type: struct GPGSScoreResult
        self.all_time = __GPGSScoreResult_decode(_buffer, buffer_tell(_buffer));

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSLeaderboard_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSLeaderboard} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSLeaderboard_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: leaderboard_id, type: String
        if (!is_string(self.leaderboard_id)) show_error($"{_where} :: self.leaderboard_id expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.leaderboard_id));
        buffer_write(_buffer, buffer_string, self.leaderboard_id);

        // field: display_name, type: String
        if (!is_string(self.display_name)) show_error($"{_where} :: self.display_name expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.display_name));
        buffer_write(_buffer, buffer_string, self.display_name);

        // field: score_order, type: Float64
        if (!is_numeric(self.score_order)) show_error($"{_where} :: self.score_order expected number", true);
        buffer_write(_buffer, buffer_f64, self.score_order);

        // field: variants, type: struct GPGSLeaderboardVariant[]
        if (!is_array(self.variants)) show_error($"{_where} :: self.variants expected array", true);
        var __length__ = array_length(self.variants);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.variants[_i].__uid != 4077043484) show_error($"{_where} :: self.variants[_i] expected GPGSLeaderboardVariant", true);
            __GPGSLeaderboardVariant_encode(self.variants[_i], _buffer, buffer_tell(_buffer), _where);
        }

    }
}

/**
 * @func __GPGSLeaderboard_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSLeaderboard}
 * @ignore
 */
function __GPGSLeaderboard_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSLeaderboard();
    with (_inst)
    {
        // field: leaderboard_id, type: String
        buffer_read(_buffer, buffer_u32);
        self.leaderboard_id = buffer_read(_buffer, buffer_string);

        // field: display_name, type: String
        buffer_read(_buffer, buffer_u32);
        self.display_name = buffer_read(_buffer, buffer_string);

        // field: score_order, type: Float64
        self.score_order = buffer_read(_buffer, buffer_f64);

        // field: variants, type: struct GPGSLeaderboardVariant[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.variants = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.variants[_i] = __GPGSLeaderboardVariant_decode(_buffer, buffer_tell(_buffer));
        }

    }

    return _inst;
}

/**
 * @func __GPGSSnapshotMetadataList_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSnapshotMetadataList} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSSnapshotMetadataList_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: snapshots, type: struct GPGSSnapshotMetadata[]
        if (!is_array(self.snapshots)) show_error($"{_where} :: self.snapshots expected array", true);
        var __length__ = array_length(self.snapshots);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.snapshots[_i].__uid != 2582870709) show_error($"{_where} :: self.snapshots[_i] expected GPGSSnapshotMetadata", true);
            __GPGSSnapshotMetadata_encode(self.snapshots[_i], _buffer, buffer_tell(_buffer), _where);
        }

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSSnapshotMetadataList_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSnapshotMetadataList}
 * @ignore
 */
function __GPGSSnapshotMetadataList_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSnapshotMetadataList();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: snapshots, type: struct GPGSSnapshotMetadata[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.snapshots = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.snapshots[_i] = __GPGSSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));
        }

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSSnapshotOpenResult_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSnapshotOpenResult} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSSnapshotOpenResult_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: is_conflict, type: Bool
        if (!is_bool(self.is_conflict)) show_error($"{_where} :: self.is_conflict expected bool", true);
        buffer_write(_buffer, buffer_bool, self.is_conflict);

        // field: snapshot_metadata, type: struct GPGSSnapshotMetadata
        if (self.snapshot_metadata.__uid != 2582870709) show_error($"{_where} :: self.snapshot_metadata expected GPGSSnapshotMetadata", true);
        __GPGSSnapshotMetadata_encode(self.snapshot_metadata, _buffer, buffer_tell(_buffer), _where);

        // field: data, type: String
        if (!is_string(self.data)) show_error($"{_where} :: self.data expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.data));
        buffer_write(_buffer, buffer_string, self.data);

        // field: conflict_id, type: String
        if (!is_string(self.conflict_id)) show_error($"{_where} :: self.conflict_id expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.conflict_id));
        buffer_write(_buffer, buffer_string, self.conflict_id);

        // field: snapshot_metadata_local, type: struct GPGSSnapshotMetadata
        if (self.snapshot_metadata_local.__uid != 2582870709) show_error($"{_where} :: self.snapshot_metadata_local expected GPGSSnapshotMetadata", true);
        __GPGSSnapshotMetadata_encode(self.snapshot_metadata_local, _buffer, buffer_tell(_buffer), _where);

        // field: data_local, type: String
        if (!is_string(self.data_local)) show_error($"{_where} :: self.data_local expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.data_local));
        buffer_write(_buffer, buffer_string, self.data_local);

        // field: snapshot_metadata_remote, type: struct GPGSSnapshotMetadata
        if (self.snapshot_metadata_remote.__uid != 2582870709) show_error($"{_where} :: self.snapshot_metadata_remote expected GPGSSnapshotMetadata", true);
        __GPGSSnapshotMetadata_encode(self.snapshot_metadata_remote, _buffer, buffer_tell(_buffer), _where);

        // field: data_remote, type: String
        if (!is_string(self.data_remote)) show_error($"{_where} :: self.data_remote expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.data_remote));
        buffer_write(_buffer, buffer_string, self.data_remote);

    }
}

/**
 * @func __GPGSSnapshotOpenResult_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSnapshotOpenResult}
 * @ignore
 */
function __GPGSSnapshotOpenResult_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSnapshotOpenResult();
    with (_inst)
    {
        // field: is_conflict, type: Bool
        self.is_conflict = buffer_read(_buffer, buffer_bool);

        // field: snapshot_metadata, type: struct GPGSSnapshotMetadata
        self.snapshot_metadata = __GPGSSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));

        // field: data, type: String
        buffer_read(_buffer, buffer_u32);
        self.data = buffer_read(_buffer, buffer_string);

        // field: conflict_id, type: String
        buffer_read(_buffer, buffer_u32);
        self.conflict_id = buffer_read(_buffer, buffer_string);

        // field: snapshot_metadata_local, type: struct GPGSSnapshotMetadata
        self.snapshot_metadata_local = __GPGSSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));

        // field: data_local, type: String
        buffer_read(_buffer, buffer_u32);
        self.data_local = buffer_read(_buffer, buffer_string);

        // field: snapshot_metadata_remote, type: struct GPGSSnapshotMetadata
        self.snapshot_metadata_remote = __GPGSSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));

        // field: data_remote, type: String
        buffer_read(_buffer, buffer_u32);
        self.data_remote = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSSavedGamesUIEvent_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSavedGamesUIEvent} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSSavedGamesUIEvent_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: result, type: Float64
        if (!is_numeric(self.result)) show_error($"{_where} :: self.result expected number", true);
        buffer_write(_buffer, buffer_f64, self.result);

        // field: snapshot_metadata, type: struct GPGSSnapshotMetadata
        if (self.snapshot_metadata.__uid != 2582870709) show_error($"{_where} :: self.snapshot_metadata expected GPGSSnapshotMetadata", true);
        __GPGSSnapshotMetadata_encode(self.snapshot_metadata, _buffer, buffer_tell(_buffer), _where);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSSavedGamesUIEvent_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSavedGamesUIEvent}
 * @ignore
 */
function __GPGSSavedGamesUIEvent_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSavedGamesUIEvent();
    with (_inst)
    {
        // field: result, type: Float64
        self.result = buffer_read(_buffer, buffer_f64);

        // field: snapshot_metadata, type: struct GPGSSnapshotMetadata
        self.snapshot_metadata = __GPGSSnapshotMetadata_decode(_buffer, buffer_tell(_buffer));

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSLeaderboardScores_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSLeaderboardScores} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSLeaderboardScores_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: leaderboard, type: struct GPGSLeaderboard
        if (self.leaderboard.__uid != 3807895483) show_error($"{_where} :: self.leaderboard expected GPGSLeaderboard", true);
        __GPGSLeaderboard_encode(self.leaderboard, _buffer, buffer_tell(_buffer), _where);

        // field: scores, type: struct GPGSLeaderboardScore[]
        if (!is_array(self.scores)) show_error($"{_where} :: self.scores expected array", true);
        var __length__ = array_length(self.scores);
        buffer_write(_buffer, buffer_u32, __length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            if (self.scores[_i].__uid != 627234391) show_error($"{_where} :: self.scores[_i] expected GPGSLeaderboardScore", true);
            __GPGSLeaderboardScore_encode(self.scores[_i], _buffer, buffer_tell(_buffer), _where);
        }

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSLeaderboardScores_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSLeaderboardScores}
 * @ignore
 */
function __GPGSLeaderboardScores_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSLeaderboardScores();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: leaderboard, type: struct GPGSLeaderboard
        self.leaderboard = __GPGSLeaderboard_decode(_buffer, buffer_tell(_buffer));

        // field: scores, type: struct GPGSLeaderboardScore[]
        var __length__ = buffer_read(_buffer, buffer_u32);
        self.scores = array_create(__length__);
        for (var _i = 0; _i < __length__; ++_i)
        {
            self.scores[_i] = __GPGSLeaderboardScore_decode(_buffer, buffer_tell(_buffer));
        }

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

    }

    return _inst;
}

/**
 * @func __GPGSSnapshot_encode(_inst, _buffer, _offset, _where)
 * @param {Struct.GPGSSnapshot} _inst
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @param {String} _where
 * @ignore
 */
function __GPGSSnapshot_encode(_inst, _buffer, _offset, _where = _GMFUNCTION_)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);
    with (_inst)
    {
        // field: success, type: Bool
        if (!is_bool(self.success)) show_error($"{_where} :: self.success expected bool", true);
        buffer_write(_buffer, buffer_bool, self.success);

        // field: result, type: struct GPGSSnapshotOpenResult
        if (self.result.__uid != 639618593) show_error($"{_where} :: self.result expected GPGSSnapshotOpenResult", true);
        __GPGSSnapshotOpenResult_encode(self.result, _buffer, buffer_tell(_buffer), _where);

        // field: error, type: String
        if (!is_string(self.error)) show_error($"{_where} :: self.error expected string", true);
        buffer_write(_buffer, buffer_u32, string_byte_length(self.error));
        buffer_write(_buffer, buffer_string, self.error);

    }
}

/**
 * @func __GPGSSnapshot_decode(_buffer, _offset)
 * @param {Id.Buffer} _buffer
 * @param {Real} _offset
 * @returns {Struct.GPGSSnapshot}
 * @ignore
 */
function __GPGSSnapshot_decode(_buffer, _offset)
{
    buffer_seek(_buffer, buffer_seek_start, _offset);

    _inst = new GPGSSnapshot();
    with (_inst)
    {
        // field: success, type: Bool
        self.success = buffer_read(_buffer, buffer_bool);

        // field: result, type: struct GPGSSnapshotOpenResult
        self.result = __GPGSSnapshotOpenResult_decode(_buffer, buffer_tell(_buffer));

        // field: error, type: String
        buffer_read(_buffer, buffer_u32);
        self.error = buffer_read(_buffer, buffer_string);

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
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_sign_in(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Function} _callback
 */
function gpgs_is_authenticated(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_is_authenticated(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _server_client_id
 * @param {Bool} _force_refresh_token
 * @param {Function} _callback
 */
function gpgs_request_server_side_access(_server_client_id, _force_refresh_token, _callback)
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

    var __return_value__ = __gpgs_request_server_side_access(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Function} _callback
 */
function gpgs_player_current(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_player_current(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Function} _callback
 */
function gpgs_player_current_id(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_player_current_id(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_player_stats_load(_force_reload, _callback)
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

    var __return_value__ = __gpgs_player_stats_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _player_id
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_player_load(_player_id, _force_reload, _callback)
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

    var __return_value__ = __gpgs_player_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Bool} _force_reload
 * @param {Real} _max_results
 * @param {Function} _callback
 */
function gpgs_friends_load(_force_reload, _max_results, _callback)
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

    var __return_value__ = __gpgs_friends_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Function} _callback
 */
function gpgs_friends_load_more(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_friends_load_more(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Bool} _force_reload
 * @param {Real} _max_results
 * @param {Function} _callback
 */
function gpgs_friends_load_with_consent(_force_reload, _max_results, _callback)
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

    var __return_value__ = __gpgs_friends_load_with_consent(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

// Skipping function gpgs_player_profile_show (no wrapper is required)


/**
 * @param {Function} _callback
 */
function gpgs_player_search_show(_callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_player_search_show(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

// Skipping function gpgs_achievements_show (no wrapper is required)


/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 */
function gpgs_achievements_increment(_achievement_id, _steps, _callback)
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

    var __return_value__ = __gpgs_achievements_increment(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 */
function gpgs_achievements_reveal(_achievement_id, _callback)
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

    var __return_value__ = __gpgs_achievements_reveal(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _achievement_id
 * @param {Real} _steps
 * @param {Function} _callback
 */
function gpgs_achievements_set_steps(_achievement_id, _steps, _callback)
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

    var __return_value__ = __gpgs_achievements_set_steps(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _achievement_id
 * @param {Function} _callback
 */
function gpgs_achievements_unlock(_achievement_id, _callback)
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

    var __return_value__ = __gpgs_achievements_unlock(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_achievements_get_status(_force_reload, _callback)
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

    var __return_value__ = __gpgs_achievements_get_status(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
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

    var __return_value__ = __gpgs_leaderboard_submit_score(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
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
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_leaderboard_load_player_centered_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
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
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_leaderboard_load_top_scores(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _uri
 * @param {Function} _callback
 */
function gpgs_uri_to_path(_uri, _callback)
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

    var __return_value__ = __gpgs_uri_to_path(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
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

    var __return_value__ = __gpgs_saved_games_show_saved_games_ui(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Struct.GPGSSavedGameCommitOptions} _options
 * @param {Function} _callback
 */
function gpgs_saved_games_commit_and_close(_options, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct GPGSSavedGameCommitOptions
    if (_options.__uid != 2822368498) show_error($"{_GMFUNCTION_} :: _options expected GPGSSavedGameCommitOptions", true);
    __GPGSSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_saved_games_commit_and_close(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Struct.GPGSSavedGameCommitOptions} _options
 * @param {Function} _callback
 */
function gpgs_saved_games_commit_new(_options, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

    var __args_buffer = __ext_core_get_args_buffer();

    // param: _options, type: struct GPGSSavedGameCommitOptions
    if (_options.__uid != 2822368498) show_error($"{_GMFUNCTION_} :: _options expected GPGSSavedGameCommitOptions", true);
    __GPGSSavedGameCommitOptions_encode(_options, __args_buffer, buffer_tell(__args_buffer), _GMFUNCTION_);

    // param: _callback, type: Function
    if (!is_callable(_callback)) show_error($"{_GMFUNCTION_} :: _callback expected callable type", true);
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_saved_games_commit_new(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {Bool} _force_reload
 * @param {Function} _callback
 */
function gpgs_saved_games_load(_force_reload, _callback)
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

    var __return_value__ = __gpgs_saved_games_load(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 */
function gpgs_saved_games_open(_name, _callback)
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

    var __return_value__ = __gpgs_saved_games_open(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _name
 * @param {Enum.GPGSSavedGamesConflictPolicy} _conflict_policy
 * @param {Function} _callback
 */
function gpgs_saved_games_open_conflict(_name, _conflict_policy, _callback)
{
    var __available__ = __GMGooglePlayServices_is_available();
    if (!__available__) return;

    var __dispatcher__ = __GMGooglePlayServices_get_dispatcher();

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
    var _callback_handle = __ext_core_function_register(_callback, __dispatcher__);
    buffer_write(__args_buffer, buffer_u64, _callback_handle);

    var __return_value__ = __gpgs_saved_games_open_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _name
 * @param {Function} _callback
 */
function gpgs_saved_games_delete(_name, _callback)
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

    var __return_value__ = __gpgs_saved_games_delete(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/**
 * @param {String} _conflict_id
 * @param {Bool} _use_local
 * @param {Function} _callback
 */
function gpgs_saved_games_resolve_conflict(_conflict_id, _use_local, _callback)
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

    var __return_value__ = __gpgs_saved_games_resolve_conflict(buffer_get_address(__args_buffer), buffer_tell(__args_buffer));

    return __return_value__;
}

/// @ignore
function __GMGooglePlayServices_get_decoders()
{
    static __decoders__ = [
        __GPGSSavedGameCommitOptions_decode,
        __GPGSAuthResult_decode,
        __GPGSPlayerInfo_decode,
        __GPGSPlayerStats_decode,
        __GPGSAchievement_decode,
        __GPGSScoreResult_decode,
        __GPGSLeaderboardVariant_decode,
        __GPGSSnapshotMetadata_decode,
        __GPGSTaskResult_decode,
        __GPGSPlayer_decode,
        __GPGSPlayerList_decode,
        __GPGSLeaderboardScore_decode,
        __GPGSAchievementList_decode,
        __GPGSScoreReport_decode,
        __GPGSLeaderboard_decode,
        __GPGSSnapshotMetadataList_decode,
        __GPGSSnapshotOpenResult_decode,
        __GPGSSavedGamesUIEvent_decode,
        __GPGSLeaderboardScores_decode,
        __GPGSSnapshot_decode
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
