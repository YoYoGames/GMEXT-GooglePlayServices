/// @description Handle the new-slot description dialog

if (async_load[? "id"] != dialog_ind) exit;
if (!async_load[? "status"]) exit;
if (async_load[? "result"] == "") exit;

var _thumbnail_path = "save_thumbnail.png";
sprite_save_w(Spr_GooglePlayServices_SavedGames, 0, _thumbnail_path);

var _objects = [];
for (var i = 0; i < 3; ++i)
{
    array_push(_objects, {
        x: 700 + i * 125,
        y: 250,
        image_index: 0
    });
}

var _save_data = {
    objs: _objects,
    icon_index: 0
};

var _options = {
    name: "slot_" + string(current_time) + "_" + string(irandom_range(0, 99999)),
    data: json_stringify(_save_data),
    desc: async_load[? "result"],
    played_time_millis: 0,
    progress_value: 0,
    cover_image_path: _thumbnail_path
};

gpgs_saved_games_commit_new(_options, function(_success, _name, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    with (Obj_GooglePlayServices_SavedGames)
    {
        gpgs_saved_games_load(true, callback_saved_games_load);
    }
});
