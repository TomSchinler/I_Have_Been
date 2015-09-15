package com.example.sca.ihavebeen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.HashMap;

public class GameStart extends AppCompatActivity {

    HashMap mFriendsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);



        mFriendsMap = FaceBookFriends.getFriendsList();
        Log.v("maybe ", String.valueOf(mFriendsMap));
        populateFbFriendsList();

    }

    private void populateFbFriendsList() {

        FriendsViewAdapter adapter = new FriendsViewAdapter(this, mFriendsMap);

        ListView listView = (ListView) findViewById(R.id.fbFriendsListView);
        listView.setAdapter(adapter);
    }






}
