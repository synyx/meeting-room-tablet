package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.screen.RoomDto;

import java.util.List;


public class StatusFragment extends Fragment {

    private static final String KEY_ROOM_ID = "key-room-id";
    private RoomStatusViewModel viewModel;
    private int roomId;

    public StatusFragment() {

        // Required empty public constructor
    }

    public static StatusFragment newInstance(int roomId) {

        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ROOM_ID, roomId);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_status, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            roomId = getArguments().getInt(KEY_ROOM_ID);
        }

        viewModel = ViewModelProviders.of(this).get(RoomStatusViewModel.class);

        viewModel.getRooms().observe(this, this::updateStatus);
    }


    void updateStatus(List<RoomDto> newRooms) {

        Log.e(this.getClass().getSimpleName(), viewModel.getRooms().toString());
    }
}
