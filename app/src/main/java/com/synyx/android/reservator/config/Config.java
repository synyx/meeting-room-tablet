package com.synyx.android.reservator.config;

import android.accounts.Account;
import android.accounts.AccountManager;

import android.app.Application;

import android.content.Context;

import android.support.annotation.NonNull;

import com.synyx.android.reservator.preferences.PreferencesService;
import com.synyx.android.reservator.preferences.PreferencesServiceImpl;

import lombok.Getter;
import lombok.Setter;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class Config {

    private static final String PREFS_NAME = "prefs";
    private static Config instance;

    @Getter
    private Account[] accounts;

    @Setter
    @Getter
    private PreferencesService preferencesService;

    private Config(@NonNull Context context) {

        preferencesService = new PreferencesServiceImpl(context.getSharedPreferences(PREFS_NAME,
                    Application.MODE_PRIVATE));
        accounts = AccountManager.get(context).getAccounts();
    }

    @NonNull
    public static Config getInstance(Context context) {

        if (instance == null) {
            instance = new Config(context);
        }

        return instance;
    }
}
