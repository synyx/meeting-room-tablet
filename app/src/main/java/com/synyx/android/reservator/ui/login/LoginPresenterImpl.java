package com.synyx.android.reservator.ui.login;

import android.accounts.Account;

import android.annotation.SuppressLint;

import com.futurice.android.reservator.model.DataProxy;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.preferences.PreferencesService;
import com.synyx.android.reservator.ui.login.LoginContract.LoginPresenter;
import com.synyx.android.reservator.ui.login.LoginContract.LoginView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginPresenterImpl implements LoginPresenter {

    private final LoginListener listener;
    private final PreferencesService preferencesService;
    private LoginView view;
    private Account[] accounts;

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
    public void onErrorDialogCloseButtonClicked() {

        view.hideProgress();
        listener.onErrorCloseButtonClick();
    }


    @Override
    public void start() {

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


    public boolean hasFatalError() {

        return Registry.get(DataProxy.class).hasFatalError();
    }


    @SuppressLint("NewApi")
    private void selectAccount() {

        accounts = listener.fetchAccounts();

        List<String> accountNames;

        if (accounts.length == 1) {
            saveSelectedAccount(accounts[0].name);
        } else {
            accountNames = Arrays.stream(accounts).map(account -> account.name).collect(Collectors.toList());
            view.showAccounts(accountNames.toArray(new String[accountNames.size()]));
        }
    }


    private void saveSelectedAccount(String account) {

        preferencesService.saveLoginAccountAndType(account, account, account.substring(account.indexOf("@") + 1));
    }
}
