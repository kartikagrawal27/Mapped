package com.mapped;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CourseSectionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<String> sections = new ArrayList<>();
    List<String> courseKeys = new ArrayList<>();
    CourseSectionsAdapter courseSectionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_sections);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Manage Courses");
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final RelativeLayout loadingPanel = (RelativeLayout) findViewById(R.id.loadingPanelCourseSections);
        TextView selectedCourse = (TextView) findViewById(R.id.selectedCourse);

        Intent intent = getIntent();
        String courseSelected = intent.getStringExtra("courseSelected");

        selectedCourse.setText(courseSelected);

        final String collegeId = courseSelected.substring(0, courseSelected.indexOf(" "));
        String courseNumber = courseSelected.substring(courseSelected.indexOf(" ")+1, courseSelected.length());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Firebase.setAndroidContext(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Firebase ref = new Firebase("https://mapped-cc2e9.firebaseio.com/eventsMaster/courses");
        Query query = ref.orderByChild("courseNumber").equalTo(courseNumber);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                while (it.hasNext()) {
                    DataSnapshot courseId = (DataSnapshot) it.next();
                    CourseInfo course = courseId.getValue(CourseInfo.class);
                    String key = courseId.getKey();
                    if(course.getCollegeId().equals(collegeId)){
                        sections.add(course.getSection());
                        courseKeys.add(key);
                    }
                }
                courseSectionsAdapter.notifyDataSetChanged();
                loadingPanel.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        ListView sectionsList = (ListView) findViewById(R.id.courseSections);
        courseSectionsAdapter = new CourseSectionsAdapter(this, R.layout.course_sections_item, sections, collegeId, courseNumber, courseKeys);
        sectionsList.setAdapter(courseSectionsAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.course_sections, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
            Intent homeIntent = new Intent(CourseSectionsActivity.this, ViewPagerActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(homeIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_courses) {
            Intent manCoursesIntent = new Intent(CourseSectionsActivity.this, CollegesActivity.class);
            startActivity(manCoursesIntent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        } else if (id == R.id.nav_myClubs) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {

            FirebaseAuth.getInstance().signOut();
            SharedPreferences loginPreferences = this.getSharedPreferences("Login", 0);
            SharedPreferences.Editor editor = loginPreferences.edit();
            editor.remove("email");
            editor.remove("password");
            editor.commit();
            Intent loginIntent = new Intent(CourseSectionsActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
