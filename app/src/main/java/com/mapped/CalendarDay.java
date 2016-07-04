package com.mapped;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Pegasus on 6/29/16.
 */
public class CalendarDay {

    private String startTime[] = new String[3];
    private String eventName[] = new String[3];

    public String[] getStartTime() {
        return startTime;
    }

    public String[] getEventName() {
        return eventName;
    }


    public CalendarDay(int position){

    }

    public CalendarDay(String[] startTime, String[] eventName) {
        this.startTime = startTime;
        this.eventName = eventName;
    }

}
