package de.synyx.android.reservator.business.calendar;

import android.content.Context;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.config.Config;
import de.synyx.android.reservator.domain.CalendarMode;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class CalendarModeServiceImpl implements CalendarModeService {

    private final String CALENDAR_VALUE;
    private final String RESOURCES_VALUE;
    private final String prefCalenderMode;

    public CalendarModeServiceImpl(Context context) {

        this.CALENDAR_VALUE = context.getString(R.string.calendarMode);
        this.RESOURCES_VALUE = context.getString(R.string.resourcesMode);
        this.prefCalenderMode = Config.getInstance(context).getPreferencesService().getSelectedCalenderMode();
    }

    @Override
    public String getStringCalenderMode(CalendarMode calendarMode) {

        switch (calendarMode) {
            case CALENDAR:
                return CALENDAR_VALUE;

            case RESOURCES:
                return RESOURCES_VALUE;

            default:
                return "";
        }
    }


    @Override
    public CalendarMode getCalendarModeOfString(String calendarMode) {

        if (calendarMode.equals(CALENDAR_VALUE)) {
            return CalendarMode.CALENDAR;
        } else if (calendarMode.equals(RESOURCES_VALUE)) {
            return CalendarMode.RESOURCES;
        } else {
            return CalendarMode.NO_SELECTED_MODE;
        }
    }


    @Override
    public String[] getCalendarModes() {

        return new String[] { CALENDAR_VALUE, RESOURCES_VALUE };
    }


    @Override
    public CalendarMode getPrefCalenderMode() {

        return getCalendarModeOfString(prefCalenderMode);
    }
}