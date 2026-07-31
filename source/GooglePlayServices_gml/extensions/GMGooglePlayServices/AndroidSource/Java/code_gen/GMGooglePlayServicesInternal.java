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

    public double __EXT_NATIVE__play_services_is_available()
    {
        boolean __result = play_services_is_available();
        return __result ? 1.0 : 0.0;
    }

    public double __EXT_NATIVE__play_services_sign_in(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_sign_in(callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_is_authenticated(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_is_authenticated(callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_request_server_side_access(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: server_client_id, type: String
        String server_client_id = GMExtWire.readString(__arg_buffer);

        // field: force_refresh_token, type: Bool
        boolean force_refresh_token = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_request_server_side_access(server_client_id, force_refresh_token, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_current(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_player_current(callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_current_id(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_player_current_id(callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_stats_load(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_player_stats_load(force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_load(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: player_id, type: String
        String player_id = GMExtWire.readString(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_player_load(player_id, force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_friends_load(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_friends_load(force_reload, max_results, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_friends_load_more(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: page_size, type: Float64
        double page_size = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_friends_load_more(page_size, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_friends_load_with_consent(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_friends_load_with_consent(force_reload, max_results, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_profile_show(String player_id, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        PlayServicesError __result = play_services_player_profile_show(player_id);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_player_search_show(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_player_search_show(callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_show(ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        PlayServicesError __result = play_services_achievements_show();

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_increment(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: steps, type: Float64
        double steps = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_achievements_increment(achievement_id, steps, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_reveal(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_achievements_reveal(achievement_id, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_set_steps(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: steps, type: Float64
        double steps = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_achievements_set_steps(achievement_id, steps, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_unlock(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: achievement_id, type: String
        String achievement_id = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_achievements_unlock(achievement_id, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_achievements_get_status(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_achievements_get_status(force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_show_all(ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        PlayServicesError __result = play_services_leaderboard_show_all();

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_show(String leaderboard_id, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        PlayServicesError __result = play_services_leaderboard_show(leaderboard_id);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_submit_score(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: score, type: Float64
        double score = GMExtWire.readF64(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_leaderboard_submit_score(leaderboard_id, score, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_submit_score_with_tag(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
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

        PlayServicesError __result = play_services_leaderboard_submit_score_with_tag(leaderboard_id, score, score_tag, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_load_player_centered_scores(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: span, type: enum PlayServicesLeaderboardTimeSpan
        PlayServicesLeaderboardTimeSpan span = PlayServicesLeaderboardTimeSpan.from(GMExtWire.readI32(__arg_buffer));

        // field: leaderboard_collection, type: enum PlayServicesLeaderboardCollection
        PlayServicesLeaderboardCollection leaderboard_collection = PlayServicesLeaderboardCollection.from(GMExtWire.readI32(__arg_buffer));

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_leaderboard_load_player_centered_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_leaderboard_load_top_scores(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: leaderboard_id, type: String
        String leaderboard_id = GMExtWire.readString(__arg_buffer);

        // field: span, type: enum PlayServicesLeaderboardTimeSpan
        PlayServicesLeaderboardTimeSpan span = PlayServicesLeaderboardTimeSpan.from(GMExtWire.readI32(__arg_buffer));

        // field: leaderboard_collection, type: enum PlayServicesLeaderboardCollection
        PlayServicesLeaderboardCollection leaderboard_collection = PlayServicesLeaderboardCollection.from(GMExtWire.readI32(__arg_buffer));

        // field: max_results, type: Float64
        double max_results = GMExtWire.readF64(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_leaderboard_load_top_scores(leaderboard_id, span, leaderboard_collection, max_results, force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_uri_to_path(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: uri, type: String
        String uri = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_uri_to_path(uri, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_show_saved_games_ui(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
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

        PlayServicesError __result = play_services_saved_games_show_saved_games_ui(title, button_add, button_delete, max_results, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_commit_and_close(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: options, type: struct PlayServicesSavedGameCommitOptions
        PlayServicesSavedGameCommitOptions options = PlayServicesSavedGameCommitOptionsCodec.read(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_commit_and_close(options, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_commit_new(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: options, type: struct PlayServicesSavedGameCommitOptions
        PlayServicesSavedGameCommitOptions options = PlayServicesSavedGameCommitOptionsCodec.read(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_commit_new(options, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_load(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: force_reload, type: Bool
        boolean force_reload = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_load(force_reload, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_open(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_open(name, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_open_conflict(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: conflict_policy, type: enum PlayServicesSavedGamesConflictPolicy
        PlayServicesSavedGamesConflictPolicy conflict_policy = PlayServicesSavedGamesConflictPolicy.from(GMExtWire.readI32(__arg_buffer));

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_open_conflict(name, conflict_policy, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_delete(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: name, type: String
        String name = GMExtWire.readString(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_delete(name, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public double __EXT_NATIVE__play_services_saved_games_resolve_conflict(ByteBuffer __arg_buffer, double __arg_buffer_length, ByteBuffer __ret_buffer, double __ret_buffer_length)
    {
        GMExtWire.order(__arg_buffer);

        // field: conflict_id, type: String
        String conflict_id = GMExtWire.readString(__arg_buffer);

        // field: use_local, type: Bool
        boolean use_local = GMExtWire.readBool(__arg_buffer);

        // field: callback, type: Function
        GMFunction callback = GMExtWire.readGMFunction(__arg_buffer, __dispatch_queue);

        PlayServicesError __result = play_services_saved_games_resolve_conflict(conflict_id, use_local, callback);

        GMExtWire.order(__ret_buffer);
        GMExtWire.IByteWriter __ret_buffer_writer = new GMExtWire.GMBufferWriter(__ret_buffer);
        // return: __result, type: enum PlayServicesError
        GMExtWire.writeI32(__ret_buffer_writer, __result.value());

        return 0;
    }

    public static final int PlayServicesMaxFriendsPageSize = 25;
    public static final int PlayServicesMaxLeaderboardResults = 25;
    public static final int PlayServicesMinPageSize = 1;
}