package com.mapped;

import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pegasus on 6/29/16.
 */
public class CalendarDay {

    private String startTime[] = new String[3];
    private String eventName[] = new String[3];
    private String eventType[] = new String[3];

    public String[] getStartTime() {
        return startTime;
    }

    public String[] getEventName() {
        return eventName;
    }


    public CalendarDay(int position, final List<String> user_subsribed_events, final CalendarAdapter calendarAdapter){

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

        final int thisDay = calendar.get(Calendar.DAY_OF_MONTH) + position;

        final Firebase ref = new Firebase("https://mapped-cc2e9.firebaseio.com/events");

        final String finalDayofweek = dayofweek;

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int count = 0;

                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                while(it.hasNext()) {
                    try {
                        DataSnapshot eventId = (DataSnapshot) it.next();

                        String event_name = eventId.getKey();


                        if (user_subsribed_events.contains(event_name)) {

                            EventInfo event = eventId.getValue(EventInfo.class);
                            if (event.getDate().equals("null")) {

                                List<String> days = event.getDays();
                                if (days.contains(finalDayofweek)) {
                                    startTime[count] = event.getStart_time();
                                    eventName[count] = event.getName();
                                    eventType[count] = event.getEvent_type();
                                    count = count + 1;
                                    if (count > 2)
                                        break;
                                }
                            } else {
                                String stringDate = event.getDate().substring(2, 4);
                                int date = Integer.parseInt(stringDate);
                                if (date == thisDay) {
                                    startTime[count] = event.getStart_time();
                                    eventName[count] = event.getName();
                                    eventType[count] = event.getEvent_type();
                                    count = count + 1;
                                    if (count > 2)
                                        break;
                                }
                            }
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
                    }
                }
                if (count <= 2) {
                    for (int tempcount = count; tempcount < 3; tempcount++) {
                        startTime[tempcount] = "";
                        eventName[tempcount] = "";
                        eventType[tempcount] = "";
                    }
                }
                calendarAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    public String[] getEventType() {
        return eventType;
    }
}
