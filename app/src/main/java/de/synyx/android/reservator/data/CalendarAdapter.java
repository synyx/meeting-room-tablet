package de.synyx.android.reservator.data;

import com.futurice.android.reservator.model.Room;

import de.synyx.android.reservator.domain.room.RoomCalendar;

import io.reactivex.Observable;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public interface CalendarAdapter {

    List<Room> getRooms();


    Observable<RoomCalendar> getNewRooms();
}
