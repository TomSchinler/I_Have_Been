package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerifyStartActivity extends AppCompatActivity {

    protected ParseLogic pl;
    String mFriendName;
    TextView mOpponent;
    String mFacebookOpponentId;
    String mObjectId;
    Intent mIntent;

    GameDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new GameDatabase(this);
        pl = new ParseLogic();
        setContentView(R.layout.activity_verify_start);

        Intent intent = getIntent();
        mFacebookOpponentId = intent.getStringExtra("Facebook Profile Id");
        mFriendName = intent.getStringExtra("Friend Name");
        mOpponent = (TextView)findViewById(R.id.verifyPerson);
        mOpponent.setText(mFriendName);

        pl.NewGameDbCall(db);
        pl.newGame(mFacebookOpponentId,mFriendName);


        mObjectId = pl.getObjectId();

        Button button = (Button) findViewById(R.id.verifyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mObjectId != null) {


                    String hardClue = pl.hardClue;
                    String mediumClue = pl.mediumClue;
                    String easy1Clue = pl.easy1Clue;
                    String easy2Clue = pl.easy2Clue;
                    String giveAwayClue = pl.giveAwayClue;
                    String actorName = pl.actorName;


                    Log.v("this should be same ", mObjectId);

                    mIntent = new Intent(VerifyStartActivity.this, GameActivity.class);
                    mIntent.putExtra("actorName", actorName);
                    mIntent.putExtra("hardClue", hardClue);
                    mIntent.putExtra("mediumClue", mediumClue);
                    mIntent.putExtra("easy1Clue", easy1Clue);
                    mIntent.putExtra("easy2Clue", easy2Clue);
                    mIntent.putExtra("giveAwayClue", giveAwayClue);
                    mIntent.putExtra("objectId", mObjectId);

                    startActivity(mIntent);
                } else {
                    mObjectId = pl.getObjectId();
                    if (mObjectId != null) {
                        String hardClue = pl.hardClue;
                        String mediumClue = pl.mediumClue;
                        String easy1Clue = pl.easy1Clue;
                        String easy2Clue = pl.easy2Clue;
                        String giveAwayClue = pl.giveAwayClue;
                        String actorName = pl.actorName;


                        Log.v("this should be same ", mObjectId);

                        mIntent = new Intent(VerifyStartActivity.this, GameActivity.class);
                        mIntent.putExtra("actorName", actorName);
                        mIntent.putExtra("hardClue", hardClue);
                        mIntent.putExtra("mediumClue", mediumClue);
                        mIntent.putExtra("easy1Clue", easy1Clue);
                        mIntent.putExtra("easy2Clue", easy2Clue);
                        mIntent.putExtra("giveAwayClue", giveAwayClue);
                        mIntent.putExtra("objectId", mObjectId);


                        startActivity(mIntent);
                    }

                }
            }
        });




    }

}
