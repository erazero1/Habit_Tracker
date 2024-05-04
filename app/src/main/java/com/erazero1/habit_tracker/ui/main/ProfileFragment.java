package com.erazero1.habit_tracker.ui.main;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.erazero1.habit_tracker.databinding.FragmentProfileBinding;
import com.erazero1.habit_tracker.models.Auth;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private Button btnUsername;
    private ImageView ivPhoto;


    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        Log.d("ProfileFragment", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ProfileFragment", "onViewCreated");
        initUi();
        initListeners();
    }

    private void initUi() {
        Log.d("ProfileFragment", "initUi");
        btnUsername = binding.btnUsername;
        ivPhoto = binding.ivPhoto;

        btnUsername.setText(Auth.currentUser.getUsername());
        if (Auth.currentUser.getAvatarUri() != null) {
            ivPhoto.setImageURI(Uri.parse(Auth.currentUser.getAvatarUri()));
        }
    }

    private void initListeners() {
        Log.d("ProfileFragment", "initListeners");
        try {
            btnUsername.setOnClickListener(view -> {
                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getActivity().getSupportFragmentManager(), "SignOutBottomSheet");
            });
        } catch (Exception e) {
            Log.d("ProfileFragment", e.toString());
        }

    }


}