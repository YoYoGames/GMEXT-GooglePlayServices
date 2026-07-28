// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSSnapshotOpenResult(boolean is_conflict, GPGSSnapshotMetadata snapshot_metadata, String data, String conflict_id, GPGSSnapshotMetadata snapshot_metadata_local, String data_local, GPGSSnapshotMetadata snapshot_metadata_remote, String data_remote) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 16;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSSnapshotOpenResultCodec.write(b, this);
    }
}
