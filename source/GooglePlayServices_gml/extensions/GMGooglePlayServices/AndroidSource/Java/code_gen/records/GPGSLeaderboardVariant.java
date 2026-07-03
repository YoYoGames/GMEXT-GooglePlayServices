// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSLeaderboardVariant(double collection, double time_span, boolean has_player_info) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 6;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSLeaderboardVariantCodec.write(b, this);
    }
}
