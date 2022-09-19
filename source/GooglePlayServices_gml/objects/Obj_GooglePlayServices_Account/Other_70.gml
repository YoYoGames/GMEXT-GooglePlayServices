/// @description Update account info

switch(async_load[?"type"])
{	
	// @triggered by GooglePlayServices_StartSignInIntent()
	case "GooglePlayServices_SignIn":
		// At this point we received a callback signIn intent.
		// But we still need to check if the signIn was successful or not
		if(async_load[?"success"]) 
		{
			// The sign in was successfull (get user information).
			accountInfoGet();
		}
		else
		{
			// The sign in was not successful (reset user information).
			accounInfoReset();
		}
		break;
	
	// @triggered by GooglePlayServices_RevokeAccess()
	case "GooglePlayServices_RevokeAccess":
	// @triggered by GooglePlayServices_SignOut()
	case "GooglePlayServices_SignOut":
		// We just signed out so the user information is now reset.
		accounInfoReset();
		break;	
}
