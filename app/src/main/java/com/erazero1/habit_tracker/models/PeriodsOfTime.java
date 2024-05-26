package com.erazero1.habit_tracker.models;

public class PeriodsOfTime {
    public static TimePeriod morningPeriod = new TimePeriod(8, 0, 14, 0);
    public static TimePeriod afternoonPeriod = new TimePeriod(14,0,19,0);
    public static TimePeriod eveningPeriod = new TimePeriod(19, 0, 23, 0);

    public static TimePeriod endOfTheDay = new TimePeriod(23, 0, 8, 0);

    public static void setEndOfTheDayPeriod(TimePeriod endOfTheDay) {
        PeriodsOfTime.endOfTheDay = endOfTheDay;
    }

    public static void setMorningPeriod(TimePeriod morningPeriod) {
        PeriodsOfTime.morningPeriod = morningPeriod;
    }

    public static void setAfternoonPeriod(TimePeriod afternoonPeriod) {
        PeriodsOfTime.afternoonPeriod = afternoonPeriod;
    }

    public static void setEveningPeriod(TimePeriod eveningPeriod) {
        PeriodsOfTime.eveningPeriod = eveningPeriod;
    }
}
