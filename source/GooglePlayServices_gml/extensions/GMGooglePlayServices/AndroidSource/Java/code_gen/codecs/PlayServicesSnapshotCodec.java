// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSnapshotCodec {
    private PlayServicesSnapshotCodec()
    {
    }
    public static PlayServicesSnapshot read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        PlayServicesSnapshotOpenResult result = PlayServicesSnapshotOpenResultCodec.read(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesSnapshot(success, result, error);
    }

    public static void write(ByteBuffer b, PlayServicesSnapshot obj)
    {
        GMExtWire.writeBool(b, obj.success());

        PlayServicesSnapshotOpenResultCodec.write(b, obj.result());

        GMExtWire.writeString(b, obj.error());

    }
}