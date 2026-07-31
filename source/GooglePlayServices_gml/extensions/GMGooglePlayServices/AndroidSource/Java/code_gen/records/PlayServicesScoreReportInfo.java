// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesScoreReportInfo(PlayServicesScoreSubmission daily, PlayServicesScoreSubmission weekly, PlayServicesScoreSubmission all_time) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 9;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesScoreReportInfoCodec.write(b, this);
    }
}
