// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSnapshotMetadataListCodec {
    private PlayServicesSnapshotMetadataListCodec()
    {
    }
    public static PlayServicesSnapshotMetadataList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<PlayServicesSnapshotMetadata> snapshots = GMExtWire.readList(b, bb -> PlayServicesSnapshotMetadataCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new PlayServicesSnapshotMetadataList(success, snapshots, error);
    }

    public static void write(ByteBuffer b, PlayServicesSnapshotMetadataList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.snapshots(), (bb, x) -> PlayServicesSnapshotMetadataCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}