package de.synyx.android.reservator.screen.agenda;

import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.room.RoomCalendar;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class AgendaDto {

    private final RoomCalendar room;
    private final List<Event> reservations;

    public AgendaDto(RoomCalendar room, List<Event> reservations) {

        this.room = room;
        this.reservations = reservations;
    }

    public List<Event> getReservations() {

        return reservations;
    }


    public String getRoomName() {

        return room.getName();
    }
}
