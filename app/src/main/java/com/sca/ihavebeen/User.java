package com.sca.ihavebeen;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by gts76 on 7/1/2016.
 */

public class User {
    public String mUserName;
    public int mUserGamesWon;
    public int mUserGamesLost;
    public String mUserMotto;
    public Uri mUsersPicture;

    public boolean mIsFriendly = false;

    private final static User instance = new User();

    private User() {}

    public static User getInstance() {
        return instance;
    }


    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public int getmUserGamesWon() {
        return mUserGamesWon;
    }

    public void setmUserGamesWon(int mUserGamesWon) {
        this.mUserGamesWon = mUserGamesWon;
    }

    public int getmUserGamesLost() {
        return mUserGamesLost;
    }

    public void setmUserGamesLost(int mUserGamesLost) {
        this.mUserGamesLost = mUserGamesLost;
    }

    public String getmUserMotto() {
        return mUserMotto;
    }

    public void setmUserMotto(String mUserMotto) {
        this.mUserMotto = mUserMotto;
    }

    public String getmUserName() {
        return mUserName;
    }

    public Uri getmUsersPicture() {
        return mUsersPicture;
    }

    public void setmUsersPicture(Uri mUsersPicture) {
        this.mUsersPicture = mUsersPicture;
    }

    public boolean setFriendly (){
        mIsFriendly = true;
        return mIsFriendly;
    }
}
