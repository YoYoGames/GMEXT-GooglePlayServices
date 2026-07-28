// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesLeaderboardCollection
{
    Public((int)0),
    Friends((int)3);

    private final int value;
    private PlayServicesLeaderboardCollection(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesLeaderboardCollection from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesLeaderboardCollection.Public;
            case 3:
                return PlayServicesLeaderboardCollection.Friends;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesLeaderboardCollection value: " + v);
        }
    }
}