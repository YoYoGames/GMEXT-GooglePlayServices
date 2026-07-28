// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSSnapshot(boolean success, GPGSSnapshotOpenResult result, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 19;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSSnapshotCodec.write(b, this);
    }
}
