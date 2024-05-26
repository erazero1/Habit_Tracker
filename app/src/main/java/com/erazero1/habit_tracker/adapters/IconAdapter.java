package com.erazero1.habit_tracker.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.erazero1.habit_tracker.R;
import com.erazero1.habit_tracker.models.MyIcon;
import com.erazero1.habit_tracker.ui.edit_habit.EditHabitActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {
    private ArrayList<MyIcon> icons = new ArrayList<MyIcon>();
    private ArrayList<String> arrayName;
    private Context context;
    private Intent intent;
    public static int currentResId;
    public final static String ICON_ID_FROM_ADAPTER = "ICON_ID_FROM_ADAPTER";
    private OnIconClickListener onIconClickListener;

    public interface OnIconClickListener {
        void onIconClick(int resId);
    }

    public void setOnIconClickListener(OnIconClickListener listener) {
        this.onIconClickListener = listener;
    }

    public IconAdapter(Context context) {
        this.context = context;
        initIcon();
    }

    private void initIcon(){
        String[] arrayOfIconsName = context.getResources().getStringArray(R.array.icons_list);
        arrayName = new ArrayList<>(Arrays.asList(arrayOfIconsName));
        try{
        for (int pos = 0; pos < arrayName.size(); pos++) {
            int resId = context.getResources().getIdentifier(arrayName.get(pos), "drawable", context.getPackageName());
            try {
                icons.add(new MyIcon(arrayName.get(pos), resId));
            }catch (Exception e){
                Log.e("initIconAdd", e.toString());
            }
        }}
        catch (Exception e){
            Log.e("initIcon", e.toString());
        }
    }

    @NonNull
    @Override
    public IconAdapter.IconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("IconAdapter", "onCreateViewHolder");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.icon_item, parent, false);
        return new IconAdapter.IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAdapter.IconViewHolder holder, int position) {
        MyIcon icon = icons.get(position);
        holder.bind(icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("IconAdapter", "holder.itemView onClick" + icon.getResId());
                if (onIconClickListener != null) {
                    onIconClickListener.onIconClick(icon.getResId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (icons != null) return icons.size();
        return 0;
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {

        private ImageButton ibIcon;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            ibIcon = itemView.findViewById(R.id.ibIcon);
        }

        public void bind(MyIcon icon) {
            ibIcon.setAdjustViewBounds(true);
            ibIcon.setBackgroundColor(context.getColor(R.color.white));
            ibIcon.setColorFilter(context.getColor(R.color.primary_text));
//            int nightModeFlags = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
//            switch (nightModeFlags){
//                case Configuration.UI_MODE_NIGHT_YES:
//                    ibIcon.setBackgroundColor(context.getColor(R.color.primary_text));
//                    ibIcon.setColorFilter(context.getColor(R.color.white));
//                    break;
//                case Configuration.UI_MODE_NIGHT_NO:
//                    ibIcon.setBackgroundColor(context.getColor(R.color.base_blue));
//                    ibIcon.setColorFilter(context.getColor(R.color.black));
//                    break;
//            }
            ibIcon.setImageResource(icon.getResId());
            ibIcon.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View v) {
                    currentResId = icon.getResId();
                    if (onIconClickListener != null) {
                        onIconClickListener.onIconClick(icon.getResId());
                    }
                    Toast.makeText(context, "Icon clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
