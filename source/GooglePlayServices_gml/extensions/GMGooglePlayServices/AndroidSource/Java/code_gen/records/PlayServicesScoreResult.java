// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesScoreResult(double raw_score, String formatted_score, String score_tag, boolean new_best) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 5;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesScoreResultCodec.write(b, this);
    }
}
