// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardScoreCodec {
    private PlayServicesLeaderboardScoreCodec()
    {
    }
    public static PlayServicesLeaderboardScore read(ByteBuffer b)
    {
        String display_rank = GMExtWire.readString(b);

        String display_score = GMExtWire.readString(b);

        double raw_score = GMExtWire.readF64(b);

        String score_tag = GMExtWire.readString(b);

        double timestamp_millis = GMExtWire.readF64(b);

        PlayServicesPlayerInfo score_holder = PlayServicesPlayerInfoCodec.read(b);

        return new PlayServicesLeaderboardScore(display_rank, display_score, raw_score, score_tag, timestamp_millis, score_holder);
    }

    public static void write(ByteBuffer b, PlayServicesLeaderboardScore obj)
    {
        GMExtWire.writeString(b, obj.display_rank());

        GMExtWire.writeString(b, obj.display_score());

        GMExtWire.writeF64(b, obj.raw_score());

        GMExtWire.writeString(b, obj.score_tag());

        GMExtWire.writeF64(b, obj.timestamp_millis());

        PlayServicesPlayerInfoCodec.write(b, obj.score_holder());

    }
}