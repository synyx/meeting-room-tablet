package de.synyx.android.reservator.config;

import android.app.Application;

import android.content.Context;

import android.support.annotation.NonNull;

import de.synyx.android.reservator.preferences.PreferencesService;
import de.synyx.android.reservator.preferences.PreferencesServiceImpl;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class Config {

    private static final String PREFS_NAME = "prefs";
    private static Config instance;

    private PreferencesService preferencesService;

    private Config(@NonNull Context context) {

        preferencesService = new PreferencesServiceImpl(context.getSharedPreferences(PREFS_NAME,
                    Application.MODE_PRIVATE));
    }

    @NonNull
    public static Config getInstance(Context context) {

        if (instance == null) {
            instance = new Config(context);
        }

        return instance;
    }


    public PreferencesService getPreferencesService() {

        return preferencesService;
    }


    public void setPreferencesService(PreferencesService preferencesService) {

        this.preferencesService = preferencesService;
    }
}
