// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record PlayServicesPlayerList(boolean success, java.util.List<PlayServicesPlayerInfo> players, boolean has_more, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 10;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesPlayerListCodec.write(b, this);
    }
}
