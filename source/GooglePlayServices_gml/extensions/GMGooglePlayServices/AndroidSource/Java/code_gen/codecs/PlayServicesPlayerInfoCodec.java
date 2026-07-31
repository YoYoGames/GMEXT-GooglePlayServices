// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesPlayerInfoCodec {
    private PlayServicesPlayerInfoCodec()
    {
    }
    public static PlayServicesPlayerInfo read(ByteBuffer b)
    {
        java.util.Optional<String> player_id = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_player_id = GMExtWire.readString(b);
            player_id = java.util.Optional.of(__opt_player_id);
        }

        java.util.Optional<String> display_name = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_display_name = GMExtWire.readString(b);
            display_name = java.util.Optional.of(__opt_display_name);
        }

        java.util.Optional<String> title = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_title = GMExtWire.readString(b);
            title = java.util.Optional.of(__opt_title);
        }

        java.util.Optional<String> icon_image_uri = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_icon_image_uri = GMExtWire.readString(b);
            icon_image_uri = java.util.Optional.of(__opt_icon_image_uri);
        }

        java.util.Optional<String> hi_res_image_uri = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_hi_res_image_uri = GMExtWire.readString(b);
            hi_res_image_uri = java.util.Optional.of(__opt_hi_res_image_uri);
        }

        return new PlayServicesPlayerInfo(player_id, display_name, title, icon_image_uri, hi_res_image_uri);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesPlayerInfo obj)
    {
        GMExtWire.writeBool(b, obj.player_id() != null && obj.player_id().isPresent());
        if (obj.player_id() != null && obj.player_id().isPresent())
        {
            GMExtWire.writeString(b, obj.player_id().get());
        }

        GMExtWire.writeBool(b, obj.display_name() != null && obj.display_name().isPresent());
        if (obj.display_name() != null && obj.display_name().isPresent())
        {
            GMExtWire.writeString(b, obj.display_name().get());
        }

        GMExtWire.writeBool(b, obj.title() != null && obj.title().isPresent());
        if (obj.title() != null && obj.title().isPresent())
        {
            GMExtWire.writeString(b, obj.title().get());
        }

        GMExtWire.writeBool(b, obj.icon_image_uri() != null && obj.icon_image_uri().isPresent());
        if (obj.icon_image_uri() != null && obj.icon_image_uri().isPresent())
        {
            GMExtWire.writeString(b, obj.icon_image_uri().get());
        }

        GMExtWire.writeBool(b, obj.hi_res_image_uri() != null && obj.hi_res_image_uri().isPresent());
        if (obj.hi_res_image_uri() != null && obj.hi_res_image_uri().isPresent())
        {
            GMExtWire.writeString(b, obj.hi_res_image_uri().get());
        }

    }
}