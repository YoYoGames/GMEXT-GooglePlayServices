// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;
import ${YYAndroidPackageName}.enums.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesAchievement(java.util.Optional<String> achievement_id, java.util.Optional<String> name, java.util.Optional<String> description, PlayServicesAchievementState state, PlayServicesAchievementType type, double current_steps, double total_steps, double last_updated_timestamp, double xp_value, java.util.Optional<String> revealed_image_uri, java.util.Optional<String> unlocked_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 4;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesAchievementCodec.write(b, this);
    }
}
