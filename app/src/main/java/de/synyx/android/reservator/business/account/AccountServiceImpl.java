package de.synyx.android.reservator.business.account;

import android.accounts.Account;
import android.accounts.AccountManager;

import android.content.Context;

import de.synyx.android.reservator.config.Config;


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


    @Override
    public String getUserAccountName() {

        return Config.getInstance(context).getPreferencesService().getUserAccountName();
    }


    @Override
    public String getUserAccountType() {

        return Config.getInstance(context).getPreferencesService().getUserAccountType();
    }
}
