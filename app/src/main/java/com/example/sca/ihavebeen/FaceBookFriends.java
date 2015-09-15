package com.example.sca.ihavebeen;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Tom Schinler on 8/4/2015.
 */
public class FaceBookFriends {




    public static JSONArray mFriendsList;
    public static HashMap<String, String> mFriendsMap;



    //Get FB Friends
    public static JSONArray getFaceBookFriends() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMyFriendsRequest(
                accessToken,
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        // Insert your code here

                        mFriendsList = array;
                        Log.v("Array: ", String.valueOf(mFriendsList));
                        Log.v("Facebook response: ", String.valueOf(response));

                    }
                });

        request.executeAsync();
        return mFriendsList;
    }

    //Convert JSONArray to Hashmap
    public static HashMap<String, String> getFriendsList() {
         mFriendsMap = new HashMap<String,String>();
        for(int i = 0; i < mFriendsList.length(); i++) {
            JSONObject jsonArray = null;
            try {
                jsonArray = mFriendsList.getJSONObject(i);
                Iterator it = jsonArray.keys();
                while (it.hasNext()) {
                    String n = (String) it.next();
                    mFriendsMap.put(n, jsonArray.getString(n));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Log.v("HashMap ", String.valueOf(mFriendsMap));
        return mFriendsMap;
    }
}


