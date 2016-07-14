package com.mapped;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CalendarActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<CalendarDay> calEvents = new ArrayList<>();
    private List<String> user_subscribed_events;
    private CalendarAdapter calendarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_calendar);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Timeline");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView monthYear = (TextView) findViewById(R.id.monthyear);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        String monthString = null;
        switch (month){
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

        monthYear.setText(monthString+ " " + yearString);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        calendarAdapter = new CalendarAdapter(this, R.layout.calendar_item, calEvents);
        final ListView lv = (ListView) findViewById(R.id.maincalendar);

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
                Intent intent = new Intent(CalendarActivity.this, EventDetailsActivity.class);
                intent.putExtra("calEvents", calEvents.get(position));
                startActivity(intent);

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
//            Intent backtoHome = new Intent(Intent.ACTION_MAIN);
//            backtoHome.addCategory(Intent.CATEGORY_HOME);
//            backtoHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(backtoHome);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
