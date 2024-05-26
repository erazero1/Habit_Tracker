package com.erazero1.habit_tracker.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.erazero1.habit_tracker.models.Habit;

import java.util.List;
@Dao
public interface HabitDao {
    @Insert
    void addHabit(Habit habit);

    @Update
    void updateHabit(Habit habit);

    @Delete
    void deleteHabit(Habit habit);

    @Query("delete from habit where habit_id = :habit_id")
    void deleteHabit(int habit_id);

    @Query("select * from habit")
    List<Habit> getAllHabit();

    @Query("select * from habit where habit_id = :habit_id")
    Habit getHabit(int habit_id);




}
