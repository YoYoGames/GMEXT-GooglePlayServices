// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;
import ${YYAndroidPackageName}.enums.*;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.List;

public record PlayServicesLeaderboard(java.util.Optional<String> leaderboard_id, java.util.Optional<String> display_name, PlayServicesLeaderboardScoreOrder score_order, java.util.List<PlayServicesLeaderboardVariant> variants) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 10;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesLeaderboardCodec.write(b, this);
    }
}
