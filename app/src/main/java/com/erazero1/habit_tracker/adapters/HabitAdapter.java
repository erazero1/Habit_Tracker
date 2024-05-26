package com.erazero1.habit_tracker.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.models.Habit;
import com.erazero1.habit_tracker.ui.edit_habit.EditHabitActivity;

import java.util.ArrayList;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private ArrayList<Habit> habits;
    private Context context;
    private Intent intent;
    public final static String HABIT_ID_FROM_ADAPTER = "HABIT_ID_FROM_ADAPTER";
    public final static String POSITION_OF_HABIT_FROM_ADAPTER = "POSITION_OF_HABIT_FROM_ADAPTER";

    public HabitAdapter(Context context, ArrayList<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("HabitAdapter", "onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habits.get(position);
        holder.bind(habit, position);
    }

    @Override
    public int getItemCount() {
        if (habits != null) return habits.size();
        return 0;
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder {

        private TextView nameOfHabit, duration;
        private ImageView ivHabitIcon;
        private CheckBox cbIsChecked;
        private Button btnMore;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfHabit = itemView.findViewById(R.id.nameOfHabit);
            ivHabitIcon = itemView.findViewById(R.id.ivHabitIcon);
            duration = itemView.findViewById(R.id.duration);
            cbIsChecked = itemView.findViewById(R.id.cbIsChecked);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

        public void bind(Habit habit, int pos) {
            nameOfHabit.setText(habit.getNameOfHabit());
            duration.setText(String.valueOf(habit.getDuration()));
            cbIsChecked.setChecked(habit.isChecked());
            ivHabitIcon.setAdjustViewBounds(true);
            ivHabitIcon.setImageResource(habit.getIconId());
            DrawableCompat.setTint(ivHabitIcon.getDrawable(), context.getResources().
                    getIdentifier(habit.getIconColor(), "color", context.getPackageName()));


            cbIsChecked.setOnClickListener(view -> Toast.makeText(context, "need to delete", Toast.LENGTH_SHORT).show());


            btnMore.setOnClickListener(v -> {
                intent = new Intent(context, EditHabitActivity.class);
                intent.putExtra(HABIT_ID_FROM_ADAPTER, habit.getId());
                intent.putExtra(POSITION_OF_HABIT_FROM_ADAPTER, pos);
                context.startActivity(intent);
                Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
