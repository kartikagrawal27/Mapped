package com.mapped;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     //     */
//    private static final boolean AUTO_HIDE = true;
//
//    /**
//     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
//     * user interaction before hiding the system UI.
//     */
//    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     * //
     */
//    private static final int UI_ANIMATION_DELAY = 300;
//    private final Handler mHideHandler = new Handler();
//    private View mContentView;
//    private final Runnable mHidePart2Runnable = new Runnable() {
//        @SuppressLint("InlinedApi")
//        @Override
//        public void run() {
//            // Delayed removal of status and navigation bar
//
//            // Note that some of these constants are new as of API 16 (Jelly Bean)
//            // and API 19 (KitKat). It is safe to use them, as they are inlined
//            // at compile-time and do nothing on earlier devices.
//            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
////                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
////                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
////                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
////                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
////        }
////    };
//    private View mControlsView;
//    private final Runnable mShowPart2Runnable = new Runnable() {
//        @Override
//        public void run() {
//            // Delayed display of UI elements
//            ActionBar actionBar = getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.show();
//            }
//            mControlsView.setVisibility(View.VISIBLE);
//        }
//    };
//    private boolean mVisible;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        setContentView(R.layout.activity_splash_screen);
        ImageView mapped = (ImageView) findViewById(R.id.splash_mapped);
        mapped.setImageResource(R.drawable.maybe1);


        TextView heading = (TextView) findViewById(R.id.mappedText);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/BeforeTheRain.ttf");
        heading.setTypeface(custom_font);

        mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Toast.makeText(SplashScreenActivity.this, "User is signed in",
//                            Toast.LENGTH_LONG).show();
//
//                } else {
//                    // User is signed out
//                    Toast.makeText(SplashScreenActivity.this, "User is not signed in",
//                            Toast.LENGTH_LONG).show();
//                }
//                // ...
//            }
//        };
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String email = user.getEmail();
//            String uid = user.getUid();
//            Toast.makeText(SplashScreenActivity.this, "User email is: " + email,
//                    Toast.LENGTH_LONG).show();
//
//        }


        SharedPreferences loginPreferences = this.getSharedPreferences("Login", 0);
        email = loginPreferences.getString("email", null);
        password = loginPreferences.getString("password", null);


        new gatherSplashInfo().execute();
    }


    private class gatherSplashInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (email == null || password == null) {

                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            else {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(email, password);

                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SplashScreenActivity.this, CalendarActivity.class);
                                    startActivity(intent);
                                } else {

                                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
        //}
    }
}
