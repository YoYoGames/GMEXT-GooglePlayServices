/// @description Update buttons

switch(async_load[?"type"])
{	
	case "GooglePlayServices_IsAuthenticated":
		
		if(async_load[?"success"])
		if(async_load[?"isAuthenticated"])
		{
			setSignedInMode(true)
			instance_create_depth(30,100,0,Obj_GooglePlayServices_PlayerStats)
		}
		else
			GooglePlayServices_SignIn()
	
	break
}
