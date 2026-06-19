// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum GPGSSavedGamesDisplayLimit
{
    None((int)-1);

    private final int value;
    private GPGSSavedGamesDisplayLimit(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static GPGSSavedGamesDisplayLimit from(int v)
    {
        switch (v)
        {
            case -1:
                return GPGSSavedGamesDisplayLimit.None;
            default:
                throw new IllegalArgumentException("Unknown GPGSSavedGamesDisplayLimit value: " + v);
        }
    }
}