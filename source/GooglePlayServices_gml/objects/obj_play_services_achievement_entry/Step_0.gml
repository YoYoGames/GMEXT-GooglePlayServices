/// @description Reveal, increment, or unlock the achievement when clicked.

if (!mouse_check_button_pressed(mb_left))
    exit;

if (!point_in_circle(mouse_x, mouse_y, x, y, 100))
    exit;

switch (state)
{
    case PlayServicesAchievementState.Hidden:
        play_services_achievements_reveal(
            achievement_id,
            function(_status)
            {
                show_debug_message(_status);

                if (!_status.success)
                    return;

                state = PlayServicesAchievementState.Revealed;

                if (!is_undefined(revealed_image_uri))
                {
                    play_services_uri_to_path(
                        revealed_image_uri,
                        play_services_uri_to_path_callback
                    );
                }
            }
        );
    break;

    case PlayServicesAchievementState.Revealed:
        switch (achievement_type)
        {
            case PlayServicesAchievementType.Incremental:
                play_services_achievements_increment(
                    achievement_id,
                    1,
                    function(_status)
                    {
                        show_debug_message(_status);

                        if (!_status.success)
                            return;

                        current_steps = min(
                            current_steps + 1,
                            total_steps
                        );

                        // The immediate API does not return the updated state.
                        // Reflect completion locally when the final step is reached.
                        if (total_steps > 0
                            && current_steps >= total_steps)
                        {
                            state = PlayServicesAchievementState.Unlocked;

                            if (!is_undefined(unlocked_image_uri))
                            {
                                play_services_uri_to_path(
                                    unlocked_image_uri,
                                    play_services_uri_to_path_callback
                                );
                            }
                        }
                    }
                );
            break;

            case PlayServicesAchievementType.Standard:
                play_services_achievements_unlock(
                    achievement_id,
                    function(_status)
                    {
                        show_debug_message(_status);

                        if (!_status.success)
                            return;

                        state = PlayServicesAchievementState.Unlocked;

                        if (!is_undefined(unlocked_image_uri))
                        {
                            play_services_uri_to_path(
                                unlocked_image_uri,
                                play_services_uri_to_path_callback
                            );
                        }
                    }
                );
            break;
        }
    break;

    case PlayServicesAchievementState.Unlocked:
        // Already completed.
    break;
}
