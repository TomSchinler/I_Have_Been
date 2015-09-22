package com.example.sca.ihavebeen;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;

/**
 * Created by Tom Schinler on 7/8/2015.
 */
public class IHaveBeen extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Set up Parse
        ParseObject.registerSubclass(Game.class);
        Parse.initialize(this, "bjA0QqhyKabcRWWTUMYfOHNk8lPw1cL8XSQzwJpg",
                "Or4rdadLABWiGO4KbjuDxdJca84wUpkjWcZompvi");

        ParseFacebookUtils.initialize(this);

        FacebookSdk.sdkInitialize(getApplicationContext());

        /*Test Parse
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

