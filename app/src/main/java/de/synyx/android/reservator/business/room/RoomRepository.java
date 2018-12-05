package de.synyx.android.reservator.business.room;

import io.reactivex.Maybe;
import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface RoomRepository {

    Observable<RoomCalendar> loadAllRooms();


    Observable<RoomCalendar> loadVisibleRooms();


    Maybe<RoomCalendar> loadRoom(long id);
}
