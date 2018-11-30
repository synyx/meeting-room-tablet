package de.synyx.android.reservator.screen.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;

import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.config.Config;
import de.synyx.android.reservator.domain.room.RoomCalendar;
import de.synyx.android.reservator.preferences.PreferencesService;
import de.synyx.android.reservator.screen.main.lobby.LobbyFragment;
import de.synyx.android.reservator.screen.main.status.StatusFragment;
import de.synyx.android.reservator.screen.settings.SettingsActivity;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import static android.support.design.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;

import static de.synyx.android.reservator.legacy.OpenOldRoomActivityAdapter.openRoomActivity;


public class MainActivity extends AppCompatActivity implements LobbyFragment.RoomSelectionListener {

    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        preferencesService = Config.getInstance(this).getPreferencesService();

        setContentView(R.layout.activity_main);

        replaceFragment(LobbyFragment.newInstance());
        setupNavigation();
        setupSettingsButton();

        setClock();
    }


    private void setupSettingsButton() {

        Button button = findViewById(R.id.settings_button);
        button.setOnClickListener(view -> startActivity(new Intent(this, SettingsActivity.class)));
    }


    private void setupNavigation() {

        BottomNavigationView navigationBar = findViewById(R.id.navigation_bar);
        navigationBar.setOnNavigationItemSelectedListener(this::onNavigationSelect);
        navigationBar.setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
        navigationBar.setSelectedItemId(R.id.menu_item_all_rooms);
    }


    private boolean onNavigationSelect(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_item_room_status:

                replaceFragment(StatusFragment.newInstance(preferencesService.getCalendarIdOfDefaultRoom()));
                break;

            case R.id.menu_item_room_agenda:

                // TODO: max 23.11.18 - Load default room
                RoomCalendar roomCalendar = new RoomCalendar(1L, "Holodeck (8)", "wohnzimmmer@synyx.de");
                openRoomActivity(this, roomCalendar);
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
        clockView.setText(formatDateAndTime());
        this.registerReceiver(new TimeTickBroadcastReciever(clockView), new IntentFilter(Intent.ACTION_TIME_TICK));
    }


    private String formatDateAndTime() {

        return new SimpleDateFormat("dd.MM.yy  |  HH:mm", Locale.getDefault()).format(new Date());
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


    @Override
    protected void onResume() {

        super.onResume();
        enableFullscreen();
    }


    @Override
    public void onRoomSelected(long calendarId) {

        replaceFragment(StatusFragment.newInstance(calendarId));
    }

    private class TimeTickBroadcastReciever extends BroadcastReceiver {

        private final TextView clockView;

        public TimeTickBroadcastReciever(TextView clockView) {

            this.clockView = clockView;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            clockView.setText(formatDateAndTime());
        }
    }
}
