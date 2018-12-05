package de.synyx.android.reservator.data;

import de.synyx.android.reservator.business.room.RoomCalendar;
import de.synyx.android.reservator.business.room.RoomRepository;
import de.synyx.android.reservator.config.Registry;

import io.reactivex.Maybe;
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

        return calendarAdapter.loadAllRooms();
    }


    @Override
    public Observable<RoomCalendar> loadVisibleRooms() {

        return calendarAdapter.loadVisibleRooms();
    }


    @Override
    public Maybe<RoomCalendar> loadRoom(long id) {

        return calendarAdapter.loadRoom(id);
    }
}
