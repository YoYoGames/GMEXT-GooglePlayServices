// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesResultCodec {
    private PlayServicesResultCodec()
    {
    }
    public static PlayServicesResult read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesResult(success, error);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesResult obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeString(b, obj.error());

    }
}