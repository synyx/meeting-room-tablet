package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.ui.login.LoginContract.LoginPresenter;
import com.synyx.android.reservator.ui.login.LoginContract.LoginView;


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
