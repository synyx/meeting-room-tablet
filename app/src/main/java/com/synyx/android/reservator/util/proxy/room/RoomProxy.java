package com.synyx.android.reservator.util.proxy.room;

import com.synyx.android.reservator.domain.room.Room;

import java.util.List;


/**
 * As implementation use ResourceRoomProxy or CalenderRoomProxy depending on the users selection when the app starts.
 *
 * @author  Julian Heetel - heetel@synyx.de
 */
public abstract class RoomProxy {

    public abstract List<Room> getRooms();
}
