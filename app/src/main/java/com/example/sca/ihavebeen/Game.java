package com.example.sca.ihavebeen;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Tom Schinler on 9/22/2015.
 */

@ParseClassName("Game")
public class Game extends ParseObject {

    public Game(){

    }

    public ParseUser getCreator() {
        return getParseUser("Created_By");
    }

    public String getOpponentId() {
        return getString("Opponent_Id");
    }

    public String getOpponentName(){
        return getString("Opponent_Name");
    }

    public String getMyFbId() {
        return getString("My_FB_Id");
    }

    public String getActorName(){
        return getString("Actor_Name");
    }

    public String getEasy1() {
        return getString("Easy_Clue_1");
    }

    public String getEasy2() {
        return getString("Easy_Clue_2");
    }

    public String getMedium() {
        return getString("Medium_Clue");
    }

    public String getHard() {
        return getString("Hard_Clue");
    }

    public String getGiveAway() {
        return getString("Give_Away_Clue");
    }

    public String getCreatorScore(){
        return getString("Creator_Score");
    }

    public String getOpponentScore(){
        return getString("OpponentScore");
    }

    public String getUpdated(){
        return getString("updatedAt");
    }

    public String getCreatorId(){
        return getString("Creator_FB_Id");
    }

    public String getCreatorFbName() {
        return getString("Creator_FB_Name");
    }

}
