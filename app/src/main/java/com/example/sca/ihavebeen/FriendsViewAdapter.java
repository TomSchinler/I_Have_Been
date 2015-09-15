package com.example.sca.ihavebeen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Tom Schinler on 9/10/2015.
 */
public class FriendsViewAdapter extends BaseAdapter {


    private ArrayList mFriendsList;

    public FriendsViewAdapter(GameStart gameStart, Map<String, String> friendsMap) {
        mFriendsList = new ArrayList();
        mFriendsList.addAll(friendsMap.entrySet());

    }

    @Override
    public int getCount() {
        return mFriendsList.size();
    }

    @Override
    public Map.Entry<String, String> getItem(int position) {
        return (Map.Entry) mFriendsList.get(position);
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
        Map.Entry<String, String> friendName = getItem(1);
        Map.Entry<String, String> friendId = getItem(0);

        String fbPhotoId = friendId.getValue();

        ProfilePictureView profilePictureView = (ProfilePictureView)result.findViewById(R.id.fbPic);
        TextView fbName = (TextView)result.findViewById(R.id.fbName);

        fbName.setText(friendName.getValue());
        profilePictureView.setProfileId(fbPhotoId);


        return result;
    }


}



