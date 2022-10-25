
switch(async_load[?"type"])
{
	// @triggered by GooglePlayServices_UriToPath()
	case "GooglePlayServices_UriToPath":

		// At this point we received a callback from a UriToPath request.
		// However we still need to check if the identification matches
		// and if the query was a success.
		if (async_load[?"ind"] != UriToPath_ind) exit;
		
		if (!async_load[?"success"]) exit;
		
		// At this point both checks above passed so we have a path that we can use to
		// load the sprite.
		var path = async_load[?"path"]
		sprite = sprite_add(path, 0, 0, 0, 0, 0);
	
	break
}

