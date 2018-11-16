package com.synyx.android.reservator.data;

import com.synyx.android.reservator.domain.event.Event;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventAdapter {

    Observable<Event> getEventsForRoom(long roomId);
}
