package com.erazero1.habit_tracker.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.adapters.HabitAdapter;
import com.erazero1.habit_tracker.databinding.ActivityMainBinding;
import com.erazero1.habit_tracker.databinding.FragmentProfileBinding;
import com.erazero1.habit_tracker.databinding.FragmentTodayBinding;
import com.erazero1.habit_tracker.models.Habit;
import com.erazero1.habit_tracker.ui.add_habit.AddNewHabit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TodayFragment extends Fragment {
    FloatingActionButton fabAddNewHabit;

    private RecyclerView habitRecyclerView;
    private HabitAdapter habitAdapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private FragmentTodayBinding fragmentTodayBinding;
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
        fragmentTodayBinding = FragmentTodayBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = fragmentTodayBinding.getRoot();
        Log.d("TodayFragment", "view initialized");

        initUi();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        habitRecyclerView.setLayoutManager(layoutManager);
        Log.d("TodayFragment", "setLayoutManager");


        ArrayList<Habit> habits = new ArrayList<>();
        habits.add(new Habit("Exercise", 5, false));
        habits.add(new Habit("Read", 10, false));

        habitAdapter = new HabitAdapter(getContext(), habits);
        Log.d("TodayFragment", "habitAdapter");
        habitRecyclerView.setAdapter(habitAdapter);
        Log.d("TodayFragment", "setAdapter");

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initListeners();
    }

    private void initUi(){
        fabAddNewHabit = fragmentTodayBinding.fabAdd;
        habitRecyclerView = fragmentTodayBinding.habitRecyclerView;
    }

    private void initListeners(){
        fabAddNewHabit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddNewHabit.class);
            startActivity(intent);
        });
    }
}