// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesTaskResult(boolean success, String value, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 8;
    @Override
    public void encode(ByteBuffer b)
    {
        PlayServicesTaskResultCodec.write(b, this);
    }
}
