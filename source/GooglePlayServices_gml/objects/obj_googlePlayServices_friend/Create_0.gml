/// @description Initialize variables

event_inherited();

//data: { title : "Pinball Wizard", icon_image_uri : "content://com.google.android.gms.games.background/images/d3cb4df3/359", hi_res_image_uri : "content://com.google.android.gms.games.background/images/d3cb4df3/2", player_id : "a_3340375223673922871", display_name : "AliNazario" }

player_sprite = noone;
callback_owner = id;

    var _image_uri = undefined;

    if (!is_undefined(data.hi_res_image_uri))
    {
        _image_uri = data.hi_res_image_uri;
    }
    else if (!is_undefined(data.icon_image_uri))
    {
        _image_uri = data.icon_image_uri;
    }

    if (!is_undefined(_image_uri))
    {
        play_services_uri_to_path(_image_uri,function(_status, _path)
			{
			    show_debug_message("play_services_uri_to_path_callback");
			    show_debug_message(_status);

			    if (!_status.success)
			    {
			        show_debug_message(_status.error);
			        return;
			    }

			    if (!instance_exists(callback_owner))
			        return;

			    with (callback_owner)
			    {
			        if (sprite_exists(player_sprite))
			            sprite_delete(player_sprite);

			        player_sprite = sprite_add(_path,1,false,false,0,0);

			        show_debug_message({player_sprite: player_sprite,sprite_exists: sprite_exists(player_sprite)});
			    }
			}
        );
    }
    else
    {
        show_debug_message("The player has no usable image URI.");
    }
	