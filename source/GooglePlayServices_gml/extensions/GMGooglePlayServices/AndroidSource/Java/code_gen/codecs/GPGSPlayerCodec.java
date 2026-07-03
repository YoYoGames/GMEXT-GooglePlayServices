// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSPlayerCodec {
    private GPGSPlayerCodec()
    {
    }
    public static GPGSPlayer read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        GPGSPlayerInfo player = GPGSPlayerInfoCodec.read(b);

        String error = GMExtWire.readString(b);

        return new GPGSPlayer(success, player, error);
    }

    public static void write(ByteBuffer b, GPGSPlayer obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GPGSPlayerInfoCodec.write(b, obj.player());

        GMExtWire.writeString(b, obj.error());

    }
}