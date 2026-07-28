// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class GPGSPlayerListCodec {
    private GPGSPlayerListCodec()
    {
    }
    public static GPGSPlayerList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<GPGSPlayerInfo> players = GMExtWire.readList(b, bb -> GPGSPlayerInfoCodec.read(bb));

        boolean has_more = GMExtWire.readBool(b);

        String error = GMExtWire.readString(b);

        return new GPGSPlayerList(success, players, has_more, error);
    }

    public static void write(ByteBuffer b, GPGSPlayerList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.players(), (bb, x) -> GPGSPlayerInfoCodec.write(bb, x));

        GMExtWire.writeBool(b, obj.has_more());

        GMExtWire.writeString(b, obj.error());

    }
}