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

import de.synyx.android.reservator.screen.ScreenSize;
import de.synyx.android.reservator.screen.main.MainActivity;

import io.reactivex.disposables.Disposable;

import static de.synyx.android.reservator.util.ScreenUtil.getSizeOfScreen;


public class LobbyFragment extends Fragment {

    private LobbyViewModel viewModel;
    private RoomSelectionListener roomSelectionListener;
    private Disposable roomSelectionObservable;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),
                calculateGridSpan(getSizeOfScreen(getActivity())));
        roomsRecyclerView.setLayoutManager(layoutManager);

        RoomRecyclerAdapter roomRecyclerAdapter = new RoomRecyclerAdapter();
        roomsRecyclerView.setAdapter(roomRecyclerAdapter);

        observeItemClicks(roomRecyclerAdapter);

        viewModel.getRooms().observe(this, roomRecyclerAdapter::updateRooms);
    }


    private static int calculateGridSpan(ScreenSize screenSize) {

        switch (screenSize) {
            case XSMALL:
                return 2;

            case SMALL:
                return 3;

            case MEDIUM:
                return 4;

            case LARGE:
            case XLARGE:
                return 5;

            default:
                return 4;
        }
    }


    private void observeItemClicks(RoomRecyclerAdapter roomRecyclerAdapter) {

        roomSelectionObservable =
            roomRecyclerAdapter.getItemClicks() //
            .subscribe(room -> roomSelectionListener.onRoomSelected(room.getCalendarId()));
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        roomSelectionListener = (MainActivity) getActivity();
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        roomSelectionObservable.dispose();
    }

    public interface RoomSelectionListener {

        void onRoomSelected(long id);
    }
}
