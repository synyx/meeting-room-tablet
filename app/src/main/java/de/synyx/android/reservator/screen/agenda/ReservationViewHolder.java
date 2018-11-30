package de.synyx.android.reservator.screen.agenda;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.event.Event;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import static java.lang.String.format;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class ReservationViewHolder extends RecyclerView.ViewHolder {

    ReservationViewHolder(@NonNull View itemView) {

        super(itemView);
    }

    void bind(Event reservation) {

        setTitle(reservation);
        setTimespan(reservation);
    }


    private void setTitle(Event reservation) {

        TextView reservationTitle = itemView.findViewById(R.id.reservation_title);
        reservationTitle.setText(reservation.getName());
    }


    private void setTimespan(Event reservation) {

        TextView reservationTimespan = itemView.findViewById(R.id.reservation_timespan);
        String beginTime = formatTime(reservation.getBegin());
        String endTime = formatTime(reservation.getEnd());
        reservationTimespan.setText(format("%s - %s", beginTime, endTime));
    }


    private String formatTime(LocalDateTime dateTime) {

        return DateTimeFormat.forPattern("HH:mm").print(dateTime);
    }
}
