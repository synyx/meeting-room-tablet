package de.synyx.android.reservator.screen.main.lobby;

import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;


public class LobbyFragment extends Fragment {

    private LobbyViewModel viewModel;

    public LobbyFragment() {

        // Required empty public constructor
    }

    public static LobbyFragment newInstance() {

        LobbyFragment fragment = new LobbyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lobby, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LobbyViewModel.class);

        setRoomRecyclerView(view);
    }


    private void setRoomRecyclerView(View view) {

        RecyclerView roomsRecyclerView = view.findViewById(R.id.roomRecyclerView);
        roomsRecyclerView.setHasFixedSize(true);
        roomsRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        RoomRecyclerAdapter roomRecyclerAdapter = new RoomRecyclerAdapter();
        roomsRecyclerView.setAdapter(roomRecyclerAdapter);

        viewModel.getRooms().observe(this, roomRecyclerAdapter::updateRooms);
    }
}
