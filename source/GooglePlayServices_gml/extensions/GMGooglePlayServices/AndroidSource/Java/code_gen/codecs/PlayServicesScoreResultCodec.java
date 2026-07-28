// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesScoreResultCodec {
    private PlayServicesScoreResultCodec()
    {
    }
    public static PlayServicesScoreResult read(ByteBuffer b)
    {
        double raw_score = GMExtWire.readF64(b);

        String formatted_score = GMExtWire.readString(b);

        String score_tag = GMExtWire.readString(b);

        boolean new_best = GMExtWire.readBool(b);

        return new PlayServicesScoreResult(raw_score, formatted_score, score_tag, new_best);
    }

    public static void write(ByteBuffer b, PlayServicesScoreResult obj)
    {
        GMExtWire.writeF64(b, obj.raw_score());

        GMExtWire.writeString(b, obj.formatted_score());

        GMExtWire.writeString(b, obj.score_tag());

        GMExtWire.writeBool(b, obj.new_best());

    }
}