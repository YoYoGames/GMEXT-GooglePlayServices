// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesSavedGamesUIEvent(double result, PlayServicesSnapshotMetadata snapshot_metadata, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 17;
    @Override
    public void encode(ByteBuffer b)
    {
        PlayServicesSavedGamesUIEventCodec.write(b, this);
    }
}
