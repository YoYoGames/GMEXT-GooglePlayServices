/// @description Initialize Saved Games demo

opened_unique_name = "";
opened_description = "";
dialog_ind = -1;

function set_slot_edit_mode(_enable)
{
    with (Obj_GooglePlayServices_SavedGames_Save) locked = !_enable;
    with (Obj_GooglePlayServices_SavedGames_Delete) locked = !_enable;
    with (Obj_GooglePlayServices_SavedGames_Discard) locked = !_enable;

    with (Obj_GooglePlayServices_SavedGames_New) locked = _enable;
    with (Obj_GooglePlayServices_SavedGames_Show) locked = _enable;
    with (Obj_GooglePlayServices_SavedGames_Slot) locked = _enable;
}

function close_local_slot()
{
    instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);
    instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);

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

    instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);
    instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);
    instance_destroy(Obj_GooglePlayServices_SavedGames_Slot);

    var _snapshots = _result.snapshots;

    for (var i = 0; i < array_length(_snapshots); ++i)
    {
        var _snapshot = _snapshots[i];
        var _slot = instance_create_depth(
            300,
            250 + 85 * i,
            depth,
            Obj_GooglePlayServices_SavedGames_Slot
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

gpgs_saved_games_load(true, callback_saved_games_load);
