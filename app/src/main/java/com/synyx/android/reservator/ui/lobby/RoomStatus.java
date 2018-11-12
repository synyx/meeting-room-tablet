package com.synyx.android.reservator.ui.lobby;

import com.synyx.android.reservator.domain.event.Event;
import com.synyx.android.reservator.domain.room.Room;
import com.synyx.android.reservator.domain.room.RoomState;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomStatus {

    private final Room room;
    private final Event event;
    private final Event nextEvent;

    public RoomStatus(Room room, Event event, Event nextEvent) {

        this.room = room;
        this.event = event;
        this.nextEvent = nextEvent;
    }

    public String getRoomName() {

        return room.getName();
    }


    public String getRoomTime() {

        return "25 Min";
    }


    public String getActiveEventName() {

        if (event == null) {
            return "VERFÜGBAR";
        }

        return event.getName();
    }


    public String getNextEventName() {

        return nextEvent.getName();
    }


    public boolean isAvailable() {

        return event == null;
    }


    public RoomState getStatus() {

        if (isAvailable())
            return RoomState.AVAILABLE;

        return RoomState.UNAVAILABLE;
    }
}
