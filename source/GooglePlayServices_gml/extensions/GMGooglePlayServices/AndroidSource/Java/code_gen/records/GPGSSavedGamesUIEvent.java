// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSSavedGamesUIEvent(double result, GPGSSnapshotMetadata snapshot_metadata, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 16;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSSavedGamesUIEventCodec.write(b, this);
    }
}
