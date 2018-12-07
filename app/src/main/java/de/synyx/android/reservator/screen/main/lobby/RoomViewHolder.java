package de.synyx.android.reservator.screen.main.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;
import de.synyx.android.reservator.domain.RoomAvailability;
import de.synyx.android.reservator.util.DateFormatter;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
class RoomViewHolder extends RecyclerView.ViewHolder {

    private TextView meetingRommName;
    private TextView availabilityTime;
    private TextView currentMeetingTitle;
    private TextView upcomingReservationTitle;

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


    public void bind(MeetingRoom meetingRoom) {

        meetingRommName.setText(meetingRoom.getName());
        availabilityTime.setText(meetingRoom.getAvailabilityTime(DateFormatter::smallPeriodFormatter));
        currentMeetingTitle.setText(getCurrentMeetingText(meetingRoom));
        setStatus(meetingRoom.getAvailability());
        upcomingReservationTitle.setText(getUpcomingReservationText(meetingRoom));
    }


    private String getUpcomingReservationText(MeetingRoom meetingRoom) {

        Reservation upcomingReservation = meetingRoom.getUpcomingReservation();

        return upcomingReservation != null //
            ? upcomingReservation.getTitle() //
            : "Kein Folgetermin";
    }


    private String getCurrentMeetingText(MeetingRoom meetingRoom) {

        Reservation currentMeeting = meetingRoom.getCurrentMeeting();

        return currentMeeting != null ? currentMeeting.getTitle() : "";
    }
}
