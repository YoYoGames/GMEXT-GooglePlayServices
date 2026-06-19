// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName};
import ${YYAndroidPackageName}.GMExtWire.GMFunction;
import ${YYAndroidPackageName}.GMExtWire.GMValue;
import ${YYAndroidPackageName}.enums.*;

public interface GMGooglePlayServicesInterface {
    public boolean gpgs_is_available();
    public void gpgs_sign_in(GMFunction callback);
    public void gpgs_is_authenticated(GMFunction callback);
    public void gpgs_request_server_side_access(String server_client_id, boolean force_refresh_token, GMFunction callback);
    public void gpgs_player_current(GMFunction callback);
    public void gpgs_player_current_id(GMFunction callback);
    public void gpgs_player_stats_load(boolean force_reload, GMFunction callback);
    public void gpgs_achievements_show();
    public void gpgs_achievements_increment(String achievement_id, double steps, GMFunction callback);
    public void gpgs_achievements_reveal(String achievement_id, GMFunction callback);
    public void gpgs_achievements_set_steps(String achievement_id, double steps, GMFunction callback);
    public void gpgs_achievements_unlock(String achievement_id, GMFunction callback);
    public void gpgs_achievements_get_status(boolean force_reload, GMFunction callback);
    public void gpgs_leaderboard_show_all();
    public void gpgs_leaderboard_show(String leaderboard_id);
    public void gpgs_leaderboard_submit_score(String leaderboard_id, double score, String score_tag, GMFunction callback);
    public void gpgs_leaderboard_load_player_centered_scores(String leaderboard_id, GPGSLeaderboardTimeSpan span, GPGSLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback);
    public void gpgs_leaderboard_load_top_scores(String leaderboard_id, GPGSLeaderboardTimeSpan span, GPGSLeaderboardCollection leaderboard_collection, double max_results, boolean force_reload, GMFunction callback);
    public void gpgs_uri_to_path(String uri, GMFunction callback);
    public void gpgs_saved_games_show_saved_games_ui(String title, boolean button_add, boolean button_delete, double max_results, GMFunction callback);
    public void __gpgs_saved_games_commit_and_close(String options_json, GMFunction callback);
    public void __gpgs_saved_games_commit_new(String options_json, GMFunction callback);
    public void gpgs_saved_games_load(boolean force_reload, GMFunction callback);
    public void gpgs_saved_games_open(String name, GMFunction callback);
    public void gpgs_saved_games_open_conflict(String name, GPGSSavedGamesConflictPolicy conflict_policy, GMFunction callback);
    public void gpgs_saved_games_delete(String name, GMFunction callback);
    public void gpgs_saved_games_resolve_conflict(String conflict_id, boolean use_local, GMFunction callback);
}