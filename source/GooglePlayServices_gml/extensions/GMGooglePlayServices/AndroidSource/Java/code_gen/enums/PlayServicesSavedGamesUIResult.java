// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesSavedGamesUIResult
{
    Cancelled((int)0),
    Selected((int)1),
    CreatedNew((int)2),
    Deleted((int)3),
    Error((int)-1);

    private final int value;
    private PlayServicesSavedGamesUIResult(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesSavedGamesUIResult from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesSavedGamesUIResult.Cancelled;
            case 1:
                return PlayServicesSavedGamesUIResult.Selected;
            case 2:
                return PlayServicesSavedGamesUIResult.CreatedNew;
            case 3:
                return PlayServicesSavedGamesUIResult.Deleted;
            case -1:
                return PlayServicesSavedGamesUIResult.Error;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesSavedGamesUIResult value: " + v);
        }
    }
}