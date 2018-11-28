package de.synyx.android.reservator.screen;

import de.synyx.android.reservator.domain.event.Event;
import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.domain.room.RoomState;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomDto {

    private final RoomCalendar room;
    private final Event event;
    private final Event nextEvent;

    public RoomDto(RoomCalendar room, Event event, Event nextEvent) {

        this.room = room;
        this.event = event;
        this.nextEvent = nextEvent;
    }

    public String getRoomName() {

        return room.getName();
    }


    public String getRoomTime() {

        if (event != null) {
            return printPeriod(event.getRemainingTime());
        }

        if (nextEvent != null) {
            return printPeriod(nextEvent.getTimeUntilBegin());
        }

        return "Frei für den restlichen Tag";
    }


    private String printPeriod(Period remainingTime) {

        PeriodFormatter formatter = new PeriodFormatterBuilder().appendDays()
                .appendSuffix(" day", " days")
                .appendSeparator(" ")
                .appendHours()
                .appendSuffix(" h")
                .appendSeparator(" ")
                .appendMinutes()
                .appendSuffix(" min")
                .toFormatter();

        return formatter.print(remainingTime.normalizedStandard());
    }


    public String getActiveEventName() {

        if (event == null) {
            return null;
        }

        return event.getName();
    }


    public String getNextEventName() {

        if (nextEvent == null) {
            return null;
        }

        return nextEvent.getName();
    }


    public boolean isAvailable() {

        return event == null;
    }


    public RoomState getStatus() {

        if (isAvailable())
            return RoomState.AVAILABLE;

        return RoomState.UNAVAILABLE;
    }


    public long getCalendarId() {

        return room.getCalendarId();
    }
}
