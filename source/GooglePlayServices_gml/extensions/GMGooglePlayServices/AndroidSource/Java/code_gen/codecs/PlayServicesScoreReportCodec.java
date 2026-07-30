// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesScoreReportCodec {
    private PlayServicesScoreReportCodec()
    {
    }
    public static PlayServicesScoreReport read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        String leaderboard_id = GMExtWire.readString(b);

        double score = GMExtWire.readF64(b);

        String score_tag = GMExtWire.readString(b);

        PlayServicesScoreResult daily = PlayServicesScoreResultCodec.read(b);

        PlayServicesScoreResult weekly = PlayServicesScoreResultCodec.read(b);

        PlayServicesScoreResult all_time = PlayServicesScoreResultCodec.read(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesScoreReport(success, leaderboard_id, score, score_tag, daily, weekly, all_time, error);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesScoreReport obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeString(b, obj.leaderboard_id());

        GMExtWire.writeF64(b, obj.score());

        GMExtWire.writeString(b, obj.score_tag());

        PlayServicesScoreResultCodec.write(b, obj.daily());

        PlayServicesScoreResultCodec.write(b, obj.weekly());

        PlayServicesScoreResultCodec.write(b, obj.all_time());

        GMExtWire.writeString(b, obj.error());

    }
}