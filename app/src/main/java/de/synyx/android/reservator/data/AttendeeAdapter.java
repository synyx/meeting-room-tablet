package de.synyx.android.reservator.data;

import de.synyx.android.reservator.domain.attendee.Attendee;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface AttendeeAdapter {

    Observable<Attendee> getAttendeesForEvent(long eventId);
}
