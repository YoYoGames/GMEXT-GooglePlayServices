/// @description Show Google Play Saved Games UI

if (locked) exit;

play_services_saved_games_show_saved_games_ui(
    "YoYo Games Saved Games",
    true,
    true,
    3,
    function(_result)
    {
        show_debug_message(_result);

        switch (_result.result)
        {
            case PlayServicesSavedGamesUIResult.Selected:
                var _metadata = _result.snapshot_metadata;
                play_services_saved_games_open(_metadata.unique_name, function(_open_result)
                {
                    if (!_open_result.success)
                        show_debug_message(_open_result.error);
					else
						obj_play_services_saved_games_slot.slot_open(_open_result.result.snapshot_metadata.unique_name )
                });
                break;

            case PlayServicesSavedGamesUIResult.CreatedNew:
                with (obj_play_services_saved_games)
                    dialog_ind = get_string_async("Description: ", "Slot #0");
                break;

            case PlayServicesSavedGamesUIResult.Deleted:
            case PlayServicesSavedGamesUIResult.Cancelled:
                with (obj_play_services_saved_games)
                    play_services_saved_games_load(true, callback_saved_games_load);
                break;

            case PlayServicesSavedGamesUIResult.Error:
                show_debug_message(_result.error);
                break;
        }
    }
);
