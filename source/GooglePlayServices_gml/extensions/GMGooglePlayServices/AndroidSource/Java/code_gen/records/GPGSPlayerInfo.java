// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSPlayerInfo(String player_id, String display_name, String title, String icon_image_uri, String hi_res_image_uri) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 2;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSPlayerInfoCodec.write(b, this);
    }
}
