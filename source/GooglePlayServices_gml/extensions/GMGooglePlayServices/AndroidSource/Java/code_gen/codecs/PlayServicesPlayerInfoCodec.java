// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesPlayerInfoCodec {
    private PlayServicesPlayerInfoCodec()
    {
    }
    public static PlayServicesPlayerInfo read(ByteBuffer b)
    {
        String player_id = GMExtWire.readString(b);

        String display_name = GMExtWire.readString(b);

        String title = GMExtWire.readString(b);

        String icon_image_uri = GMExtWire.readString(b);

        String hi_res_image_uri = GMExtWire.readString(b);

        return new PlayServicesPlayerInfo(player_id, display_name, title, icon_image_uri, hi_res_image_uri);
    }

    public static void write(ByteBuffer b, PlayServicesPlayerInfo obj)
    {
        GMExtWire.writeString(b, obj.player_id());

        GMExtWire.writeString(b, obj.display_name());

        GMExtWire.writeString(b, obj.title());

        GMExtWire.writeString(b, obj.icon_image_uri());

        GMExtWire.writeString(b, obj.hi_res_image_uri());

    }
}