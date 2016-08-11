package com.sca.ihavebeen;

/**
 * Created by gts76 on 7/1/2016.
 */

public class OppFriend {

    public String mFriendName;
    public int mFriendGamesWon;
    public int mFriendGamesLost;
    public String mFriendsMotto;

    public OppFriend() {
    }

    public String getFriendName() {
        return mFriendName;
    }

    public void setFriendName(String mFriendName) {
        this.mFriendName = mFriendName;
    }

    public int getFriendGamesWon() {
        return mFriendGamesWon;
    }

    public void setFriendGamesWon(int mFriendGamesWon) {
        this.mFriendGamesWon = mFriendGamesWon;
    }

    public int getFriendGamesLost() {
        return mFriendGamesLost;
    }

    public void setFriendGamesLost(int mFriendGamesLost) {
        this.mFriendGamesLost = mFriendGamesLost;
    }

    public String getFriendsMotto() {
        return mFriendsMotto;
    }

    public void setFriendsMotto(String mFriendsMotto) {
        this.mFriendsMotto = mFriendsMotto;
    }
}
