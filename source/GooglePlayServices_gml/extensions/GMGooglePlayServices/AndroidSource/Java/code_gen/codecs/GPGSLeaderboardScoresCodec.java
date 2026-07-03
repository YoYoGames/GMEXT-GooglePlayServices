// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class GPGSLeaderboardScoresCodec {
    private GPGSLeaderboardScoresCodec()
    {
    }
    public static GPGSLeaderboardScores read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        GPGSLeaderboard leaderboard = GPGSLeaderboardCodec.read(b);

        java.util.List<GPGSLeaderboardScore> scores = GMExtWire.readList(b, bb -> GPGSLeaderboardScoreCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new GPGSLeaderboardScores(success, leaderboard, scores, error);
    }

    public static void write(ByteBuffer b, GPGSLeaderboardScores obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GPGSLeaderboardCodec.write(b, obj.leaderboard());

        GMExtWire.writeList(b, obj.scores(), (bb, x) -> GPGSLeaderboardScoreCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}