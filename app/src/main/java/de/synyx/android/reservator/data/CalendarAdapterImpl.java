package de.synyx.android.reservator.data;

import android.content.ContentResolver;

import android.database.Cursor;

import android.provider.CalendarContract;

import android.support.annotation.NonNull;

import android.text.TextUtils;

import com.futurice.android.reservator.model.Room;
import com.futurice.android.reservator.model.platformcalendar.PlatformCalendarRoom;

import de.synyx.android.reservator.config.Registry;
import de.synyx.android.reservator.domain.account.AccountService;
import de.synyx.android.reservator.domain.calendar.CalendarMode;
import de.synyx.android.reservator.domain.calendar.CalendarModeService;
import de.synyx.android.reservator.domain.room.RoomCalendar;

import io.reactivex.Observable;

import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.List;

import static de.synyx.android.reservator.util.rx.CursorIterable.closeCursorIfLast;
import static de.synyx.android.reservator.util.rx.CursorIterable.fromCursor;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class CalendarAdapterImpl implements CalendarAdapter {

    private static final String RESSOURCE_SUFFIX = "*@resource.calendar.google.com";

    private final CalendarModeService calendarModeService;
    private final ContentResolver contentResolver;
    private final AccountService accountService;

    public CalendarAdapterImpl() {

        this.contentResolver = Registry.get(ContentResolver.class);
        this.calendarModeService = Registry.get(CalendarModeService.class);
        this.accountService = Registry.get(AccountService.class);
    }

    @Override
    public List<Room> getRooms() {

        return mapToRooms(loadRoomCalendars());
    }


    @Override
    public Observable<RoomCalendar> getNewRooms() {

        return
            Observable.fromIterable(fromCursor(loadRoomCalendars())) //
            .doAfterNext(closeCursorIfLast()) //
            .map(toRoomCalendar());
    }


    private Cursor loadRoomCalendars() {

        String[] mProjection = {
            CalendarContract.Calendars._ID, //
            CalendarContract.Calendars.OWNER_ACCOUNT, //
            CalendarContract.Calendars.NAME, //
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
        };

        List<String> mSelectionClauses = new ArrayList<>();
        List<String> mSelectionArgs = new ArrayList<>();

        addOwnerAccountSelection(mSelectionClauses, mSelectionArgs);
        addAccountNameSelection(mSelectionClauses, mSelectionArgs);

        return queryCalendarProvider(mProjection, mSelectionClauses, mSelectionArgs);
    }


    @NonNull
    private static Function<Cursor, RoomCalendar> toRoomCalendar() {

        return c -> new RoomCalendar(getIdFrom(c), getNameFrom(c));
    }


    @NonNull
    private static List<Room> mapToRooms(Cursor cursor) {

        List<Room> rooms = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                rooms.add(mapCursorEntryToRoom(cursor));
            }

            cursor.close();
        }

        return rooms;
    }


    private Cursor queryCalendarProvider(String[] mProjection, List<String> mSelectionClauses,
        List<String> selectionArgs) {

        String selection = TextUtils.join(" AND ", mSelectionClauses);

        String[] selectionArgsArray = selectionArgs.toArray(new String[0]);

        return contentResolver.query(CalendarContract.Calendars.CONTENT_URI, mProjection, selection,
                selectionArgsArray, null);
    }


    private void addAccountNameSelection(List<String> mSelectionClauses, List<String> mSelectionArgs) {

        mSelectionClauses.add(CalendarContract.Calendars.ACCOUNT_NAME + " = ?");
        mSelectionArgs.add(accountService.getUserAccountName());
    }


    private void addOwnerAccountSelection(List<String> mSelectionClauses, List<String> mSelectionArgs) {

        CalendarMode calendarMode = calendarModeService.getPrefCalenderMode();

        if (calendarMode == CalendarMode.RESOURCES) {
            mSelectionClauses.add(CalendarContract.Calendars.OWNER_ACCOUNT + " GLOB ?");
            mSelectionArgs.add(RESSOURCE_SUFFIX);
        } else {
            String accountType = accountService.getUserAccountType();
            mSelectionClauses.add(CalendarContract.Calendars.OWNER_ACCOUNT + " LIKE '%" + accountType + "'");
        }
    }


    @NonNull
    private static PlatformCalendarRoom mapCursorEntryToRoom(Cursor cursor) {

        long id = getIdFrom(cursor);
        String ownerAccount = getOwnerAccountFrom(cursor);
        String name = getNameFrom(cursor);

        return new PlatformCalendarRoom(name, ownerAccount, id, "", false);
    }


    private static long getIdFrom(Cursor cursor) {

        return cursor.getLong(cursor.getColumnIndex(CalendarContract.Calendars._ID));
    }


    private static String getOwnerAccountFrom(Cursor cursor) {

        return cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.OWNER_ACCOUNT));
    }


    private static String getNameFrom(Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.NAME));

        return name == null //
            ? cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME)) //
            : name;
    }
}
