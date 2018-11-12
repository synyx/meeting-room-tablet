package com.synyx.android.reservator.ui.login;

import android.content.Context;

import android.support.annotation.NonNull;

import com.synyx.android.reservator.config.Registry;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginConfig {

    private LoginConfig() {

        // hide
    }

    public static void init(@NonNull Context context) {

        Registry.put(LoginPresenterFactory.class, new LoginPresenterFactory());
    }
}
