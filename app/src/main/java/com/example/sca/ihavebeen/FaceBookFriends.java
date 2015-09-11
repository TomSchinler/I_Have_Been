package com.example.sca.ihavebeen;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;

/**
 * Created by Tom Schinler on 8/4/2015.
 */
public class FaceBookFriends {




    public static JSONArray mFriendsList;
    public String name;
    public String id;




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

    public static JSONArray getFriendsList() {
        return mFriendsList;
    }

    public FaceBookFriends(String name, String id){
        this.name = name;
        this.id = id;
    }

}


