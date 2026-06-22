// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSavedGameCommitOptionsCodec {
    private GPGSSavedGameCommitOptionsCodec()
    {
    }
    public static GPGSSavedGameCommitOptions read(ByteBuffer b)
    {
        String name = GMExtWire.readString(b);

        String data = GMExtWire.readString(b);

        String desc = GMExtWire.readString(b);

        double played_time_millis = GMExtWire.readF64(b);

        double progress_value = GMExtWire.readF64(b);

        String cover_image_path = GMExtWire.readString(b);

        return new GPGSSavedGameCommitOptions(name, data, desc, played_time_millis, progress_value, cover_image_path);
    }

    public static void write(ByteBuffer b, GPGSSavedGameCommitOptions obj)
    {
        GMExtWire.writeString(b, obj.name());

        GMExtWire.writeString(b, obj.data());

        GMExtWire.writeString(b, obj.desc());

        GMExtWire.writeF64(b, obj.played_time_millis());

        GMExtWire.writeF64(b, obj.progress_value());

        GMExtWire.writeString(b, obj.cover_image_path());

    }
}