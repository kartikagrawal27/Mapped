package com.mapped;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Pegasus on 7/17/16.
 */
public class EventsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_layout,container, false);

//        TextView textView = (TextView) view.findViewById(R.id.testBox);
//        textView.setText("Hello")

//        RelativeLayout corporateLayout = (RelativeLayout) view.findViewById(R.id.corporateButton);
//        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) corporateLayout.getLayoutParams();
//        params.width=;
//        view_instance.setLayoutParams(params);

        return view;
    }
}
