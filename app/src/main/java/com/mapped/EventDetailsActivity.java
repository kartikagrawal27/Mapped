package com.mapped;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<EventInfo> dayEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Day Details");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        CalendarDay touchedDay = intent.getParcelableExtra("calEvents");

        int offset = intent.getIntExtra("position",0);

        String finalDay = null;


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, offset);


        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case 1:
                finalDay = "Sunday";
                break;
            case 2:
                finalDay = "Monday";
                break;
            case 3:
                finalDay = "Tuesday";
                break;
            case 4:
                finalDay = "Wednesday";
                break;
            case 5:
                finalDay = "Thursday";
                break;
            case 6:
                finalDay = "Friday";
                break;
            case 7:
                finalDay = "Saturday";
                break;
        }

        String finalMonth = null;


        switch (calendar.get(Calendar.MONTH)){
            case 0:
                finalMonth = "January";
                break;
            case 1:
                finalMonth = "February";
                break;
            case 2:
                finalMonth = "March";
                break;
            case 3:
                finalMonth = "April";
                break;
            case 4:
                finalMonth = "May";
                break;
            case 5:
                finalMonth = "June";
                break;
            case 6:
                finalMonth = "July";
                break;
            case 7:
                finalMonth = "August";
                break;
            case 8:
                finalMonth = "September";
                break;
            case 9:
                finalMonth = "October";
                break;
            case 10:
                finalMonth = "November";
                break;
            case 11:
                finalMonth = "December";
                break;
        }

        String finalDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));


        TextView chosenDate = (TextView) findViewById(R.id.currentDay);
        chosenDate.setText(finalDay + ", " + finalMonth + " " + finalDate);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createCustomData(touchedDay);

        EventDetailsAdapter eventDetailsAdapter = new EventDetailsAdapter(this, R.layout.event_detail_item, dayEvents);
        ListView lv = (ListView) findViewById(R.id.eventDetails);
        lv.setAdapter(eventDetailsAdapter);
        navigationView.setItemIconTintList(null);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventKey = dayEvents.get(position).getEventKey();
                Toast.makeText(EventDetailsActivity.this, "You have selected " + eventKey , Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void createCustomData(CalendarDay touchedDay){
        List<String> startTime = touchedDay.getStartTime();
        List<String> eventName = touchedDay.getEventShortDesc();
        List<String> endTime = touchedDay.getEndTime();
        List<String> location = touchedDay.getLocation();
        List<String> presenterName = touchedDay.getPresenterName();
        List<String> eventType = touchedDay.getEventType();
        List<String> eventKeys = touchedDay.getEventKeys();

        for(int i=0;i<startTime.size();i++){
            dayEvents.add(new EventInfo(null,eventType.get(i), location.get(i), presenterName.get(i),startTime.get(i), endTime.get(i), eventName.get(i),null, null, null, null, eventKeys.get(i)));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.myCourses) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent homeIntent = new Intent(EventDetailsActivity.this, ViewPagerActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(homeIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_courses) {
            Intent manCoursesIntent = new Intent(EventDetailsActivity.this, CollegesActivity.class);
            startActivity(manCoursesIntent);

        } else if (id == R.id.nav_myClubs) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

            FirebaseAuth.getInstance().signOut();
            SharedPreferences loginPreferences = this.getSharedPreferences("Login", 0);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.remove("email");
            editor.remove("password");
            editor.commit();
            Intent loginIntent = new Intent(EventDetailsActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
