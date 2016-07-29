package com.mapped;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pegasus on 7/16/16.
 */
public class NewsFeedFragment extends Fragment {

    List<NewsFeedEvents> newsFeedToday = new ArrayList<>();
    List<NewsFeedEvents> newsFeedUpcoming  =new ArrayList<>();
    List<NewsFeedEvents> newsFeedFeatured = new ArrayList<>();
    HashMap<String, String> linksToOrgs = new HashMap<>();
    NewsFeedListAdapter newsFeedListAdapterToday;
    NewsFeedListAdapter newsFeedListAdapterUpcoming;
    NewsFeedListAdapter newsFeedListAdapterFeatured;
    List<String> tempNamesToday = new ArrayList<>();
    List<String> tempDescToday = new ArrayList<>();
    List<String> tempNamesUpcoming = new ArrayList<>();
    List<String> tempDescUpcoming = new ArrayList<>();
    List<String> tempNamesFeatured = new ArrayList<>();
    List<String> tempDescFeatured = new ArrayList<>();
    FragmentActivity faActivity;
    int checkedStatusToday=0;
    int checkedStatusUpcoming=0;
    int checkedStatusFeatured = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v =inflater.inflate(R.layout.news_feed_fragment_layout, container, false);
        faActivity = super.getActivity();
        Calendar calendar = Calendar.getInstance();

        final int today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DAY_OF_MONTH,1);
        final int upcomingDate = calendar.get(Calendar.DAY_OF_MONTH);
        Firebase orgsRef = new Firebase("https://mapped-cc2e9.firebaseio.com/organisations");

        orgsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                int i=0;
                while (it.hasNext()) {
                    DataSnapshot orgId = (DataSnapshot) it.next();
                    OrganisationsInfo org = orgId.getValue(OrganisationsInfo.class);
                    linksToOrgs.put(org.getOrgname(), org.getOrglogolink());
                }
                while(i< tempNamesToday.size()){
                    newsFeedToday.add(new NewsFeedEvents(linksToOrgs.get(tempNamesToday.get(i)), tempNamesToday.get(i), tempDescToday.get(i)));
                    i++;
                }
                i=0;
                while(i<tempNamesToday.size()){
                    newsFeedUpcoming.add(new NewsFeedEvents(linksToOrgs.get(tempNamesUpcoming.get(i)), tempNamesUpcoming.get(i), tempDescUpcoming.get(i)));
                    i++;
                }
                i=0;
                while(i<tempNamesFeatured.size()){
                    newsFeedFeatured.add(new NewsFeedEvents(linksToOrgs.get(tempNamesFeatured.get(i)),tempNamesFeatured.get(i), tempDescFeatured.get(i)));
                    i++;
                }

                newsFeedListAdapterToday.notifyDataSetChanged();
                newsFeedListAdapterUpcoming.notifyDataSetChanged();
                newsFeedListAdapterFeatured.notifyDataSetChanged();
//                v.findViewById(R.id.loadingPanelNewsFeed).setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

        Firebase eventsRef = new Firebase("https://mapped-cc2e9.firebaseio.com/eventsMaster/events");

        Query queryRef = eventsRef.orderByChild("university").equalTo("univ_001");

        queryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                while (it.hasNext()) {
                    DataSnapshot eventId = (DataSnapshot) it.next();
                    EventInfo event = eventId.getValue(EventInfo.class);
                    String tempeventDate = event.getDate().substring(2,4);
                    int eventDate = Integer.parseInt(tempeventDate);
                    if(eventDate==today){
                        tempNamesToday.add(event.getOrganisation());
                        tempDescToday.add(event.getDescription());
                    }
                    if(eventDate==upcomingDate){
                        tempNamesUpcoming.add(event.getOrganisation());
                        tempDescUpcoming.add(event.getDescription());
                    }
                    if(event.getFeatured().equals("yes")){
                        tempNamesFeatured.add(event.getOrganisation());
                        tempDescFeatured.add(event.getDescription());
                    }

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(1500);

//        final Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(1000);
//        fadeOut.setDuration(1500);

        newsFeedListAdapterToday = new NewsFeedListAdapter(v.getContext(), R.layout.news_feed_fragment_item, newsFeedToday);
        newsFeedListAdapterUpcoming = new NewsFeedListAdapter(v.getContext(),R.layout.news_feed_fragment_item, newsFeedUpcoming);
        newsFeedListAdapterFeatured = new NewsFeedListAdapter(v.getContext(), R.layout.news_feed_fragment_item, newsFeedFeatured);

        checkedStatusToday=1;
        final ListView mainList = (ListView) v.findViewById(R.id.mainList);
        mainList.setAdapter(newsFeedListAdapterToday);

        RadioButton todayRadio = (RadioButton) v.findViewById(R.id.todayRadio);
        todayRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedStatusToday==0){
                    mainList.setAdapter(newsFeedListAdapterToday);
                    mainList.startAnimation(fadeIn);
                }
                checkedStatusToday=1;
                checkedStatusUpcoming=0;
                checkedStatusFeatured=0;

            }
        });

        RadioButton upcomingRadio = (RadioButton) v.findViewById(R.id.upcomingRadio);
        upcomingRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkedStatusUpcoming==0){
                    mainList.setAdapter(newsFeedListAdapterUpcoming);
                    mainList.startAnimation(fadeIn);
                }
                checkedStatusToday=0;
                checkedStatusUpcoming=1;
                checkedStatusFeatured=0;
            }
        });
        RadioButton featuredRadio = (RadioButton) v.findViewById(R.id.featuredRadio);
        featuredRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedStatusFeatured==0){
                    mainList.setAdapter(newsFeedListAdapterFeatured);
                    mainList.startAnimation(fadeIn);
                }
                checkedStatusToday=0;
                checkedStatusUpcoming=0;
                checkedStatusFeatured=1;
            }
        });
        return v;
    }
}
