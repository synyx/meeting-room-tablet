package com.synyx.android.reservator.ui.login;

import android.accounts.Account;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public interface LoginListener {

    void onErrorCloseButtonClick();


    void onAccountClick(String account);


    Account[] fetchAccounts();
}
