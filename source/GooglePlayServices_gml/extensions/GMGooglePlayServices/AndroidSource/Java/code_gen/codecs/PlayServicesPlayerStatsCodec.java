// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesPlayerStatsCodec {
    private PlayServicesPlayerStatsCodec()
    {
    }
    public static PlayServicesPlayerStats read(ByteBuffer b)
    {
        boolean success = GMExtWire.readBool(b);

        double average_session_length = GMExtWire.readF64(b);

        double days_since_last_played = GMExtWire.readF64(b);

        double number_of_purchases = GMExtWire.readF64(b);

        double number_of_sessions = GMExtWire.readF64(b);

        double session_percentile = GMExtWire.readF64(b);

        double spend_percentile = GMExtWire.readF64(b);

        double churn_probability = GMExtWire.readF64(b);

        double high_spender_probability = GMExtWire.readF64(b);

        double spend_probability = GMExtWire.readF64(b);

        double total_spend_next_28_days = GMExtWire.readF64(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesPlayerStats(success, average_session_length, days_since_last_played, number_of_purchases, number_of_sessions, session_percentile, spend_percentile, churn_probability, high_spender_probability, spend_probability, total_spend_next_28_days, error);
    }

    public static void write(ByteBuffer b, PlayServicesPlayerStats obj)
    {
        GMExtWire.writeBool(b, obj.success());

        GMExtWire.writeF64(b, obj.average_session_length());

        GMExtWire.writeF64(b, obj.days_since_last_played());

        GMExtWire.writeF64(b, obj.number_of_purchases());

        GMExtWire.writeF64(b, obj.number_of_sessions());

        GMExtWire.writeF64(b, obj.session_percentile());

        GMExtWire.writeF64(b, obj.spend_percentile());

        GMExtWire.writeF64(b, obj.churn_probability());

        GMExtWire.writeF64(b, obj.high_spender_probability());

        GMExtWire.writeF64(b, obj.spend_probability());

        GMExtWire.writeF64(b, obj.total_spend_next_28_days());

        GMExtWire.writeString(b, obj.error());

    }
}