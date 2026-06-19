
struct = {}

gpgs_player_stats_load(true,function(success,stats,error){
	struct = json_parse(stats)
})
