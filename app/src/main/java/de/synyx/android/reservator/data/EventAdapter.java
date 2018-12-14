package de.synyx.android.reservator.data;

import de.synyx.android.reservator.business.event.EventModel;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import org.joda.time.DateTime;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventAdapter {

    Observable<EventModel> getEventsForRoom(long roomId);


    Maybe<Long> insertEvent(long calendarId, DateTime start, DateTime end, String title);
}
