package de.synyx.android.reservator.business.calendar;

import de.synyx.android.reservator.domain.CalendarMode;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public interface CalendarModeService {

    String getStringCalenderMode(CalendarMode calendarMode);


    CalendarMode getCalendarModeOfString(String calendarMode);


    String[] getCalendarModes();


    CalendarMode getPrefCalenderMode();
}
