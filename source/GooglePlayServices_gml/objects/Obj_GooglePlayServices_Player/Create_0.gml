/// @description Initialize variables

// These variables will be used to store player information.
playerInfo = noone;
sprite = noone

// Variable to identify the uriToPath request
UriToPath_request = noone

// This is a help function that will reset the player information.
function playerInfoReset()
{
	playerInfo = noone;
	
	// We need to check if the sprite exists and delete it
	// otherwise we will have memory leaks.
	if (sprite_exists(sprite))
	{
		sprite_delete(sprite);
		sprite = undefined;
	}
}

// We check to see if we are signed in and if so we proceed to getting player info.
if (GooglePlayServices_IsSignedIn())
{
	// This is a method that triggers a Social Async event callback
	// returning the player information (check social async event).
	GooglePlayServices_Player_Current()
}