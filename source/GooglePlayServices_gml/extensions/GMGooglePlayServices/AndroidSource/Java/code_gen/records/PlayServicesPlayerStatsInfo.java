// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.records;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.codecs.*;

import java.nio.ByteBuffer;

public record PlayServicesPlayerStatsInfo(double average_session_length, double days_since_last_played, double number_of_purchases, double number_of_sessions, double session_percentile, double spend_percentile, double churn_probability, double high_spender_probability, double spend_probability, double total_spend_next_28_days) implements GMExtWire.ITypedStruct
{
    public static final int CODEC_ID = 3;
    @Override
    public void encode(GMExtWire.IByteWriter b)
    {
        PlayServicesPlayerStatsInfoCodec.write(b, this);
    }
}
