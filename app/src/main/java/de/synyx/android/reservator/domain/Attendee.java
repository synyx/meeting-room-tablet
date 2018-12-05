package de.synyx.android.reservator.domain;

/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class Attendee {

    private Long attendeeId;
    private final String name;
    private final int status;

    public Attendee(String name, int status) {

        this.name = name;
        this.status = status;
    }

    public String getName() {

        return name;
    }


    public int getStatus() {

        return status;
    }
}
