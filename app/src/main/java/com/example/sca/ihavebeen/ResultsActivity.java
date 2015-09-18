package com.example.sca.ihavebeen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends Activity {


    TextView mScoreView;
    String mScore;
    String mWin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        mScoreView = (TextView) findViewById(R.id.score);

        Intent intent = getIntent();
        mScore = intent.getStringExtra("Score");
        mWin = intent.getStringExtra("Win");
        mScoreView.setText(mScore);

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
