package de.synyx.android.reservator.screen.main.agenda;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.event.EventRepository;
import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.domain.room.RoomRepository;

import io.reactivex.Maybe;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class LoadAgendaUseCase {

    private final RoomRepository roomRepository;
    private final EventRepository eventRepository;

    LoadAgendaUseCase() {

        roomRepository = Registry.get(RoomRepository.class);
        eventRepository = Registry.get(EventRepository.class);
    }

    public Maybe<AgendaDto> execute(long calendarId) {

        Maybe<RoomCalendar> roomStream = roomRepository.loadRoom(calendarId);
        Maybe<List<Event>> reservationsStream = eventRepository.loadAllEventsForRoom(calendarId).toMaybe();

        return roomStream.zipWith(reservationsStream, AgendaDto::new);
    }
}
