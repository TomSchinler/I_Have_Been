package com.example.sca.ihavebeen;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tom Schinler on 8/4/2015.
 */
public class FaceBookLogic {



    public String mFbName;
    public String mFbProfilePicID;
    public JSONArray mFbArray;



    //get users friends that are also on the app
    public  GraphRequest getUserFriends () {


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.v("Result", String.valueOf(response));
                        JSONObject friendsJSONObjectName;
                        if(response != null) {
                            friendsJSONObjectName = response.getJSONObject();
                            try {
                                mFbArray = friendsJSONObjectName.getJSONArray("data");
                                for (int i = 0; i < mFbArray.length(); i++){
                                    JSONObject object = (JSONObject) mFbArray.get(i);
                                    Log.v("ID Response", String.valueOf(object.get("id").toString()));
                                    Log.v("Name response", String.valueOf(object.get("name")));
                                    mFbName= String.valueOf(object.get("name"));
                                    mFbProfilePicID = String.valueOf(object.get("id"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).executeAsync();

        return null;
    }

    public String getFbName() {
        return mFbName;
    }

    public void setFbName(String mFbName) {
        this.mFbName = mFbName;
    }

    public JSONArray getFbArray() {
        return mFbArray;
    }


}

