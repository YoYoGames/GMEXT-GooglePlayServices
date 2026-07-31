// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import java.util.Optional;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSnapshotMetadataCodec {
    private PlayServicesSnapshotMetadataCodec()
    {
    }
    public static PlayServicesSnapshotMetadata read(ByteBuffer b)
    {
        java.util.Optional<String> unique_name = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_unique_name = GMExtWire.readString(b);
            unique_name = java.util.Optional.of(__opt_unique_name);
        }

        java.util.Optional<String> description = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_description = GMExtWire.readString(b);
            description = java.util.Optional.of(__opt_description);
        }

        java.util.Optional<String> device_name = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_device_name = GMExtWire.readString(b);
            device_name = java.util.Optional.of(__opt_device_name);
        }

        double last_modified_timestamp = GMExtWire.readF64(b);

        double played_time = GMExtWire.readF64(b);

        double progress_value = GMExtWire.readF64(b);

        boolean has_change_pending = GMExtWire.readBool(b);

        java.util.Optional<String> cover_image_uri = java.util.Optional.empty();
        if (GMExtWire.readBool(b))
        {
            String __opt_cover_image_uri = GMExtWire.readString(b);
            cover_image_uri = java.util.Optional.of(__opt_cover_image_uri);
        }

        return new PlayServicesSnapshotMetadata(unique_name, description, device_name, last_modified_timestamp, played_time, progress_value, has_change_pending, cover_image_uri);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesSnapshotMetadata obj)
    {
        GMExtWire.writeBool(b, obj.unique_name() != null && obj.unique_name().isPresent());
        if (obj.unique_name() != null && obj.unique_name().isPresent())
        {
            GMExtWire.writeString(b, obj.unique_name().get());
        }

        GMExtWire.writeBool(b, obj.description() != null && obj.description().isPresent());
        if (obj.description() != null && obj.description().isPresent())
        {
            GMExtWire.writeString(b, obj.description().get());
        }

        GMExtWire.writeBool(b, obj.device_name() != null && obj.device_name().isPresent());
        if (obj.device_name() != null && obj.device_name().isPresent())
        {
            GMExtWire.writeString(b, obj.device_name().get());
        }

        GMExtWire.writeF64(b, obj.last_modified_timestamp());

        GMExtWire.writeF64(b, obj.played_time());

        GMExtWire.writeF64(b, obj.progress_value());

        GMExtWire.writeBool(b, obj.has_change_pending());

        GMExtWire.writeBool(b, obj.cover_image_uri() != null && obj.cover_image_uri().isPresent());
        if (obj.cover_image_uri() != null && obj.cover_image_uri().isPresent())
        {
            GMExtWire.writeString(b, obj.cover_image_uri().get());
        }

    }
}