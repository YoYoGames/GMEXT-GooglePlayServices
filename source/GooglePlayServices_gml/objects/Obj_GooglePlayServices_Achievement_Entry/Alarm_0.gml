
switch(state)
{
	case GPGSAchievementState.Hidden:

	break
	
	case GPGSAchievementState.Revealed:
		UriToPath_request = gpgs_uri_to_path(revealedImage,gpgs_uri_to_path_callback)
	break
	
	case GPGSAchievementState.Unlocked:
		UriToPath_request = gpgs_uri_to_path(unlockedImage,gpgs_uri_to_path_callback)
	break
}

