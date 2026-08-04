/// @description Initialize save slot

event_inherited();

cover_sprite = noone;
cover_image_uri = undefined;
cover_image_requested = false;
callback_owner = id;
unique_name = "";
description = "";

image_xscale = 5;

uri_to_path_callback = function(_status, _path)
{
    if (!_status.success)
    {
        show_debug_message(_status.error);
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
	play_services_saved_games_open(_slot_name, false, PlayServicesSavedGamesConflictPolicy.MostRecentlyModified, function(_status, _opened)
	{
	    show_debug_message(_status);

	    if (!_status.success)
	    {
	        show_debug_message(_status.error);
	        with (obj_play_services_saved_games)
	            play_services_saved_games_load(true, callback_saved_games_load);
	        return;
	    }

	    if (_opened.is_conflict)
	    {
	        show_debug_message("Unexpected Saved Games conflict.");
	        return;
	    }

	    var _metadata = _opened.snapshot_metadata;
	    var _data = {};

	    if (!is_undefined(_opened.data))
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

	    with (obj_play_services_saved_games)
	    {
	        opened_unique_name = _metadata.unique_name;
	        opened_description = _metadata.description;
	        set_slot_edit_mode(true);

	        instance_destroy(obj_play_services_saved_games_icon);
	        instance_destroy(obj_play_services_saved_games_data_obj);

	        var _icon = instance_create_depth(
	            room_width - 120,
	            150,
	            depth,
	            obj_play_services_saved_games_icon
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
	                    obj_play_services_saved_games_data_obj
	                );
	                _instance.image_index = _properties.image_index;
	            }
	        }
	    }
	});	
}

