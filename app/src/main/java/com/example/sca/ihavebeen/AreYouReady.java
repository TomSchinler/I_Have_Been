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
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class AreYouReady extends Activity {


    String mScore;
    String mObjectId;

    String mHardClue;
    String mMediumClue;
    String mEasy1Clue;
    String mEasy2Clue;
    String mGiveAwayClue;
    String mActorName;

    Intent mIntent;

    TicketSystem mTicketSystem;

    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTicketSystem = new TicketSystem();

        setContentView(R.layout.activity_are_you_ready);


        Intent intent = getIntent();
        mScore = intent.getStringExtra("Score");
        mObjectId = intent.getStringExtra("Object Id");
        TextView scoreNumber = (TextView)findViewById(R.id.scoreToBeatNumber);
        scoreNumber.setText(mScore);

        ParseQuery<Game> gameQuery = new ParseQuery<Game>("Game");
        gameQuery.whereEqualTo("objectId", mObjectId);
        gameQuery.findInBackground(new FindCallback<Game>() {
            @Override
            public void done(List<Game> objects, ParseException e) {
                if (e == null) {
                    for(ParseObject clues : objects){
                        mActorName = clues.get("Actor_Name").toString();
                        mHardClue = clues.get("Hard_Clue").toString();
                        mMediumClue = clues.get("Medium_Clue").toString();
                        mEasy1Clue = clues.get("Easy_Clue_1").toString();
                        mEasy2Clue = clues.get("Easy_Clue_2").toString();
                        mGiveAwayClue = clues.get("Give_Away_Clue").toString();
                     }
                 } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        mButton = (Button) findViewById(R.id.letsDoThisButton);

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int tickets;
                    tickets = mTicketSystem.costOfGame();
                    mTicketSystem.setTickets(tickets);

                    mIntent = new Intent(AreYouReady.this, GameActivity.class);
                    mIntent.putExtra("actorName", mActorName);
                    mIntent.putExtra("hardClue", mHardClue);
                    mIntent.putExtra("mediumClue", mMediumClue);
                    mIntent.putExtra("easy1Clue", mEasy1Clue);
                    mIntent.putExtra("easy2Clue", mEasy2Clue);
                    mIntent.putExtra("giveAwayClue", mGiveAwayClue);
                    mIntent.putExtra("objectId", mObjectId);
                    mIntent.putExtra("carried score", mScore);

                    startActivity(mIntent);
                }
            });






    }

}
