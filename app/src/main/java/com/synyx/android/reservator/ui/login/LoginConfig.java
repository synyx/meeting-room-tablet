package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.config.Registry;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginConfig {

    private LoginConfig() {

        // hide
    }

    public static void init() {

        Registry.put(LoginPresenterFactory.class, new LoginPresenterFactory());
    }
}
