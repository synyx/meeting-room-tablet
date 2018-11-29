package de.synyx.android.reservator.domain.event;

import de.synyx.android.reservator.domain.attendee.Attendee;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class Event implements Comparable<Event> {

    private final Long id;
    private final String name;
    private final LocalDateTime begin;
    private final LocalDateTime end;
    private List<Attendee> attendees = new ArrayList<>();

    public Event(Long id, String name, LocalDateTime begin, LocalDateTime end) {

        this.id = id;
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {

        return id;
    }


    public String getName() {

        return name;
    }


    public boolean isCurrent() {

        LocalDateTime now = LocalDateTime.now();

        return begin.isBefore(now) && end.isAfter(now);
    }


    public boolean isNextUpcoming() {

        return isSameDay() && begin.isAfter(LocalDateTime.now());
    }


    private boolean isSameDay() {

        return LocalDate.now().isEqual(begin.toLocalDate());
    }


    public Period getRemainingTime() {

        return new Period(LocalDateTime.now(), end);
    }


    public Period getTimeUntilBegin() {

        return new Period(LocalDateTime.now(), begin);
    }


    @Override
    public int compareTo(Event other) {

        return begin.compareTo(other.begin);
    }


    public LocalDateTime getBegin() {

        return begin;
    }


    public void addAttendee(Attendee attendee) {

        this.attendees.add(attendee);
    }


    public boolean hasAttendeeCanceled(String attendeeName) {

        for (Attendee attendee : attendees) {
            if (attendee.getName().equals(attendeeName) && attendee.getStatus() == 2) {
                return true;
            }
        }

        return false;
    }
}
