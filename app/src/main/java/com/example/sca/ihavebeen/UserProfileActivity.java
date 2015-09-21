package com.example.sca.ihavebeen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

//import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.AppEventsLogger;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


public class UserProfileActivity extends AppCompatActivity {

    ParseLogic mPl;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_profile);
        mPl = new ParseLogic();

        FaceBookFriends.getFaceBookFriends();


        //Testing start game remove when building rest of page
        Button button = (Button)findViewById(R.id.startNewGame);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserProfileActivity.this, GameStart.class);
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

