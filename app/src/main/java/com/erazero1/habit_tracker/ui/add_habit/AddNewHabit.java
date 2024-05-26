package com.erazero1.habit_tracker.ui.add_habit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.databinding.ActivityAddNewHabitBinding;
import com.erazero1.habit_tracker.models.Habit;

public class AddNewHabit extends AppCompatActivity {

    private ActivityAddNewHabitBinding binding;
    private Button btnRegular, btnNegative, btnOneTimeToDo, btnCreate;
    private int typeOfHabit = Habit.REGULAR_HABIT;
    private ImageButton ibBack;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewHabitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListeners();

    }

    private void initUi(){
        btnRegular = binding.btnRegular;
        btnNegative = binding.btnNegative;
        btnOneTimeToDo = binding.btnOneTimeToDo;
        btnCreate = binding.btnCreate;
        ibBack = binding.ibBack;

        setSupportActionBar(binding.addNewHabitToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.addNewHabitToolbar.getNavigationIcon().setTint(getResources().getColor(R.color.background_color));

    }

    private void initListeners(){
        ibBack.setOnClickListener(view -> finish());
        btnRegular.setOnClickListener(view -> {
            typeOfHabit = Habit.REGULAR_HABIT;
            Toast.makeText(this, "Regular", Toast.LENGTH_SHORT).show();

        });

        btnNegative.setOnClickListener(view -> {
            typeOfHabit = Habit.NEGATIVE_HABIT;
            Toast.makeText(this, "Negative", Toast.LENGTH_SHORT).show();

        });

        btnOneTimeToDo.setOnClickListener(view -> {
            typeOfHabit = Habit.ONE_TIME_TO_DO_HABIT;
            Toast.makeText(this, "One Time", Toast.LENGTH_SHORT).show();

        });

        btnCreate.setOnClickListener(view -> {
            switch (typeOfHabit){
                case Habit.REGULAR_HABIT:
                    intent = new Intent(this, AddRegularHabit.class);
                    break;
                case Habit.NEGATIVE_HABIT:
                    intent = new Intent(this, AddNegativeHabit.class);
                    break;
                case Habit.ONE_TIME_TO_DO_HABIT:
                    intent = new Intent(this, AddOneTimeToDoHabit.class);
            }
            startActivity(intent);
        });
    }
}