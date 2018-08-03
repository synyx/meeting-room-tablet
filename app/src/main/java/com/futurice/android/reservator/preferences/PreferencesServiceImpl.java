package com.futurice.android.reservator.preferences;

import android.content.SharedPreferences;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class PreferencesServiceImpl implements PreferencesService {

    private static final String PREF_RESERVATION_ACCOUNT = "reservationAccount";
    private static final String PREF_ADDRESS_BOOK_OPTION = "addressBookOption";

    private static final String PREF_USER_ACCOUNT = "calendarAccount"; // TODO rename to "userAccount"
    private static final String PREF_ACCOUNT_TYPE = "accountType";
    private SharedPreferences sharedPreferences;

    public PreferencesServiceImpl(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isLoggedIn() {

        final String selectedAccount = sharedPreferences.getString(PREF_RESERVATION_ACCOUNT, "");
        boolean addressBookOption = sharedPreferences.getBoolean(PREF_ADDRESS_BOOK_OPTION, false);

        return selectedAccount.equals("") && !addressBookOption;
    }


    @Override
    public void saveLoginAccount(String reservationAccount, String userAccount) {

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PREF_RESERVATION_ACCOUNT, reservationAccount);

        edit.putString(PREF_USER_ACCOUNT, userAccount);
        edit.apply();
    }


    @Override
    public void saveLoginAccountAndType(String reservationAccount, String userAccount, String accountType) {

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(PREF_RESERVATION_ACCOUNT, reservationAccount);

        edit.putString(PREF_ACCOUNT_TYPE, accountType);

        edit.putString(PREF_USER_ACCOUNT, userAccount);
        edit.apply();
    }
}
