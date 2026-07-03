// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSAuthResultCodec {
    private GPGSAuthResultCodec()
    {
    }
    public static GPGSAuthResult read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        boolean is_authenticated = GMExtWire.readBool(b);

        String error = GMExtWire.readString(b);

        return new GPGSAuthResult(success, is_authenticated, error);
    }

    public static void write(ByteBuffer b, GPGSAuthResult obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeBool(b, obj.is_authenticated());

        GMExtWire.writeString(b, obj.error());

    }
}