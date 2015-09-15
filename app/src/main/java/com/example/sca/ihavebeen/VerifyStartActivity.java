package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VerifyStartActivity extends AppCompatActivity {

    protected ParseLogic pl;
    String mFriendName;
    TextView mOpponent;

    GameDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new GameDatabase(this);
        pl = new ParseLogic();
        setContentView(R.layout.activity_verify_start);

        Intent intent = getIntent();
        mFriendName = intent.getStringExtra("Friend Name");
        mOpponent = (TextView)findViewById(R.id.verifyPerson);
        mOpponent.setText(mFriendName);




        Button button = (Button)findViewById(R.id.verifyButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pl.NewGameDbCall(db);
                String hardClue = pl.hardClue;
                String mediumClue = pl.mediumClue;
                String easy1Clue = pl.easy1Clue;
                String easy2Clue = pl.easy2Clue;
                String giveAwayClue = pl.giveAwayClue;
                String actorName = pl.actorName;

                pl.newGame();

                //intent for starting game
                Intent intent = new Intent(VerifyStartActivity.this, GameActivity.class);
                intent.putExtra("actorName", actorName);
                intent.putExtra("hardClue", hardClue);
                intent.putExtra("mediumClue", mediumClue);
                intent.putExtra("easy1Clue", easy1Clue);
                intent.putExtra("easy2Clue", easy2Clue);
                intent.putExtra("giveAwayClue", giveAwayClue);
                startActivity(intent);


            }
        });



    }

    }

