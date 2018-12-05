package de.synyx.android.reservator.business.event;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventRepository {

    Observable<Event> loadAllEventsForRoom(long roomId);
}
