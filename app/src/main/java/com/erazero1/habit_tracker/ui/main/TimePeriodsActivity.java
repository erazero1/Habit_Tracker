package com.erazero1.habit_tracker.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.databinding.ActivityTimePeriodsBinding;
import com.erazero1.habit_tracker.models.PeriodsOfTime;
import com.erazero1.habit_tracker.models.TimePeriod;

import java.util.Calendar;

public class TimePeriodsActivity extends AppCompatActivity {

    private ActivityTimePeriodsBinding binding;
    private Button btnMorning, btnAfternoon, btnEvening, btnEndOfTheDay;
    private TextView currentDateTime, tvMorningTime, tvAfternoonTime, tvEveningTime, tvEndOfTheDayTime;
    private Calendar dateAndTime = Calendar.getInstance();
    private ImageButton ibBack;
    private Button currentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimePeriodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();


        initListeners();
    }

    private void initUi(){
        setSupportActionBar(binding.timePeriodsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.timePeriodsToolbar.getNavigationIcon().setTint(getResources().getColor(R.color.background_color));
        ibBack = binding.ibBack;
        btnMorning = binding.btnMorning;
        btnAfternoon = binding.btnAfternoon;
        btnEvening = binding.btnEvening;
        btnEndOfTheDay = binding.btnEndOfTheDay;
        tvMorningTime = binding.tvMorningTime;
        tvMorningTime.setText(DateUtils.formatDateTime(this,
                PeriodsOfTime.morningPeriod.getStartTimeInMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
        tvAfternoonTime = binding.tvAfternoonTime;
        tvAfternoonTime.setText(DateUtils.formatDateTime(this,
                PeriodsOfTime.afternoonPeriod.getStartTimeInMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
        tvEveningTime = binding.tvEveningTime;
        tvEveningTime.setText(DateUtils.formatDateTime(this,
                PeriodsOfTime.eveningPeriod.getStartTimeInMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
        tvEndOfTheDayTime = binding.tvEndOfTheDayTime;
        tvEndOfTheDayTime.setText(DateUtils.formatDateTime(this,
                PeriodsOfTime.endOfTheDay.getStartTimeInMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
    }

    private void initListeners(){
        ibBack.setOnClickListener(view -> finish());

        btnMorning.setOnClickListener(view -> {
            setTime(view);
            Toast.makeText(this, "Morning Button", Toast.LENGTH_SHORT).show();
        
        });
        btnAfternoon.setOnClickListener(view -> setTime(view));
        btnEvening.setOnClickListener(view -> setTime(view));
        btnEndOfTheDay.setOnClickListener(view -> setTime(view));

    }

    public void setTime(View v) {
        currentButton =(Button) v;
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    private void setInitialDateTime(TextView tv) {

        tv.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_24HOUR));
    }

    private TimePickerDialog.OnTimeSetListener t = (view, hourOfDay, minute) -> {
        dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dateAndTime.set(Calendar.MINUTE, minute);
        TextView textViewToUpdate = null;
        if (currentButton == btnMorning) {
            textViewToUpdate = tvMorningTime;
            PeriodsOfTime.setMorningPeriod(new TimePeriod(hourOfDay, minute));
        } else if (currentButton == btnAfternoon) {
            textViewToUpdate = tvAfternoonTime;
            PeriodsOfTime.setAfternoonPeriod(new TimePeriod(hourOfDay, minute));
        } else if (currentButton == btnEvening) {
            textViewToUpdate = tvEveningTime;
            PeriodsOfTime.setEveningPeriod(new TimePeriod(hourOfDay, minute));
        } else if (currentButton == btnEndOfTheDay) {
            textViewToUpdate = tvEndOfTheDayTime;
            PeriodsOfTime.setEndOfTheDayPeriod(new TimePeriod(hourOfDay, minute));
        }

        if (textViewToUpdate != null) {
            setInitialDateTime(textViewToUpdate);
        }
    };
}