package de.synyx.android.reservator.data;

import android.content.ContentResolver;

import android.database.Cursor;

import android.provider.CalendarContract;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.Attendee;

import io.reactivex.Observable;

import io.reactivex.functions.Function;

import static de.synyx.android.reservator.util.rx.CursorIterable.closeCursorIfLast;
import static de.synyx.android.reservator.util.rx.CursorIterable.fromCursor;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class AttendeeAdapterImpl implements AttendeeAdapter {

    private final ContentResolver contentResolver;

    public AttendeeAdapterImpl() {

        contentResolver = Registry.get(ContentResolver.class);
    }

    @Override
    public Observable<Attendee> getAttendeesForEvent(long eventId) {

        String[] mProjection = {
            CalendarContract.Attendees.ATTENDEE_NAME, //
            CalendarContract.Attendees.ATTENDEE_STATUS
        };
        String isEventAttendee = CalendarContract.Attendees.EVENT_ID + " = " + eventId;
        String isResource = CalendarContract.Attendees.ATTENDEE_TYPE + " = "
            + CalendarContract.Attendees.TYPE_RESOURCE;
        String selection = isEventAttendee + " AND " + isResource;

        String[] mSelectionArgs = {};

        Cursor cursor = contentResolver.query(CalendarContract.Attendees.CONTENT_URI, mProjection, selection,
                mSelectionArgs, null);

        return Observable.fromIterable(fromCursor(cursor)) //
            .doAfterNext(closeCursorIfLast()) //
            .map(toAttendee());
    }


    private Function<Cursor, Attendee> toAttendee() {

        return
            cursor -> {
            String name = cursor.getString(cursor.getColumnIndex(CalendarContract.Attendees.ATTENDEE_NAME));
            int status = cursor.getInt(cursor.getColumnIndex(CalendarContract.Attendees.ATTENDEE_STATUS));

            return new Attendee(name, status);
        };
    }
}
