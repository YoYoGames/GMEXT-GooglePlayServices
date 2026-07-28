// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesPlayer(boolean success, PlayServicesPlayerInfo player, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 9;
    @Override
    public void encode(ByteBuffer b)
    {
        PlayServicesPlayerCodec.write(b, this);
    }
}
