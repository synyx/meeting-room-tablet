package de.synyx.android.reservator.screen.main;

import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.screen.main.lobby.LobbyFragment;
import de.synyx.android.reservator.screen.main.status.StatusFragment;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import static de.synyx.android.reservator.legacy.OpenOldRoomActivityAdapter.openRoomActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        enableFullscreen();
        setContentView(R.layout.activity_newlobby);

        replaceFragment(LobbyFragment.newInstance());
        setupNavigation();

        setClock();
    }


    private void setupNavigation() {

        BottomNavigationView navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setOnNavigationItemSelectedListener(this::onNavigationSelect);
    }


    private boolean onNavigationSelect(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_item_room_status:
                replaceFragment(StatusFragment.newInstance());
                break;

            case R.id.room_agenda:

                // TODO: max 23.11.18 Load default room
                RoomCalendar roomCalendar = new RoomCalendar(1L, "Holodeck (8)", "wohnzimmmer@synyx.de");
                openRoomActivity(getApplicationContext(), roomCalendar);
                break;

            default:
                replaceFragment(LobbyFragment.newInstance());
        }

        return true;
    }


    private void replaceFragment(Fragment fragment) {

        getSupportFragmentManager() //
        .beginTransaction() //
        .replace(R.id.content_main, fragment) //
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
