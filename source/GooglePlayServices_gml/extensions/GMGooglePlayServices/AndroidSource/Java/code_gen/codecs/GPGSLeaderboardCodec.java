// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class GPGSLeaderboardCodec {
    private GPGSLeaderboardCodec()
    {
    }
    public static GPGSLeaderboard read(ByteBuffer b)
    {
        String leaderboard_id = GMExtWire.readString(b);

        String display_name = GMExtWire.readString(b);

        double score_order = GMExtWire.readF64(b);

        java.util.List<GPGSLeaderboardVariant> variants = GMExtWire.readList(b, bb -> GPGSLeaderboardVariantCodec.read(bb));

        return new GPGSLeaderboard(leaderboard_id, display_name, score_order, variants);
    }

    public static void write(ByteBuffer b, GPGSLeaderboard obj)
    {
        GMExtWire.writeString(b, obj.leaderboard_id());

        GMExtWire.writeString(b, obj.display_name());

        GMExtWire.writeF64(b, obj.score_order());

        GMExtWire.writeList(b, obj.variants(), (bb, x) -> GPGSLeaderboardVariantCodec.write(bb, x));

    }
}