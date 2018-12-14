package de.synyx.android.reservator.data;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.business.event.EventModel;
import de.synyx.android.reservator.business.event.EventRepository;
import de.synyx.android.reservator.config.Registry;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import io.reactivex.functions.Function;

import org.joda.time.DateTime;


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
    public Observable<EventModel> loadAllEventsForRoom(long roomId) {

        return eventAdapter.getEventsForRoom(roomId) //
            .flatMap(loadAttendees());
    }


    @Override
    public Maybe<Long> insertEvent(long calendarId, String title, DateTime start, DateTime end) {

        return eventAdapter.insertEvent(calendarId, start, end, title);
    }


    @NonNull
    private Function<EventModel, Observable<EventModel>> loadAttendees() {

        return
            event ->
                attendeeAdapter.getAttendeesForEvent(event.getId())
                .collectInto(event, EventModel::addAttendee)
                .toObservable();
    }
}
