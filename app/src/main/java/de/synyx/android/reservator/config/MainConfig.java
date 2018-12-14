package de.synyx.android.reservator.config;

import de.synyx.android.reservator.business.calendar.RoomCalendarRepository;
import de.synyx.android.reservator.business.event.AttendeeRepository;
import de.synyx.android.reservator.business.event.EventRepository;
import de.synyx.android.reservator.data.AttendeeAdapter;
import de.synyx.android.reservator.data.AttendeeAdapterImpl;
import de.synyx.android.reservator.data.AttendeeRepositoryImpl;
import de.synyx.android.reservator.data.EventAdapter;
import de.synyx.android.reservator.data.EventAdapterImpl;
import de.synyx.android.reservator.data.EventRepositoryImpl;
import de.synyx.android.reservator.data.RoomCalendarRepositoryImpl;
import de.synyx.android.reservator.util.SchedulerFacade;
import de.synyx.android.reservator.util.SchedulerFacadeImpl;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class MainConfig {

    private MainConfig() {

        // hide
    }

    public static void init() {

        Registry.put(AttendeeAdapter.class, new AttendeeAdapterImpl());
        Registry.put(EventAdapter.class, new EventAdapterImpl());
        Registry.put(AttendeeRepository.class, new AttendeeRepositoryImpl());
        Registry.put(EventRepository.class, new EventRepositoryImpl());
        Registry.put(SchedulerFacade.class, new SchedulerFacadeImpl());
        Registry.put(RoomCalendarRepository.class, new RoomCalendarRepositoryImpl());
    }
}
