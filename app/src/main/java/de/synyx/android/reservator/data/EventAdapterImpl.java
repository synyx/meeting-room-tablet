package de.synyx.android.reservator.data;

import android.content.ContentResolver;
import android.content.ContentUris;

import android.database.Cursor;

import android.net.Uri;

import android.provider.CalendarContract.Instances;

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

    private final ContentResolver contentResolver;

    public EventAdapterImpl() {

        contentResolver = Registry.get(ContentResolver.class);
    }

    @Override
    public Observable<Event> getEventsForRoom(long roomId) {

        String[] projection = {
            Instances.EVENT_ID, //
            Instances.TITLE, //
            Instances.BEGIN, //
            Instances.END,
        };
        String selection = Instances.CALENDAR_ID + " = " + roomId;
        String sortChronological = Instances.BEGIN + " ASC";

        Cursor result = contentResolver.query(constructContentUri(), projection, selection, null, sortChronological);

        return Observable.fromIterable(fromCursor(result)) //
            .doAfterNext(closeCursorIfLast()) //
            .map(toEvent());
    }


    private Uri constructContentUri() {

        Uri.Builder builder = Instances.CONTENT_URI.buildUpon();
        long now = new Date().getTime();
        long endOfDay = LocalDateTime.now().withTime(23, 59, 59, 999).toDate().getTime();
        ContentUris.appendId(builder, now);
        ContentUris.appendId(builder, endOfDay);

        return builder.build();
    }


    @NonNull
    private Function<Cursor, Event> toEvent() {

        return
            cursor -> {
            long eventId = cursor.getLong(cursor.getColumnIndex(Instances.EVENT_ID));
            String title = cursor.getString(cursor.getColumnIndex(Instances.TITLE));
            long beginMillis = cursor.getLong(cursor.getColumnIndex(Instances.BEGIN));
            long endMillis = cursor.getLong(cursor.getColumnIndex(Instances.END));

            LocalDateTime begin = LocalDateTime.fromDateFields(new Date(beginMillis));
            LocalDateTime end = LocalDateTime.fromDateFields(new Date(endMillis));

            return new Event(eventId, title, begin, end);
        };
    }
}
