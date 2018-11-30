package de.synyx.android.reservator.domain.room;

import io.reactivex.Maybe;
import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface RoomRepository {

    Observable<RoomCalendar> loadAllRooms();


    Maybe<RoomCalendar> loadRoom(long id);
}
