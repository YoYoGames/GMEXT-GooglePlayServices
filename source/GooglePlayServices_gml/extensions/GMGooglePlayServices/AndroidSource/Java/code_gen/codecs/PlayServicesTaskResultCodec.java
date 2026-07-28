// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesTaskResultCodec {
    private PlayServicesTaskResultCodec()
    {
    }
    public static PlayServicesTaskResult read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        String value = GMExtWire.readString(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesTaskResult(success, value, error);
    }

    public static void write(ByteBuffer b, PlayServicesTaskResult obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeString(b, obj.value());

        GMExtWire.writeString(b, obj.error());

    }
}