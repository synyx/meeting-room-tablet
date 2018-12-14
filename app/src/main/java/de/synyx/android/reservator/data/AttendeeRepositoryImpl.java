package de.synyx.android.reservator.data;

import de.synyx.android.reservator.business.event.AttendeeRepository;
import de.synyx.android.reservator.config.Registry;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class AttendeeRepositoryImpl implements AttendeeRepository {

    private final AttendeeAdapter attendeeAdapter;

    public AttendeeRepositoryImpl() {

        attendeeAdapter = Registry.get(AttendeeAdapter.class);
    }

    @Override
    public void insertAttendeeForEvent(String attendeeName, long eventId) {

        attendeeAdapter.insertAttendeeForEvent(eventId, attendeeName);
    }
}
