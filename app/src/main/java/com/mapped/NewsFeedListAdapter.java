package com.mapped;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pegasus on 7/28/16.
 */
public class NewsFeedListAdapter extends ArrayAdapter<NewsFeedEvents> {

    List<NewsFeedEvents> events = new ArrayList<>();
    int logocheck=0;
    View view;


    public NewsFeedListAdapter(Context context, int resource, List<NewsFeedEvents> objects) {
        super(context, resource, objects);
        View rootView = ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        view = rootView;
        events = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_feed_fragment_item, parent, false);
        }

        if(events.size()==0){
            RelativeLayout loader = (RelativeLayout) view.findViewById(R.id.loadingPanelNewsFeed);
            loader.setVisibility(View.GONE);
            return convertView;
        }
        else
        {
            NewsFeedEvents event = events.get(position);

            TextView orgName = (TextView) convertView.findViewById(R.id.orgName);
            TextView orgDesc = (TextView) convertView.findViewById(R.id.eventDesc);
            orgName.setText(event.getOrgName());
            orgDesc.setText(event.getDesc());

            final ImageView logo = (ImageView) convertView.findViewById(R.id.orgLogo);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://mapped-cc2e9.appspot.com");

            storageRef.child(event.getOrgName() + ".png").getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    logo.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    logo.setImageResource(R.drawable.defaultcompanylogo);
                }
            });
            if(events.size()-1 == position){
                RelativeLayout loader = (RelativeLayout) view.findViewById(R.id.loadingPanelNewsFeed);
                loader.setVisibility(View.GONE);
            }
            return convertView;
        }
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
