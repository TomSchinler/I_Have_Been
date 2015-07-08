package com.example.sca.ihavebeen;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Tom Schinler on 7/8/2015.
 */
public class IHaveBeen extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Set up Parse
        Parse.initialize(this, "bjA0QqhyKabcRWWTUMYfOHNk8lPw1cL8XSQzwJpg",
                "Or4rdadLABWiGO4KbjuDxdJca84wUpkjWcZompvi");

        /*Test Parse
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
    }
}

