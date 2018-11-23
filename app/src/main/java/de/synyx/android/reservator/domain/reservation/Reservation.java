package de.synyx.android.reservator.domain.reservation;

import android.support.annotation.NonNull;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class Reservation implements Comparable<Reservation>, Serializable {

    @NonNull
    private final String uuid;

    @NonNull
    private String title;

    @NonNull
    private Timespan timespan;

    private Set<String> attendees;

    @NonNull
    private Date createdTime;

    public Reservation(@NonNull String uuid, @NonNull String title, @NonNull Timespan timespan,
        @NonNull Date createdTime) {

        this.uuid = uuid;
        this.title = title;
        this.timespan = timespan;
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object other) {

        if (other instanceof Reservation) {
            return equals((Reservation) other);
        }

        return super.equals(other);
    }


    public boolean equals(Reservation other) {

        return other != null && uuid.equals(other.uuid);
    }


    @Override
    public int compareTo(@NonNull Reservation anotherReservation) {

        return this.timespan.getStart().compareTo(anotherReservation.timespan.getStart());
    }


    @Override
    public String toString() {

        return String.format("Reservation <%s,%h> %s:%s", uuid, hashCode(), title, timespan.toString());
    }


    @NonNull
    public String getTitle() {

        return title;
    }


    @NonNull
    public Timespan getTimespan() {

        return timespan;
    }


    public Set<String> getAttendees() {

        return attendees;
    }


    @NonNull
    public Date getCreatedTime() {

        return createdTime;
    }
}
