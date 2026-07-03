// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSPlayer(boolean success, GPGSPlayerInfo player, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 9;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSPlayerCodec.write(b, this);
    }
}
