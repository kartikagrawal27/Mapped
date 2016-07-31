package com.mapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pegasus on 8/1/16.
 */
public class CourseSectionsAdapter extends ArrayAdapter {

    List<String> courseKeys = new ArrayList<>();
    List<String> sections = new ArrayList<>();
    String collegeId;
    String courseNumber;

    public CourseSectionsAdapter(Context context, int resource, List<String> objects, String collgeId, String courseNumber, List<String> courseKeys) {
        super(context, resource, objects);
        sections = objects;
        this.collegeId = collgeId;
        this.courseNumber = courseNumber;
        this.courseKeys = courseKeys;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_sections_item, parent, false);
        }

        if(sections.size()==0){
            return convertView;
        }
        TextView sectionBox = (TextView) convertView.findViewById(R.id.sectionName);
        String courseSection = sections.get(position);
        sectionBox.setText(courseSection);

        ImageButton addCourse = (ImageButton) convertView.findViewById(R.id.addCourse);
        addCourse.setTag(position);
        addCourse.setOnClickListener(myButtonClickListener);

        return convertView;
    }

    private View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            String courseKey = courseKeys.get(position);
            Toast.makeText(v.getContext(), "You selected " + courseKey, Toast.LENGTH_LONG).show();
        }
    };
}
