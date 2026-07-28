// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesSnapshotOpenResult(boolean is_conflict, PlayServicesSnapshotMetadata snapshot_metadata, String data, String conflict_id, PlayServicesSnapshotMetadata snapshot_metadata_local, String data_local, PlayServicesSnapshotMetadata snapshot_metadata_remote, String data_remote) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 16;
    @Override
    public void encode(ByteBuffer b)
    {
        PlayServicesSnapshotOpenResultCodec.write(b, this);
    }
}
