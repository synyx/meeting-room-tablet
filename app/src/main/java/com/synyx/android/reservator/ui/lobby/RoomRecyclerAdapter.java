package com.synyx.android.reservator.ui.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomViewHolder> {

    private List<RoomStatus> rooms;

    public RoomRecyclerAdapter(List<RoomStatus> rooms) {

        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_room_item, parent, false);

        return new RoomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int index) {

        RoomStatus roomStatus = rooms.get(index);

        roomViewHolder.roomName.setText(roomStatus.getRoomName());
        roomViewHolder.roomTime.setText(roomStatus.getRoomTime());
        roomViewHolder.eventName.setText(roomStatus.getActiveEventName());
        roomViewHolder.nextEventName.setText(roomStatus.getNextEventName());
        roomViewHolder.setStatus(roomStatus.getStatus());
    }


    @Override
    public int getItemCount() {

        return rooms.size();
    }
}
