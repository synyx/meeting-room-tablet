package de.synyx.android.reservator.screen.login;

import android.content.Context;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.config.Registry;


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
