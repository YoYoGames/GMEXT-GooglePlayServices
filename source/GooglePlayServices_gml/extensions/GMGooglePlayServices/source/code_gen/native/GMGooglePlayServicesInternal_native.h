// ##### extgen :: Auto-generated file do not edit!! #####

#pragma once
#include <cstdint>
#include <string_view>
#include <vector>
#include <array>
#include <optional>
#include "core/GMExtWire.h"

namespace gm_consts
{
}


namespace gm_enums
{
    enum class GPGSAchievementState : std::int32_t
    {
        Unlocked = 0,
        Revealed = 1,
        Hidden = 2
    };

    enum class GPGSAchievementType : std::int32_t
    {
        Standard = 0,
        Incremental = 1
    };

    enum class GPGSLeaderboardTimeSpan : std::int32_t
    {
        Daily = 0,
        Weekly = 1,
        AllTime = 2
    };

    enum class GPGSLeaderboardCollection : std::int32_t
    {
        Public = 0,
        Friends = 3
    };

    enum class GPGSLeaderboardScoreOrder : std::int32_t
    {
        SmallerIsBetter = 0,
        LargerIsBetter = 1
    };

    enum class GPGSSavedGamesConflictPolicy : std::int32_t
    {
        Manual = -1,
        LongestPlaytime = 1,
        LastKnownGood = 2,
        MostRecentlyModified = 3,
        HighestProgress = 4
    };

    enum class GPGSSavedGamesDisplayLimit : std::int32_t
    {
        None = -1
    };

    enum class GPGSSavedGamesUIResult : std::int32_t
    {
        Cancelled = 0,
        Selected = 1,
        CreatedNew = 2,
        Deleted = 3,
        Error = -1
    };

}


namespace gm_structs
{
    struct GPGSSavedGameCommitOptions;

    struct GPGSSavedGameCommitOptions
    {
        std::string name;
        std::string data;
        std::string desc;
        double played_time_millis;
        double progress_value;
        std::string cover_image_path;
    };

}

namespace gm::wire::codec
{
    template<>
    inline void writeValue<gm_structs::GPGSSavedGameCommitOptions>(gm::byteio::IByteWriter& _buf, const gm_structs::GPGSSavedGameCommitOptions& obj)
    {
        gm::wire::codec::writeValue(_buf, obj.name);
        gm::wire::codec::writeValue(_buf, obj.data);
        gm::wire::codec::writeValue(_buf, obj.desc);
        gm::wire::codec::writeValue(_buf, obj.played_time_millis);
        gm::wire::codec::writeValue(_buf, obj.progress_value);
        gm::wire::codec::writeValue(_buf, obj.cover_image_path);
    }

    template<>
    inline gm_structs::GPGSSavedGameCommitOptions readValue<gm_structs::GPGSSavedGameCommitOptions>(gm::byteio::BufferReader& _buf)
    {
        gm_structs::GPGSSavedGameCommitOptions obj;
        obj.name = gm::wire::codec::readValue<std::string>(_buf);
        obj.data = gm::wire::codec::readValue<std::string>(_buf);
        obj.desc = gm::wire::codec::readValue<std::string>(_buf);
        obj.played_time_millis = gm::wire::codec::readValue<double>(_buf);
        obj.progress_value = gm::wire::codec::readValue<double>(_buf);
        obj.cover_image_path = gm::wire::codec::readValue<std::string>(_buf);
        return obj;
    }

}

namespace gm::wire::details
{
    template<>
    struct gm_struct_traits<gm_structs::GPGSSavedGameCommitOptions>
    {
        static constexpr bool is_gm_struct = true;
        static constexpr std::uint32_t codec_id = 0;
    };

}

bool gpgs_is_available();
void gpgs_sign_in(const gm::wire::GMFunction& callback);
void gpgs_is_authenticated(const gm::wire::GMFunction& callback);
void gpgs_request_server_side_access(std::string_view server_client_id, bool force_refresh_token, const gm::wire::GMFunction& callback);
void gpgs_player_current(const gm::wire::GMFunction& callback);
void gpgs_player_current_id(const gm::wire::GMFunction& callback);
void gpgs_player_stats_load(bool force_reload, const gm::wire::GMFunction& callback);
void gpgs_achievements_show();
void gpgs_achievements_increment(std::string_view achievement_id, double steps, const gm::wire::GMFunction& callback);
void gpgs_achievements_reveal(std::string_view achievement_id, const gm::wire::GMFunction& callback);
void gpgs_achievements_set_steps(std::string_view achievement_id, double steps, const gm::wire::GMFunction& callback);
void gpgs_achievements_unlock(std::string_view achievement_id, const gm::wire::GMFunction& callback);
void gpgs_achievements_get_status(bool force_reload, const gm::wire::GMFunction& callback);
void gpgs_leaderboard_show_all();
void gpgs_leaderboard_show(std::string_view leaderboard_id);
void gpgs_leaderboard_submit_score(std::string_view leaderboard_id, double score, std::string_view score_tag, const gm::wire::GMFunction& callback);
void gpgs_leaderboard_load_player_centered_scores(std::string_view leaderboard_id, gm_enums::GPGSLeaderboardTimeSpan span, gm_enums::GPGSLeaderboardCollection leaderboard_collection, double max_results, bool force_reload, const gm::wire::GMFunction& callback);
void gpgs_leaderboard_load_top_scores(std::string_view leaderboard_id, gm_enums::GPGSLeaderboardTimeSpan span, gm_enums::GPGSLeaderboardCollection leaderboard_collection, double max_results, bool force_reload, const gm::wire::GMFunction& callback);
void gpgs_uri_to_path(std::string_view uri, const gm::wire::GMFunction& callback);
void gpgs_saved_games_show_saved_games_ui(std::string_view title, bool button_add, bool button_delete, double max_results, const gm::wire::GMFunction& callback);
void gpgs_saved_games_commit_and_close(const gm_structs::GPGSSavedGameCommitOptions& options, const gm::wire::GMFunction& callback);
void gpgs_saved_games_commit_new(const gm_structs::GPGSSavedGameCommitOptions& options, const gm::wire::GMFunction& callback);
void gpgs_saved_games_load(bool force_reload, const gm::wire::GMFunction& callback);
void gpgs_saved_games_open(std::string_view name, const gm::wire::GMFunction& callback);
void gpgs_saved_games_open_conflict(std::string_view name, gm_enums::GPGSSavedGamesConflictPolicy conflict_policy, const gm::wire::GMFunction& callback);
void gpgs_saved_games_delete(std::string_view name, const gm::wire::GMFunction& callback);
void gpgs_saved_games_resolve_conflict(std::string_view conflict_id, bool use_local, const gm::wire::GMFunction& callback);
