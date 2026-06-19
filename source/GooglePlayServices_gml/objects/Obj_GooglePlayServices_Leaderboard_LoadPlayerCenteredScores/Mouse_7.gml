
instance_destroy(Obj_GooglePlayServices_Leaderboard_Entry);

gpgs_leaderboard_load_player_centered_scores(
				Leaderboard1,
				GPGSLeaderboardTimeSpan.AllTime,
				GPGSLeaderboardCollection.Public,
				5,
				true,
				function(success,leaderboard_json,score_array,error){
					if(!is_string(score_array))
						return
	
					var array = json_parse(score_array)
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
			)
