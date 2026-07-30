// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesSnapshotMetadata(String unique_name, String description, String device_name, double last_modified_timestamp, double played_time, double progress_value, boolean has_change_pending, String cover_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 7;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesSnapshotMetadataCodec.write(b, this);
    }
}
