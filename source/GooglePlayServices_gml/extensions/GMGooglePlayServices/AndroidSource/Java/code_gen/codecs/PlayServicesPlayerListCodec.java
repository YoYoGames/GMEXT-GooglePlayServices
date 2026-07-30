// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.List;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesPlayerListCodec {
    private PlayServicesPlayerListCodec()
    {
    }
    public static PlayServicesPlayerList read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        java.util.List<PlayServicesPlayerInfo> players = GMExtWire.readList(b, bb -> PlayServicesPlayerInfoCodec.read(bb));

        boolean has_more = GMExtWire.readBool(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesPlayerList(success, players, has_more, error);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesPlayerList obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeList(b, obj.players(), (bb, x) -> PlayServicesPlayerInfoCodec.write(bb, x));

        GMExtWire.writeBool(b, obj.has_more());

        GMExtWire.writeString(b, obj.error());

    }
}