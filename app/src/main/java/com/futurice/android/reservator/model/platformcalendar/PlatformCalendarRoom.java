package com.futurice.android.reservator.model.platformcalendar;

import com.futurice.android.reservator.model.Room;


public class PlatformCalendarRoom extends Room {

    private static final long serialVersionUID = 1L;
    private long id;
    private String location;

    public PlatformCalendarRoom(String name, String email, long id, String location,
        boolean filterRoomNameFromAttendees) {

        super(name, email, filterRoomNameFromAttendees);
        this.id = id;
        this.location = location;
    }

    public long getId() {

        return id;
    }


    @Override
    public boolean equals(Room room) {

        return room instanceof PlatformCalendarRoom && ((PlatformCalendarRoom) room).getId() == id;
    }


    public String getLocation() {

        return this.location;
    }
}
