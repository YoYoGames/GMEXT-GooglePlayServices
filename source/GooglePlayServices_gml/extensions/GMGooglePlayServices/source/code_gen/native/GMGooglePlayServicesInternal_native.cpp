// ##### extgen :: Auto-generated file do not edit!! #####

#include "GMGooglePlayServicesInternal_native.h"
#include "GMGooglePlayServicesInternal_exports.h"

using namespace gm_structs;
using namespace gm::wire::codec;

static gm::runtime::DispatchQueue __dispatch_queue;

// Internal function used for fetching dispatched function calls to GML
GMEXPORT double __EXT_NATIVE__GMGooglePlayServices_invocation_handler(char* __ret_buffer, double __ret_buffer_length)
{
    gm::byteio::BufferWriter __bw{ __ret_buffer, static_cast<size_t>(__ret_buffer_length) };
    return __dispatch_queue.fetch(__bw);
}

GMEXPORT double __EXT_NATIVE__gpgs_is_available()
{
    auto&& __result = gpgs_is_available();
    return static_cast<double>(__result);
}

GMEXPORT double __EXT_NATIVE__gpgs_sign_in(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_sign_in(callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_is_authenticated(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_is_authenticated(callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_request_server_side_access(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: server_client_id, type: String
    std::string_view server_client_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: force_refresh_token, type: Bool
    bool force_refresh_token = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_request_server_side_access(server_client_id, force_refresh_token, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_player_current(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_player_current(callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_player_current_id(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_player_current_id(callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_player_stats_load(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: force_reload, type: Bool
    bool force_reload = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_player_stats_load(force_reload, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_show()
{
    gpgs_achievements_show();
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_increment(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: achievement_id, type: String
    std::string_view achievement_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: steps, type: Float64
    double steps = gm::wire::codec::readValue<double>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_achievements_increment(achievement_id, steps, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_reveal(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: achievement_id, type: String
    std::string_view achievement_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_achievements_reveal(achievement_id, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_set_steps(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: achievement_id, type: String
    std::string_view achievement_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: steps, type: Float64
    double steps = gm::wire::codec::readValue<double>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_achievements_set_steps(achievement_id, steps, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_unlock(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: achievement_id, type: String
    std::string_view achievement_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_achievements_unlock(achievement_id, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_achievements_get_status(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: force_reload, type: Bool
    bool force_reload = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_achievements_get_status(force_reload, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_leaderboard_show_all()
{
    gpgs_leaderboard_show_all();
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_leaderboard_show(char* leaderboard_id)
{
    gpgs_leaderboard_show(leaderboard_id);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_leaderboard_submit_score(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: leaderboard_id, type: String
    std::string_view leaderboard_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: score, type: Float64
    double score = gm::wire::codec::readValue<double>(__br);

    // field: score_tag, type: String
    std::string_view score_tag = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_leaderboard_submit_score(leaderboard_id, score, score_tag, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_leaderboard_load_player_centered_scores(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: leaderboard_id, type: String
    std::string_view leaderboard_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: span, type: enum GPGSLeaderboardTimeSpan
    gm_enums::GPGSLeaderboardTimeSpan span = gm::wire::codec::readValue<gm_enums::GPGSLeaderboardTimeSpan>(__br);

    // field: leaderboard_collection, type: enum GPGSLeaderboardCollection
    gm_enums::GPGSLeaderboardCollection leaderboard_collection = gm::wire::codec::readValue<gm_enums::GPGSLeaderboardCollection>(__br);

    // field: max_results, type: Float64
    double max_results = gm::wire::codec::readValue<double>(__br);

    // field: force_reload, type: Bool
    bool force_reload = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_leaderboard_load_player_centered_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_leaderboard_load_top_scores(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: leaderboard_id, type: String
    std::string_view leaderboard_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: span, type: enum GPGSLeaderboardTimeSpan
    gm_enums::GPGSLeaderboardTimeSpan span = gm::wire::codec::readValue<gm_enums::GPGSLeaderboardTimeSpan>(__br);

    // field: leaderboard_collection, type: enum GPGSLeaderboardCollection
    gm_enums::GPGSLeaderboardCollection leaderboard_collection = gm::wire::codec::readValue<gm_enums::GPGSLeaderboardCollection>(__br);

    // field: max_results, type: Float64
    double max_results = gm::wire::codec::readValue<double>(__br);

    // field: force_reload, type: Bool
    bool force_reload = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_leaderboard_load_top_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_uri_to_path(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: uri, type: String
    std::string_view uri = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_uri_to_path(uri, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_show_saved_games_ui(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: title, type: String
    std::string_view title = gm::wire::codec::readValue<std::string_view>(__br);

    // field: button_add, type: Bool
    bool button_add = gm::wire::codec::readValue<bool>(__br);

    // field: button_delete, type: Bool
    bool button_delete = gm::wire::codec::readValue<bool>(__br);

    // field: max_results, type: Float64
    double max_results = gm::wire::codec::readValue<double>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_show_saved_games_ui(title, button_add, button_delete, max_results, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE____gpgs_saved_games_commit_and_close(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: options_json, type: String
    std::string_view options_json = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    __gpgs_saved_games_commit_and_close(options_json, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE____gpgs_saved_games_commit_new(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: options_json, type: String
    std::string_view options_json = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    __gpgs_saved_games_commit_new(options_json, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_load(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: force_reload, type: Bool
    bool force_reload = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_load(force_reload, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_open(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: name, type: String
    std::string_view name = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_open(name, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_open_conflict(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: name, type: String
    std::string_view name = gm::wire::codec::readValue<std::string_view>(__br);

    // field: conflict_policy, type: enum GPGSSavedGamesConflictPolicy
    gm_enums::GPGSSavedGamesConflictPolicy conflict_policy = gm::wire::codec::readValue<gm_enums::GPGSSavedGamesConflictPolicy>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_open_conflict(name, conflict_policy, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_delete(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: name, type: String
    std::string_view name = gm::wire::codec::readValue<std::string_view>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_delete(name, callback);
    return 0;
}

GMEXPORT double __EXT_NATIVE__gpgs_saved_games_resolve_conflict(char* __arg_buffer, double __arg_buffer_length)
{
    gm::byteio::BufferReader __br{__arg_buffer, static_cast<size_t>(__arg_buffer_length)};

    // field: conflict_id, type: String
    std::string_view conflict_id = gm::wire::codec::readValue<std::string_view>(__br);

    // field: use_local, type: Bool
    bool use_local = gm::wire::codec::readValue<bool>(__br);

    // field: callback, type: Function
    gm::wire::GMFunction callback = gm::wire::codec::readFunction(__br, &__dispatch_queue);

    gpgs_saved_games_resolve_conflict(conflict_id, use_local, callback);
    return 0;
}

