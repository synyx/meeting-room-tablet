package com.synyx.android.reservator.ui.login;

import com.futurice.android.reservator.model.DataProxy;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.domain.account.AccountService;
import com.synyx.android.reservator.domain.calendar.CalendarMode;
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

    public LoginPresenterImpl(LoginView view, LoginListener listener, PreferencesService preferencesService) {

        this.view = view;
        this.view.setPresenter(this);
        this.listener = listener;
        this.preferencesService = preferencesService;
    }

    @Override
    public void onAccountSelected(String account) {

        saveSelectedAccount(account);
        listener.onAccountClick(account);
    }


    @Override
    public void onCalendarModeSelected(CalendarMode calendarMode) {

        // TODO implement: save to preferences
    }


    @Override
    public void onErrorDialogCloseButtonClicked() {

        listener.onErrorCloseButtonClick();
    }


    @Override
    public void start() {

        // TODO check if already logged in and handle

        view.showProgress();

        if (hasFatalError()) {
            view.showErrorDialog();
        } else {
            selectAccount();
        }
    }


    @Override
    public void stop() {

        // not needed
    }


    private boolean hasFatalError() {

        return Registry.get(DataProxy.class).hasFatalError();
    }


    private void selectAccount() {

        // TODO rename

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
}
