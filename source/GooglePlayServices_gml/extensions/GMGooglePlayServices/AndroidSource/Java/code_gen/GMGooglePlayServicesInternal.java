// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName};

import java.nio.ByteBuffer;
import java.util.*;
import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.GMExtWire.GMFunction;
import ${YYAndroidPackageName}.GMExtWire.GMValue;
import ${YYAndroidPackageName}.records.*;
import ${YYAndroidPackageName}.codecs.*;
import ${YYAndroidPackageName}.enums.*;

public abstract class GMGooglePlayServicesInternal extends RunnerSocial implements GMGooglePlayServicesInterface {

    private final GMExtWire.DispatchQueue __dispatch_queue = new GMExtWire.DispatchQueue();
    public double __EXT_NATIVE__GMGooglePlayServices_invocation_handler(ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        return __dispatch_queue.fetch(__ret_buffer);
    }

    public double __EXT_NATIVE__gpgs_is_available()
    {
        boolean __result = gpgs_is_available();
        return __result ? 1.0 : 0.0;
    }

    public double __EXT_NATIVE__gpgs_sign_in(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_sign_in(callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_is_authenticated(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_is_authenticated(callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_request_server_side_access(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: server_client_id, type: String
        String server_client_id = GMExtWire.readString(__arg_buffer);

        // field: force_refresh_token, type: Bool
        boolean force_refresh_token = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_request_server_side_access(server_client_id, force_refresh_token, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_player_current(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_player_current(callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_player_current_id(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_player_current_id(callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_player_stats_load(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_player_stats_load(force_reload, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_show()
    {
        gpgs_achievements_show();
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_increment(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: steps, type: Float64
        double steps = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_achievements_increment(achievement_id, steps, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_reveal(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_achievements_reveal(achievement_id, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_set_steps(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: steps, type: Float64
        double steps = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_achievements_set_steps(achievement_id, steps, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_unlock(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_achievements_unlock(achievement_id, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_achievements_get_status(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_achievements_get_status(force_reload, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_leaderboard_show_all()
    {
        gpgs_leaderboard_show_all();
        return 0;
    }

    public double __EXT_NATIVE__gpgs_leaderboard_show(String leaderboard_id)
    {
        gpgs_leaderboard_show(leaderboard_id);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_leaderboard_submit_score(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: score, type: Float64
        double score = GMExtWire.readF64(__arg_buffer);

        // field: score_tag, type: String
        String score_tag = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_leaderboard_submit_score(leaderboard_id, score, score_tag, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_leaderboard_load_player_centered_scores(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: span, type: enum GPGSLeaderboardTimeSpan
        GPGSLeaderboardTimeSpan span = GPGSLeaderboardTimeSpan.from(GMExtWire.readI32(__arg_buffer));

        // field: leaderboard_collection, type: enum GPGSLeaderboardCollection
        GPGSLeaderboardCollection leaderboard_collection = GPGSLeaderboardCollection.from(GMExtWire.readI32(__arg_buffer));

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_leaderboard_load_player_centered_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_leaderboard_load_top_scores(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: span, type: enum GPGSLeaderboardTimeSpan
        GPGSLeaderboardTimeSpan span = GPGSLeaderboardTimeSpan.from(GMExtWire.readI32(__arg_buffer));

        // field: leaderboard_collection, type: enum GPGSLeaderboardCollection
        GPGSLeaderboardCollection leaderboard_collection = GPGSLeaderboardCollection.from(GMExtWire.readI32(__arg_buffer));

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_leaderboard_load_top_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_uri_to_path(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: uri, type: String
        String uri = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_uri_to_path(uri, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_show_saved_games_ui(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: title, type: String
        String title = GMExtWire.readString(__arg_buffer);

        // field: button_add, type: Bool
        boolean button_add = GMExtWire.readBool(__arg_buffer);

        // field: button_delete, type: Bool
        boolean button_delete = GMExtWire.readBool(__arg_buffer);

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_show_saved_games_ui(title, button_add, button_delete, max_results, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_commit_and_close(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: options, type: struct GPGSSavedGameCommitOptions
        GPGSSavedGameCommitOptions options = GPGSSavedGameCommitOptionsCodec.read(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_commit_and_close(options, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_commit_new(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: options, type: struct GPGSSavedGameCommitOptions
        GPGSSavedGameCommitOptions options = GPGSSavedGameCommitOptionsCodec.read(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_commit_new(options, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_load(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_load(force_reload, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_open(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_open(name, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_open_conflict(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: conflict_policy, type: enum GPGSSavedGamesConflictPolicy
        GPGSSavedGamesConflictPolicy conflict_policy = GPGSSavedGamesConflictPolicy.from(GMExtWire.readI32(__arg_buffer));

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_open_conflict(name, conflict_policy, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_delete(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_delete(name, callback);
        return 0;
    }

    public double __EXT_NATIVE__gpgs_saved_games_resolve_conflict(ByteBuffer __arg_buffer, double __arg_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: conflict_id, type: String
        String conflict_id = GMExtWire.readString(__arg_buffer);

        // field: use_local, type: Bool
        boolean use_local = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        gpgs_saved_games_resolve_conflict(conflict_id, use_local, callback);
        return 0;
    }

}