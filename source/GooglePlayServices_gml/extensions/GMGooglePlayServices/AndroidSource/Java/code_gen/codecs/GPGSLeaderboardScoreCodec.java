// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSLeaderboardScoreCodec {
    private GPGSLeaderboardScoreCodec()
    {
    }
    public static GPGSLeaderboardScore read(ByteBuffer b)
    {
        String display_rank = GMExtWire.readString(b);

        String display_score = GMExtWire.readString(b);

        double raw_score = GMExtWire.readF64(b);

        String score_tag = GMExtWire.readString(b);

        double timestamp_millis = GMExtWire.readF64(b);

        GPGSPlayerInfo score_holder = GPGSPlayerInfoCodec.read(b);

        return new GPGSLeaderboardScore(display_rank, display_score, raw_score, score_tag, timestamp_millis, score_holder);
    }

    public static void write(ByteBuffer b, GPGSLeaderboardScore obj)
    {
        GMExtWire.writeString(b, obj.display_rank());

        GMExtWire.writeString(b, obj.display_score());

        GMExtWire.writeF64(b, obj.raw_score());

        GMExtWire.writeString(b, obj.score_tag());

        GMExtWire.writeF64(b, obj.timestamp_millis());

        GPGSPlayerInfoCodec.write(b, obj.score_holder());

    }
}