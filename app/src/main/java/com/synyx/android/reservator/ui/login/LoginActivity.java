package com.synyx.android.reservator.ui.login;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.widget.Toast;

import com.futurice.android.reservator.LobbyActivity;
import com.futurice.android.reservator.R;
import com.futurice.android.reservator.ReservatorActivity;
import com.futurice.android.reservator.model.AddressBook;
import com.futurice.android.reservator.model.AddressBookUpdatedListener;
import com.futurice.android.reservator.model.ReservatorException;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.domain.calendar.CalendarMode;


public class LoginActivity extends ReservatorActivity implements LoginListener, AddressBookUpdatedListener {

    private static final int REQUEST_LOBBY = 0;
    private static final String FRAGMENT_TAG = "login-fragment";
    private boolean addressBookOk = false;

    /**
     * Get an intent to create a new instance of this activity.
     *
     * @param  context  the package context
     *
     * @return  intent to call this activity
     */
    public static Intent getIntent(@NonNull Context context) {

        return new Intent(context, LoginActivity.class);
    }


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

        AddressBook ab = this.getResApplication().getAddressBook();
        ab.refetchEntries();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        finish();
    }


    @Override
    public void onErrorCloseButtonClick() {

        finish();
    }


    @Override
    public void onCalenderModeClick(CalendarMode calendarMode) {

        Intent intent = new Intent(this, LobbyActivity.class);
        startActivityForResult(intent, REQUEST_LOBBY);
    }


    @Override
    public void addressBookUpdated() {

        addressBookOk = true;
    }


    @Override
    public void addressBookUpdateFailed(ReservatorException e) {

        addressBookOk = false;

        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        setContentView(R.layout.login_activity);
    }
}
