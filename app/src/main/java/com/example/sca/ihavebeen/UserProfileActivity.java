package com.example.sca.ihavebeen;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.appevents.AppEventsLogger;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class UserProfileActivity extends AppCompatActivity {



    protected ParseLogic pl;
    protected FaceBookLogic fbl;
    GameDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new GameDatabase(this);
        pl = new ParseLogic();
        fbl = new FaceBookLogic();

        setContentView(R.layout.activity_user_profile);

        fbl.getUserFriends();

        //Testing start game remove when building rest of page
        Button button = (Button)findViewById(R.id.testingStartButton);
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
                Intent intent = new Intent(UserProfileActivity.this, GameActivity.class);
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

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //create the logout button in action bar
        switch (item.getItemId()){
            case R.id.logoutButton:
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                Intent intent = new Intent(UserProfileActivity.this, WelcomeActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
