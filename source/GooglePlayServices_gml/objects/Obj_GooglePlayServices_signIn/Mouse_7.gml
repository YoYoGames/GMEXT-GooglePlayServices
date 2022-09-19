/// @description Sign in/out

if (GooglePlayServices_IsSignedIn())
{
	// This function call will sign out from the google play account.
	// Generates a Social Async callback 'GooglePlayServices_SignOut'
	GooglePlayServices_SignOut();
}
else
{
	// This function call will express an intent to login to the user account.
	// It will present the user with a window to select account (can succeed or fail)
	// Generates a Social Async callback 'GooglePlayServices_SignIn'
	GooglePlayServices_StartSignInIntent();
}