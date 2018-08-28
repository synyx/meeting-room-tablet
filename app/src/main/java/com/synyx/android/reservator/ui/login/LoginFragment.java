package com.synyx.android.reservator.ui.login;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginFragment extends Fragment implements LoginContract.LoginView {

    LoginContract.LoginPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }


    @Override
    public void showAccounts() {
    }


    @Override
    public void showErrorDialog(String errorTitle, String errorMessage) {
    }


    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {

        this.presenter = presenter;
    }
}
