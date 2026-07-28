// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesAchievementCodec {
    private PlayServicesAchievementCodec()
    {
    }
    public static PlayServicesAchievement read(ByteBuffer b)
    {
        String achievement_id = GMExtWire.readString(b);

        String name = GMExtWire.readString(b);

        String description = GMExtWire.readString(b);

        double state = GMExtWire.readF64(b);

        double type = GMExtWire.readF64(b);

        double current_steps = GMExtWire.readF64(b);

        double total_steps = GMExtWire.readF64(b);

        double last_updated_timestamp = GMExtWire.readF64(b);

        double xp_value = GMExtWire.readF64(b);

        String revealed_image_uri = GMExtWire.readString(b);

        String unlocked_image_uri = GMExtWire.readString(b);

        return new PlayServicesAchievement(achievement_id, name, description, state, type, current_steps, total_steps, last_updated_timestamp, xp_value, revealed_image_uri, unlocked_image_uri);
    }

    public static void write(ByteBuffer b, PlayServicesAchievement obj)
    {
        GMExtWire.writeString(b, obj.achievement_id());

        GMExtWire.writeString(b, obj.name());

        GMExtWire.writeString(b, obj.description());

        GMExtWire.writeF64(b, obj.state());

        GMExtWire.writeF64(b, obj.type());

        GMExtWire.writeF64(b, obj.current_steps());

        GMExtWire.writeF64(b, obj.total_steps());

        GMExtWire.writeF64(b, obj.last_updated_timestamp());

        GMExtWire.writeF64(b, obj.xp_value());

        GMExtWire.writeString(b, obj.revealed_image_uri());

        GMExtWire.writeString(b, obj.unlocked_image_uri());

    }
}