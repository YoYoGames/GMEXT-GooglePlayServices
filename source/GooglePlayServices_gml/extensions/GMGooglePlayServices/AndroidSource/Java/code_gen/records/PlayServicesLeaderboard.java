// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record PlayServicesLeaderboard(String leaderboard_id, String display_name, double score_order, java.util.List<PlayServicesLeaderboardVariant> variants) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 14;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardCodec.write(b, this);
    }
}
