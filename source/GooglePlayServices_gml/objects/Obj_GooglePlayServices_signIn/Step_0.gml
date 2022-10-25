/// @description Update text

event_inherited()

if (GooglePlayServices_IsSignedIn())
{
	text = "Sign Out";
}
else
{
	text = "Sign In";
}
