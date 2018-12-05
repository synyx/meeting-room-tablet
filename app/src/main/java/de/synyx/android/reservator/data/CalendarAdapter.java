package de.synyx.android.reservator.data;

import com.futurice.android.reservator.model.Room;

import de.synyx.android.reservator.business.room.RoomCalendar;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface CalendarAdapter {

    List<Room> getRooms();


    Observable<RoomCalendar> loadAllRooms();


    Observable<RoomCalendar> loadVisibleRooms();


    Maybe<RoomCalendar> loadRoom(long id);
}
