// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesPlayerCodec {
    private PlayServicesPlayerCodec()
    {
    }
    public static PlayServicesPlayer read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        PlayServicesPlayerInfo player = PlayServicesPlayerInfoCodec.read(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesPlayer(success, player, error);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesPlayer obj)
    {
        GMExtWire.writeBool(b, obj.success());

        PlayServicesPlayerInfoCodec.write(b, obj.player());

        GMExtWire.writeString(b, obj.error());

    }
}