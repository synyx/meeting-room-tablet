package de.synyx.android.reservator.domain.reservation;

import java.util.Date;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class Timespan {

    private Date start;

    private Date end;

    public Timespan(Date start, Date end) {

        this.start = start;
        this.end = end;
    }

    public Date getStart() {

        return start;
    }


    public void setStart(Date start) {

        this.start = start;
    }


    public Date getEnd() {

        return end;
    }


    public void setEnd(Date end) {

        this.end = end;
    }


    @Override
    public String toString() {

        return String.format("[%s - %s]", start.getTime(), end.getTime());
    }
}
