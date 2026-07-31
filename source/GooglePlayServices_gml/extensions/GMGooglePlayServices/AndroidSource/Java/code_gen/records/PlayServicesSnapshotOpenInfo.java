// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesSnapshotOpenInfo(boolean is_conflict, java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata, java.util.Optional<String> data, java.util.Optional<String> conflict_id, java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata_local, java.util.Optional<String> data_local, java.util.Optional<PlayServicesSnapshotMetadata> snapshot_metadata_remote, java.util.Optional<String> data_remote) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 11;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesSnapshotOpenInfoCodec.write(b, this);
    }
}
