
if(async_load[?"type"] == GOOGLE_PLAY_EV_PLAYERSTATS_LOAD)
if(async_load[?"success"])
{
	ds_map_delete(async_load,"success")
	ds_map_delete(async_load,"type")
	struct = json_parse(json_encode(async_load))
}
