package com.synyx.android.reservator.ui.lobby;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import com.synyx.android.reservator.domain.event.Event;
import com.synyx.android.reservator.domain.room.Room;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;


public class NewLobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newlobby);

        setRoomRecyclerView();
        setClock();
    }


    private void setRoomRecyclerView() {

        RecyclerView roomsRecyclerView = findViewById(R.id.roomRecyclerView);
        roomsRecyclerView.setHasFixedSize(true);
        roomsRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));

        RecyclerView.Adapter<RoomViewHolder> roomRecyclerAdapter = new RoomRecyclerAdapter(mockData());
        roomsRecyclerView.setAdapter(roomRecyclerAdapter);

        setClock();
    }


   private void setClock() {

        TextView clockView = findViewById(R.id.clock);
        Date now = new Date();
        String clockText = new SimpleDateFormat("dd.MM.yy  |  HH:mm", Locale.getDefault()).format(now);

        clockView.setText(clockText);
    }


    private void enableFullscreen() {

        getWindow().getDecorView()
            .setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE //
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE //
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    private List<RoomStatus> mockData() {

        RoomStatus roomStatus1 = new RoomStatus(new Room("Wohnzimmer"), new Event("Daily"),
                new Event("Bewerbungsgespräch"));
        RoomStatus roomStatus2 = new RoomStatus(new Room("Holodeck"), null, new Event("Bewerbungsgespräch"));
        RoomStatus roomStatus3 = new RoomStatus(new Room("Nullpointer"), null, new Event("Bewerbungsgespräch"));
        RoomStatus roomStatus4 = new RoomStatus(new Room("Billard-Ecke"), new Event("Daily"),
                new Event("Bewerbungsgespräch"));
        RoomStatus roomStatus5 = new RoomStatus(new Room("Kreativraum"), new Event("Daily"),
                new Event("Bewerbungsgespräch"));
        RoomStatus roomStatus6 = new RoomStatus(new Room("BMW"), null, new Event("Bewerbungsgespräch"));

        return asList(roomStatus1, roomStatus2, roomStatus3, roomStatus4, roomStatus5, roomStatus6);
    }
}
