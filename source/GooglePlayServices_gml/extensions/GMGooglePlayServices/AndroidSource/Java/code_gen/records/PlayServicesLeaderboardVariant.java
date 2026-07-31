// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;
import ${YYAndroidPackageName}.enums.*;

import java.nio.ByteBuffer;

public record PlayServicesLeaderboardVariant(PlayServicesLeaderboardCollection collection, PlayServicesLeaderboardTimeSpan time_span, boolean has_player_info) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 6;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardVariantCodec.write(b, this);
    }
}
