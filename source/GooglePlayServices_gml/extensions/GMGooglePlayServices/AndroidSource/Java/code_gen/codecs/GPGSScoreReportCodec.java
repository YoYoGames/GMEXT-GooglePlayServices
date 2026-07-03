// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSScoreReportCodec {
    private GPGSScoreReportCodec()
    {
    }
    public static GPGSScoreReport read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        String leaderboard_id = GMExtWire.readString(b);

        double score = GMExtWire.readF64(b);

        String score_tag = GMExtWire.readString(b);

        GPGSScoreResult daily = GPGSScoreResultCodec.read(b);

        GPGSScoreResult weekly = GPGSScoreResultCodec.read(b);

        GPGSScoreResult all_time = GPGSScoreResultCodec.read(b);

        String error = GMExtWire.readString(b);

        return new GPGSScoreReport(success, leaderboard_id, score, score_tag, daily, weekly, all_time, error);
    }

    public static void write(ByteBuffer b, GPGSScoreReport obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeString(b, obj.leaderboard_id());

        GMExtWire.writeF64(b, obj.score());

        GMExtWire.writeString(b, obj.score_tag());

        GPGSScoreResultCodec.write(b, obj.daily());

        GPGSScoreResultCodec.write(b, obj.weekly());

        GPGSScoreResultCodec.write(b, obj.all_time());

        GMExtWire.writeString(b, obj.error());

    }
}