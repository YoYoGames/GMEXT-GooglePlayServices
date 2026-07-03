// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSTaskResult(boolean success, String value, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 8;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSTaskResultCodec.write(b, this);
    }
}
