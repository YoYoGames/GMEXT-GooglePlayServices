// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class GPGSAchievementListCodec {
    private GPGSAchievementListCodec()
    {
    }
    public static GPGSAchievementList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<GPGSAchievement> achievements = GMExtWire.readList(b, bb -> GPGSAchievementCodec.read(bb));

        String error = GMExtWire.readString(b);

        return new GPGSAchievementList(success, achievements, error);
    }

    public static void write(ByteBuffer b, GPGSAchievementList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.achievements(), (bb, x) -> GPGSAchievementCodec.write(bb, x));

        GMExtWire.writeString(b, obj.error());

    }
}