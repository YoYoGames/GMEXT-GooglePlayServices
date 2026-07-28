// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesLeaderboardScoreOrder
{
    SmallerIsBetter((int)0),
    LargerIsBetter((int)1);

    private final int value;
    private PlayServicesLeaderboardScoreOrder(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesLeaderboardScoreOrder from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesLeaderboardScoreOrder.SmallerIsBetter;
            case 1:
                return PlayServicesLeaderboardScoreOrder.LargerIsBetter;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesLeaderboardScoreOrder value: " + v);
        }
    }
}