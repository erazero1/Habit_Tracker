package com.erazero1.habit_tracker.ui.add_habit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.adapters.IconAdapter;
import com.erazero1.habit_tracker.databinding.FragmentBottomSheetBinding;
import com.erazero1.habit_tracker.databinding.FragmentBottomSheetIconsBinding;
import com.erazero1.habit_tracker.interfaces.OnIconSelectedListener;
import com.erazero1.habit_tracker.models.Auth;
import com.erazero1.habit_tracker.models.MyIcon;
import com.erazero1.habit_tracker.ui.auth.EntryActivity;
import com.erazero1.habit_tracker.ui.main.BottomSheetFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class IconBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentBottomSheetIconsBinding binding;
    private RecyclerView iconRecyclerView;
    private IconAdapter iconAdapter;
    private BottomSheetFragment bottomSheet;

    private OnIconSelectedListener onIconSelectedListener;

    public IconBottomSheetFragment() {
    }

    public static IconBottomSheetFragment newInstance() {
        IconBottomSheetFragment fragment = new IconBottomSheetFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onIconSelectedListener = (OnIconSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnIconSelectedListener");
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("IconBottomSheetFragment", "onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("IconBottomSheetFragment", "onCreateView");

        binding = FragmentBottomSheetIconsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
        initPopUpUi();
        iconSelectBottomSheetDialog(getActivity());
        Log.d("IconBottomSheetFragment", "onViewCreated");
    }

    private void initUi() {
        iconRecyclerView = binding.rvIcons;
        Log.d("IconBottomSheetFragment", "initUi");
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    private void initPopUpUi() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        iconRecyclerView.setLayoutManager(gridLayoutManager);

        iconAdapter = new IconAdapter(getContext());
        iconAdapter.setOnIconClickListener(new IconAdapter.OnIconClickListener() {
            @Override
            public void onIconClick(int resId) {
                Log.d("IconBottomSheetFragment", "iconAdapter onIconClick");
                if (onIconSelectedListener != null) {
                    onIconSelectedListener.onIconSelected(resId);
                    dismissNow();
                }
            }
        });
        iconRecyclerView.setAdapter(iconAdapter);
    }

    public void iconSelectBottomSheetDialog(Context context) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet_icons, null);
        initPopUpUi();
        bottomSheetDialog.setContentView(v);
        Log.d("IconBottomSheetFragment", "iconSelectBottomSheetDialog");
    }
}
