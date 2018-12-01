package de.synyx.android.reservator.screen.agenda;

import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.screen.main.MainActivity;


public class AgendaFragment extends Fragment {

    private static final String CALENDAR_ID = "calendarId";
    private AgendaViewModel viewModel;
    private long calendarId;
    private TextView agendaTitle;
    private ReservationsRecyclerAdapter reservationsRecyclerAdapter;

    public static AgendaFragment newInstance(long calendarId) {

        AgendaFragment agendaFragment = new AgendaFragment();

        Bundle args = new Bundle();
        args.putLong(CALENDAR_ID, calendarId);
        agendaFragment.setArguments(args);

        return agendaFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        calendarId = arguments.getLong(CALENDAR_ID);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        agendaTitle = view.findViewById(R.id.agenda_title);

        setupReservationsRecyclerView(view);

        loadData();
    }


    private void loadData() {

        viewModel.getAgenda(calendarId) //
        .observe(getActivity(),
            agendaDto -> {
                reservationsRecyclerAdapter.updateReservations(agendaDto.getReservations());

                String roomName = agendaDto.getRoomName();
                agendaTitle.setText(getString(R.string.agenda_title, roomName));
                setHeaderTitle(roomName);
            });
    }


    private void setupReservationsRecyclerView(@NonNull View view) {

        RecyclerView reservationsRecyclerView = view.findViewById(R.id.agenda_reservations);
        reservationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        reservationsRecyclerAdapter = new ReservationsRecyclerAdapter();
        reservationsRecyclerView.setAdapter(reservationsRecyclerAdapter);
    }


    private void setHeaderTitle(String title) {

        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle(title);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }
}
