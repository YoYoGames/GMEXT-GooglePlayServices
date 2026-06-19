// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSLeaderboardScoreOrder
{
    SmallerIsBetter((int)0),
    LargerIsBetter((int)1);

    private final int value;
    private GPGSLeaderboardScoreOrder(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSLeaderboardScoreOrder from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSLeaderboardScoreOrder.SmallerIsBetter;
            case 1:
                return GPGSLeaderboardScoreOrder.LargerIsBetter;
            default:
                throw new IllegalArgumentException("Unknown GPGSLeaderboardScoreOrder value: " + v);
        }
    }
}