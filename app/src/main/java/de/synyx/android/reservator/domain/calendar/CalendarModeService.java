package de.synyx.android.reservator.domain.calendar;

/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public interface CalendarModeService {

    String getStringCalenderMode(CalendarMode calendarMode);


    CalendarMode getCalendarModeOfString(String calendarMode);


    String[] getCalendarModes();


    CalendarMode getPrefCalenderMode();
}
