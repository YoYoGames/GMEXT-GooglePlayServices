// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSAchievementState
{
    Unlocked((int)0),
    Revealed((int)1),
    Hidden((int)2);

    private final int value;
    private GPGSAchievementState(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSAchievementState from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSAchievementState.Unlocked;
            case 1:
                return GPGSAchievementState.Revealed;
            case 2:
                return GPGSAchievementState.Hidden;
            default:
                throw new IllegalArgumentException("Unknown GPGSAchievementState value: " + v);
        }
    }
}