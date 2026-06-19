/// @description Initialize variables

// These variables will be used to store player information.
playerInfo = noone;
sprite = noone

gpgs_uri_to_path_callback = function(success,uri,error){
	
	show_debug_message("gpgs_uri_to_path_callback")
	show_debug_message({success,uri,error})
	
	if(!success)
		return
	
	// At this point we matched the request id and can load the sprite using the retreived path.
	sprite = sprite_add(uri, 0, 0, 0, 0, 0);
	
	show_debug_message("Here?")
	show_debug_message(sprite)
	
	if(sprite_exists(sprite))
		show_debug_message("Sprite Exists")
	else
		show_debug_message("Sprite NOT Exists")
}


// This is a help function that will reset the player information.
function playerInfoReset()
{
	playerInfo = noone;
	
	// We need to check if the sprite exists and delete it
	// otherwise we will have memory leaks.
	if (sprite_exists(sprite))
	{
		sprite_delete(sprite);
		sprite = noone;
	}
}

{
	// This is a method that triggers a Social Async event callback
	// returning the player information (check social async event).
	gpgs_player_current(function(success,player,error){
			// If we succeeded to get the player info
			if(success)
			{
				show_debug_message(player)
				
				// We can parse the infomation into a struct
				playerInfo = json_parse(player);
			
				// Check if the player has a HiRes image or an icon.
				// Either way this retrieved variable is an URI and needs to be converted to a path
				// for the sprite to be loaded from so we need to call 'gpgs_UriToPath'
				// which will trigger an Async Social event with the sprite path.
				if (struct_exists(playerInfo,"iconImageUri"))
				{
					gpgs_uri_to_path(playerInfo.iconImageUri,gpgs_uri_to_path_callback);
				}
				else if (struct_exists(playerInfo,"hiResImageUri"))
				{
					gpgs_uri_to_path(playerInfo.hiResImageUri,gpgs_uri_to_path_callback);
				}
			}
			else
			{
				// At this point we were not successful to retreive user data so
				// we proceed to reset any previously cached information.
				playerInfoReset();
			}
		})
}