package com.example.sca.ihavebeen;

import com.example.sca.ihavebeen.util.SystemUiHider;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class AreYouReady extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    String mScore;
    String mObjectId;

    String mHardClue = "";
    String mMediumClue = "";
    String mEasy1Clue = "";
    String mEasy2Clue = "";
    String mGiveAwayClue = "";
    String mActorName = "";

    Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_are_you_ready);
        setupActionBar();


        Intent intent = getIntent();
        mScore = intent.getStringExtra("Score");
        mObjectId = intent.getStringExtra("Object Id");

        getGameDeets(new Game());



        TextView scoreNumber = (TextView)findViewById(R.id.scoreToBeatNumber);
        scoreNumber.setText(mScore);

        Button button = (Button) findViewById(R.id.letsDoThisButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActorName != null) {

                    Log.v("this should be same ", mObjectId);

                    mIntent = new Intent(AreYouReady.this, GameActivity.class);
                    mIntent.putExtra("actorName", mActorName);
                    mIntent.putExtra("hardClue", mHardClue);
                    mIntent.putExtra("mediumClue", mMediumClue);
                    mIntent.putExtra("easy1Clue", mEasy1Clue);
                    mIntent.putExtra("easy2Clue", mEasy2Clue);
                    mIntent.putExtra("giveAwayClue", mGiveAwayClue);
                    mIntent.putExtra("objectId", mObjectId);

                    startActivity(mIntent);
                } else {

                    if (mActorName != null) {


                        Log.v("this should be same ", mObjectId);

                        mIntent = new Intent(AreYouReady.this, GameActivity.class);
                        mIntent.putExtra("actorName", mActorName);
                        mIntent.putExtra("hardClue", mHardClue);
                        mIntent.putExtra("mediumClue", mMediumClue);
                        mIntent.putExtra("easy1Clue", mEasy1Clue);
                        mIntent.putExtra("easy2Clue", mEasy2Clue);
                        mIntent.putExtra("giveAwayClue", mGiveAwayClue);
                        mIntent.putExtra("objectId", mObjectId);


                        startActivity(mIntent);
                    }

                }
            }
        });

    }



    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    public void getGameDeets (final Game game) {
        ParseQuery<Game> gameQuery = new ParseQuery<Game>("Game");
        gameQuery.whereEqualTo("objectId", mObjectId);
        gameQuery.findInBackground(new FindCallback<Game>() {
            @Override
            public void done(List<Game> objects, ParseException e) {
                if(e == null) {
                    mActorName = game.getActorName();
                    mHardClue = game.getHard();
                    mMediumClue = game.getMedium();
                    mEasy1Clue = game.getEasy1();
                    mEasy2Clue = game.getEasy2();
                    mGiveAwayClue = game.getGiveAway();
                }
                else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

}
