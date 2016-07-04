package com.mapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<CalendarDay>{

    private List<CalendarDay> calendarDayList;

    public CalendarAdapter(Context context, int resource, List<CalendarDay> objects) {
        super(context, resource, objects);
        calendarDayList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_item, parent, false);
        }

        String finalday = getCurrentDay(position);

        CalendarDay calendarDay = calendarDayList.get(position);
        String starttimes[] = calendarDay.getStartTime();
        String eventnames[] = calendarDay.getEventName();

        TextView dayofweek = (TextView) convertView.findViewById(R.id.day);
        dayofweek.setText(finalday);

        TextView dateView = (TextView) convertView.findViewById(R.id.particularDate);

//        int daysInMonth = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar c  = Calendar.getInstance();
        int monthDate = c.get(Calendar.DAY_OF_MONTH)+position;

        dateView.setText(String.valueOf(monthDate));


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
