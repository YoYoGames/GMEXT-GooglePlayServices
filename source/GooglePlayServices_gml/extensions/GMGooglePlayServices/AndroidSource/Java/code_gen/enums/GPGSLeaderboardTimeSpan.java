// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSLeaderboardTimeSpan
{
    Daily((int)0),
    Weekly((int)1),
    AllTime((int)2);

    private final int value;
    private GPGSLeaderboardTimeSpan(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSLeaderboardTimeSpan from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSLeaderboardTimeSpan.Daily;
            case 1:
                return GPGSLeaderboardTimeSpan.Weekly;
            case 2:
                return GPGSLeaderboardTimeSpan.AllTime;
            default:
                throw new IllegalArgumentException("Unknown GPGSLeaderboardTimeSpan value: " + v);
        }
    }
}