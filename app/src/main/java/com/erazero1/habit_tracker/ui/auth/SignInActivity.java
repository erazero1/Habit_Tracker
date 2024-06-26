package com.erazero1.habit_tracker.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.databinding.ActivitySignInBinding;
import com.erazero1.habit_tracker.interfaces.OnUserSignedListener;
import com.erazero1.habit_tracker.models.Auth;
import com.erazero1.habit_tracker.models.Constants;
import com.erazero1.habit_tracker.models.User;
import com.erazero1.habit_tracker.ui.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class SignInActivity extends AppCompatActivity {
    private ProgressDialog dialog;

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Sign up transition button
        binding.tvSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Auth Button
        binding.btnSignIn.setOnClickListener(view -> {
            String email = binding.etEmail.getText().toString();
            String passwd = binding.etPassword.getText().toString();
            if (!isFieldsEmpty(email, passwd)) {
                showLoading();
                binding.emailLayout.setErrorEnabled(false);
                binding.passwordLayout.setErrorEnabled(false);
                signIn(email, passwd);
            }
        });


        // Reset Password Button
        binding.tvForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });
    }


    // User authorization
    private void signIn(String email, String passwd) {
        Auth.auth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SharedPreferences sp = getSharedPreferences(Constants.SP_USER_SIGN_KEY, MODE_PRIVATE);
                        sp.edit().putString("UID", Auth.auth.getUid()).apply();
                        sp.edit().putString("email", email).apply();
                        sp.edit().putString("passwd", passwd).apply();

                        Auth.signIn(SignInActivity.this, email, passwd, new OnUserSignedListener() {
                            @Override
                            public void onUserSigned(boolean isSigned) {
                                User user = Auth.getCurrentUser();
                                user.setPasswd(passwd);
                                Auth.updateUserInFireBase(user);
                                hideLoading();
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });

                    } else {
                        hideLoading();
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException ex) {
                            Toast.makeText(SignInActivity.this,
                                    getString(R.string.error_user_not_found),
                                    Toast.LENGTH_SHORT).show();

                            binding.emailLayout.setErrorEnabled(true);
                            binding.emailLayout.setError(getString(R.string.error_user_not_found));
                        } catch (FirebaseAuthInvalidCredentialsException ex) {
                            binding.emailLayout.setErrorEnabled(false);
                            Toast.makeText(SignInActivity.this,
                                    getString(R.string.error_invalid_pass_or_email),
                                    Toast.LENGTH_SHORT).show();
                        } catch (Exception ex) {
                            Log.d("signInException", ex.getMessage());
                        }
                    }

                });
    }


    private void showLoading() {
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();
    }

    private void hideLoading() {
        dialog.cancel();
    }


    private boolean isFieldsEmpty(String email, String passwd) {
        binding.emailLayout.setErrorEnabled(false);
        binding.passwordLayout.setErrorEnabled(false);
        boolean isFieldsEmpty = false;

        if (TextUtils.isEmpty(email)) {
            binding.emailLayout.setErrorEnabled(true);
            binding.emailLayout.setError(getString(R.string.error_empty_field));
            isFieldsEmpty = true;
        }

        if (TextUtils.isEmpty(passwd)) {
            binding.passwordLayout.setErrorEnabled(true);
            binding.passwordLayout.setError(getString(R.string.error_empty_field));
            isFieldsEmpty = true;

        }

        return isFieldsEmpty;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}