package de.synyx.android.reservator.screen.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.room.RoomState;

import static de.synyx.android.reservator.domain.room.RoomState.AVAILABLE;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class RoomViewHolder extends RecyclerView.ViewHolder {

    TextView roomName;
    TextView roomTime;
    TextView eventName;
    TextView nextEventName;

    RoomViewHolder(@NonNull View itemView) {

        super(itemView);

        roomName = itemView.findViewById(R.id.roomName);
        roomTime = itemView.findViewById(R.id.roomTime);
        eventName = itemView.findViewById(R.id.eventName);
        nextEventName = itemView.findViewById(R.id.nextEventName);
    }

    void setStatus(RoomState status) {

        int color = status == AVAILABLE ? R.color.room_status_available : R.color.room_status_unavailable;
        itemView.setBackgroundResource(color);
    }
}
