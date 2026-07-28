/// @description Initialize Saved Games demo

opened_unique_name = "";
opened_description = "";
dialog_ind = -1;

function set_slot_edit_mode(_enable)
{
    with (obj_play_services_saved_games_save) locked = !_enable;
    with (obj_play_services_saved_games_delete) locked = !_enable;
    with (obj_play_services_saved_games_discard) locked = !_enable;

    with (obj_play_services_saved_games_new) locked = _enable;
    with (obj_play_services_saved_games_show) locked = _enable;
    with (obj_play_services_saved_games_slot) locked = _enable;
}

function close_local_slot()
{
    instance_destroy(obj_play_services_saved_games_data_obj);
    instance_destroy(obj_play_services_saved_games_icon);

    opened_unique_name = "";
    opened_description = "";
    set_slot_edit_mode(false);
}

callback_saved_games_load = function(_result)
{
    show_debug_message(_result);

    if (!_result.success)
    {
        show_debug_message(_result.error);
        return;
    }

    instance_destroy(obj_play_services_saved_games_data_obj);
    instance_destroy(obj_play_services_saved_games_icon);
    instance_destroy(obj_play_services_saved_games_slot);

    var _snapshots = _result.snapshots;

    for (var i = 0; i < array_length(_snapshots); ++i)
    {
        var _snapshot = _snapshots[i];
        var _slot = instance_create_depth(
            300,
            250 + 85 * i,
            depth,
            obj_play_services_saved_games_slot
        );

        _slot.cover_image_uri = _snapshot.cover_image_uri;
        _slot.unique_name = _snapshot.unique_name;
        _slot.description = _snapshot.description;
        _slot.text = _snapshot.description != ""
            ? _snapshot.description
            : _snapshot.unique_name;
    }

    set_slot_edit_mode(false);
};

play_services_saved_games_load(true, callback_saved_games_load);
