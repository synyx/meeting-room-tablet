package de.synyx.android.reservator.screen.main.status;

import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.futurice.android.reservator.R;


public class StatusFragment extends Fragment {

    public StatusFragment() {

        // Required empty public constructor
    }

    public static StatusFragment newInstance() {

        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_status, container, false);
    }
}
