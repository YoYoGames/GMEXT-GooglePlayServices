// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSLeaderboardScore(String display_rank, String display_score, double raw_score, String score_tag, double timestamp_millis, GPGSPlayerInfo score_holder) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 10;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSLeaderboardScoreCodec.write(b, this);
    }
}
