// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.List;

public record PlayServicesAchievementList(boolean success, java.util.List<PlayServicesAchievement> achievements, String error) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 12;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesAchievementListCodec.write(b, this);
    }
}
