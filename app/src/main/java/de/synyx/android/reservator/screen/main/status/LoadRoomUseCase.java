package de.synyx.android.reservator.screen.main.status;

import de.synyx.android.reservator.business.event.Event;
import de.synyx.android.reservator.business.event.EventRepository;
import de.synyx.android.reservator.business.room.RoomRepository;
import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;

import io.reactivex.Maybe;
import io.reactivex.Observable;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoadRoomUseCase {

    private final RoomRepository roomRepository;
    private final EventRepository eventRepository;

    public LoadRoomUseCase() {

        roomRepository = Registry.get(RoomRepository.class);
        eventRepository = Registry.get(EventRepository.class);
    }

    public Maybe<MeetingRoom> execute(long calendarId) {

        return roomRepository.loadRoom(calendarId)
            .map(roomCalendar -> new MeetingRoom(roomCalendar.getCalendarId(), roomCalendar.getName()))
            .flatMap(this::addReservations);
    }


    private Maybe<MeetingRoom> addReservations(MeetingRoom meetingRoom) {

        return loadEventsFor(meetingRoom) //
            .map(event -> new Reservation(event.getId(), event.getName(), event.getBegin(), event.getEnd())) //
            .collectInto(meetingRoom, MeetingRoom::addReservation) //
            .toMaybe();
    }


    private Observable<Event> loadEventsFor(MeetingRoom meetingRoom) {

        return eventRepository.loadAllEventsForRoom(meetingRoom.getCalendarId());
    }
}
