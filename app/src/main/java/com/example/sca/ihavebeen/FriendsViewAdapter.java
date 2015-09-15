package com.example.sca.ihavebeen;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tom Schinler on 9/10/2015.
 */
public class FriendsViewAdapter extends BaseAdapter {


    private ArrayList<HashMap<String, String>> mFriendsList;

    public FriendsViewAdapter(GameStart gameStart, ArrayList<HashMap<String, String>> friendslist) {
        mFriendsList = new ArrayList<HashMap<String, String>>(friendslist);
        Log.v("I hope this works ", String.valueOf(mFriendsList));

    }

    @Override
    public int getCount() {
        Log.v("Size is ", String.valueOf(mFriendsList.size()));
        return mFriendsList.size();
    }

    @Override
    public HashMap<String, String> getItem(int position) {
        return mFriendsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final View result;
        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.fb_friends_layout, parent, false);
        }
        else {
            result = convertView;
        }

        String friendName = getItem(1).get("name");
        String friendId = getItem(0).get("id");




        String fbPhotoId = friendId.toString();

        ProfilePictureView profilePictureView = (ProfilePictureView)result.findViewById(R.id.fbPic);
        TextView fbName = (TextView)result.findViewById(R.id.fbName);

        fbName.setText(friendName);
        profilePictureView.setProfileId(fbPhotoId);


        return result;
    }


}



