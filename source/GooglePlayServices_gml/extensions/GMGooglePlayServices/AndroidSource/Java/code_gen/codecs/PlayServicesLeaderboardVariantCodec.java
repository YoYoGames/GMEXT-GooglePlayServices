// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.enums.*;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesLeaderboardVariantCodec {
    private PlayServicesLeaderboardVariantCodec()
    {
    }
    public static PlayServicesLeaderboardVariant read(ByteBuffer b)
    {
        PlayServicesLeaderboardCollection collection = PlayServicesLeaderboardCollection.from(GMExtWire.readI32(b));

        PlayServicesLeaderboardTimeSpan time_span = PlayServicesLeaderboardTimeSpan.from(GMExtWire.readI32(b));

        boolean has_player_info = GMExtWire.readBool(b);

        return new PlayServicesLeaderboardVariant(collection, time_span, has_player_info);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesLeaderboardVariant obj)
    {
        GMExtWire.writeI32(b, obj.collection().value());

        GMExtWire.writeI32(b, obj.time_span().value());

        GMExtWire.writeBool(b, obj.has_player_info());

    }
}