package de.synyx.android.reservator.screen.main.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.RoomAvailability;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class RoomViewHolder extends RecyclerView.ViewHolder {

    TextView meetingRommName;
    TextView availabilityTime;
    TextView currentMeetingTitle;
    TextView upcomingReservationTitle;

    RoomViewHolder(@NonNull View itemView) {

        super(itemView);

        meetingRommName = itemView.findViewById(R.id.roomName);
        availabilityTime = itemView.findViewById(R.id.roomTime);
        currentMeetingTitle = itemView.findViewById(R.id.current_meeting_title);
        upcomingReservationTitle = itemView.findViewById(R.id.upcoming_reservervation_title);
    }

    void setStatus(RoomAvailability roomAvailability) {

        itemView.setBackgroundResource(roomAvailability.getColorRes());
    }
}
