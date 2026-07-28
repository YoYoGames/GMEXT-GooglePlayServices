// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardScoresCodec {
    private PlayServicesLeaderboardScoresCodec()
    {
    }
    public static PlayServicesLeaderboardScores read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        PlayServicesLeaderboard leaderboard = PlayServicesLeaderboardCodec.read(b);

        java.util.List<PlayServicesLeaderboardScore> scores = GMExtWire.readList(b, bb -> PlayServicesLeaderboardScoreCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new PlayServicesLeaderboardScores(success, leaderboard, scores, error);
    }

    public static void write(ByteBuffer b, PlayServicesLeaderboardScores obj)
    {
        GMExtWire.writeBool(b, obj.success());

        PlayServicesLeaderboardCodec.write(b, obj.leaderboard());

        GMExtWire.writeList(b, obj.scores(), (bb, x) -> PlayServicesLeaderboardScoreCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}