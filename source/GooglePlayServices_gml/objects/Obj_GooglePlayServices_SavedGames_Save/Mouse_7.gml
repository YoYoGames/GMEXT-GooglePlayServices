/// @description Save and close the opened slot

if (locked) exit;

var _thumbnail_path = "save_thumbnail.png";

if (instance_exists(Obj_GooglePlayServices_SavedGames_Icon))
{
    sprite_save_w(
        Obj_GooglePlayServices_SavedGames_Icon.sprite_index,
        Obj_GooglePlayServices_SavedGames_Icon.image_index,
        _thumbnail_path
    );
}

var _objects = [];
with (Obj_GooglePlayServices_SavedGames_DataObj)
{
    array_push(_objects, {
        x: x,
        y: y,
        image_index: image_index
    });
}

var _icon_index = 0;
if (instance_exists(Obj_GooglePlayServices_SavedGames_Icon))
    _icon_index = Obj_GooglePlayServices_SavedGames_Icon.image_index;

var _save_data = {
    objs: _objects,
    icon_index: _icon_index
};

var _options = {
    name: Obj_GooglePlayServices_SavedGames.opened_unique_name,
    data: json_stringify(_save_data),
    desc: Obj_GooglePlayServices_SavedGames.opened_description,
    played_time_millis: 0,
    progress_value: 0,
    cover_image_path: _thumbnail_path
};

gpgs_saved_games_commit_and_close(_options, function(_success, _name, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    with (Obj_GooglePlayServices_SavedGames)
    {
        close_local_slot();
        gpgs_saved_games_load(true, callback_saved_games_load);
    }
});
