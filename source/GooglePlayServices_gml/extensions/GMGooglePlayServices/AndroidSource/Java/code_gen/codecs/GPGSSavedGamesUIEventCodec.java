// ##### extgen :: Auto-generated file do not edit!! #####

package ${YYAndroidPackageName}.codecs;

import java.nio.ByteBuffer;

import ${YYAndroidPackageName}.GMExtWire;
import ${YYAndroidPackageName}.records.*;

public final class GPGSSavedGamesUIEventCodec {
    private GPGSSavedGamesUIEventCodec()
    {
    }
    public static GPGSSavedGamesUIEvent read(ByteBuffer b)
    {
        double result = GMExtWire.readF64(b);

        GPGSSnapshotMetadata snapshot_metadata = GPGSSnapshotMetadataCodec.read(b);

        String error = GMExtWire.readString(b);

        return new GPGSSavedGamesUIEvent(result, snapshot_metadata, error);
    }

    public static void write(ByteBuffer b, GPGSSavedGamesUIEvent obj)
    {
        GMExtWire.writeF64(b, obj.result());

        GPGSSnapshotMetadataCodec.write(b, obj.snapshot_metadata());

        GMExtWire.writeString(b, obj.error());

    }
}