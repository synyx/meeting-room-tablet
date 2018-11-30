package de.synyx.android.reservator.legacy;

import android.app.Activity;

import com.futurice.android.reservator.RoomActivity;
import com.futurice.android.reservator.model.DataProxy;
import com.futurice.android.reservator.model.Room;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.room.RoomCalendar;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class OpenOldRoomActivityAdapter {

    public static void openRoomActivity(Activity context, RoomCalendar roomCalendar) {

        Room room = new Room(roomCalendar.getName(), roomCalendar.getOwner(), false);
        DataProxy dataProxy = Registry.get(DataProxy.class);
        RoomActivity.startWith(context, room, dataProxy);
    }
}
