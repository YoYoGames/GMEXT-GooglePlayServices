// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesAchievementType
{
    Standard((int)0),
    Incremental((int)1);

    private final int value;
    private PlayServicesAchievementType(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesAchievementType from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesAchievementType.Standard;
            case 1:
                return PlayServicesAchievementType.Incremental;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesAchievementType value: " + v);
        }
    }
}