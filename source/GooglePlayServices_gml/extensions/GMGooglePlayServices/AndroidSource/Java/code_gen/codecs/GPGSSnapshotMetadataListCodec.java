// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSnapshotMetadataListCodec {
    private GPGSSnapshotMetadataListCodec()
    {
    }
    public static GPGSSnapshotMetadataList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<GPGSSnapshotMetadata> snapshots = GMExtWire.readList(b, bb -> GPGSSnapshotMetadataCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new GPGSSnapshotMetadataList(success, snapshots, error);
    }

    public static void write(ByteBuffer b, GPGSSnapshotMetadataList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.snapshots(), (bb, x) -> GPGSSnapshotMetadataCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}