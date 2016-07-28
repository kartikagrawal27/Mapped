package com.mapped;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private VideoView bgview;
    private TextView tx;
    private EditText emailinput;
    private EditText passwordInput;
    private Button loginbutton;
    private EditText fullname;
    private Button signupbutton;
    private Button alreadyregistered;
    private Button forgotpasswordbutton;
    private Button signup;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        bgview = (VideoView) findViewById(R.id.videoView);

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

        tx = (TextView) findViewById(R.id.Mapped);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/BeforeTheRain.ttf");
        tx.setTypeface(custom_font);


        emailinput = (EditText) findViewById(R.id.emailinput);
        assert emailinput != null;
        emailinput.clearComposingText();
        final int viewwidth = emailinput.getWidth();

        passwordInput = (EditText) findViewById(R.id.loginpassword);

        loginbutton = (Button) findViewById(R.id.loginbutton);


//        fullname = (EditText) findViewById(R.id.fullname);
//        assert fullname != null;
//        final int viewwidth = fullname.getWidth();

//        fullname.animate()
//                .translationX(fullname.getWidth())
//                .alpha(0.0f)
//                .setDuration(1);

        signupbutton = (Button) findViewById(R.id.signupbutton);
        assert signupbutton != null;
        signupbutton.animate()
                .translationX(viewwidth)
                .alpha(0.0f)
                .setDuration(1);

        signupbutton.setVisibility(View.GONE);

        alreadyregistered = (Button) findViewById(R.id.alreadyregistered);
        assert alreadyregistered != null;
        alreadyregistered.animate()
                .translationX(viewwidth)
                .alpha(0.0f)
                .setDuration(1);

        alreadyregistered.setVisibility(View.GONE);

        forgotpasswordbutton = (Button) findViewById(R.id.forgotpasswordbutton);

        signup = (Button) findViewById(R.id.signup);


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
//                fullname.animate()
//                        .translationX(-(viewwidth))
//                        .alpha(1.0f)
//                        .setDuration(400);
                signupbutton.animate()
                        .translationX(-(viewwidth))
                        //.alpha(1.0f)
                        .setDuration(400);
                alreadyregistered.animate()
                        .translationX(-(viewwidth))
                        //.alpha(1.0f)
                        .setDuration(400);
                signupbutton.setVisibility(View.VISIBLE);
                alreadyregistered.setVisibility(View.VISIBLE);
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
//                fullname.animate()
//                        .translationX((viewwidth))
//                        .alpha(0.0f)
//                        .setDuration(400);
                signupbutton.animate()
                        .translationX((viewwidth))
                        .alpha(0.0f)
                        .setDuration(400);
                alreadyregistered.animate()
                        .translationX((viewwidth))
                        .alpha(0.0f)
                        .setDuration(400);
                signupbutton.setVisibility(View.GONE);
                alreadyregistered.setVisibility(View.GONE);
            }
        });

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();



        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //to be modified
            }
        };


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "You clicked on Login button", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                login(emailinput.getText().toString(), passwordInput.getText().toString());

            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "You clicked on Signup button", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();


                createAccount(emailinput.getText().toString(), passwordInput.getText().toString());


            }
        });

        forgotpasswordbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword(emailinput.getText().toString());
            }
        });

    }

    private void forgotPassword(String email) {
        if (!validateForgotPassword()) {
            return;
        }

        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Password Reset Email Sent",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void login(final String email, final String password) {
        if (!validate()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Log In Successful",
                                    Toast.LENGTH_LONG).show();

                            loginPreferences = getSharedPreferences("Login",0);
                            editor = loginPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void createAccount(final String email, final String password) {

        if (!validate()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Sign Up Successful",
                                    Toast.LENGTH_LONG).show();
                            loginPreferences = getSharedPreferences("Login",0);
                            editor = loginPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.commit();
                        }
                    }

                });
    }

    private boolean validateForgotPassword(){
        boolean valid = true;
        String email = emailinput.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailinput.setError("Required");
            valid = false;
        } else {
            emailinput.setError(null);
        }
        return valid;
    }

    private boolean validate() {
        boolean valid = true;

        String email = emailinput.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailinput.setError("Required");
            valid = false;
        } else {
            emailinput.setError(null);
        }

        String password = passwordInput.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Required");
            valid = false;
        } else {
            passwordInput.setError(null);
        }
        return valid;
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final VideoView bgview = (VideoView) findViewById(R.id.videoView);
        String uripath2 = "android.resource://" + getPackageName() + "/" + R.raw.aurora_login_screen;
        Uri bguri = Uri.parse(uripath2);

        assert bgview != null;
        bgview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bgview.start();
            }
        });


        bgview.setVideoURI(bguri);
        bgview.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}
