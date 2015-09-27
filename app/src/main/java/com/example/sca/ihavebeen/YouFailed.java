package com.example.sca.ihavebeen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.Button;

public class YouFailed extends AppCompatActivity {

    String mObjectId;
    String mUserScore;
    String mCarriedScore;
    ParseLogic mPl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_you_failed);

        Intent intent = getIntent();
        mObjectId = intent.getStringExtra("objectID");
        mCarriedScore = intent.getStringExtra("carried score");
        mPl = new ParseLogic();

        mUserScore = "0";

        mPl.updateGame(mObjectId, mUserScore, mCarriedScore);

        Button button = (Button)findViewById(R.id.failButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YouFailed.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });



    }


    @Override
    public void onBackPressed(){}

}
