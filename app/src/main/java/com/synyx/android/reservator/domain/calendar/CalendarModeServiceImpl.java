package com.synyx.android.reservator.domain.calendar;

import android.content.Context;

import com.futurice.android.reservator.R;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class CalendarModeServiceImpl implements CalendarModeService {

    private final Context context;
    private final String CALENDAR_VALUE;

    public CalendarModeServiceImpl(Context context) {

        this.context = context;
        this.CALENDAR_VALUE = context.getString(R.string.calendarMode);
    }

    @Override
    public String getStringCalenderMode(CalendarMode calendarMode) {

        switch (calendarMode) {
            case CALENDAR:
                return CALENDAR_VALUE;

            default:
                return context.getString(R.string.resourcesMode);
        }
    }


    @Override
    public CalendarMode getCalendarModeOfString(String calendarMode) {

        if (calendarMode.equals(CALENDAR_VALUE)) {
            return CalendarMode.CALENDAR;
        } else {
            return CalendarMode.RESOURCES;
        }
    }


    @Override
    public String[] getCalendarModes() {

        return new String[0];
    }


    @Override
    public String getPrefCalenderModeString() {

        return context.getString(R.string.PREFERENCES_CALENDAR_MODE);
    }
}
