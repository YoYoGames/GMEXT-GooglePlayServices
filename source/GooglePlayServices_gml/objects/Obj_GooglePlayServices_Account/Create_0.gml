/// @description Initialize variables

// These variables will be used to store account information.
accountInfo = undefined;
sprite = noone//undefined;

// This is a help function that will reset the account information.
function accounInfoReset() 
{
	accountInfo = undefined;
	
	// We need to check if the sprite exists and delete it
	// otherwise we will have memory leaks.
	if (sprite_exists(sprite)) {
		sprite_delete(sprite);
		sprite = undefined;	
	}
}

// This is a help function that will get the account information.
function accountInfoGet()
{
	// This function call will return a json string with account information.
	var accountJson = GooglePlayServices_GetAccount();
	
	// We can parse the string into a struct
	accountInfo = json_parse(accountJson)
	
	// We can check if there is a photoUrl and if we have,
	// we go ahead and create a sprite out or it.
	var photoUrl = accountInfo[$ "photoUrl"];
	if (photoUrl != undefined) {
		sprite = sprite_add(photoUrl, 0, 0, 0, 0, 0);
	}
}

// We check to see if we are signed in and if so we proceed to getting acount info.
if(GooglePlayServices_IsSignedIn()) 
{
	accountInfoGet();
}

