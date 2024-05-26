package com.erazero1.habit_tracker.ui.edit_habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.adapters.HabitAdapter;
import com.erazero1.habit_tracker.databinding.ActivityEditHabitBinding;
import com.erazero1.habit_tracker.models.Habit;
import com.erazero1.habit_tracker.room.RoomDatabaseHelper;
import com.erazero1.habit_tracker.ui.main.TodayFragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditHabitActivity extends AppCompatActivity {
    private ActivityEditHabitBinding binding;
    private Button btnDelete;
    private RoomDatabaseHelper habitDB;

    private Intent intent;
    private int habitId;
    private ImageButton ibBack;

    private int habitPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditHabitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRoomDatabase();
        initUi();
        initListeners();
    }

    private void initUi(){
        ibBack = binding.ibBack;
        btnDelete = binding.btnDelete;
        setSupportActionBar(binding.editHabitToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.editHabitToolbar.getNavigationIcon().setTint(getResources().getColor(R.color.background_color));
    }

    private void initListeners(){
        ibBack.setOnClickListener(view -> finish());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                habitId = intent.getIntExtra(HabitAdapter.HABIT_ID_FROM_ADAPTER, 0);
                habitPos = intent.getIntExtra(HabitAdapter.POSITION_OF_HABIT_FROM_ADAPTER, 0);
                if(habitId == 0){
                    Toast.makeText(EditHabitActivity.this, "habitId = 0", Toast.LENGTH_SHORT).show();
                }
                deleteHabitInBackground(habitId, habitPos);
                Toast.makeText(EditHabitActivity.this, "Habit deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }

    private void initRoomDatabase(){
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

        habitDB = Room.databaseBuilder(EditHabitActivity.this, RoomDatabaseHelper.class, "habit_db")
                .addCallback(callback).build();
    }



    public void deleteHabitInBackground(int habit_id, int habitPos) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            habitDB.habitDao().deleteHabit(habit_id);

            handler.post(() -> {
                TodayFragment.notifyAdapter(habitPos);
            });
        });
    }
}

