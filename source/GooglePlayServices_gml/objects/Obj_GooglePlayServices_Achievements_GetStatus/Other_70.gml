
if(async_load[?"type"] == GOOGLE_PLAY_EV_ACHIEVEMENTS_GET_STATUS)
{
	var array = json_parse(async_load[?"data"])
	
	array_sort(array, function(_ach1, _ach2) { return _ach1.name < _ach2.name ? -1 : 1 });
	for(var a = 0 ; a < array_length(array) ; a++)
	{
		var struct = array[a];
		
		show_debug_message(struct);
		
		var ins = instance_create_depth(150+a*300,room_height/2-50,0,Obj_GooglePlayServices_Achievement_Entry)
		ins.ID = struct.id
		ins.description = struct.description
		ins.lastUpdatedTimestamp = struct.lastUpdatedTimestamp
		ins.name = struct.name
		ins.revealedImage = struct.revealedImage
		ins.state = struct.state
		ins.typeAchievement = struct.typeAchievement
		ins.unlockedImage = struct.unlockedImage
		ins.xpValue = struct.xpValue
		
		if(ins.typeAchievement == Achievement_TYPE_INCREMENTAL)
		{
			ins.currentSteps = struct.currentSteps
			ins.formattedCurrentSteps = struct.formattedCurrentSteps
			ins.formattedTotalSteps = struct.formattedTotalSteps
			ins.totalSteps = struct.totalSteps
		}
	}
}
