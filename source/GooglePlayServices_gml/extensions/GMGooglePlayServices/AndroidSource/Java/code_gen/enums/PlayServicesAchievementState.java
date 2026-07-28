// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesAchievementState
{
    Unlocked((int)0),
    Revealed((int)1),
    Hidden((int)2);

    private final int value;
    private PlayServicesAchievementState(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesAchievementState from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesAchievementState.Unlocked;
            case 1:
                return PlayServicesAchievementState.Revealed;
            case 2:
                return PlayServicesAchievementState.Hidden;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesAchievementState value: " + v);
        }
    }
}