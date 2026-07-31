// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesLeaderboardScore(java.util.Optional<String> display_rank, java.util.Optional<String> display_score, double raw_score, java.util.Optional<String> score_tag, double timestamp_millis, java.util.Optional<PlayServicesPlayerInfo> score_holder) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 8;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardScoreCodec.write(b, this);
    }
}
