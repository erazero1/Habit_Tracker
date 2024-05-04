package com.erazero1.habit_tracker;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TodayFragment todayFragment;
    HistoryFragment historyFragment;
    ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        initFragments();
        initListeners();
        bottomNavigationView.setSelectedItemId(R.id.today);

    }

    public void initUi(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    public void initFragments(){
        todayFragment = new TodayFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();
    }

    public void initListeners(){
            bottomNavigationView.setOnItemSelectedListener(item -> {

                if(item.getItemId() == R.id.today){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, todayFragment)
                            .commit();
                    return true;
                }else if(item.getItemId() == R.id.history){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, historyFragment)
                            .commit();
                    return true;
                }else if(item.getItemId() == R.id.profile){
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