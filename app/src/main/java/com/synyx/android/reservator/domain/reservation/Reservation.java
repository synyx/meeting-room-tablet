package com.synyx.android.reservator.domain.reservation;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
@Builder
public class Reservation implements Comparable<Reservation>, Serializable {

    @NonNull
    private final String uuid;

    @Getter
    @NonNull
    private String title;

    @NonNull
    @Getter
    private Timespan timespan;

    @Getter
    private Set<String> attendees;

    @Getter
    @NonNull
    private Date createdTime;

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
}
