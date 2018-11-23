package de.synyx.android.reservator.domain.room;

/**
 * @author  Julia Dasch - dasch@synyx.de
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomCalendar {

    private final String name;
    private final long calendarId;

    public RoomCalendar(long calendarId, String name) {

        this.calendarId = calendarId;
        this.name = name;
    }

    public String getName() {

        return name;
    }


    public long getCalendarId() {

        return calendarId;
    }
}
