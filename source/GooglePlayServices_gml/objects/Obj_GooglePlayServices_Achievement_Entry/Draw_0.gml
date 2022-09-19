
draw_set_halign(fa_center)
draw_set_valign(fa_middle)

switch(state)
{
	case Achievement_STATE_HIDDEN:
		
		draw_text(x,y-120,"Hidden")
	
		draw_rectangle(x-100,y-100,x+100,y+100,true)
	break
	
	case Achievement_STATE_REVEALED:
	case Achievement_STATE_UNLOCKED:
	
		draw_text(x,y-120,name)
		
		if(sprite_exists(sprite))
			draw_sprite_stretched(sprite,0,x-100,y-100,200,200)
			
			
		switch(typeAchievement)
		{
			case Achievement_TYPE_INCREMENTAL:
				draw_text(x,y+120,formattedCurrentSteps + "/" + formattedTotalSteps)
				
			break
	
			case Achievement_TYPE_STANDARD:
				
			break
		}

	break
}

