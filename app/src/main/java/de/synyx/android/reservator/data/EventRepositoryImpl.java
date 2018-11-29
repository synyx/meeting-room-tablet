package de.synyx.android.reservator.data;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.event.EventRepository;

import io.reactivex.Observable;
import io.reactivex.Single;

import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;


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
    public Single<List<Event>> loadAllEventsForRoom(long roomId) {

        return eventAdapter.getEventsForRoom(roomId) //
            .flatMap(loadAttendees()) //
            .collect(ArrayList::new, List::add);
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
