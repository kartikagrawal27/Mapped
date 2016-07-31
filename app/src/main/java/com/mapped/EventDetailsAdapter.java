package com.mapped;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class EventDetailsAdapter extends ArrayAdapter {

    List<EventInfo> eventsOfDay = new ArrayList<>();


    public EventDetailsAdapter(Context context, int resource, List<EventInfo> dayEvents) {
        super(context, resource, dayEvents);
        eventsOfDay = dayEvents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_detail_item, parent, false);
        }

        EventInfo currentDay = eventsOfDay.get(position);

        Button infoButton = (Button) convertView.findViewById(R.id.infoButton);
        infoButton.setTag(position);

        infoButton.setOnClickListener(myButtonClickListener);

        LinearLayout colorCode = (LinearLayout) convertView.findViewById(R.id.eventColorCode);

        TextView startTime = (TextView) convertView.findViewById(R.id.startTimeD);
        TextView endTime = (TextView) convertView.findViewById(R.id.endTimeD);

        TextView eventName = (TextView) convertView.findViewById(R.id.eventNameD);
        TextView eventLocation = (TextView) convertView.findViewById(R.id.eventLocationD);
        TextView presenterName = (TextView) convertView.findViewById(R.id.eventPresenterD);


        switch (currentDay.getEvent_type()){
            case "course":
                colorCode.setBackgroundResource(R.color.school);
                break;
            case "social":
                colorCode.setBackgroundResource(R.color.social);
                break;
            case "corporate":
                colorCode.setBackgroundResource(R.color.corporate);
                break;
            case "club":
                colorCode.setBackgroundResource(R.color.club);
                break;
        }

        startTime.setText(currentDay.getStart_time());
        endTime.setText(currentDay.getEnd_time());

        eventName.setText(currentDay.getShort_desc());
        eventLocation.setText(currentDay.getLocation());
        presenterName.setText(currentDay.getPresenter_name());

        return convertView;
    }

    private View.OnClickListener myButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (Integer) v.getTag();
            EventInfo selectedEvent = eventsOfDay.get(position);
            Toast.makeText(v.getContext(), "You pressed the i button of " + selectedEvent.getEventKey(), Toast.LENGTH_LONG).show();
        }
    };

}
