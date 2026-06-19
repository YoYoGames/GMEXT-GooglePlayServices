
if(mouse_check_button_pressed(mb_left))
if(point_in_circle(mouse_x,mouse_y,x,y,100))
switch(state)
{
	case GPGSAchievementState.Hidden:
		gpgs_achievements_reveal(ID,function(success,achievementId,error){
			show_debug_message({success,achievementId,error})
		});
	break
	
	case GPGSAchievementState.Revealed:
		
		switch(typeAchievement)
		{
			case GPGSAchievementType.Incremental:
				gpgs_achievements_increment(ID,1,function(success,achievementId,error){
						show_debug_message({success,achievementId,error})
					});
			break
	
			case GPGSAchievementType.Standard:
				gpgs_achievements_unlock(ID,function(success,achievementId,error){
						show_debug_message({success,achievementId,error})
					});
			break
		}

	break
	
	case GPGSAchievementState.Unlocked:
		//Noting... DONE
	break
}
