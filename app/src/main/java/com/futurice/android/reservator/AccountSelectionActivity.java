package com.futurice.android.reservator;

import android.accounts.Account;
import android.accounts.AccountManager;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class AccountSelectionActivity extends ReservatorActivity {

    static final int REQUEST_LOBBY = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.account_selection);
        selectAccount();
    }


    @Override
    public void onResume() {

        super.onResume();
        selectAccount();
    }


    @Override
    public void onPause() {

        super.onPause();
    }


    public void moveToModeSelection() {

        Intent i = new Intent(this, ModeSelectionActivity.class);
        startActivityForResult(i, REQUEST_LOBBY);
    }


    public String[] fetchAccounts() {

        List<String> accountsList = new ArrayList<String>();

        for (Account account : AccountManager.get(this).getAccounts()) {
            accountsList.add(account.name);
        }

        return accountsList.toArray(new String[accountsList.size()]);
    }


    public void selectAccount() {

        if (config.getPreferencesService().isLoggedIn()) {
            final String[] values = fetchAccounts();

            // Only one Google account available so the selection isn't needed.
            if (values.length == 1) {
                config.getPreferencesService().saveLoginAccount(values[0], values[0]);

                moveToModeSelection();
            } else {
                // Build an alert dialog to select the account.
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.selectAccount);
                builder.setItems(values, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                            config.getPreferencesService()
                            .saveLoginAccountAndType(values[which], values[which],
                                values[which].substring(values[which].indexOf("@") + 1));

                            moveToModeSelection();
                        }
                    });

                builder.show();
            }
        } else {
            moveToModeSelection();
        }
    }
}
