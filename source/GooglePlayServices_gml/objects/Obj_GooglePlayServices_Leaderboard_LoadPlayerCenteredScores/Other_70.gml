
if(async_load[?"type"] == "GooglePlayServices_Leaderboard_LoadPlayerCenteredScores")
{
	var array = json_parse(async_load[?"data"])
	for(var a = 0 ; a < array_length(array) ; a ++)
	{
		var struct = array[a]
		var ins = instance_create_depth(800,200+a*75,00,Obj_GooglePlayServices_Leaderboard_Entry)
		ins.displayRank = struct.displayRank;
		ins.displayScore = struct.displayScore;
		ins.rank = struct.rank;
		ins.rawScore = struct.rawScore;
		ins.scoreHolder = struct.scoreHolder;
		ins.scoreHolderDisplayName = struct.scoreHolderDisplayName;
		ins.scoreHolderHiResImageUri = struct.scoreHolderHiResImageUri;
		ins.scoreHolderIconImageUri = struct.scoreHolderIconImageUri;
		
		// This is an options parameter and is only present if a scoreTag was provided.
		ins.scoreTag = struct[$ "scoreTag"];
		
		ins.timestampMillis = struct.timestampMillis;
	}
}
