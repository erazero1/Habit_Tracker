package com.erazero1.habit_tracker.ui.main;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.erazero1.habit_tracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TodayFragment todayFragment;
    private HistoryFragment historyFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onStart() {
        Log.d("MainActivity", "onStart");
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");
        initUi();
        initFragments();
        initListeners();
        bottomNavigationView.setSelectedItemId(R.id.today);
    }

    public void initUi() {
        Log.d("MainActivity", "initUi");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);
        Log.d("main_init_ui", "Ui are init");

    }

    public void initFragments() {
        Log.d("MainActivity", "initFragments");
        todayFragment = new TodayFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();
        Log.d("main_init_fragments", "Fragments are init");

    }

    public boolean isUserAuthorized(){
        return true;
    }


    public void initListeners() {
        Log.d("MainActivity", "initListeners");
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.today) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, todayFragment)
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.history) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, historyFragment)
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.profile) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragment, profileFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }


}