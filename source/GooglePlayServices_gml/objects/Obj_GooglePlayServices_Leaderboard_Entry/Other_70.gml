
if(async_load[?"type"] == "GooglePlayServices_UriToPath")
{
	// We need to check the id of the request to make sure it matches the current request
	if(async_load[?"ind"] == UriToPath_request)
	{
		// At this point we matched the request id and can load the sprite using the retreived path.
		sprite = sprite_add(async_load[?"path"], 0, 0, 0, 0, 0);
	}
}		
