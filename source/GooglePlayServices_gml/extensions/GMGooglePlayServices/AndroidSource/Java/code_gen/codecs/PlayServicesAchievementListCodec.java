// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesAchievementListCodec {
    private PlayServicesAchievementListCodec()
    {
    }
    public static PlayServicesAchievementList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<PlayServicesAchievement> achievements = GMExtWire.readList(b, bb -> PlayServicesAchievementCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new PlayServicesAchievementList(success, achievements, error);
    }

    public static void write(ByteBuffer b, PlayServicesAchievementList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.achievements(), (bb, x) -> PlayServicesAchievementCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}