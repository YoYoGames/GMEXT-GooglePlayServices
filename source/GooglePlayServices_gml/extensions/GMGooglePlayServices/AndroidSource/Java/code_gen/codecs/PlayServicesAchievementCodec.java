// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesAchievementCodec {
    private PlayServicesAchievementCodec()
    {
    }
    public static PlayServicesAchievement read(ByteBuffer b)
    {
        java.util.Optional<String> achievement_id = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_achievement_id = GMExtWire.readString(b);
            achievement_id = java.util.Optional.of(__opt_achievement_id);
        }

        java.util.Optional<String> name = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_name = GMExtWire.readString(b);
            name = java.util.Optional.of(__opt_name);
        }

        java.util.Optional<String> description = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_description = GMExtWire.readString(b);
            description = java.util.Optional.of(__opt_description);
        }

        PlayServicesAchievementState state = PlayServicesAchievementState.from(GMExtWire.readI32(b));

        PlayServicesAchievementType type = PlayServicesAchievementType.from(GMExtWire.readI32(b));

        double current_steps = GMExtWire.readF64(b);

        double total_steps = GMExtWire.readF64(b);

        double last_updated_timestamp = GMExtWire.readF64(b);

        double xp_value = GMExtWire.readF64(b);

        java.util.Optional<String> revealed_image_uri = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_revealed_image_uri = GMExtWire.readString(b);
            revealed_image_uri = java.util.Optional.of(__opt_revealed_image_uri);
        }

        java.util.Optional<String> unlocked_image_uri = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_unlocked_image_uri = GMExtWire.readString(b);
            unlocked_image_uri = java.util.Optional.of(__opt_unlocked_image_uri);
        }

        return new PlayServicesAchievement(achievement_id, name, description, state, type, current_steps, total_steps, last_updated_timestamp, xp_value, revealed_image_uri, unlocked_image_uri);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesAchievement obj)
    {
        GMExtWire.writeBool(b, obj.achievement_id() != null && obj.achievement_id().isPresent());
        if (obj.achievement_id() != null && obj.achievement_id().isPresent())
        {
            GMExtWire.writeString(b, obj.achievement_id().get());
        }

        GMExtWire.writeBool(b, obj.name() != null && obj.name().isPresent());
        if (obj.name() != null && obj.name().isPresent())
        {
            GMExtWire.writeString(b, obj.name().get());
        }

        GMExtWire.writeBool(b, obj.description() != null && obj.description().isPresent());
        if (obj.description() != null && obj.description().isPresent())
        {
            GMExtWire.writeString(b, obj.description().get());
        }

        GMExtWire.writeI32(b, obj.state().value());

        GMExtWire.writeI32(b, obj.type().value());

        GMExtWire.writeF64(b, obj.current_steps());

        GMExtWire.writeF64(b, obj.total_steps());

        GMExtWire.writeF64(b, obj.last_updated_timestamp());

        GMExtWire.writeF64(b, obj.xp_value());

        GMExtWire.writeBool(b, obj.revealed_image_uri() != null && obj.revealed_image_uri().isPresent());
        if (obj.revealed_image_uri() != null && obj.revealed_image_uri().isPresent())
        {
            GMExtWire.writeString(b, obj.revealed_image_uri().get());
        }

        GMExtWire.writeBool(b, obj.unlocked_image_uri() != null && obj.unlocked_image_uri().isPresent());
        if (obj.unlocked_image_uri() != null && obj.unlocked_image_uri().isPresent())
        {
            GMExtWire.writeString(b, obj.unlocked_image_uri().get());
        }

    }
}