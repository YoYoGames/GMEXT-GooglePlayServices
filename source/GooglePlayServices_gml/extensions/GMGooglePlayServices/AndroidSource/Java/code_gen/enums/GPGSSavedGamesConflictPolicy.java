// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSSavedGamesConflictPolicy
{
    Manual((int)-1),
    LongestPlaytime((int)1),
    LastKnownGood((int)2),
    MostRecentlyModified((int)3),
    HighestProgress((int)4);

    private final int value;
    private GPGSSavedGamesConflictPolicy(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSSavedGamesConflictPolicy from(int v)
    {
        switch (v)
        {
            case -1:
                return GPGSSavedGamesConflictPolicy.Manual;
            case 1:
                return GPGSSavedGamesConflictPolicy.LongestPlaytime;
            case 2:
                return GPGSSavedGamesConflictPolicy.LastKnownGood;
            case 3:
                return GPGSSavedGamesConflictPolicy.MostRecentlyModified;
            case 4:
                return GPGSSavedGamesConflictPolicy.HighestProgress;
            default:
                throw new IllegalArgumentException("Unknown GPGSSavedGamesConflictPolicy value: " + v);
        }
    }
}