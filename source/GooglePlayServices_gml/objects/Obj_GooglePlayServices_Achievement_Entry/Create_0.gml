
sprite = noone

ID = ""
description = ""
lastUpdatedTimestamp = noone
name = ""
revealedImage = ""
unlockedImage = ""
state = noone
typeAchievement = noone
xpValue = noone

currentSteps = noone
formattedCurrentSteps = ""
formattedTotalSteps = ""
totalSteps = noone

alarm[0] = 1

gpgs_uri_to_path_callback = function(success,uri,error){
	
	if(!success)
		return
	
	// At this point we matched the request id and can load the sprite using the retreived path.
	sprite = sprite_add(uri, 0, 0, 0, 0, 0);
}

