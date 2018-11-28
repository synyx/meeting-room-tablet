package de.synyx.android.reservator.domain.event;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class Event implements Comparable<Event> {

    private final String name;
    private final LocalDateTime begin;
    private final LocalDateTime end;

    public Event(String name, LocalDateTime begin, LocalDateTime end) {

        this.name = name;
        this.begin = begin;
        this.end = end;
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
}
