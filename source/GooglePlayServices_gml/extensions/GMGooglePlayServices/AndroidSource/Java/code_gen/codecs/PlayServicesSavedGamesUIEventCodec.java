// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class PlayServicesSavedGamesUIEventCodec {
    private PlayServicesSavedGamesUIEventCodec()
    {
    }
    public static PlayServicesSavedGamesUIEvent read(ByteBuffer b)
    {
        double result = GMExtWire.readF64(b);

        PlayServicesSnapshotMetadata snapshot_metadata = PlayServicesSnapshotMetadataCodec.read(b);

        String error = GMExtWire.readString(b);

        return new PlayServicesSavedGamesUIEvent(result, snapshot_metadata, error);
    }

    public static void write(GMExtWire.IByteWriter b, PlayServicesSavedGamesUIEvent obj)
    {
        GMExtWire.writeF64(b, obj.result());

        PlayServicesSnapshotMetadataCodec.write(b, obj.snapshot_metadata());

        GMExtWire.writeString(b, obj.error());

    }
}