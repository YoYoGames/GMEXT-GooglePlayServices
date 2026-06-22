// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record GPGSSavedGameCommitOptions(String name, String data, String desc, double played_time_millis, double progress_value, String cover_image_path) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 0;
    @Override
    public void encode(ByteBuffer b)
    {
        GPGSSavedGameCommitOptionsCodec.write(b, this);
    }
}
