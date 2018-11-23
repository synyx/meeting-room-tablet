package de.synyx.android.reservator.screen.main;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.screen.main.lobby.LobbyFragment;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        enableFullscreen();
        setContentView(R.layout.activity_newlobby);

        setFragment();

        setClock();
    }


    private void setFragment() {

        getSupportFragmentManager().beginTransaction()
            .replace(R.id.content_main, LobbyFragment.newInstance())
            .commit();
    }


    private void setClock() {

        TextView clockView = findViewById(R.id.clock);
        Date now = new Date();
        String clockText = new SimpleDateFormat("dd.MM.yy  |  HH:mm", Locale.getDefault()).format(now);

        clockView.setText(clockText);
    }


    private void enableFullscreen() {

        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY //
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE //
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
