// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record PlayServicesLeaderboardScores(boolean success, PlayServicesLeaderboard leaderboard, java.util.List<PlayServicesLeaderboardScore> scores, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 18;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardScoresCodec.write(b, this);
    }
}
