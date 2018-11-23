package de.synyx.android.reservator.data;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.event.EventRepository;

import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class EventRepositoryImpl implements EventRepository {

    private final EventAdapter eventAdapter;

    public EventRepositoryImpl() {

        eventAdapter = Registry.get(EventAdapter.class);
    }

    @Override
    public Single<List<Event>> loadAllEventsForRoom(long roomId) {

        return eventAdapter.getEventsForRoom(roomId).collect(ArrayList::new, List::add);
    }
}
