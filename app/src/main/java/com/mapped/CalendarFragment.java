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
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v =inflater.inflate(R.layout.calendar_fragment_layout, container, false);
        faActivity = super.getActivity();

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
                v.findViewById(R.id.loadingPanelCalendarFragment).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, position);
                Intent intent = new Intent(faActivity, EventDetailsActivity.class);
                intent.putExtra("calEvents", calEvents.get(position));
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        return v;
    }

}
