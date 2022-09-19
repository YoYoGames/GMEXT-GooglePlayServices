/// @description Update buttons

switch(async_load[?"type"])
{	
	// @triggered by GooglePlayServices_StartSignInIntent()
	case "GooglePlayServices_SignIn":
		// At this point we are now signed in to the google play account.
		setSignedInMode(true);
		break;
	
	// @triggered by GooglePlayServices_RevokeAccess()
	case "GooglePlayServices_RevokeAccess":
	// @triggered by GooglePlayServices_SignOut()
	case "GooglePlayServices_SignOut":
		// At this point we are now signed out of the google play account.
		setSignedInMode(false);
		break;	
}
