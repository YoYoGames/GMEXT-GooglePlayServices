// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesLeaderboardScore(String display_rank, String display_score, double raw_score, String score_tag, double timestamp_millis, PlayServicesPlayerInfo score_holder) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 11;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardScoreCodec.write(b, this);
    }
}
