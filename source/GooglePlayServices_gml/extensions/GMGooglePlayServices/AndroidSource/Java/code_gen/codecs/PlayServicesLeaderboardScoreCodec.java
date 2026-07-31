// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardScoreCodec {
    private PlayServicesLeaderboardScoreCodec()
    {
    }
    public static PlayServicesLeaderboardScore read(ByteBuffer b)
    {
        java.util.Optional<String> display_rank = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_display_rank = GMExtWire.readString(b);
            display_rank = java.util.Optional.of(__opt_display_rank);
        }

        java.util.Optional<String> display_score = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_display_score = GMExtWire.readString(b);
            display_score = java.util.Optional.of(__opt_display_score);
        }

        double raw_score = GMExtWire.readF64(b);

        java.util.Optional<String> score_tag = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_score_tag = GMExtWire.readString(b);
            score_tag = java.util.Optional.of(__opt_score_tag);
        }

        double timestamp_millis = GMExtWire.readF64(b);

        java.util.Optional<PlayServicesPlayerInfo> score_holder = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            PlayServicesPlayerInfo __opt_score_holder = PlayServicesPlayerInfoCodec.read(b);
            score_holder = java.util.Optional.of(__opt_score_holder);
        }

        return new PlayServicesLeaderboardScore(display_rank, display_score, raw_score, score_tag, timestamp_millis, score_holder);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesLeaderboardScore obj)
    {
        GMExtWire.writeBool(b, obj.display_rank() != null && obj.display_rank().isPresent());
        if (obj.display_rank() != null && obj.display_rank().isPresent())
        {
            GMExtWire.writeString(b, obj.display_rank().get());
        }

        GMExtWire.writeBool(b, obj.display_score() != null && obj.display_score().isPresent());
        if (obj.display_score() != null && obj.display_score().isPresent())
        {
            GMExtWire.writeString(b, obj.display_score().get());
        }

        GMExtWire.writeF64(b, obj.raw_score());

        GMExtWire.writeBool(b, obj.score_tag() != null && obj.score_tag().isPresent());
        if (obj.score_tag() != null && obj.score_tag().isPresent())
        {
            GMExtWire.writeString(b, obj.score_tag().get());
        }

        GMExtWire.writeF64(b, obj.timestamp_millis());

        GMExtWire.writeBool(b, obj.score_holder() != null && obj.score_holder().isPresent());
        if (obj.score_holder() != null && obj.score_holder().isPresent())
        {
            PlayServicesPlayerInfoCodec.write(b, obj.score_holder().get());
        }

    }
}