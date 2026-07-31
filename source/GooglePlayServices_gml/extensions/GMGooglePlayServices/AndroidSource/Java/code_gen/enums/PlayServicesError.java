// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.enums;

public enum PlayServicesError
{
    Ok((int)0),
    NotAuthenticated((int)-1),
    ActivityNull((int)-2),
    InvalidArgument((int)-3);

    private final int value;
    private PlayServicesError(int v)
    {
        this.value = v;
    }
    public int value()
    {
        return this.value;
    }
    public static PlayServicesError from(int v)
    {
        switch (v)
        {
            case 0:
                return PlayServicesError.Ok;
            case -1:
                return PlayServicesError.NotAuthenticated;
            case -2:
                return PlayServicesError.ActivityNull;
            case -3:
                return PlayServicesError.InvalidArgument;
            default:
                throw new IllegalArgumentException("Unknown PlayServicesError value: " + v);
        }
    }
}