package de.synyx.android.reservator.screen.main.status;

import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.futurice.android.reservator.R;

import de.synyx.android.reservator.domain.BookingResult;
import de.synyx.android.reservator.domain.MeetingRoom;
import de.synyx.android.reservator.domain.Reservation;
import de.synyx.android.reservator.domain.RoomAvailability;
import de.synyx.android.reservator.screen.main.MainActivity;
import de.synyx.android.reservator.screen.reservation.ReservationActivity;
import de.synyx.android.reservator.util.DateFormatter;
import de.synyx.android.reservator.util.livedata.SingleEvent;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

import static de.synyx.android.reservator.domain.RoomAvailability.AVAILABLE;


public class StatusFragment extends Fragment {

    private MeetingRoomViewModel viewModel;
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

    public static StatusFragment newInstance() {

        return new StatusFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_status, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(MeetingRoomViewModel.class);
        viewModel.getRoom().observe(this, this::updateStatus);
        viewModel.getBookingResult().observe(this, this::showSnackbarOnError);

        fragmentContainer = view.findViewById(R.id.status_fragment_container);

        tvAvailability = view.findViewById(R.id.availability);
        tvEventName = view.findViewById(R.id.event_name);
        tvEventDuration = view.findViewById(R.id.event_duration);
        tvNextEventName = view.findViewById(R.id.next_event_name);

        btnReserve = view.findViewById(R.id.reserve);
        btnBookNow = view.findViewById(R.id.book_now);
    }


    private void showSnackbarOnError(SingleEvent<BookingResult> bookingResultEvent) {

        BookingResult bookingResult = bookingResultEvent.getContentIfNotHandled();

        if (bookingResult != null && bookingResult.hasError()) {
            Snackbar.make(getView(), bookingResult.errorMessage, LENGTH_LONG).show();
        }
    }


    void updateStatus(MeetingRoom meetingRoom) {

        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle(meetingRoom.getName());

        RoomAvailability roomAvailablility = meetingRoom.getAvailability();
        fragmentContainer.setBackgroundColor(getActivity().getColor(roomAvailablility.getColorRes()));

        setupReserveButton(roomAvailablility);
        setupBookNowButton(roomAvailablility);

        tvAvailability.setText(roomAvailablility.getStringRes());
        tvEventDuration.setText(meetingRoom.getAvailabilityTime(() -> DateFormatter.periodFormatter(getContext())));
        tvEventName.setText(getCurrentMeetingText(meetingRoom));
        tvNextEventName.setText(getNextReservationText(meetingRoom));
    }


    private void setupReserveButton(RoomAvailability roomAvailablility) {

        btnReserve.setTextColor(getActivity().getColor(roomAvailablility.getColorRes()));
        btnReserve.setOnClickListener(view -> startActivity(new Intent(getActivity(), ReservationActivity.class)));
    }


    private void setupBookNowButton(RoomAvailability roomAvailablility) {

        btnBookNow.setTextColor(getActivity().getColor(roomAvailablility.getColorRes()));
        btnBookNow.setOnClickListener(view -> new BookNowDialogFragment().show(getFragmentManager(), "BookNowDialog"));
        btnBookNow.setVisibility(roomAvailablility == AVAILABLE ? View.VISIBLE : View.GONE);
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
