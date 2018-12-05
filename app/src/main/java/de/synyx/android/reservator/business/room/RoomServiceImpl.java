package de.synyx.android.reservator.business.room;

import com.futurice.android.reservator.R;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class RoomServiceImpl implements RoomService {

    @Override
    public int getRoomListDialog(boolean checkRoomListOK) {

        if (checkRoomListOK) {
            return R.string.calendarOk;
        } else {
            return R.string.calendarPending;
        }
    }
}
