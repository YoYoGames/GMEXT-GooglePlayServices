// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSnapshotOpenInfoCodec {
    private PlayServicesSnapshotOpenInfoCodec()
    {
    }
    public static PlayServicesSnapshotOpenInfo read(ByteBuffer b)
    {
        boolean is_conflict = GMExtWire.readBool(b);

        java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            PlayServicesSnapshotMetadata __opt_snapshot_metadata = PlayServicesSnapshotMetadataCodec.read(b);
            snapshot_metadata = java.util.Optional.of(__opt_snapshot_metadata);
        }

        java.util.Optional<String> data = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_data = GMExtWire.readString(b);
            data = java.util.Optional.of(__opt_data);
        }

        java.util.Optional<String> conflict_id = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_conflict_id = GMExtWire.readString(b);
            conflict_id = java.util.Optional.of(__opt_conflict_id);
        }

        java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata_local = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            PlayServicesSnapshotMetadata __opt_snapshot_metadata_local = PlayServicesSnapshotMetadataCodec.read(b);
            snapshot_metadata_local = java.util.Optional.of(__opt_snapshot_metadata_local);
        }

        java.util.Optional<String> data_local = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_data_local = GMExtWire.readString(b);
            data_local = java.util.Optional.of(__opt_data_local);
        }

        java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata_remote = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            PlayServicesSnapshotMetadata __opt_snapshot_metadata_remote = PlayServicesSnapshotMetadataCodec.read(b);
            snapshot_metadata_remote = java.util.Optional.of(__opt_snapshot_metadata_remote);
        }

        java.util.Optional<String> data_remote = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_data_remote = GMExtWire.readString(b);
            data_remote = java.util.Optional.of(__opt_data_remote);
        }

        return new PlayServicesSnapshotOpenInfo(is_conflict, snapshot_metadata, data, conflict_id, snapshot_metadata_local, data_local, snapshot_metadata_remote, data_remote);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesSnapshotOpenInfo obj)
    {
        GMExtWire.writeBool(b, obj.is_conflict());

        GMExtWire.writeBool(b, obj.snapshot_metadata() != null && obj.snapshot_metadata().isPresent());
        if (obj.snapshot_metadata() != null && obj.snapshot_metadata().isPresent())
        {
            PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata().get());
        }

        GMExtWire.writeBool(b, obj.data() != null && obj.data().isPresent());
        if (obj.data() != null && obj.data().isPresent())
        {
            GMExtWire.writeString(b, obj.data().get());
        }

        GMExtWire.writeBool(b, obj.conflict_id() != null && obj.conflict_id().isPresent());
        if (obj.conflict_id() != null && obj.conflict_id().isPresent())
        {
            GMExtWire.writeString(b, obj.conflict_id().get());
        }

        GMExtWire.writeBool(b, obj.snapshot_metadata_local() != null && obj.snapshot_metadata_local().isPresent());
        if (obj.snapshot_metadata_local() != null && obj.snapshot_metadata_local().isPresent())
        {
            PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata_local().get());
        }

        GMExtWire.writeBool(b, obj.data_local() != null && obj.data_local().isPresent());
        if (obj.data_local() != null && obj.data_local().isPresent())
        {
            GMExtWire.writeString(b, obj.data_local().get());
        }

        GMExtWire.writeBool(b, obj.snapshot_metadata_remote() != null && obj.snapshot_metadata_remote().isPresent());
        if (obj.snapshot_metadata_remote() != null && obj.snapshot_metadata_remote().isPresent())
        {
            PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata_remote().get());
        }

        GMExtWire.writeBool(b, obj.data_remote() != null && obj.data_remote().isPresent());
        if (obj.data_remote() != null && obj.data_remote().isPresent())
        {
            GMExtWire.writeString(b, obj.data_remote().get());
        }

    }
}