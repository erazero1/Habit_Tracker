package com.erazero1.habit_tracker.ui.add_habit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.databinding.ActivityAddRegularHabitBinding;
import com.erazero1.habit_tracker.interfaces.OnIconSelectedListener;
import com.erazero1.habit_tracker.models.Habit;
import com.erazero1.habit_tracker.models.RepeatDays;
import com.erazero1.habit_tracker.models.TimePeriod;
import com.erazero1.habit_tracker.room.RoomDatabaseHelper;
import com.erazero1.habit_tracker.ui.main.BottomSheetFragment;
import com.erazero1.habit_tracker.ui.main.MainActivity;
import com.erazero1.habit_tracker.ui.main.TodayFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddRegularHabit extends AppCompatActivity implements OnIconSelectedListener {

    private ActivityAddRegularHabitBinding binding;
    private TextView currentDateTime;
    private Calendar dateAndTime = Calendar.getInstance();
    private RoomDatabaseHelper habitDB;
    private ArrayList<Habit> habits;
    private Button btnIcon, btnColor, btnHabitDays, btnGoal, btnAnytime, btnMorning, btnAfternoon, btnEvening, btnReminderTime, btnSaveHabit;
    private Switch swReminder;
    private TextView tvGoal, tvRepeatDays;
    private Intent intent;
    private ImageButton ivIcon;
    private EditText etHabitName;
    private Habit currentHabit = new Habit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRegularHabitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();

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

        habitDB = Room.databaseBuilder(this, RoomDatabaseHelper.class, "habit_db")
                .addCallback(callback).build();

    }

    private void initUi() {
        btnIcon = binding.btnIcon;
        btnColor = binding.btnColor;
        btnHabitDays = binding.btnHabitDays;
        ivIcon = binding.ivIcon;
        ivIcon.setAdjustViewBounds(true);
        etHabitName = binding.etHabitName;
        tvGoal = binding.tvGoal;
        tvRepeatDays = binding.tvRepeatDays;
        swReminder = binding.swReminder;
        btnGoal = binding.btnGoal;
        btnAnytime = binding.btnAnytime;
        btnMorning = binding.btnMorning;
        btnAfternoon = binding.btnAfternoon;
        btnEvening = binding.btnEvening;
        btnReminderTime = binding.btnReminderTime;
        btnSaveHabit = binding.btnSaveHabit;
    }

    private void initListeners() {
        btnAnytime.setOnClickListener(view -> {
            currentHabit.setDoItAt(Habit.DO_IT_AT_ANYTIME);
        });
        btnMorning.setOnClickListener(view -> {
            currentHabit.setDoItAt(Habit.DO_IT_AT_MORNING);
        });
        btnAfternoon.setOnClickListener(view -> {
            currentHabit.setDoItAt(Habit.DO_IT_AT_AFTERNOON);
        });
        btnEvening.setOnClickListener(view -> {
            currentHabit.setDoItAt(Habit.DO_IT_AT_EVENING);
        });


        btnSaveHabit.setOnClickListener(view -> {
            updateHabit();
            addHabitInBackground(currentHabit);
            intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });
        swReminder.setOnClickListener(view -> {
            if (swReminder.isChecked()) {
                btnReminderTime.setVisibility(View.VISIBLE);
            } else {
                btnReminderTime.setVisibility(View.GONE);
            }
        });

        btnReminderTime.setOnClickListener(view -> {
            setTime(view);
        });


        btnIcon.setOnClickListener(view -> {
            try {
                IconBottomSheetFragment iconBottomSheetFragment = new IconBottomSheetFragment();
                iconBottomSheetFragment.show(getSupportFragmentManager(), "IconSelectBottomSheet");
            } catch (Exception e) {
                Log.e("IconSelectBottomSheet", e.toString());
            }
            Toast.makeText(getApplicationContext(), "Icon button", Toast.LENGTH_SHORT).show();
        });

        btnColor.setOnClickListener(view -> {
            DrawableCompat.setTint(ivIcon.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.dark_gray));
            currentHabit.setIconColor("dark_gray");
            Toast.makeText(getApplicationContext(), "Color button", Toast.LENGTH_SHORT).show();
        });

        btnHabitDays.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "HabitDays button", Toast.LENGTH_SHORT).show();
        });

        ivIcon.setOnClickListener(view -> {
            try {
                IconBottomSheetFragment iconBottomSheetFragment = new IconBottomSheetFragment();
                iconBottomSheetFragment.show(getSupportFragmentManager(), "IconSelectBottomSheet");
            } catch (Exception e) {
                Log.e("IconSelectBottomSheet", e.toString());
            }
            Toast.makeText(getApplicationContext(), "ivIcon button", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onIconSelected(int resId) {
        Log.d("AddRegularHabit", "Icon selected: " + resId);
        ivIcon.setImageResource(resId);
        currentHabit.setIconId(resId);
        Toast.makeText(this, "Icon selected: " + resId, Toast.LENGTH_SHORT).show();
    }

    private boolean isHabitNameNull(EditText etHabitName) {
        return etHabitName.getText() == null;
    }

    public void setTime(View v) {
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialDateTime() {

        btnReminderTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
    }

    private TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        setInitialDateTime();
        currentHabit.setReminderHour(hourOfDay);
        currentHabit.setReminderMinute(minute);
    };

    private Habit updateHabit(){
        if(!isHabitNameNull(etHabitName)){
            currentHabit.setNameOfHabit(String.valueOf(etHabitName.getText()));
//            currentHabit.setIconId(ivIcon.);
            if(currentHabit.getIconColor() == null){
                currentHabit.setIconColor("black");
            }
            ArrayList<RepeatDays> repeatDays = new ArrayList<>();
            repeatDays.add(RepeatDays.MONDAY);
            repeatDays.add(RepeatDays.TUESDAY);
            repeatDays.add(RepeatDays.WEDNESDAY);
            repeatDays.add(RepeatDays.THURSDAY);
            repeatDays.add(RepeatDays.FRIDAY);
            repeatDays.add(RepeatDays.SATURDAY);
            repeatDays.add(RepeatDays.SUNDAY);
            currentHabit.setRepeatDays(repeatDays);
            currentHabit.setDoItAt("");
            currentHabit.setGoal((String) tvGoal.getText());
        }
        return currentHabit;
    }

    public void addHabitInBackground(Habit habit) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            habitDB.habitDao().addHabit(currentHabit);

            handler.post(() -> Toast.makeText(this, "Added to Database", Toast.LENGTH_SHORT).show());
        });
    }
}