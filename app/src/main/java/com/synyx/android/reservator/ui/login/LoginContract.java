package com.synyx.android.reservator.ui.login;

import com.synyx.android.reservator.domain.calendar.CalendarMode;
import com.synyx.android.reservator.mvp.BasePresenter;
import com.synyx.android.reservator.mvp.BaseView;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public interface LoginContract {

    interface LoginView extends BaseView<LoginPresenter> {

        void showAccountSelection(String[] accounts);


        void showCalendarModeSelection();


        void showErrorDialog();


        void showProgress();
    }

    interface LoginPresenter extends BasePresenter {

        void onAccountSelected(String account);


        void onCalendarModeSelected(CalendarMode calendarMode);


        void onErrorDialogCloseButtonClicked();
    }
}
