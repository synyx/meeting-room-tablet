package net.synyx.mobile.reservator.ui.login;

import com.futurice.android.reservator.mvp.BasePresenter;
import com.futurice.android.reservator.mvp.BaseView;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public interface LoginContract {

    interface LoginView extends BaseView<LoginPresenter> {

        void showAccounts();


        void showErrorDialog(String errorTitle, String errorMessage);
    }

    interface LoginPresenter extends BasePresenter {

        void onAccountSelected();


        void onErrorDialogCloseButtonClicked();
    }
}
