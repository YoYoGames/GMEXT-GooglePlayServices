// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSTaskResultCodec {
    private GPGSTaskResultCodec()
    {
    }
    public static GPGSTaskResult read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        String value = GMExtWire.readString(b);

        String error = GMExtWire.readString(b);

        return new GPGSTaskResult(success, value, error);
    }

    public static void write(ByteBuffer b, GPGSTaskResult obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeString(b, obj.value());

        GMExtWire.writeString(b, obj.error());

    }
}