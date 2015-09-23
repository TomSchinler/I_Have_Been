package com.example.sca.ihavebeen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends Activity {


    TextView mScoreView;
    String mScore;
    String mCarriedScore;
    String mWin;
    String mObjectId;
    ParseLogic mPl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        mPl = new ParseLogic();

        mScoreView = (TextView) findViewById(R.id.score);

        Intent intent = getIntent();
        mScore = intent.getStringExtra("Score");
        mCarriedScore = intent.getStringExtra("carried score");
        mWin = intent.getStringExtra("Win");
        mObjectId = intent.getStringExtra("objectId");


        mScoreView.setText(mScore);

        Log.v("Results object ID is: ", mObjectId);
        mPl.updateGame(mObjectId, mScore, mCarriedScore);


        Button button = (Button)findViewById(R.id.backToProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ResultsActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }


}

