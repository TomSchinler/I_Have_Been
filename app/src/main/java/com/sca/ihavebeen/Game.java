package com.sca.ihavebeen;

/**
 * Created by gts76 on 7/1/2016.
 */

public class Game {

    public String mUsername;
    public String mOppName;
    public String mActorName;
    public String mEasy1;
    public String mEasy2;
    public String mMedium;
    public String mHard;
    public String mGiveAway;
    public int mUserScore;
    public int mOppScore;

    public Game(String mUsername, String mOppName, String mActorName, String mEasy1, String mEasy2, String mMedium, String mHard, String mGiveAway) {
        this.mUsername = mUsername;
        this.mOppName = mOppName;
        this.mActorName = mActorName;
        this.mEasy1 = mEasy1;
        this.mEasy2 = mEasy2;
        this.mMedium = mMedium;
        this.mHard = mHard;
        this.mGiveAway = mGiveAway;
    }
}
