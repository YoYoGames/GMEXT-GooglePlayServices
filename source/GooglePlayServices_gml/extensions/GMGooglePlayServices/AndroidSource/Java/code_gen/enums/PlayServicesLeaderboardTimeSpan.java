// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesLeaderboardTimeSpan
{
    Daily((int)0),
    Weekly((int)1),
    AllTime((int)2);

    private final int value;
    private PlayServicesLeaderboardTimeSpan(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesLeaderboardTimeSpan from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesLeaderboardTimeSpan.Daily;
            case 1:
                return PlayServicesLeaderboardTimeSpan.Weekly;
            case 2:
                return PlayServicesLeaderboardTimeSpan.AllTime;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesLeaderboardTimeSpan value: " + v);
        }
    }
}