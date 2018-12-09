package de.synyx.android.reservator.util.proxy.room;

import de.synyx.android.reservator.business.calendar.RoomCalendarModel;

import java.util.List;


/**
 * As implementation use ResourceRoomProxy or CalenderRoomProxy depending on the users selection when the app starts.
 *
 * @author  Julian Heetel - heetel@synyx.de
 */
public abstract class RoomProxy {

    public abstract List<RoomCalendarModel> getRooms();
}
