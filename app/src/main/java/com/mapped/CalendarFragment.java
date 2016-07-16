package com.mapped;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pegasus on 7/16/16.
 */
public class CalendarFragment extends Fragment {



    private TextView myMonthYear;
    private View view;
    private FragmentActivity faActivity;
    private List<CalendarDay> calEvents = new ArrayList<>();
    private List<String> user_subscribed_events;
    private CalendarAdapter calendarAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.calendar_fragment_layout, container, false);
        faActivity = super.getActivity();

        TextView myView = (TextView) v.findViewById(R.id.aasd);
        myView.setText("Hello");

        Firebase.setAndroidContext(faActivity);



        myMonthYear = (TextView) v.findViewById(R.id.aasd);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        String monthString = null;
        switch (month) {
            case 0:
                monthString = "JANUARY";
                break;
            case 1:
                monthString = "FEBRUARY";
                break;
            case 2:
                monthString = "MARCH";
                break;
            case 3:
                monthString = "APRIL";
                break;
            case 4:
                monthString = "MAY";
                break;
            case 5:
                monthString = "JUNE";
                break;
            case 6:
                monthString = "JULY";
                break;
            case 7:
                monthString = "AUGUST";
                break;
            case 8:
                monthString = "SEPTEMBER";
                break;
            case 9:
                monthString = "OCTOBER";
                break;
            case 10:
                monthString = "NOVEMBER";
                break;
            case 11:
                monthString = "DECEMBER";
                break;
        }

        int year = c.get(Calendar.YEAR);
        String yearString = Integer.toString(year);

        myMonthYear.setText(monthString + " " + yearString);



        calendarAdapter = new CalendarAdapter(faActivity, R.layout.calendar_item, calEvents);
        final ListView lv = (ListView) v.findViewById(R.id.qwert);

        Firebase ref = new Firebase("https://mapped-cc2e9.firebaseio.com/users/user_001");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserInfo myUser = dataSnapshot.getValue(UserInfo.class);
                user_subscribed_events = myUser.getSubscribed_events();

                calEvents.add(new CalendarDay(0, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(1, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(2, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(3, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(4, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(5, user_subscribed_events, calendarAdapter));
                calEvents.add(new CalendarDay(6, user_subscribed_events, calendarAdapter));
                lv.setAdapter(calendarAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(faActivity, EventDetailsActivity.class);
                intent.putExtra("calEvents", calEvents.get(position));
                startActivity(intent);
            }
        });

        return v;
    }
}
