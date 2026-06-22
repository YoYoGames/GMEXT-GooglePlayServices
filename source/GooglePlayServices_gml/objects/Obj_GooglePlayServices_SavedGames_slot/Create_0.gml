/// @description Initialize save slot

event_inherited();

cover_sprite = noone;
cover_image_uri = "";
cover_image_requested = false;
callback_owner = id;
unique_name = "";
description = "";

image_xscale = 5;

uri_to_path_callback = function(_success, _path, _error)
{
    if (!_success)
    {
        show_debug_message(_error);
        return;
    }

    if (!instance_exists(callback_owner))
        return;

    with (callback_owner)
    {
        if (sprite_exists(cover_sprite))
            sprite_delete(cover_sprite);

        cover_sprite = sprite_add(_path, 1, false, false, 0, 0);
    }
};





function slot_open(_slot_name)
{
	gpgs_saved_games_open(_slot_name, function(_result)
	{
	    show_debug_message(_result);

	    if (!_result.success)
	    {
	        show_debug_message(_result.error);
	        with (Obj_GooglePlayServices_SavedGames)
	            gpgs_saved_games_load(true, callback_saved_games_load);
	        return;
	    }

	    var _opened = _result.result;

	    if (_opened.is_conflict)
	    {
	        show_debug_message("Unexpected Saved Games conflict.");
	        return;
	    }

	    var _metadata = _opened.snapshot_metadata;
	    var _data = {};

	    if (_opened.data != "")
	    {
	        try
	        {
	            _data = json_parse(_opened.data);
	        }
	        catch (_exception)
	        {
	            show_debug_message("Could not parse saved-game data.");
	            show_debug_message(_exception);
	            return;
	        }
	    }

	    with (Obj_GooglePlayServices_SavedGames)
	    {
	        opened_unique_name = _metadata.unique_name;
	        opened_description = _metadata.description;
	        set_slot_edit_mode(true);

	        instance_destroy(Obj_GooglePlayServices_SavedGames_Icon);
	        instance_destroy(Obj_GooglePlayServices_SavedGames_DataObj);

	        var _icon = instance_create_depth(
	            room_width - 120,
	            150,
	            depth,
	            Obj_GooglePlayServices_SavedGames_Icon
	        );

	        if (is_struct(_data) && struct_exists(_data, "icon_index"))
	            _icon.image_index = _data.icon_index;

	        if (is_struct(_data) && struct_exists(_data, "objs"))
	        {
	            var _objects = _data.objs;

	            for (var i = 0; i < array_length(_objects); ++i)
	            {
	                var _properties = _objects[i];
	                var _instance = instance_create_depth(
	                    _properties.x,
	                    _properties.y,
	                    depth,
	                    Obj_GooglePlayServices_SavedGames_DataObj
	                );
	                _instance.image_index = _properties.image_index;
	            }
	        }
	    }
	});	
}

