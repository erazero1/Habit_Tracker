package com.erazero1.habit_tracker.ui.auth;

import static com.erazero1.habit_tracker.models.Constants.SP_USER_SIGN_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.interfaces.OnUserSignedListener;
import com.erazero1.habit_tracker.models.Auth;
import com.erazero1.habit_tracker.ui.main.MainActivity;
import com.erazero1.habit_tracker.ui.main.ProfileFragment;

public class EntryActivity extends AppCompatActivity {

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        preferences = getSharedPreferences(SP_USER_SIGN_KEY, MODE_PRIVATE);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            String UID = preferences.getString("UID", null);
            String email = preferences.getString("email", null);
            String passwd = preferences.getString("passwd", null);

            if (UID == null || email == null || passwd == null) {
                Intent intent = new Intent(EntryActivity.this, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                        | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            } else {
                Log.d("EntryActivity", "SignIn");
                Auth.signIn(EntryActivity.this, email, passwd, isSigned -> {
                    if (isSigned) {
                        Log.d("EntryActivity", "before intent init");
                        Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        Log.d("EntryActivity", "intent starting");
                        startActivity(intent);
                    }
                });
            }
        }, 2500);

    }
}