package com.synyx.android.reservator.domain.room;

/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class Room {

    private final String name;

    public Room(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
