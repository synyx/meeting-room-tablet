package net.synyx.mobile.reservator.ui.login;

import net.synyx.mobile.reservator.ui.login.LoginContract.LoginPresenter;
import net.synyx.mobile.reservator.ui.login.LoginContract.LoginView;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    public LoginPresenterImpl(LoginView view) {

        this.view = view;
    }

    @Override
    public void onAccountSelected() {
    }


    @Override
    public void onErrorDialogCloseButtonClicked() {
    }


    @Override
    public void start() {
    }


    @Override
    public void stop() {

        // not needed
    }
}
