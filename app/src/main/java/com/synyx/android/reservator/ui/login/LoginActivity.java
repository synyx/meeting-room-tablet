package com.synyx.android.reservator.ui.login;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.futurice.android.reservator.ModeSelectionActivity;
import com.futurice.android.reservator.R;
import com.futurice.android.reservator.ReservatorActivity;

import com.synyx.android.reservator.config.Registry;


public class LoginActivity extends ReservatorActivity implements LoginListener {

    static final int REQUEST_LOBBY = 0;
    private static final String FRAGMENT_TAG = "login-fragment";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);

        if (fragment == null) {
            fragment = new LoginFragment();
            fragmentManager.beginTransaction().replace(R.id.relativeLayout1, fragment).commit();
        }

        LoginPresenterFactory presenterFactory = Registry.get(LoginPresenterFactory.class);
        presenterFactory.createPresenter((LoginContract.LoginView) fragment, this, config.getPreferencesService());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        finish();
    }


    @Override
    public void onErrorCloseButtonClick() {

        this.finish();
    }


    @Override
    public void onAccountClick(String account) {

        moveToModeSelection();
    }


    public void moveToModeSelection() {

        Intent i = new Intent(this, ModeSelectionActivity.class);
        startActivityForResult(i, REQUEST_LOBBY);
    }
}
