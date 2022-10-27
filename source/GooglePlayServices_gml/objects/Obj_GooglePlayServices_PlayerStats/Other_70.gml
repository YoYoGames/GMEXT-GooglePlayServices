
if(async_load[?"type"] == "GooglePlayServices_PlayerStats_LoadPlayerStats")
if(async_load[?"success"])
{
	ds_map_delete(async_load,"success")
	ds_map_delete(async_load,"type")
	struct = json_parse(json_encode(async_load))
}
