package com.mapped;

import java.util.List;

/**
 * Created by Pegasus on 7/5/16.
 */
public class EventInfo {
    private String date;
    private String event_type;
    private String location;
    private String presenter_name;
    private List<String> days;
    private String start_time;
    private String end_time;
    private String name;

    public EventInfo(){

    }

    public EventInfo(String date, String event_type, String location, String presenter_name, List<String> days, String start_time, String end_time, String name) {
        this.date = date;
        this.event_type = event_type;
        this.location = location;
        this.presenter_name = presenter_name;
        this.days = days;
        this.start_time = start_time;
        this.end_time = end_time;
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getLocation() {
        return location;
    }

    public String getPresenter_name() {
        return presenter_name;
    }

    public List<String> getDays() {
        return days;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getName() {
        return name;
    }
}
