/// @description Update account info

switch (async_load[?"type"])
{
	// @triggered by GooglePlayServices_StartSignInIntent()
	case GOOGLE_PLAY_EV_SIGNIN:
	
		// If we succeeded to get the player info
		if(async_load[?"success"])
		{
			GooglePlayServices_Player_Current();
		}
		break;
	
	// @triggered by GooglePlayServices_Player_Current()
	case GOOGLE_PLAY_EV_PLAYERS_CURRENT:
	
		// If we succeeded to get the player info
		if(async_load[?"success"])
		{
			
			// We can parse the infomation into a struct
			playerInfo = json_parse(async_load[? "player"]);
			
			// Check if the player has a HiRes image or an icon.
			// Either way this retrieved variable is an URI and needs to be converted to a path
			// for the sprite to be loaded from so we need to call 'GooglePlayServices_UriToPath'
			// which will trigger an Async Social event with the sprite path.
			if (playerInfo.hasHiResImage)
			{
				UriToPath_request = GooglePlayServices_UriToPath(playerInfo.hiResImageUri);
			}
			else if (playerInfo.hasIconImage)
			{
				UriToPath_request = GooglePlayServices_UriToPath(playerInfo.hasIconImage);
			}
		}
		else
		{
			// At this point we were not successful to retreive user data so
			// we proceed to reset any previously cached information.
			playerInfoReset();
		}
		break;
	
	// @triggered by GooglePlayServices_UriToPath()
	case GOOGLE_PLAY_EV_UTILS_URI_TO_PATH:
		
		// We need to check the id of the request to make sure it matches the current request
		if(async_load[?"ind"] == UriToPath_request)
		{
			// At this point we matched the request id and can load the sprite using the retreived path.
			sprite = sprite_add(async_load[?"path"], 0, 0, 0, 0, 0);
		}
		break;
}

