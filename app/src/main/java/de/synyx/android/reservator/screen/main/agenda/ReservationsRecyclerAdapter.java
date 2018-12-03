package de.synyx.android.reservator.screen.main.agenda;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.event.Event;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class ReservationsRecyclerAdapter extends RecyclerView.Adapter<ReservationViewHolder> {

    private List<Event> reservations = new ArrayList<>();

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_reservation_item, parent, false);

        return new ReservationViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder reservationViewHolder, int index) {

        Event reservation = reservations.get(index);
        reservationViewHolder.bind(reservation);
    }


    @Override
    public int getItemCount() {

        return reservations.size();
    }


    public void updateReservations(List<Event> newReservations) {

        reservations.clear();
        reservations.addAll(newReservations);

        notifyDataSetChanged();
    }
}
