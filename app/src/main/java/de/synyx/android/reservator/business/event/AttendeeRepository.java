package de.synyx.android.reservator.business.event;

/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface AttendeeRepository {

    void insertAttendeeForEvent(String attendeeName, long eventId);
}
