package de.synyx.android.reservator.screen.agenda;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.event.Event;

import java.util.Arrays;
import java.util.List;

import static org.joda.time.LocalDateTime.now;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class ReservationsRecyclerAdapter extends RecyclerView.Adapter<ReservationViewHolder> {

    private List<Event> reservations;

    public ReservationsRecyclerAdapter() {

        Event event1 = new Event(1L, "Bewerbungsgespräch", now().minusHours(1), now().plusHours(1));
        Event event2 = new Event(2L, "Bewerbungsgespräch2 Christop der Größte mit langem Namen", now().plusHours(1),
                now().plusHours(2));
        reservations = Arrays.asList(event1, event2);
    }

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
}
