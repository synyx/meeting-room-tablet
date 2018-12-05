package de.synyx.android.reservator.data;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.business.event.Event;
import de.synyx.android.reservator.business.event.EventRepository;
import de.synyx.android.reservator.config.Registry;

import io.reactivex.Observable;

import io.reactivex.functions.Function;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class EventRepositoryImpl implements EventRepository {

    private final EventAdapter eventAdapter;
    private final AttendeeAdapter attendeeAdapter;

    public EventRepositoryImpl() {

        eventAdapter = Registry.get(EventAdapter.class);
        attendeeAdapter = Registry.get(AttendeeAdapter.class);
    }

    @Override
    public Observable<Event> loadAllEventsForRoom(long roomId) {

        return eventAdapter.getEventsForRoom(roomId) //
            .flatMap(loadAttendees());
    }


    @NonNull
    private Function<Event, Observable<Event>> loadAttendees() {

        return
            event ->
                attendeeAdapter.getAttendeesForEvent(event.getId())
                .collectInto(event, Event::addAttendee)
                .toObservable();
    }
}
