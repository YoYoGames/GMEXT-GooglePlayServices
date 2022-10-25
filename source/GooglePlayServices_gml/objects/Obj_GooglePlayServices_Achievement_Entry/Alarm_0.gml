
switch(state)
{
	case Achievement_STATE_HIDDEN:

	break
	
	case Achievement_STATE_REVEALED:
		UriToPath_request = GooglePlayServices_UriToPath(revealedImage)
	break
	
	case Achievement_STATE_UNLOCKED:
		UriToPath_request = GooglePlayServices_UriToPath(unlockedImage)
	break
}

