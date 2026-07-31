// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesScoreSubmissionCodec {
    private PlayServicesScoreSubmissionCodec()
    {
    }
    public static PlayServicesScoreSubmission read(ByteBuffer b)
    {
        double raw_score = GMExtWire.readF64(b);

        java.util.Optional<String> formatted_score = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_formatted_score = GMExtWire.readString(b);
            formatted_score = java.util.Optional.of(__opt_formatted_score);
        }

        java.util.Optional<String> score_tag = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_score_tag = GMExtWire.readString(b);
            score_tag = java.util.Optional.of(__opt_score_tag);
        }

        boolean new_best = GMExtWire.readBool(b);

        return new PlayServicesScoreSubmission(raw_score, formatted_score, score_tag, new_best);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesScoreSubmission obj)
    {
        GMExtWire.writeF64(b, obj.raw_score());

        GMExtWire.writeBool(b, obj.formatted_score() != null && obj.formatted_score().isPresent());
        if (obj.formatted_score() != null && obj.formatted_score().isPresent())
        {
            GMExtWire.writeString(b, obj.formatted_score().get());
        }

        GMExtWire.writeBool(b, obj.score_tag() != null && obj.score_tag().isPresent());
        if (obj.score_tag() != null && obj.score_tag().isPresent())
        {
            GMExtWire.writeString(b, obj.score_tag().get());
        }

        GMExtWire.writeBool(b, obj.new_best());

    }
}