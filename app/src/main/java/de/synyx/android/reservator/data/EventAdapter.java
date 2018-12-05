package de.synyx.android.reservator.data;

import de.synyx.android.reservator.business.event.Event;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventAdapter {

    Observable<Event> getEventsForRoom(long roomId);
}
