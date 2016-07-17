package com.mapped;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Pegasus on 7/16/16.
 */
public class NewsFeedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.news_feed_fragment_layout, container, false);
//        TextView myView = (TextView) v.findViewById(R.id.ggh);
//        myView.setText("2");
        return v;

    }
}
