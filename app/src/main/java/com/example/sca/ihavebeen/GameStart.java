package com.example.sca.ihavebeen;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStart extends AppCompatActivity {

    public static ArrayList<HashMap<String, String>> mFinalFriendsList;
    ListView mFacebookListView;
    String mFbPhotoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        mFacebookListView = (ListView) findViewById(R.id.fbFriendsListView);
        mFinalFriendsList = FaceBookFriends.getFriendsList();
        Log.v("maybe ", String.valueOf(mFinalFriendsList));

        populateFbFriendsList();



        mFacebookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clickedTextView = ((TextView) view.findViewById(R.id.fbName)).getText().toString();
                String FbId = ((ProfilePictureView) view.findViewById(R.id.fbPic)).getProfileId();

                Intent intent = new Intent(GameStart.this, VerifyStartActivity.class);
                intent.putExtra("Friend Name", clickedTextView);
                intent.putExtra("Facebook Profile Id", FbId);
                startActivity(intent);
            }
        });

    }



    private void populateFbFriendsList() {

        FriendsViewAdapter adapter = new FriendsViewAdapter(this, mFinalFriendsList);
        mFacebookListView.setAdapter(adapter);
    }






}
