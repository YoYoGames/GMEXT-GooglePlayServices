// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSLeaderboardCollection
{
    Public((int)0),
    Friends((int)3);

    private final int value;
    private GPGSLeaderboardCollection(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSLeaderboardCollection from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSLeaderboardCollection.Public;
            case 3:
                return GPGSLeaderboardCollection.Friends;
            default:
                throw new IllegalArgumentException("Unknown GPGSLeaderboardCollection value: " + v);
        }
    }
}