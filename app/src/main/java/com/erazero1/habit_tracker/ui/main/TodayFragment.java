package com.erazero1.habit_tracker.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.adapters.HabitAdapter;
import com.erazero1.habit_tracker.databinding.ActivityMainBinding;
import com.erazero1.habit_tracker.databinding.FragmentProfileBinding;
import com.erazero1.habit_tracker.databinding.FragmentTodayBinding;
import com.erazero1.habit_tracker.models.Habit;
import com.erazero1.habit_tracker.room.RoomDatabaseHelper;
import com.erazero1.habit_tracker.ui.add_habit.AddNewHabit;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodayFragment extends Fragment {
    FloatingActionButton fabAddNewHabit;

    private RecyclerView habitRecyclerView;
    private static HabitAdapter habitAdapter;
    private RoomDatabaseHelper habitDB;

    private ArrayList<Habit> habits;


    private FragmentTodayBinding fragmentTodayBinding;

    public TodayFragment() {
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

        initUi();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        habitRecyclerView.setLayoutManager(layoutManager);

        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        habitDB = Room.databaseBuilder(getActivity(), RoomDatabaseHelper.class, "habit_db")
                .addCallback(callback).build();
        habits = getAllHabitInBackground();
        if(habits != null){
            setHabitAdapter(habits);
        }

        return view;
    }

    public void addHabitInBackground(Habit habit) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            habitDB.habitDao().addHabit(habit);

            handler.post(() -> Toast.makeText(getActivity(), "Added to Database", Toast.LENGTH_SHORT).show());
        });
    }




    public ArrayList<Habit> getAllHabitInBackground() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            habits = (ArrayList<Habit>) habitDB.habitDao().getAllHabit();
            handler.post(() -> {
                setHabitAdapter(habits);
                habitAdapter.notifyDataSetChanged();
            });
        });
        return habits;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initListeners();


    }

    private void initUi() {
        fabAddNewHabit = fragmentTodayBinding.fabAdd;
        habitRecyclerView = fragmentTodayBinding.habitRecyclerView;
    }

    private void initListeners() {
        fabAddNewHabit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddNewHabit.class);
            startActivity(intent);
        });
    }

    public static void notifyAdapter(int habitPos){
        habitAdapter.notifyItemRemoved(habitPos);
    }

    private void setHabitAdapter(ArrayList<Habit> habits) {
        habitAdapter = new HabitAdapter(getContext(), habits);
        habitRecyclerView.setAdapter(habitAdapter);
    }
}