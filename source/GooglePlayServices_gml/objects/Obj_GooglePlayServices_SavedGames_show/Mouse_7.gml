/// @description Show Google Play Saved Games UI

if (locked) exit;

gpgs_saved_games_show_saved_games_ui(
    "YoYo Games Saved Games",
    true,
    true,
    3,
    function(_result)
    {
        show_debug_message(_result);

        switch (_result.result)
        {
            case GPGSSavedGamesUIResult.Selected:
                var _metadata = _result.snapshot_metadata;
                gpgs_saved_games_open(_metadata.unique_name, function(_open_result)
                {
                    if (!_open_result.success)
                        show_debug_message(_open_result.error);
					else
						Obj_GooglePlayServices_SavedGames_Slot.slot_open(_open_result.result.snapshot_metadata.unique_name )
                });
                break;

            case GPGSSavedGamesUIResult.CreatedNew:
                with (Obj_GooglePlayServices_SavedGames)
                    dialog_ind = get_string_async("Description: ", "Slot #0");
                break;

            case GPGSSavedGamesUIResult.Deleted:
            case GPGSSavedGamesUIResult.Cancelled:
                with (Obj_GooglePlayServices_SavedGames)
                    gpgs_saved_games_load(true, callback_saved_games_load);
                break;

            case GPGSSavedGamesUIResult.Error:
                show_debug_message(_result.error);
                break;
        }
    }
);
