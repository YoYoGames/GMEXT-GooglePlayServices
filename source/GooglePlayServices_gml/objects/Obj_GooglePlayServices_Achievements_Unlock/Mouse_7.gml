/// @description Unlock achievement

// This function will unlock a new achievement in the achievement board and mark it as cleared
// it requires you to use the unique achievement id string.
gpgs_achievements_unlock(Achievement3,function(success,achievementId,error){
	show_debug_message({success,achievementId,error})
});
