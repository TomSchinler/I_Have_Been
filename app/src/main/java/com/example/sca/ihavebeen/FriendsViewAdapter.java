package com.example.sca.ihavebeen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom Schinler on 9/10/2015.
 */
public class FriendsViewAdapter extends ArrayAdapter<FaceBookFriends> {
    public FriendsViewAdapter(Context context, JSONArray friend) {
        super(context,0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        FaceBookFriends friend = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fb_friends_layout, parent, false);
        }
        TextView fbName = (TextView)convertView.findViewById(R.id.fbName);
        ImageView fbPic = (ImageView)convertView.findViewById(R.id.fbPic);
        fbName.setText(friend.name);


        return convertView;
    }
}
