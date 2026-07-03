// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSAchievement(String achievement_id, String name, String description, double state, double type, double current_steps, double total_steps, double last_updated_timestamp, double xp_value, String revealed_image_uri, String unlocked_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 4;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSAchievementCodec.write(b, this);
    }
}
