package com.synyx.android.reservator.preferences;

/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public interface PreferencesService {

    boolean isLoggedIn();


    void saveLoginAccount(String reservationAccount, String userAccount);


    void saveLoginAccountAndType(String reservationAccount, String userAccount, String accountType);


    void saveCalendarMode(String calendarMode);


    String getSelectedCalenderMode();
}
