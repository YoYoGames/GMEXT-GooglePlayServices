
if(mouse_check_button_pressed(mb_left))
if(point_in_circle(mouse_x,mouse_y,x,y,100))
switch(state)
{
	case Achievement_STATE_HIDDEN:
		GooglePlayServices_Achievements_Reveal()
	break
	
	case Achievement_STATE_REVEALED:
		
		switch(typeAchievement)
		{
			case Achievement_TYPE_INCREMENTAL:
				GooglePlayServices_Achievements_Increment(ID,1)
			break
	
			case Achievement_TYPE_STANDARD:
				GooglePlayServices_Achievements_Unlock(ID)
			break
		}

	break
	
	case Achievement_STATE_UNLOCKED:
		//Noting... DONE
	break
}
