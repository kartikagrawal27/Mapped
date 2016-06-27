package com.mapped;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        final VideoView bgview = (VideoView) findViewById(R.id.videoView);

        String uripath2 = "android.resource://" + getPackageName() + "/" + R.raw.aurora_login_screen;
        Uri bguri = Uri.parse(uripath2);

        bgview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bgview.start();
            }
        });


        bgview.setVideoURI(bguri);
        bgview.start();

        TextView tx = (TextView) findViewById(R.id.Mapped);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.otf");
        tx.setTypeface(custom_font);

        final EditText emailinput = (EditText) findViewById(R.id.emailinput);
        emailinput.clearComposingText();

        final EditText passwordinput = (EditText) findViewById(R.id.loginpassword);

        final Button loginbutton = (Button) findViewById(R.id.loginbutton);


        final EditText fullname = (EditText) findViewById(R.id.fullname);
        final int viewwidth = fullname.getWidth();

        fullname.animate()
                .translationX(fullname.getWidth())
                .alpha(0.0f)
                .setDuration(1);

        final Button signupbutton = (Button) findViewById(R.id.signupbutton);
        signupbutton.animate()
                .translationX(viewwidth)
                .alpha(0.0f)
                .setDuration(1);

        final Button alreadyregistered = (Button) findViewById(R.id.alreadyregistered);
        alreadyregistered.animate()
                .translationX(viewwidth)
                .alpha(0.0f)
                .setDuration(1);

        final Button forgotpasswordbutton = (Button) findViewById(R.id.forgotpasswordbutton);

        final Button signup = (Button) findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbutton.animate()
                        .translationX(-(loginbutton.getWidth()))
                        .alpha(0.0f)
                        .setDuration(400);
                forgotpasswordbutton.animate()
                        .translationX(-(forgotpasswordbutton.getWidth()))
                        .alpha(0.0f)
                        .setDuration(400);
                signup.animate()
                        .translationX(-(signup.getWidth()))
                        .alpha(0.0f)
                        .setDuration(400);
                fullname.animate()
                        .translationX(-(viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
                signupbutton.animate()
                        .translationX(-(viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
                alreadyregistered.animate()
                        .translationX(-(viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
            }
        });

        alreadyregistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbutton.animate()
                        .translationX((viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
                forgotpasswordbutton.animate()
                        .translationX((viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
                signup.animate()
                        .translationX((viewwidth))
                        .alpha(1.0f)
                        .setDuration(400);
                fullname.animate()
                        .translationX((viewwidth))
                        .alpha(0.0f)
                        .setDuration(400);
                signupbutton.animate()
                        .translationX((viewwidth))
                        .alpha(0.0f)
                        .setDuration(400);
                alreadyregistered.animate()
                        .translationX((viewwidth))
                        .alpha(0.0f)
                        .setDuration(400);
            }
        });


        //onResume(); video needs to be loaded and started
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();

        final VideoView bgview = (VideoView) findViewById(R.id.videoView);
        String uripath2 = "android.resource://" + getPackageName() + "/" + R.raw.aurora_login_screen;
        Uri bguri = Uri.parse(uripath2);

        bgview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bgview.start();
            }
        });


        bgview.setVideoURI(bguri);
        bgview.start();
    }
}
