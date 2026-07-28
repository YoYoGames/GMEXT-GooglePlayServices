/// @description Save and close the opened slot

if (locked) exit;

var _thumbnail_path = "save_thumbnail.png";

if (instance_exists(obj_play_services_saved_games_icon))
{
    sprite_save_w(
        obj_play_services_saved_games_icon.sprite_index,
        obj_play_services_saved_games_icon.image_index,
        _thumbnail_path
    );
}

var _objects = [];
with (obj_play_services_saved_games_data_obj)
{
    array_push(_objects, {
        x: x,
        y: y,
        image_index: image_index
    });
}

var _icon_index = 0;
if (instance_exists(obj_play_services_saved_games_icon))
    _icon_index = obj_play_services_saved_games_icon.image_index;

var _save_data = {
    objs: _objects,
    icon_index: _icon_index
};

var _options = new PlayServicesSavedGameCommitOptions();
_options.name = obj_play_services_saved_games.opened_unique_name;
_options.data = json_stringify(_save_data);
_options.desc = obj_play_services_saved_games.opened_description;
_options.played_time_millis = 0;
_options.progress_value = 0;
_options.cover_image_path = _thumbnail_path;

play_services_saved_games_commit_and_close(_options, function(_result)
{
    if (!_result.success)
    {
        show_debug_message(_result.error);
        return;
    }

    with (obj_play_services_saved_games)
    {
        close_local_slot();
        play_services_saved_games_load(true, callback_saved_games_load);
    }
});
