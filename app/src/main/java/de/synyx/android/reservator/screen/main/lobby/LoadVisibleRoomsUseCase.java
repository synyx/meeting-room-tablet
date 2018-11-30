package de.synyx.android.reservator.screen.main.lobby;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.event.EventRepository;
import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.domain.room.RoomRepository;
import de.synyx.android.reservator.screen.RoomDto;

import io.reactivex.Observable;
import io.reactivex.Single;

import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LoadVisibleRoomsUseCase {

    private final RoomRepository roomRepository;
    private final EventRepository eventRepository;

    public LoadVisibleRoomsUseCase() {

        roomRepository = Registry.get(RoomRepository.class);
        eventRepository = Registry.get(EventRepository.class);
    }

    public Single<List<RoomDto>> execute() {

        return
            roomRepository.loadVisibleRooms() //
            .flatMap(this::loadEventsAndConstructRoomDto) //
            .collect(ArrayList::new, List::add);
    }


    private Observable<RoomDto> loadEventsAndConstructRoomDto(RoomCalendar roomCalendar) {

        return loadEventsFor(roomCalendar) //
            .map(toRoomDto(roomCalendar)) //
            .toObservable();
    }


    @NonNull
    private static Function<List<Event>, RoomDto> toRoomDto(RoomCalendar roomCalendar) {

        return
            events -> {
            Event currentEvent = null;
            Event nextUpcomingEvent = null;

            for (Event event : events) {
                if (event.hasAttendeeCanceled(roomCalendar.getName())) {
                    continue;
                }

                if (event.isCurrent()) {
                    currentEvent = event;

                    continue;
                }

                if (event.isNextUpcoming()) {
                    nextUpcomingEvent = event;

                    break;
                }
            }

            return new RoomDto(roomCalendar, currentEvent, nextUpcomingEvent);
        };
    }


    private Single<List<Event>> loadEventsFor(RoomCalendar roomCalendar) {

        return eventRepository.loadAllEventsForRoom(roomCalendar.getCalendarId());
    }
}
