package com.synyx.android.reservator.data;

import com.synyx.android.reservator.config.Registry;
import com.synyx.android.reservator.domain.room.RoomCalendar;
import com.synyx.android.reservator.domain.room.RoomRepository;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomRepositoryImpl implements RoomRepository {

    private CalendarAdapter calendarAdapter;

    public RoomRepositoryImpl() {

        calendarAdapter = Registry.get(CalendarAdapter.class);
    }

    @Override
    public Observable<RoomCalendar> loadAllRooms() {

        return calendarAdapter.getNewRooms();
    }
}
