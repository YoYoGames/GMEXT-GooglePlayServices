/// @description Reveal achievement

// This function will reveal a hidden achievement in the achievement board.
// it requires you to use the unique achievement id string.
gpgs_achievements_reveal(Achievement2,function(success,achievementId,error){
	show_debug_message({success,achievementId,error})
});
