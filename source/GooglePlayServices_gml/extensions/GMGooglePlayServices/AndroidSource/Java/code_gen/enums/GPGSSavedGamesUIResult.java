// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSSavedGamesUIResult
{
    Cancelled((int)0),
    Selected((int)1),
    CreatedNew((int)2),
    Deleted((int)3),
    Error((int)-1);

    private final int value;
    private GPGSSavedGamesUIResult(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSSavedGamesUIResult from(int v)
    {
        switch (v)
        {
            case 0:
                return GPGSSavedGamesUIResult.Cancelled;
            case 1:
                return GPGSSavedGamesUIResult.Selected;
            case 2:
                return GPGSSavedGamesUIResult.CreatedNew;
            case 3:
                return GPGSSavedGamesUIResult.Deleted;
            case -1:
                return GPGSSavedGamesUIResult.Error;
            default:
                throw new IllegalArgumentException("Unknown GPGSSavedGamesUIResult value: " + v);
        }
    }
}