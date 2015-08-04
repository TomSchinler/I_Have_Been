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
                        JSONObject friendsJSONObjectPaging;
                        if(response != null) {
                            friendsJSONObjectName = response.getJSONObject();
                            friendsJSONObjectPaging = response.getJSONObject();
                            try {
                                JSONArray array = friendsJSONObjectName.getJSONArray("data");
                                for (int i = 0; i < array.length(); i++){
                                    JSONObject object = (JSONObject) array.get(i);
                                    Log.v("Object response", String.valueOf(object.get("name")));
                                }
                                //This is trying to get the Next data result but is not able to
                                //convert to an array
                                JSONArray array2 = friendsJSONObjectPaging.getJSONArray("paging");
                                for (int i = 0; i < array2.length(); i++){
                                    JSONObject object = (JSONObject) array2.get(i);
                                    Log.v("Paging response", String.valueOf(object.get("next")));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).executeAsync();

        return null ;
    }

}

