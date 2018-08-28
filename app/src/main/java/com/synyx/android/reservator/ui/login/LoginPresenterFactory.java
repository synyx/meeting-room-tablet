package net.synyx.mobile.reservator.ui.login;

/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginPresenterFactory {

    public LoginContract.LoginPresenter createPresenter(LoginContract.LoginView view) {

        return new LoginPresenterImpl(view);
    }
}
