package com.mapped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pegasus on 7/28/16.
 */
public class NewsFeedListAdapter extends ArrayAdapter<NewsFeedEvents> {

    List<NewsFeedEvents> events = new ArrayList<>();


    public NewsFeedListAdapter(Context context, int resource, List<NewsFeedEvents> objects) {
        super(context, resource, objects);
        events = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_feed_fragment_item, parent, false);
        }

        if(events.size()==0){
            return convertView;
        }
        else
        {
            NewsFeedEvents event = events.get(position);

            TextView orgName = (TextView) convertView.findViewById(R.id.orgName);
            TextView orgDesc = (TextView) convertView.findViewById(R.id.eventDesc);
            orgName.setText(event.getOrgName());
            orgDesc.setText(event.getDesc());

            ImageView logo = (ImageView) convertView.findViewById(R.id.orgLogo);
            int resID = getContext().getResources().getIdentifier(event.getImageCode() , "drawable", getContext().getPackageName());

            logo.setImageResource(resID);
            return convertView;
        }
    }
}
