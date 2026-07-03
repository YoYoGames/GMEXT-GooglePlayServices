// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSnapshotOpenResultCodec {
    private GPGSSnapshotOpenResultCodec()
    {
    }
    public static GPGSSnapshotOpenResult read(ByteBuffer b)
    {
        boolean is_conflict = GMExtWire.readBool(b);

        GPGSSnapshotMetadata snapshot_metadata = GPGSSnapshotMetadataCodec.read(b);

        String data = GMExtWire.readString(b);

        String conflict_id = GMExtWire.readString(b);

        GPGSSnapshotMetadata snapshot_metadata_local = GPGSSnapshotMetadataCodec.read(b);

        String data_local = GMExtWire.readString(b);

        GPGSSnapshotMetadata snapshot_metadata_remote = GPGSSnapshotMetadataCodec.read(b);

        String data_remote = GMExtWire.readString(b);

        return new GPGSSnapshotOpenResult(is_conflict, snapshot_metadata, data, conflict_id, snapshot_metadata_local, data_local, snapshot_metadata_remote, data_remote);
    }

    public static void write(ByteBuffer b, GPGSSnapshotOpenResult obj)
    {
        GMExtWire.writeBool(b, obj.is_conflict());

        GPGSSnapshotMetadataCodec.write(b, obj.snapshot_metadata());

        GMExtWire.writeString(b, obj.data());

        GMExtWire.writeString(b, obj.conflict_id());

        GPGSSnapshotMetadataCodec.write(b, obj.snapshot_metadata_local());

        GMExtWire.writeString(b, obj.data_local());

        GPGSSnapshotMetadataCodec.write(b, obj.snapshot_metadata_remote());

        GMExtWire.writeString(b, obj.data_remote());

    }
}