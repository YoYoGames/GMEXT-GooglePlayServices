/// @description Initialize leaderboard entry

entry_sprite = noone;

// The loader passes the score through the instance variable `data`.
if (!variable_instance_exists(id, "data") || !is_struct(data))
{
    show_debug_message("Leaderboard entry received invalid score data.");
    instance_destroy();
    exit;
}

display_rank = data.display_rank;
display_score = data.display_score;
raw_score = data.raw_score;
score_tag = data.score_tag;
timestamp_millis = data.timestamp_millis;

score_holder = data.score_holder;

score_holder_name = "";
score_holder_player_id = "";
score_holder_icon_image_uri = "";
score_holder_hi_res_image_uri = "";

if (is_struct(score_holder))
{
    score_holder_name = score_holder.display_name;
    score_holder_player_id = score_holder.player_id;
    score_holder_icon_image_uri = score_holder.icon_image_uri;
    score_holder_hi_res_image_uri = score_holder.hi_res_image_uri;
}

var _image_uri = "";

if (score_holder_hi_res_image_uri != "")
    _image_uri = score_holder_hi_res_image_uri;
else if (score_holder_icon_image_uri != "")
    _image_uri = score_holder_icon_image_uri;

var _owner = id;

gpgs_uri_to_path_callback = function(_success, _path, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    if (!instance_exists(_owner))
        return;

    with (_owner)
    {
        if (sprite_exists(entry_sprite))
            sprite_delete(entry_sprite);

        entry_sprite = sprite_add(
            _path,
            0,
            false,
            false,
            0,
            0
        );
    }
};

if (_image_uri != "")
{
    gpgs_uri_to_path(
        _image_uri,
        gpgs_uri_to_path_callback
    );
}
