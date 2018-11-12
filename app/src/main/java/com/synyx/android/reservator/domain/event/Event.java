package com.synyx.android.reservator.domain.event;

/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class Event {

    private String name;

    public Event(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }


    public void setName(String name) {

        this.name = name;
    }
}
