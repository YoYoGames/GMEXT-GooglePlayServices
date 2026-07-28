// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardCodec {
    private PlayServicesLeaderboardCodec()
    {
    }
    public static PlayServicesLeaderboard read(ByteBuffer b)
    {
        String leaderboard_id = GMExtWire.readString(b);

        String display_name = GMExtWire.readString(b);

        double score_order = GMExtWire.readF64(b);

        java.util.List<PlayServicesLeaderboardVariant> variants = GMExtWire.readList(b, bb -> PlayServicesLeaderboardVariantCodec.read(bb));

        return new PlayServicesLeaderboard(leaderboard_id, display_name, score_order, variants);
    }

    public static void write(ByteBuffer b, PlayServicesLeaderboard obj)
    {
        GMExtWire.writeString(b, obj.leaderboard_id());

        GMExtWire.writeString(b, obj.display_name());

        GMExtWire.writeF64(b, obj.score_order());

        GMExtWire.writeList(b, obj.variants(), (bb, x) -> PlayServicesLeaderboardVariantCodec.write(bb, x));

    }
}