// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesSavedGamesConflictPolicy
{
    Manual((int)-1),
    LongestPlaytime((int)1),
    LastKnownGood((int)2),
    MostRecentlyModified((int)3),
    HighestProgress((int)4);

    private final int value;
    private PlayServicesSavedGamesConflictPolicy(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesSavedGamesConflictPolicy from(int v)
    {
        switch (v)
        {
            case -1:
                return PlayServicesSavedGamesConflictPolicy.Manual;
            case 1:
                return PlayServicesSavedGamesConflictPolicy.LongestPlaytime;
            case 2:
                return PlayServicesSavedGamesConflictPolicy.LastKnownGood;
            case 3:
                return PlayServicesSavedGamesConflictPolicy.MostRecentlyModified;
            case 4:
                return PlayServicesSavedGamesConflictPolicy.HighestProgress;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesSavedGamesConflictPolicy value: " + v);
        }
    }
}