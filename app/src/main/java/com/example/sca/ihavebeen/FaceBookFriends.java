package com.example.sca.ihavebeen;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Tom Schinler on 8/4/2015.
 */
public class FaceBookFriends {




    public static JSONArray mFriendsList;
    public static HashMap<String, Object> mFriendsMap;
    public static ArrayList<HashMap<String, String>> mFinalFriendsList;



    public static String mMyFbId;
    public static String mFBName;

    public static String getMyFbName(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            mFBName = response.getJSONObject().getString("name");


                            Log.v("My FB ID is: ", mFBName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name");
        request.setParameters(parameters);
        request.executeAsync();
        return mFBName;
    }

    public static String getMyFbId(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            mMyFbId = response.getJSONObject().getString("id");

                            Log.v("My FB ID is: ", mMyFbId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id");
        request.setParameters(parameters);
        request.executeAsync();
      return mMyFbId;
    }


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
    public static ArrayList getFriendsList() {

        mFinalFriendsList = new ArrayList<HashMap<String, String>>();

        try {
            int i = 0;
            String value;
            JSONObject e = new JSONObject();
            while (i < mFriendsList.length()){
                HashMap<String, String> friend = new HashMap<String, String>();
                e=mFriendsList.getJSONObject(i);
                value = e.getString("name");
                friend.put("name", value);
                value = e.getString("id");
                friend.put("id", value);
                mFinalFriendsList.add(i, friend);
                i++;
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
     return mFinalFriendsList;
    }


}


