package de.synyx.android.reservator.domain;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import com.futurice.android.reservator.R;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public enum RoomAvailability {

    AVAILABLE(R.color.room_status_available, R.string.free),
    RESERVED(R.color.room_status_reserved, R.string.defaultTitleForReservation),
    UNAVAILABLE(R.color.room_status_unavailable, R.string.defaultTitleForReservation);

    @StringRes
    private int colorRes;
    @ColorRes
    private int stringRes;

    RoomAvailability(int colorRes, int stringRes) {

        this.colorRes = colorRes;
        this.stringRes = stringRes;
    }

    public int getColorRes() {

        return colorRes;
    }


    public int getStringRes() {

        return stringRes;
    }
}
