// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesSnapshotMetadata(java.util.Optional<String> unique_name, java.util.Optional<String> description, java.util.Optional<String> device_name, double last_modified_timestamp, double played_time, double progress_value, boolean has_change_pending, java.util.Optional<String> cover_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 7;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesSnapshotMetadataCodec.write(b, this);
    }
}
