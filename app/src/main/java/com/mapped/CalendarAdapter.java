package com.mapped;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<CalendarDay>{

    private List<CalendarDay> calendarDayList;
    Context context;

    public CalendarAdapter(Context context, int resource, List<CalendarDay> objects) {
        super(context, resource, objects);
        calendarDayList = objects;
        context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_item, parent, false);
        }

        String finalday = getCurrentDay(position);

        CalendarDay calendarDay = calendarDayList.get(position);

        ImageView colorCode1 = (ImageView) convertView.findViewById(R.id.colorCode1);
        ImageView colorCode2 = (ImageView) convertView.findViewById(R.id.colorCode2);
        ImageView colorCode3 = (ImageView) convertView.findViewById(R.id.colorCode3);


        List<String> eventType = calendarDay.getEventType();

        notifyDataSetChanged();

        if(eventType!=null){

                if (eventType.size()>0){

                    switch (eventType.get(0)){
                        case "course":
                            colorCode1.setImageResource(R.drawable.course);
                            break;
                        case "social":
                            colorCode1.setImageResource(R.drawable.social);
                            break;
                        case "corporate":
                            colorCode1.setImageResource(R.drawable.corporate);
                            break;
                        case "club":
                            colorCode1.setImageResource(R.drawable.club);
                            break;
                    }
                }

                if(eventType.size()>1){

                    switch (eventType.get(1)){
                        case "course":
                            colorCode2.setImageResource(R.drawable.course);
                            break;
                        case "social":
                            colorCode2.setImageResource(R.drawable.social);
                            break;
                        case "corporate":
                            colorCode2.setImageResource(R.drawable.corporate);
                            break;
                        case "club":
                            colorCode2.setImageResource(R.drawable.club);
                            break;
                    }
                }

                if(eventType.size()>2) {
                    switch (eventType.get(2)){
                        case "course":
                            colorCode3.setImageResource(R.drawable.course);
                            break;
                        case "social":
                            colorCode3.setImageResource(R.drawable.social);
                            break;
                        case "corporate":
                            colorCode3.setImageResource(R.drawable.corporate);
                            break;
                        case "club":
                            colorCode3.setImageResource(R.drawable.club);
                            break;
                    }
                }
        }

        List<String> starttimes = calendarDay.getStartTime();
        List<String> eventnames = calendarDay.getEventName();

        TextView dayofweek = (TextView) convertView.findViewById(R.id.day);
        dayofweek.setText(finalday);

        TextView dateView = (TextView) convertView.findViewById(R.id.particularDate);

        Calendar c  = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, position);
        int monthDate = c.get(Calendar.DAY_OF_MONTH);

        dateView.setText(String.valueOf(monthDate));


        TextView starttime1 = (TextView) convertView.findViewById(R.id.starttime1);
        TextView starttime2 = (TextView) convertView.findViewById(R.id.starttime2);
        TextView starttime3 = (TextView) convertView.findViewById(R.id.starttime3);

        TextView eventname1 = (TextView) convertView.findViewById(R.id.eventname1);
        TextView eventname2 = (TextView) convertView.findViewById(R.id.eventname2);
        TextView eventname3 = (TextView) convertView.findViewById(R.id.eventname3);

        if(starttimes!=null) {
            if (starttimes.size()>0)
                starttime1.setText(starttimes.get(0));

            if (starttimes.size()>1)
                starttime2.setText(starttimes.get(1));

            if (starttimes.size()>2)
                starttime3.setText(starttimes.get(2));
        }

        if(eventnames!=null) {
            if (eventnames.size()>0)
                eventname1.setText(eventnames.get(0));

            if(starttimes.size()>1)
                eventname2.setText(eventnames.get(1));

            if (starttimes.size()>2)
                eventname3.setText(eventnames.get(2));
        }
        return convertView;
    }

    public String getCurrentDay(int position){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int finalDay = (day+position);
        String dayofweek = null;

        if(finalDay>7)
            finalDay=finalDay-7;


        switch (finalDay) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                dayofweek = "SUN";
                break;

            case Calendar.MONDAY:
                dayofweek = "MON";
                break;
            // Current day is Monday

            case Calendar.TUESDAY:
                dayofweek = "TUE";
                break;

            case Calendar.WEDNESDAY:
                dayofweek = "WED";
                break;

            case Calendar.THURSDAY:
                dayofweek = "THU";
                break;

            case Calendar.FRIDAY:
                dayofweek = "FRI";
                break;

            case Calendar.SATURDAY:
                dayofweek = "SAT";
                break;
        }
        return dayofweek;
    }
}
