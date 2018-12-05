package de.synyx.android.reservator.screen.settings;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.business.event.Event;
import de.synyx.android.reservator.business.event.EventRepository;
import de.synyx.android.reservator.business.room.RoomCalendar;
import de.synyx.android.reservator.business.room.RoomRepository;
import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LoadAllRoomsUseCase {

    private final RoomRepository roomRepository;
    private final EventRepository eventRepository;

    public LoadAllRoomsUseCase() {

        roomRepository = Registry.get(RoomRepository.class);
        eventRepository = Registry.get(EventRepository.class);
    }

    public Single<List<MeetingRoom>> execute() {

        return
            roomRepository.loadAllRooms() //
            .map(this::toMeetingRoom) //
            .flatMapSingle(this::addReservations) //
            .collect(ArrayList::new, List::add);
    }


    @NonNull
    private MeetingRoom toMeetingRoom(RoomCalendar roomCalendar) {

        return new MeetingRoom(roomCalendar.getCalendarId(), roomCalendar.getName());
    }


    private Single<MeetingRoom> addReservations(MeetingRoom meetingRoom) {

        return
            loadEventsFor(meetingRoom) //
            .map(this::toReservation) //
            .collectInto(meetingRoom, MeetingRoom::addReservation);
    }


    @NonNull
    private Reservation toReservation(Event event) {

        return new Reservation(event.getId(), event.getName(), event.getBegin(), event.getEnd());
    }


    private Observable<Event> loadEventsFor(MeetingRoom meetingRoom) {

        return eventRepository.loadAllEventsForRoom(meetingRoom.getCalendarId());
    }
}
