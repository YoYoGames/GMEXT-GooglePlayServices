// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import java.util.List;
import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardCodec {
    private PlayServicesLeaderboardCodec()
    {
    }
    public static PlayServicesLeaderboard read(ByteBuffer b)
    {
        java.util.Optional<String> leaderboard_id = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_leaderboard_id = GMExtWire.readString(b);
            leaderboard_id = java.util.Optional.of(__opt_leaderboard_id);
        }

        java.util.Optional<String> display_name = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_display_name = GMExtWire.readString(b);
            display_name = java.util.Optional.of(__opt_display_name);
        }

        PlayServicesLeaderboardScoreOrder score_order = PlayServicesLeaderboardScoreOrder.from(GMExtWire.readI32(b));

        java.util.List<PlayServicesLeaderboardVariant> variants = GMExtWire.readList(b, bb -> PlayServicesLeaderboardVariantCodec.read(bb));

        return new PlayServicesLeaderboard(leaderboard_id, display_name, score_order, variants);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesLeaderboard obj)
    {
        GMExtWire.writeBool(b, obj.leaderboard_id() != null && obj.leaderboard_id().isPresent());
        if (obj.leaderboard_id() != null && obj.leaderboard_id().isPresent())
        {
            GMExtWire.writeString(b, obj.leaderboard_id().get());
        }

        GMExtWire.writeBool(b, obj.display_name() != null && obj.display_name().isPresent());
        if (obj.display_name() != null && obj.display_name().isPresent())
        {
            GMExtWire.writeString(b, obj.display_name().get());
        }

        GMExtWire.writeI32(b, obj.score_order().value());

        GMExtWire.writeList(b, obj.variants(), (bb, x) -> PlayServicesLeaderboardVariantCodec.write(bb, x));

    }
}