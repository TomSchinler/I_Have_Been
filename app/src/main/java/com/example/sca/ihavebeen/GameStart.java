package com.example.sca.ihavebeen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GameStart extends AppCompatActivity {

    JSONArray mArrayofFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);



        mArrayofFriends = FaceBookFriends.getFriendsList();


        Log.v("maybe ", String.valueOf(mArrayofFriends));

        populateFbFriendsList();

    }

    private void populateFbFriendsList() {

        FriendsViewAdapter adapter = new FriendsViewAdapter(this, mArrayofFriends);

        ListView listView = (ListView) findViewById(R.id.fbFriendsListView);
        listView.setAdapter(adapter);
    }




}
