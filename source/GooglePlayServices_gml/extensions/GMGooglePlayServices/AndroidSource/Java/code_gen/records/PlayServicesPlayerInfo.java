// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;
import java.util.Optional;

public record PlayServicesPlayerInfo(java.util.Optional<String> player_id, java.util.Optional<String> display_name, java.util.Optional<String> title, java.util.Optional<String> icon_image_uri, java.util.Optional<String> hi_res_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 2;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesPlayerInfoCodec.write(b, this);
    }
}
