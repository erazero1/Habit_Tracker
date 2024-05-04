package com.erazero1.habit_tracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.models.Habit;

import java.util.ArrayList;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private ArrayList<Habit> habits;
    private Context context;

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
        holder.bind(habit);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    public class HabitViewHolder extends RecyclerView.ViewHolder {

        private TextView nameOfHabit, duration;
        private CheckBox cbIsChecked;
        private Button btnMore;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOfHabit = itemView.findViewById(R.id.nameOfHabit);
            duration = itemView.findViewById(R.id.duration);
            cbIsChecked = itemView.findViewById(R.id.cbIsChecked);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

        public void bind(Habit habit) {
            nameOfHabit.setText(habit.getNameOfHabit());
            duration.setText(String.valueOf(habit.getDuration()));
            cbIsChecked.setChecked(habit.isChecked());


            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
