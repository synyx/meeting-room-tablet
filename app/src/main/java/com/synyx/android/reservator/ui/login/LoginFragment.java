package com.synyx.android.reservator.ui.login;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.futurice.android.reservator.R;


/**
 * @author  Julian Heetel - heetel@synyx.de
 */
public class LoginFragment extends Fragment implements LoginContract.LoginView {

    LoginContract.LoginPresenter presenter;
    private TextView progressText;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        progressText = view.findViewById(R.id.login_progress_text);
        progressBar = view.findViewById(R.id.login_progress_bar);

        return view;
    }


    @Override
    public void onStart() {

        super.onStart();
        presenter.start();
    }


    @Override
    public void showAccounts() {
    }


    @Override
    public void showErrorDialog(String errorTitle, String errorMessage) {

        new AlertDialog.Builder(getActivity()).setMessage(errorMessage)
            .setTitle(errorTitle)
            .setPositiveButton(R.string.close, ((dialog, which) -> presenter.onErrorDialogCloseButtonClicked()))
            .create()
            .show();
    }


    @Override
    public void showProgress(String message) {

        progressBar.setVisibility(View.VISIBLE);
        progressText.setVisibility(View.VISIBLE);
        progressText.setText(message);
    }


    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.GONE);
        progressText.setVisibility(View.GONE);
    }


    @Override
    public void setPresenter(LoginContract.LoginPresenter presenter) {

        this.presenter = presenter;
    }
}
