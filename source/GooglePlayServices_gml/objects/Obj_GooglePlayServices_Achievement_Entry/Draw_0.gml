
draw_set_halign(fa_center)
draw_set_valign(fa_middle)

switch(state)
{
	case GPGSAchievementState.Hidden:
		
		draw_text(x,y-120,"Hidden")
	
		draw_rectangle(x-100,y-100,x+100,y+100,true)
	break
	
	case GPGSAchievementState.Revealed:
	case GPGSAchievementState.Unlocked:
	
		draw_text(x,y-120,name)
		
		if(sprite_exists(sprite))
			draw_sprite_stretched(sprite,0,x-100,y-100,200,200)
			
			
		switch(typeAchievement)
		{
			case GPGSAchievementType.Incremental:
				draw_text(x,y+120,formattedCurrentSteps + "/" + formattedTotalSteps)
				
			break
	
			case GPGSAchievementType.Standard:
				
			break
		}

	break
}

