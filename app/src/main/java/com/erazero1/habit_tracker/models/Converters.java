package com.erazero1.habit_tracker.models;

import android.util.Log;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {
    @TypeConverter
    public static ArrayList<RepeatDays> fromString(String value) {
        List<String> dbValues = Arrays.asList(value.split("\\s*,\\s*"));
        ArrayList<RepeatDays> enums = new ArrayList<>();

        try {
            if (value != null) {
                for (String s : dbValues)
                    enums.add(RepeatDays.valueOf(s));
            }

        } catch (Exception e) {
            Log.e("fromString", e.toString());
        }


        return enums;
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<RepeatDays> list) {
        String value = "";

        try {
            if (list != null) {
                for (RepeatDays lang : list)
                    value += lang.name() + ",";
            }

        } catch (Exception e) {
            Log.e("fromArrayList", e.toString());
        }


        return value;
    }
}