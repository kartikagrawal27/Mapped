package com.mapped;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Pegasus on 7/16/16.
 */
public class NewsFeedFragment extends Fragment {

    List<NewsFeedEvents> newsFeedToday = new ArrayList<>();
    HashMap<String, String> linksToOrgs = new HashMap<>();
    NewsFeedListAdapter newsFeedListAdapter;
    List<String> tempNames = new ArrayList<>();
    List<String> tempDesc = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v =inflater.inflate(R.layout.news_feed_fragment_layout, container, false);
        Calendar calendar = Calendar.getInstance();

        final int today = calendar.get(Calendar.DAY_OF_MONTH);

        Firebase orgsRef = new Firebase("https://mapped-cc2e9.firebaseio.com/organisations");

        orgsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();
                int i=0;
                while (it.hasNext()) {
                    DataSnapshot orgId = (DataSnapshot) it.next();
                    OrganisationsInfo org = orgId.getValue(OrganisationsInfo.class);
                    linksToOrgs.put(org.getQwename(), org.getAsdlink());
                }
                while(i<tempNames.size()){
                    newsFeedToday.add(new NewsFeedEvents(linksToOrgs.get(tempNames.get(i)), tempNames.get(i), tempDesc.get(i)));
                    i++;
                }

                newsFeedListAdapter.notifyDataSetChanged();
                v.findViewById(R.id.loadingPanelNewsFeed).setVisibility(View.GONE);
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
                        tempNames.add(event.getOrganisation());
                        tempDesc.add(event.getDescription());
                    }
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        newsFeedListAdapter = new NewsFeedListAdapter(v.getContext(), R.layout.news_feed_fragment_item, newsFeedToday);
        ListView mainList = (ListView) v.findViewById(R.id.mainList);
        mainList.setAdapter(newsFeedListAdapter);

        return v;

    }
}
