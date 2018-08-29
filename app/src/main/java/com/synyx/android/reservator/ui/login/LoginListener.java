package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.domain.calendar.CalendarMode;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public interface LoginListener {

    void onErrorCloseButtonClick();


    void onCalenderModeClick(CalendarMode calendarMode);
}
