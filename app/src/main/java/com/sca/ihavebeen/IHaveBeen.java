package com.sca.ihavebeen;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;



/**
 * Created by Tom Schinler on 7/8/2015.
 */
public class IHaveBeen extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


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

