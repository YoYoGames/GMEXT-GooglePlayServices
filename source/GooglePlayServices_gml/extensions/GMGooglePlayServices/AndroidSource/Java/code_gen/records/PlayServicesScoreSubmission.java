// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesScoreSubmission(double raw_score, java.util.Optional<String> formatted_score, java.util.Optional<String> score_tag, boolean new_best) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 5;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesScoreSubmissionCodec.write(b, this);
    }
}
