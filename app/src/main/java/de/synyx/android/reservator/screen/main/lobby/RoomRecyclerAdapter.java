package de.synyx.android.reservator.screen.main.lobby;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.screen.RoomDto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Max Dobler - dobler@synyx.de
 */
public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomViewHolder> {

    private List<RoomDto> rooms = new ArrayList<>();

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby_room_item, parent, false);

        return new RoomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int index) {

        RoomDto roomDto = rooms.get(index);

        roomViewHolder.roomName.setText(roomDto.getRoomName());
        roomViewHolder.roomTime.setText(roomDto.getRoomTime());
        roomViewHolder.eventName.setText(roomDto.getActiveEventName());
        roomViewHolder.nextEventName.setText(roomDto.getNextEventName());
        roomViewHolder.setStatus(roomDto.getStatus());
    }


    @Override
    public int getItemCount() {

        return rooms.size();
    }


    public void updateRooms(List<RoomDto> newRooms) {

        rooms.clear();
        rooms.addAll(newRooms);
        notifyDataSetChanged();
    }
}
