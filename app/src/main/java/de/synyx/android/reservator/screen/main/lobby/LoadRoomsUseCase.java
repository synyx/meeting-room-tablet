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
import java.util.Collections;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class LoadRoomsUseCase {

    private final RoomRepository roomRepository;
    private final EventRepository eventRepository;

    public LoadRoomsUseCase() {

        roomRepository = Registry.get(RoomRepository.class);
        eventRepository = Registry.get(EventRepository.class);
    }

    public Single<List<RoomDto>> execute() {

        return
            roomRepository.loadAllRooms() //
            .flatMap(this::loadEventsAndConstructRoomDto) //
            .collect(ArrayList::new, List::add);
    }


    private Observable<RoomDto> loadEventsAndConstructRoomDto(RoomCalendar roomCalendar) {

        return loadEventsFor(roomCalendar) //
            .map(sortChronological()).map(toRoomDto(roomCalendar)) //
            .toObservable();
    }


    private Function<List<Event>, List<Event>> sortChronological() {

        return
            events -> {
            Collections.sort(events, (e1, e2) -> e1.getBegin().compareTo(e2.getBegin()));

            return events;
        };
    }


    @NonNull
    private static Function<List<Event>, RoomDto> toRoomDto(RoomCalendar roomCalendar) {

        return
            events -> {
            Event currentEvent = null;
            Event nextUpcomingEvent = null;

            for (Event event : events) {
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
