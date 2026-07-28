// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record GPGSLeaderboardScores(boolean success, GPGSLeaderboard leaderboard, java.util.List<GPGSLeaderboardScore> scores, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 18;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSLeaderboardScoresCodec.write(b, this);
    }
}
