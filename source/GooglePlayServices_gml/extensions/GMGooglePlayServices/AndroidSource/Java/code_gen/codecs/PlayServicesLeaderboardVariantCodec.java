// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardVariantCodec {
    private PlayServicesLeaderboardVariantCodec()
    {
    }
    public static PlayServicesLeaderboardVariant read(ByteBuffer b)
    {
        double collection = GMExtWire.readF64(b);

        double time_span = GMExtWire.readF64(b);

        boolean has_player_info = GMExtWire.readBool(b);

        return new PlayServicesLeaderboardVariant(collection, time_span, has_player_info);
    }

    public static void write(ByteBuffer b, PlayServicesLeaderboardVariant obj)
    {
        GMExtWire.writeF64(b, obj.collection());

        GMExtWire.writeF64(b, obj.time_span());

        GMExtWire.writeBool(b, obj.has_player_info());

    }
}