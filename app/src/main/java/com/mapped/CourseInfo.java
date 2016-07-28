package com.mapped;

import java.util.List;

/**
 * Created by Pegasus on 7/25/16.
 */
public class CourseInfo {

    private String college;
    private String collegeId;
    private String courseNumber;
    private List<String> days;
    private String end_time;
    private String event_type;
    private String location;
    private String name;
    private String presenter_name;
    private String section;
    private String start_time;
    private String semestersOffered;

    public String getSemestersOffered() {
        return semestersOffered;
    }

    public String getCollege() {
        return college;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public List<String> getDays() {
        return days;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getPresenter_name() {
        return presenter_name;
    }

    public String getSection() {
        return section;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getUniversity() {
        return university;
    }

    private String university;



    public CourseInfo(){

    }

    public CourseInfo(String college, String collegeId, String courseNumber, List<String> days, String end_time, String event_type, String location, String name, String presenter_name, String section, String start_time, String university) {
        this.college = college;
        this.collegeId = collegeId;
        this.courseNumber = courseNumber;
        this.days = days;
        this.end_time = end_time;
        this.event_type = event_type;
        this.location = location;
        this.name = name;
        this.presenter_name = presenter_name;
        this.section = section;
        this.start_time = start_time;
        this.university = university;
    }

}

