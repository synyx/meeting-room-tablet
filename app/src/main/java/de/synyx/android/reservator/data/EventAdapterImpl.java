package de.synyx.android.reservator.data;

import android.content.ContentResolver;
import android.content.ContentUris;

import android.database.Cursor;

import android.net.Uri;

import android.provider.CalendarContract;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.event.Event;

import io.reactivex.Observable;

import io.reactivex.functions.Function;

import org.joda.time.LocalDateTime;

import java.util.Date;

import static de.synyx.android.reservator.util.rx.CursorIterable.closeCursorIfLast;
import static de.synyx.android.reservator.util.rx.CursorIterable.fromCursor;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class EventAdapterImpl implements EventAdapter {

    private static final String NO_SORT = null;
    private static final int ONE_DAY_IN_MILLIS = 24 * 60 * 60 * 1000;
    private final ContentResolver contentResolver;

    public EventAdapterImpl() {

        contentResolver = Registry.get(ContentResolver.class);
    }

    @Override
    public Observable<Event> getEventsForRoom(long roomId) {

        String[] mProjection = {
            CalendarContract.Instances.EVENT_ID, CalendarContract.Instances.TITLE, CalendarContract.Instances.BEGIN,
            CalendarContract.Instances.END, CalendarContract.Instances.ORGANIZER
        };
        String selectionClause = CalendarContract.Instances.CALENDAR_ID + " = " + roomId;

        String[] mSelectionArgs = {};

        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        long now = new Date().getTime();
        ContentUris.appendId(builder, now - ONE_DAY_IN_MILLIS);
        ContentUris.appendId(builder, now + ONE_DAY_IN_MILLIS);

        Cursor result = contentResolver.query(builder.build(), mProjection, selectionClause, mSelectionArgs, NO_SORT);

        return Observable.fromIterable(fromCursor(result)) //
            .doAfterNext(closeCursorIfLast()) //
            .map(toEvent());
    }


    @NonNull
    private Function<Cursor, Event> toEvent() {

        return
            cursor -> {
            String title = cursor.getString(cursor.getColumnIndex(CalendarContract.Instances.TITLE));
            long beginMillis = cursor.getLong(cursor.getColumnIndex(CalendarContract.Instances.BEGIN));
            long endMillis = cursor.getLong(cursor.getColumnIndex(CalendarContract.Instances.END));

            LocalDateTime begin = LocalDateTime.fromDateFields(new Date(beginMillis));
            LocalDateTime end = LocalDateTime.fromDateFields(new Date(endMillis));

            return new Event(title, begin, end);
        };
    }
}
