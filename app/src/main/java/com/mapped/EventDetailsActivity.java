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
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
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
    }

    public void createCustomData(CalendarDay touchedDay){
        List<String> startTime = touchedDay.getStartTime();
        List<String> eventName = touchedDay.getEventShortDesc();
        List<String> endTime = touchedDay.getEndTime();
        List<String> location = touchedDay.getLocation();
        List<String> presenterName = touchedDay.getPresenterName();
        List<String> eventType = touchedDay.getEventType();

        for(int i=0;i<startTime.size();i++){
            dayEvents.add(new EventInfo(null,eventType.get(i), location.get(i), presenterName.get(i),startTime.get(i), endTime.get(i), eventName.get(i),null, null, null));
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
