/// @description Increment achievement

// This function will work for incremental achievements and allows the developer to 
// increase the user progression on a given achievement.
// It requires you to use the unique achievement id string.
gpgs_achievements_increment(Achievement1, 1,function(success,achievementId,error){
	show_debug_message({success,achievementId,error})
});
