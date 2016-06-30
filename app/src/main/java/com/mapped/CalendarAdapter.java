package com.mapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CalendarAdapter extends ArrayAdapter<EventDay>{

    private List<EventDay> eventDay;

    public CalendarAdapter(Context context, int resource, List<EventDay> objects) {
        super(context, resource, objects);
        eventDay = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_item, parent, false);
        }


        EventDay eventday = eventDay.get(position);
        String starttimes[] = eventday.getStartTime();
        String eventnames[] = eventday.getEventName();

        TextView dayofweek = (TextView) convertView.findViewById(R.id.day);
        dayofweek.setText(eventday.getDayofweek());

        TextView starttime1 = (TextView) convertView.findViewById(R.id.starttime1);
        TextView starttime2 = (TextView) convertView.findViewById(R.id.starttime2);
        TextView starttime3 = (TextView) convertView.findViewById(R.id.starttime3);

        TextView eventname1 = (TextView) convertView.findViewById(R.id.eventname1);
        TextView eventname2 = (TextView) convertView.findViewById(R.id.eventname2);
        TextView eventname3 = (TextView) convertView.findViewById(R.id.eventname3);

        starttime1.setText(starttimes[0]);
        starttime2.setText(starttimes[1]);
        starttime3.setText(starttimes[2]);

        eventname1.setText(eventnames[0]);
        eventname2.setText(eventnames[1]);
        eventname3.setText(eventnames[2]);

        return convertView;
    }
}
