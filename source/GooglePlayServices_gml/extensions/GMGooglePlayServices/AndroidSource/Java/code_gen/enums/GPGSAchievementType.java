// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSAchievementType
{
    Standard((int)0),
    Incremental((int)1);

    private final int value;
    private GPGSAchievementType(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSAchievementType from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSAchievementType.Standard;
            case 1:
                return GPGSAchievementType.Incremental;
            default:
                throw new IllegalArgumentException("Unknown GPGSAchievementType value: " + v);
        }
    }
}