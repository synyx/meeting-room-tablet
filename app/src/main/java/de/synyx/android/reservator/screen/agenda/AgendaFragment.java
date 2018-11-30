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

    public static AgendaFragment newInstance(long calendarId) {

        AgendaFragment agendaFragment = new AgendaFragment();

        Bundle args = new Bundle();
        args.putLong(CALENDAR_ID, calendarId);
        agendaFragment.setArguments(args);

        return agendaFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_agenda, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        TextView agendaTitle = view.findViewById(R.id.agenda_title);
        agendaTitle.setText("Agenda für Nullpointer");

        RecyclerView reservationsRecyclerView = view.findViewById(R.id.agenda_reservations);
        reservationsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reservationsRecyclerView.setAdapter(new ReservationsRecyclerAdapter());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(AgendaViewModel.class);

        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("Nullpointer");
    }
}
