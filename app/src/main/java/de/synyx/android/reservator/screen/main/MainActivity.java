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

import android.widget.ImageButton;
import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.config.Config;
import de.synyx.android.reservator.preferences.PreferencesService;
import de.synyx.android.reservator.screen.ScreenSize;
import de.synyx.android.reservator.screen.main.agenda.AgendaFragment;
import de.synyx.android.reservator.screen.main.lobby.LobbyFragment;
import de.synyx.android.reservator.screen.main.status.StatusFragment;
import de.synyx.android.reservator.screen.settings.SettingsActivity;
import de.synyx.android.reservator.util.ScreenUtil;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import static android.support.design.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;

import static de.synyx.android.reservator.screen.ScreenSize.XSMALL;


public class MainActivity extends AppCompatActivity implements LobbyFragment.RoomSelectionListener {

    private PreferencesService preferencesService;
    private TextView headerTitle;
    private BottomNavigationView navigationBar;
    private TextView clockView;
    private TimeTickBroadcastReciever timeTickBroadcastReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        preferencesService = Config.getInstance(this).getPreferencesService();

        setContentView(R.layout.activity_main);

        headerTitle = findViewById(R.id.main_header_title);

        replaceFragment(LobbyFragment.newInstance());
        setupNavigation();
        setupSettingsButton();

        setClock();
    }


    private void setupSettingsButton() {

        ImageButton button = findViewById(R.id.settings_button);
        button.setOnClickListener(view -> startActivity(new Intent(this, SettingsActivity.class)));
    }


    private void setupNavigation() {

        navigationBar = findViewById(R.id.navigation_bar);
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

                replaceFragment(AgendaFragment.newInstance(preferencesService.getCalendarIdOfDefaultRoom()));
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

        clockView = findViewById(R.id.clock);
        clockView.setText(formatDateAndTime());
    }


    private void registerToTimeTickIntent() {

        timeTickBroadcastReciever = new TimeTickBroadcastReciever(clockView);
        this.registerReceiver(timeTickBroadcastReciever, new IntentFilter(Intent.ACTION_TIME_TICK));
    }


    private String formatDateAndTime() {

        String clockFormat = getClockFormat(ScreenUtil.getSizeOfScreen(this));

        return new SimpleDateFormat(clockFormat, Locale.getDefault()).format(new Date());
    }


    private static String getClockFormat(ScreenSize screenSize) {

        if (screenSize == XSMALL) {
            return "HH:mm";
        }

        return "dd.MM.yy  |  HH:mm";
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
        registerToTimeTickIntent();
    }


    @Override
    protected void onPause() {

        super.onPause();
        unregisterReceiver(timeTickBroadcastReciever);
    }


    @Override
    public void onRoomSelected(long calendarId) {

        navigationBar.setSelectedItemId(R.id.menu_item_room_status);
        replaceFragment(StatusFragment.newInstance(calendarId));
    }


    @Override
    public void setTitle(CharSequence title) {

        headerTitle.setText(title);
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
