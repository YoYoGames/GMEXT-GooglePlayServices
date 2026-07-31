// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesScoreReportInfoCodec {
    private PlayServicesScoreReportInfoCodec()
    {
    }
    public static PlayServicesScoreReportInfo read(ByteBuffer b)
    {
        PlayServicesScoreSubmission daily = PlayServicesScoreSubmissionCodec.read(b);

        PlayServicesScoreSubmission weekly = PlayServicesScoreSubmissionCodec.read(b);

        PlayServicesScoreSubmission all_time = PlayServicesScoreSubmissionCodec.read(b);

        return new PlayServicesScoreReportInfo(daily, weekly, all_time);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesScoreReportInfo obj)
    {
        PlayServicesScoreSubmissionCodec.write(b, obj.daily());

        PlayServicesScoreSubmissionCodec.write(b, obj.weekly());

        PlayServicesScoreSubmissionCodec.write(b, obj.all_time());

    }
}