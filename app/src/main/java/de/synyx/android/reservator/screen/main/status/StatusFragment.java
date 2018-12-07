package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.ViewModelProviders;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;
import de.synyx.android.reservator.domain.RoomAvailability;
import de.synyx.android.reservator.screen.main.MainActivity;
import de.synyx.android.reservator.util.DateFormatter;


public class StatusFragment extends Fragment {

    private static final String CALENDAR_ID = "calendarId";
    private RoomStatusViewModel viewModel;
    private long calendarId;
    private TextView tvAvailability;
    private TextView tvEventName;
    private TextView tvEventDuration;
    private TextView tvNextEventName;
    private Button btnReserve;
    private Button btnBookNow;
    private ViewGroup fragmentContainer;

    public StatusFragment() {

        // Required empty public constructor
    }

    public static StatusFragment newInstance(long calendarId) {

        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        args.putLong(CALENDAR_ID, calendarId);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_status, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            calendarId = getArguments().getLong(CALENDAR_ID);
        }

        viewModel = ViewModelProviders.of(this).get(RoomStatusViewModel.class);
        viewModel.getRoom(calendarId).observe(this, this::updateStatus);

        fragmentContainer = view.findViewById(R.id.status_fragment_container);

        tvAvailability = view.findViewById(R.id.availability);
        tvEventName = view.findViewById(R.id.event_name);
        tvEventDuration = view.findViewById(R.id.event_duration);
        tvNextEventName = view.findViewById(R.id.next_event_name);

        btnReserve = view.findViewById(R.id.reserve);
        btnBookNow = view.findViewById(R.id.book_now);
    }


    void updateStatus(MeetingRoom meetingRoom) {

        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle(meetingRoom.getName());

        RoomAvailability roomAvailablility = meetingRoom.getAvailability();
        fragmentContainer.setBackgroundColor(getActivity().getColor(roomAvailablility.getColorRes()));

        btnReserve.setTextColor(getActivity().getColor(roomAvailablility.getColorRes()));
        btnBookNow.setTextColor(getActivity().getColor(roomAvailablility.getColorRes()));

        tvAvailability.setText(roomAvailablility.getStringRes());
        tvEventDuration.setText(meetingRoom.getAvailabilityTime(() -> DateFormatter.periodFormatter(getContext())));
        tvEventName.setText(getCurrentMeetingText(meetingRoom));
        tvNextEventName.setText(getNextReservationText(meetingRoom));
    }


    private String getNextReservationText(MeetingRoom meetingRoom) {

        Reservation upcomingReservation = meetingRoom.getUpcomingReservation();

        return upcomingReservation != null ? upcomingReservation.getTitle() : "Kein Folgetermin vorhanden";
    }


    private String getCurrentMeetingText(MeetingRoom meetingRoom) {

        Reservation currentMeeting = meetingRoom.getCurrentMeeting();

        return currentMeeting != null ? currentMeeting.getTitle() : "";
    }
}
