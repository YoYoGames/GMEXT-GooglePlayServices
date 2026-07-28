// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesScoreReport(boolean success, String leaderboard_id, double score, String score_tag, PlayServicesScoreResult daily, PlayServicesScoreResult weekly, PlayServicesScoreResult all_time, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 13;
    @Override
    public void encode(ByteBuffer b)
    {
        PlayServicesScoreReportCodec.write(b, this);
    }
}
