package com.synyx.android.reservator.util.proxy;

import android.content.ContentResolver;
import android.content.Context;

import android.support.annotation.NonNull;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.data.CalendarAdapter;
import com.synyx.android.reservator.data.CalendarAdapterImpl;
import com.synyx.android.reservator.domain.account.AccountService;
import com.synyx.android.reservator.domain.account.AccountServiceImpl;
import com.synyx.android.reservator.domain.calendar.CalendarModeService;
import com.synyx.android.reservator.domain.calendar.CalendarModeServiceImpl;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class ProxyConfig {

    private ProxyConfig() {

        // hide
    }

    public static void init(@NonNull Context context) {

        Registry.put(PermissionManager.class, new PermissionManager(context));
        Registry.put(ContentResolver.class, context.getContentResolver());
        Registry.put(CalendarModeService.class, new CalendarModeServiceImpl(context));
        Registry.put(AccountService.class, new AccountServiceImpl(context));
        Registry.put(CalendarAdapter.class, new CalendarAdapterImpl());
    }
}
