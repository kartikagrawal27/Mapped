package com.mapped;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pegasus on 6/29/16.
 */
public class CalendarDay implements Parcelable {

    private List<String> startTime = new ArrayList<String>();
    private List<String> eventName = new ArrayList<String>();
    private List<String> eventType = new ArrayList<String>();
    private List<String> endTime   = new ArrayList<String>();
    private List<String> location  = new ArrayList<String>();
    private List<String> presenterName = new ArrayList<String>();

    public List<String> getPresenterName(){
        return presenterName;
    }

    public List<String> getLocation(){
        return location;
    }

    public List<String> getStartTime() {
        return startTime;
    }

    public List<String> getEventName() {
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
                                    startTime.add(event.getStart_time());
                                    eventName.add(event.getName());
                                    eventType.add(event.getEvent_type());
                                    endTime.add(event.getEnd_time());
                                    location.add(event.getLocation());
                                    presenterName.add(event.getPresenter_name());
                                }
                            } else {
                                String stringDate = event.getDate().substring(2, 4);
                                int date = Integer.parseInt(stringDate);
                                if (date == thisDay) {
                                    startTime.add(event.getStart_time());
                                    eventName.add(event.getName());
                                    eventType.add(event.getEvent_type());
                                    endTime.add(event.getEnd_time());
                                    location.add(event.getLocation());
                                    presenterName.add(event.getPresenter_name());
                                }
                            }
                        }
                    } catch(Exception ex){
                        System.out.println(ex);
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


    public List<String> getEventType() {
        return eventType;
    }

    public List<String>getEndTime(){
        return endTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(startTime);
        dest.writeList(eventName);
        dest.writeList(eventType);
        dest.writeList(endTime);
        dest.writeList(location);
        dest.writeList(presenterName);
    }

    public CalendarDay(Parcel in) {
        in.readList(startTime, List.class.getClassLoader());
        in.readList(eventName, List.class.getClassLoader());
        in.readList(eventType, List.class.getClassLoader());
        in.readList(endTime, List.class.getClassLoader());
        in.readList(location, List.class.getClassLoader());
        in.readList(presenterName, List.class.getClassLoader());
    }
    public static final Parcelable.Creator<CalendarDay> CREATOR = new Parcelable.Creator<CalendarDay>() {

        public CalendarDay createFromParcel(Parcel in) {
            return new CalendarDay(in);
        }

        public CalendarDay[] newArray(int size) {
            return new CalendarDay[size];
        }
    };

}
