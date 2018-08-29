package com.synyx.android.reservator.ui.login;

import android.content.Context;

import android.support.annotation.NonNull;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.domain.account.AccountService;
import com.synyx.android.reservator.domain.account.AccountServiceImpl;
import com.synyx.android.reservator.domain.calendar.CalendarModeService;
import com.synyx.android.reservator.domain.calendar.CalendarModeServiceImpl;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginConfig {

    private LoginConfig() {

        // hide
    }

    public static void init(@NonNull Context context) {

        Registry.put(LoginPresenterFactory.class, new LoginPresenterFactory());
        Registry.put(AccountService.class, new AccountServiceImpl(context));
        Registry.put(CalendarModeService.class, new CalendarModeServiceImpl(context));
    }
}
