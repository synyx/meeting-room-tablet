package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.mvp.BasePresenter;
import com.synyx.android.reservator.mvp.BaseView;

import java.util.List;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public interface LoginContract {

    interface LoginView extends BaseView<LoginPresenter> {

        void askForPermissions(List<String> neededPermissions);


        void showAccountSelection(String[] accounts);


        void showCalendarModeSelection(String[] calendarModes);


        void showErrorDialog();


        void showProgress();
    }

    interface LoginPresenter extends BasePresenter {

        void askForPermissionsAgain();


        void onAllPermissionsGranted();


        void onAccountSelected(String account);


        void onCalendarModeSelected(String calendarMode);


        void onErrorDialogCloseButtonClicked();
    }
}
