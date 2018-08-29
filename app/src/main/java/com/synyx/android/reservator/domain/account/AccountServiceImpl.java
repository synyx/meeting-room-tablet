package com.synyx.android.reservator.domain.account;

import android.accounts.Account;
import android.accounts.AccountManager;

import android.content.Context;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class AccountServiceImpl implements AccountService {

    private Context context;

    public AccountServiceImpl(Context context) {

        this.context = context;
    }

    @Override
    public String[] getAccountNames() {

        Account[] accounts = AccountManager.get(context).getAccounts();

        String[] accountNames = new String[accounts.length];

        for (int i = 0; i < accounts.length; i++) {
            accountNames[i] = accounts[i].name;
        }

        return accountNames;
    }
}
