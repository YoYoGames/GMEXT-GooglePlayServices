// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record GPGSAchievementList(boolean success, java.util.List<GPGSAchievement> achievements, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 11;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSAchievementListCodec.write(b, this);
    }
}
