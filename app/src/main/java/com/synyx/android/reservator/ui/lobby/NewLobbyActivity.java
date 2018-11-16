package com.synyx.android.reservator.ui.lobby;

import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class NewLobbyActivity extends AppCompatActivity {

    private LobbyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LobbyViewModel.class);

        enableFullscreen();
        setContentView(R.layout.activity_newlobby);

        setRoomRecyclerView();
        setClock();
    }


    private void setRoomRecyclerView() {

        RecyclerView roomsRecyclerView = findViewById(R.id.roomRecyclerView);
        roomsRecyclerView.setHasFixedSize(true);
        roomsRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        RoomRecyclerAdapter roomRecyclerAdapter = new RoomRecyclerAdapter();
        roomsRecyclerView.setAdapter(roomRecyclerAdapter);

        viewModel.getRooms().observe(this, roomRecyclerAdapter::updateRooms);
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
