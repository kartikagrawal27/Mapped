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
    private String start_time;
    private String end_time;
    private String short_desc;
    private String university;
    private String organisation;

    public String getOrganisation() {
        return organisation;
    }

    public String getDescription() {
        return description;
    }

    private String description;

    public EventInfo(){

    }

    public EventInfo(String date, String event_type, String location, String presenter_name, String start_time, String end_time, String short_desc, String university, String organisation, String description) {
        this.date = date;
        this.event_type = event_type;
        this.location = location;
        this.presenter_name = presenter_name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.short_desc = short_desc;
        this.university = university;
        this.organisation = organisation;
        this.description = description;
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

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public String getUniversity() {
        return university;
    }
}
