package com.example.sca.ihavebeen;

import com.example.sca.ihavebeen.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends Activity {

     //member variables
    ProgressBar mProgressBar;
    TextView mHardClue;
    TextView mMediumClue;
    TextView mEasy1Clue;
    TextView mEasy2Clue;
    TextView mGiveAwayClue;
    String mMyFbName;
    String mActorName;
    String mWinOrLose;
    Integer mTimerPosition;
    final int mTotalMsecs = 90 * 1000; // 90 seconds in milli seconds
    int mCallInterval = 10;
    CountDownTimer mCountDownTimer;
    String mObjectId;
    String mCarriedScore = "";

    AutoCompleteTextView mUserGuess;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyFbName = FaceBookFriends.getMyFbName();

        setContentView(R.layout.activity_game);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

         //Get Text Views
        mProgressBar = (ProgressBar) findViewById(R.id.gameTimer);
        mHardClue = (TextView) findViewById(R.id.hardClueTextView);
        mMediumClue = (TextView) findViewById(R.id.mediumClueTextView);
        mEasy1Clue = (TextView) findViewById(R.id.easy1ClueTextView);
        mEasy2Clue = (TextView) findViewById(R.id.easy2ClueTextView);
        mGiveAwayClue = (TextView) findViewById(R.id.giveAwayClueTextView);
        mUserGuess = (AutoCompleteTextView) findViewById(R.id.userGuessBox);
        mUserGuess.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    if((keyCode == KeyEvent.KEYCODE_ENTER )){
                        rightGuess();
                        return true;
                    }
                }
                return false;
            }
        });

        //Set AutoComplete Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (GameActivity.this, android.R.layout.select_dialog_item, mGuessAC);
        mUserGuess.setAdapter(adapter);
        mUserGuess.setThreshold(1);


        //grab the game data from the intent
        Intent intent = getIntent();
        String hardClue = intent.getStringExtra("hardClue");
        String mediumClue = intent.getStringExtra("mediumClue");
        String easy1Clue = intent.getStringExtra("easy1Clue");
        String easy2Clue = intent.getStringExtra("easy2Clue");
        String giveAwayClue = intent.getStringExtra("giveAwayClue");
        String actorName = intent.getStringExtra("actorName");
        mCarriedScore= intent.getStringExtra("carried score");
        mObjectId = intent.getStringExtra("objectId");
        Log.v("GA ObjId Result: ", mObjectId);

        // bind the game data to the views
        mHardClue.setText(hardClue);
        mMediumClue.setText(mediumClue);
        mEasy1Clue.setText(easy1Clue);
        mEasy2Clue.setText(easy2Clue);
        mGiveAwayClue.setText(giveAwayClue);
        mActorName = actorName;

        countDownTimer(mTotalMsecs, mCallInterval).start();


        Button button = (Button) findViewById(R.id.gameSubmitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if guess is correct
                rightGuess();
            }
        });


        //Start timer onCreate
        mProgressBar.setProgress(0);
    }

    /** CountDownTimer */
    private CountDownTimer countDownTimer(final int milliSecs, int callInterval) {
        mCountDownTimer = new CountDownTimer(milliSecs, callInterval) {

            public void onTick(long millisUntilFinished) {

                int secondsRemaining = (int) millisUntilFinished / 1000;

                mTimerPosition = secondsRemaining;

                float fraction = millisUntilFinished / (float) milliSecs;

                // progress bar is based on scale of 1 to 100;
                mProgressBar.setProgress((int) (fraction * 100));

                if (secondsRemaining <= 80) {
                    mMediumClue.setVisibility(View.VISIBLE);
                }
                if (secondsRemaining <= 70) {
                    mEasy1Clue.setVisibility(View.VISIBLE);
                }
                if (secondsRemaining <= 60) {
                    mEasy2Clue.setVisibility(View.VISIBLE);
                }
            }

            // if time runs out move to next round screen
            public void onFinish() {
                mWinOrLose = "lose";
                Intent intent = new Intent(GameActivity.this, YouFailed.class);
                intent.putExtra("Lose", mWinOrLose);
                intent.putExtra("objectID", mObjectId);
                intent.putExtra("carried score", mCarriedScore);
                startActivity(intent);
            }
        };

        return mCountDownTimer;
    }

    //Get the score based on timer position
    public String getTimerScore() {
        int score;
        String scoreString;
        score = mTimerPosition *10;
        scoreString = String.valueOf(score);
        Log.v("Score is ", String.valueOf(score));
        return scoreString;
    }

    public void rightGuess(){

        String answer = mUserGuess.getText().toString();
        String actorName = mActorName;
        Context context = getApplicationContext();

        // if guess is correct move to next round activity
        if (answer.trim().equalsIgnoreCase(actorName)) {
            mWinOrLose = "win";
            Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
            intent.putExtra("Score", getTimerScore());
            intent.putExtra("Win", mWinOrLose);
            intent.putExtra("objectId", mObjectId);
            intent.putExtra("carried score", mCarriedScore);
            intent.putExtra("mMyFbName", mMyFbName);
            mCountDownTimer.cancel();
            startActivity(intent);

        } else {

            Toast toast = Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    private static final String[] mGuessAC = new String[] {
            "Christian Slater", "Samuel L. Jackson", "Leonardo DiCaprio"
    };

}
