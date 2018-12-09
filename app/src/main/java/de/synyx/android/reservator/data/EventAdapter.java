package de.synyx.android.reservator.data;

import de.synyx.android.reservator.business.event.EventModel;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventAdapter {

    Observable<EventModel> getEventsForRoom(long roomId);
}
