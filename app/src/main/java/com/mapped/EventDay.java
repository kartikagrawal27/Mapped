package com.mapped;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pegasus on 6/29/16.
 */
public class EventDay {

    private String startTime[] = new String[3];
    private String eventName[] = new String[3];
    private String dayofweek;

    public String[] getStartTime() {
        return startTime;
    }

    public String[] getEventName() {
        return eventName;
    }
    public EventDay(){

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                dayofweek = "SUN";

            case Calendar.MONDAY:
                dayofweek = "MON";
                // Current day is Monday

            case Calendar.TUESDAY:
                dayofweek = "TUE";

            case Calendar.WEDNESDAY:
                dayofweek = "WED";

            case Calendar.THURSDAY:
                dayofweek = "THU";

            case Calendar.FRIDAY:
                dayofweek = "FRI";

            case Calendar.SATURDAY:
                dayofweek = "SAT";
        }

        startTime[0] = "11:00AM";
        startTime[1] = "3:00PM";
        startTime[2] = "6:00PM";

        eventName[0] = "Apple Workshop";
        eventName[1] = "TA Office hours";
        eventName[2] = "Sam's Party";

    }

    public String getDayofweek() {
        return dayofweek;
    }
}
