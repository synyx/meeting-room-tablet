package de.synyx.android.reservator.screen.main.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;

import io.reactivex.Observable;

import io.reactivex.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomViewHolder> {

    private List<MeetingRoom> rooms = new ArrayList<>();
    private final PublishSubject<MeetingRoom> onClickSubject = PublishSubject.create();

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_room_item, parent, false);

        return new RoomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int index) {

        MeetingRoom meetingRoom = rooms.get(index);

        roomViewHolder.meetingRommName.setText(meetingRoom.getName());
        roomViewHolder.availabilityTime.setText(meetingRoom.getAvailabilityTime());
        roomViewHolder.currentMeetingTitle.setText(getCurrentMeetingText(meetingRoom));
        roomViewHolder.setStatus(meetingRoom.getAvailability());
        roomViewHolder.upcomingReservationTitle.setText(getUpcomingReservationText(meetingRoom));

        roomViewHolder.itemView.setOnClickListener(view -> onClickSubject.onNext(meetingRoom));
    }


    private String getUpcomingReservationText(MeetingRoom meetingRoom) {

        Reservation upcomingReservation = meetingRoom.getUpcomingReservation();

        return upcomingReservation != null //
            ? upcomingReservation.getTitle() //
            : "";
    }


    private String getCurrentMeetingText(MeetingRoom meetingRoom) {

        Reservation currentMeeting = meetingRoom.getCurrentMeeting();

        return currentMeeting != null ? currentMeeting.getTitle() : "";
    }


    @Override
    public int getItemCount() {

        return rooms.size();
    }


    public void updateRooms(List<MeetingRoom> newRooms) {

        rooms.clear();
        rooms.addAll(newRooms);
        notifyDataSetChanged();
    }


    public Observable<MeetingRoom> getItemClicks() {

        return onClickSubject;
    }
}
