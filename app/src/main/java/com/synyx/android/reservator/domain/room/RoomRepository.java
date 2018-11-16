package com.synyx.android.reservator.domain.room;

import io.reactivex.Observable;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface RoomRepository {

    Observable<RoomCalendar> loadAllRooms();
}
