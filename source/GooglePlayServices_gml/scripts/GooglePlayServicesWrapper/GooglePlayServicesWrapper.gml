

function GooglePlayServices_SavedGames_CommitAndClose(			name,
			desc = "",
			data = "",
			coverImagePath = "",
			playedTimeMillis = noone,
			progressValue = noone)
{
	
	var struct = 
	{
			name: name,
			desc: desc,
			data: data,
			coverImagePath: coverImagePath,
			playedTimeMillis: playedTimeMillis,
			progressValue: progressValue
	}
	
	return __GooglePlayServices_SavedGames_CommitAndClose(json_stringify(struct))
}


function GooglePlayServices_SavedGames_CommitNew(
			name,
			desc = "",
			data = "",
			coverImagePath = "",
			playedTimeMillis = noone,
			progressValue = noone){
	
	var struct = 
	{
			name: name,
			desc: desc,
			data: data,
			coverImagePath: coverImagePath,
			playedTimeMillis: playedTimeMillis,
			progressValue: progressValue
	}
	
	return __GooglePlayServices_SavedGames_CommitNew(json_stringify(struct))
}
