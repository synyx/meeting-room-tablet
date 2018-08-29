package com.synyx.android.reservator.ui.login;

import com.futurice.android.reservator.model.DataProxy;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.domain.account.AccountService;
import com.synyx.android.reservator.domain.calendar.CalendarMode;
import com.synyx.android.reservator.domain.calendar.CalendarModeService;
import com.synyx.android.reservator.preferences.PreferencesService;
import com.synyx.android.reservator.ui.login.LoginContract.LoginPresenter;
import com.synyx.android.reservator.ui.login.LoginContract.LoginView;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginPresenterImpl implements LoginPresenter {

    private final LoginListener listener;
    private final PreferencesService preferencesService;
    private LoginView view;
    private CalendarMode calendarMode = Registry.get(CalendarMode.class);
    private CalendarModeService calendarModeService;

    public LoginPresenterImpl(LoginView view, LoginListener listener, PreferencesService preferencesService) {

        this.view = view;
        this.view.setPresenter(this);
        this.listener = listener;
        this.preferencesService = preferencesService;
        this.calendarModeService = Registry.get(CalendarModeService.class);
    }

    @Override
    public void onAccountSelected(String account) {

        saveSelectedAccount(account);
    }


    @Override
    public void onCalendarModeSelected(String calendarMode) {

        saveSelectedCalendarMode(calendarMode);
    }


    @Override
    public void onErrorDialogCloseButtonClicked() {

        listener.onErrorCloseButtonClick();
    }


    @Override
    public void start() {

        view.showProgress();

        if (hasFatalError()) {
            view.showErrorDialog();
        } else if (preferencesService.isLoggedIn()) {
            checkAccounts();
        }

        view.showCalendarModeSelection(calendarModeService.getCalendarModes());
    }


    @Override
    public void stop() {

        // not needed
    }


    private boolean hasFatalError() {

        return Registry.get(DataProxy.class).hasFatalError();
    }


    private void checkAccounts() {

        String[] accountNames = Registry.get(AccountService.class).getAccountNames();

        if (accountNames.length == 1) {
            saveSelectedAccount(accountNames[0]);
        } else {
            view.showAccountSelection(accountNames);
        }
    }


    private void saveSelectedAccount(String account) {

        preferencesService.saveLoginAccountAndType(account, account, account.substring(account.indexOf("@") + 1));
    }


    private void saveSelectedCalendarMode(String calendarMode) {

        preferencesService.saveCalendarMode(calendarModeService.getPrefCalenderModeString(), calendarMode);
    }
}
