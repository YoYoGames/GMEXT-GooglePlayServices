// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName};
import ${YYAndroidPackageName}.GMExtWire.GMFunction;
import ${YYAndroidPackageName}.GMExtWire.GMValue;
import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;

import java.util.Optional;
import java.util.List;

public interface GMGooglePlayServicesInterface {
    public boolean play_services_is_available();
    public PlayServicesError play_services_sign_in(GMFunction callback);
    public PlayServicesError play_services_is_authenticated(GMFunction callback);
    public PlayServicesError play_services_request_server_side_access(String server_client_id, boolean force_refresh_token, GMFunction callback);
    public PlayServicesError play_services_player_current(GMFunction callback);
    public PlayServicesError play_services_player_current_id(GMFunction callback);
    public PlayServicesError play_services_player_stats_load(boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_player_load(String player_id, boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_friends_load(boolean force_reload, double max_results, GMFunction callback);
    public PlayServicesError play_services_friends_load_more(double page_size, GMFunction callback);
    public PlayServicesError play_services_friends_load_with_consent(boolean force_reload, double max_results, GMFunction callback);
    public PlayServicesError play_services_player_profile_show(String player_id);
    public PlayServicesError play_services_player_search_show(GMFunction callback);
    public PlayServicesError play_services_achievements_show();
    public PlayServicesError play_services_achievements_increment(String achievement_id, double steps, GMFunction callback);
    public PlayServicesError play_services_achievements_reveal(String achievement_id, GMFunction callback);
    public PlayServicesError play_services_achievements_set_steps(String achievement_id, double steps, GMFunction callback);
    public PlayServicesError play_services_achievements_unlock(String achievement_id, GMFunction callback);
    public PlayServicesError play_services_achievements_get_status(boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_leaderboard_show_all();
    public PlayServicesError play_services_leaderboard_show(String leaderboard_id);
    public PlayServicesError play_services_leaderboard_submit_score(String leaderboard_id, double score, GMFunction callback);
    public PlayServicesError play_services_leaderboard_submit_score_with_tag(String leaderboard_id, double score, String score_tag, GMFunction callback);
    public PlayServicesError play_services_leaderboard_load_player_centered_scores(String leaderboard_id, PlayServicesLeaderboardTimeSpan span, PlayServicesLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_leaderboard_load_top_scores(String leaderboard_id, PlayServicesLeaderboardTimeSpan span, PlayServicesLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_uri_to_path(String uri, GMFunction callback);
    public PlayServicesError play_services_saved_games_show_saved_games_ui(String title, boolean button_add, boolean button_delete, double max_results, GMFunction callback);
    public PlayServicesError play_services_saved_games_commit_and_close(PlayServicesSavedGameCommitOptions options, GMFunction callback);
    public PlayServicesError play_services_saved_games_commit_new(PlayServicesSavedGameCommitOptions options, GMFunction callback);
    public PlayServicesError play_services_saved_games_load(boolean force_reload, GMFunction callback);
    public PlayServicesError play_services_saved_games_open(String name, GMFunction callback);
    public PlayServicesError play_services_saved_games_open_conflict(String name, PlayServicesSavedGamesConflictPolicy conflict_policy, GMFunction callback);
    public PlayServicesError play_services_saved_games_delete(String name, GMFunction callback);
    public PlayServicesError play_services_saved_games_resolve_conflict(String conflict_id, boolean use_local, GMFunction callback);
}