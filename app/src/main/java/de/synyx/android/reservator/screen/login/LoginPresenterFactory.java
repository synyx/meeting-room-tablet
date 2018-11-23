package de.synyx.android.reservator.screen.login;

import de.synyx.android.reservator.preferences.PreferencesService;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginPresenterFactory {

    public LoginContract.LoginPresenter createPresenter(LoginContract.LoginView view, LoginListener listener,
        PreferencesService preferencesService) {

        return new LoginPresenterImpl(view, listener, preferencesService);
    }
}
