package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.mvp.BasePresenter;
import com.synyx.android.reservator.mvp.BaseView;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public interface LoginContract {

    interface LoginView extends BaseView<LoginPresenter> {

        void showAccounts(String[] accounts);


        void showErrorDialog();


        void showProgress();


        void hideProgress();
    }

    interface LoginPresenter extends BasePresenter {

        void onAccountSelected(String account);


        void onErrorDialogCloseButtonClicked();
    }
}
