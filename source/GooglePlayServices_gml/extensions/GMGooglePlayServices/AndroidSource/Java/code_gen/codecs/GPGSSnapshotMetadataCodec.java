// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSnapshotMetadataCodec {
    private GPGSSnapshotMetadataCodec()
    {
    }
    public static GPGSSnapshotMetadata read(ByteBuffer b)
    {
        String unique_name = GMExtWire.readString(b);

        String description = GMExtWire.readString(b);

        String device_name = GMExtWire.readString(b);

        double last_modified_timestamp = GMExtWire.readF64(b);

        double played_time = GMExtWire.readF64(b);

        double progress_value = GMExtWire.readF64(b);

        boolean has_change_pending = GMExtWire.readBool(b);

        String cover_image_uri = GMExtWire.readString(b);

        return new GPGSSnapshotMetadata(unique_name, description, device_name, last_modified_timestamp, played_time, progress_value, has_change_pending, cover_image_uri);
    }

    public static void write(ByteBuffer b, GPGSSnapshotMetadata obj)
    {
        GMExtWire.writeString(b, obj.unique_name());

        GMExtWire.writeString(b, obj.description());

        GMExtWire.writeString(b, obj.device_name());

        GMExtWire.writeF64(b, obj.last_modified_timestamp());

        GMExtWire.writeF64(b, obj.played_time());

        GMExtWire.writeF64(b, obj.progress_value());

        GMExtWire.writeBool(b, obj.has_change_pending());

        GMExtWire.writeString(b, obj.cover_image_uri());

    }
}