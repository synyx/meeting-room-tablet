package de.synyx.android.reservator.screen.reservation;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.TextView;

import com.futurice.android.reservator.R;


public class ReservationActivity extends AppCompatActivity {

    private EditText txtName;
    private TextView txtDate;
    private TextView txtStart;
    private TextView txtEnd;
    private FloatingActionButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        showEndTimePicker();
        showStartTimePicker();
        showDatePicker();

        txtDate = findViewById(R.id.event_date);
        txtDate.setOnClickListener(ignored -> showDatePicker());

        txtStart = findViewById(R.id.event_start);
        txtStart.setOnClickListener(ignored -> showStartTimePicker());

        txtEnd = findViewById(R.id.event_end);
        txtEnd.setOnClickListener(ignored -> showEndTimePicker());

        btnSubmit = findViewById(R.id.event_submit);
        btnSubmit.setOnClickListener(view -> finish());
    }


    private void showDatePicker() {

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.addOnDateSelectionListener(date -> txtDate.setText(date.toString("dd.MM.yyyy")));
        datePickerFragment.show(getSupportFragmentManager(), "SelectDate");
    }


    private void showStartTimePicker() {

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.addOnTimeSelectionListener(time -> txtStart.setText(time.toString()));
        timePickerFragment.show(getSupportFragmentManager(), "SelectStartTime");
    }


    private void showEndTimePicker() {

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.addOnTimeSelectionListener(endTime -> txtEnd.setText(endTime.toString()));
        timePickerFragment.show(getSupportFragmentManager(), "SelectEndTime");
    }
}
