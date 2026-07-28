// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSnapshotOpenResultCodec {
    private PlayServicesSnapshotOpenResultCodec()
    {
    }
    public static PlayServicesSnapshotOpenResult read(ByteBuffer b)
    {
        boolean is_conflict = GMExtWire.readBool(b);

        PlayServicesSnapshotMetadata snapshot_metadata = PlayServicesSnapshotMetadataCodec.read(b);

        String data = GMExtWire.readString(b);

        String conflict_id = GMExtWire.readString(b);

        PlayServicesSnapshotMetadata snapshot_metadata_local = PlayServicesSnapshotMetadataCodec.read(b);

        String data_local = GMExtWire.readString(b);

        PlayServicesSnapshotMetadata snapshot_metadata_remote = PlayServicesSnapshotMetadataCodec.read(b);

        String data_remote = GMExtWire.readString(b);

        return new PlayServicesSnapshotOpenResult(is_conflict, snapshot_metadata, data, conflict_id, snapshot_metadata_local, data_local, snapshot_metadata_remote, data_remote);
    }

    public static void write(ByteBuffer b, PlayServicesSnapshotOpenResult obj)
    {
        GMExtWire.writeBool(b, obj.is_conflict());

        PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata());

        GMExtWire.writeString(b, obj.data());

        GMExtWire.writeString(b, obj.conflict_id());

        PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata_local());

        GMExtWire.writeString(b, obj.data_local());

        PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata_remote());

        GMExtWire.writeString(b, obj.data_remote());

    }
}