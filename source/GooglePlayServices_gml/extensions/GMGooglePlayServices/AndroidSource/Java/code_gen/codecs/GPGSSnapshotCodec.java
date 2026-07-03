// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSnapshotCodec {
    private GPGSSnapshotCodec()
    {
    }
    public static GPGSSnapshot read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        GPGSSnapshotOpenResult result = GPGSSnapshotOpenResultCodec.read(b);

        String error = GMExtWire.readString(b);

        return new GPGSSnapshot(success, result, error);
    }

    public static void write(ByteBuffer b, GPGSSnapshot obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GPGSSnapshotOpenResultCodec.write(b, obj.result());

        GMExtWire.writeString(b, obj.error());

    }
}