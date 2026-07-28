// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record GPGSSnapshotMetadataList(boolean success, java.util.List<GPGSSnapshotMetadata> snapshots, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 15;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSSnapshotMetadataListCodec.write(b, this);
    }
}
