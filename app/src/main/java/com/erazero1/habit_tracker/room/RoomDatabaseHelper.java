package com.erazero1.habit_tracker.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import com.erazero1.habit_tracker.models.Converters;
import com.erazero1.habit_tracker.models.Habit;

@Database(entities = Habit.class, exportSchema = false, version = 1)
@TypeConverters(Converters.class)

public abstract class RoomDatabaseHelper extends androidx.room.RoomDatabase {
    private static final String DB_NAME = "habit_db";
    private static RoomDatabaseHelper instance;

    public static synchronized RoomDatabaseHelper getDB(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, RoomDatabaseHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    public abstract HabitDao habitDao();

}
