package de.synyx.android.reservator.domain.event;

import io.reactivex.Single;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface EventRepository {

    Single<List<Event>> loadAllEventsForRoom(long roomId);
}
