package com.erazero1.habit_tracker.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.databinding.FragmentBottomSheetBinding;
import com.erazero1.habit_tracker.models.Auth;
import com.erazero1.habit_tracker.ui.auth.EntryActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentBottomSheetBinding binding;
    private Button btnLogOut, btnCancel;
    private ImageView ivPhotoPopUp;
    private TextView tvUsernamePopUp, tvEmailPopUp;

    public BottomSheetFragment() {
    }

    public static BottomSheetFragment newInstance() {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentBottomSheetBinding.inflate(getLayoutInflater());
        Log.d("BottomSheetFragment", "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("BottomSheetFragment", "onCreateView");


        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        signOutBottomSheetDialog(getActivity());
        Log.d("BottomSheetFragment", "onViewCreated");
    }

    private void initUi() {
        ivPhotoPopUp = binding.ivPhotoPopUp;
        tvEmailPopUp = binding.tvEmailPopUp;
        tvUsernamePopUp = binding.tvUsernamePopUp;
        btnLogOut = binding.btnLogOut;
        btnCancel = binding.btnCancelPopUp;
        Log.d("BottomSheetFragment", "initUi");
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    private void initPopUpUi() {
        tvUsernamePopUp.setText(Auth.currentUser.getUsername());
        tvEmailPopUp.setText(Auth.currentUser.getEmail());
        if (Auth.currentUser.getAvatarUri() != null) {
            ivPhotoPopUp.setImageURI(Uri.parse(Auth.currentUser.getAvatarUri()));
        }
    }

    public void signOutBottomSheetDialog(Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet, null);
        bottomSheetDialog.setContentView(v);
        Log.d("BottomSheetFragment", "signOutBottomSheetDialog");
        initPopUpUi();

        btnCancel.setOnClickListener(view -> bottomSheetDialog.dismiss());
        btnLogOut.setOnClickListener(view -> {
            Log.d("BottomSheetFragment", "btnLogOut");
            Auth.signOutInSharedPref(context);
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            Toast.makeText(context, "Signed Out from SharedPref", Toast.LENGTH_LONG).show();
            startActivity(intent);
        });


    }
}