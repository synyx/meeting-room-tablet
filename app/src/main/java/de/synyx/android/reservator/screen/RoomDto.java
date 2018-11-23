package de.synyx.android.reservator.screen;

import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.domain.room.RoomState;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomDto {

    private final RoomCalendar room;
    private final Event event;
    private final Event nextEvent;

    public RoomDto(RoomCalendar room, Event event, Event nextEvent) {

        this.room = room;
        this.event = event;
        this.nextEvent = nextEvent;
    }

    public String getRoomName() {

        return room.getName();
    }


    public String getRoomTime() {

        if (event != null) {
            return event.getRemainingTime().getMinutes() + " Min";
        }

        if (nextEvent != null) {
            return nextEvent.getTimeUntilBegin().getMinutes() + " Min";
        }

        return "Frei für den ganzen Tag";
    }


    public String getActiveEventName() {

        if (event == null) {
            return "VERFÜGBAR";
        }

        return event.getName();
    }


    public String getNextEventName() {

        if (nextEvent == null) {
            return "";
        }

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


    public long getCalendarId() {

        return room.getCalendarId();
    }
}
