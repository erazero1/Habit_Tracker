package com.erazero1.habit_tracker.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.ui.add_habit.AddNewHabit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodayFragment extends Fragment {
    FloatingActionButton fabAddNewHabit;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public TodayFragment() {
    }

    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);
        initListeners();
    }

    private void initUi(View v){
        fabAddNewHabit = v.findViewById(R.id.fab_add);
    }

    private void initListeners(){
        fabAddNewHabit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddNewHabit.class);
            startActivity(intent);
        });
    }
}