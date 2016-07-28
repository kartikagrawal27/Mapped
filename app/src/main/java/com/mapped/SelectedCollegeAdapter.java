package com.mapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Pegasus on 7/26/16.
 */
public class SelectedCollegeAdapter extends ArrayAdapter {

    ArrayList<String> courseInfo;

    public SelectedCollegeAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        courseInfo = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.selected_college_item, parent, false);
        }

        TextView collegeCodeNumber = (TextView) convertView.findViewById(R.id.courseCodeNumber);
        TextView courseName = (TextView) convertView.findViewById(R.id.collgeCourseName);

        String course = courseInfo.get(position);
        String code = course.substring(0, course.indexOf("("));
        String name = course.substring(course.indexOf("(")+1, course.indexOf(")"));

        collegeCodeNumber.setText(code);
        courseName.setText(name);

        return convertView;
    }
}
