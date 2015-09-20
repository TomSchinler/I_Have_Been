package com.example.sca.ihavebeen;

import android.app.Application;
import android.database.Cursor;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tom Schinler on 7/16/2015.
 */
public class ParseLogic extends Application {


    ParseUser mUser = ParseUser.getCurrentUser();
    String hardClue = "";
    String mediumClue = "";
    String easy1Clue = "";
    String easy2Clue = "";
    String giveAwayClue = "";
    String actorName = "";
    String mObjectId;
    String mNoScoreYet = "";

    ArrayList<String> mGameList;



    //Parse Logic to handle the db call and distribute data
    public Cursor NewGameDbCall(GameDatabase db) {

        Cursor cursor = db.getActorsFromDB();
        try {

            while (cursor.moveToNext()) {
                actorName = cursor.getString(0);

                hardClue = cursor.getString(1);
                mediumClue = cursor.getString(2);
                easy1Clue = cursor.getString(3);
                easy2Clue = cursor.getString(4);
                giveAwayClue = cursor.getString(5);
            }
        } finally {
            cursor.close();
        }
        db.close();
        return cursor;
    }


    // Create ParseObject that for individual games
    public void newGame(String opponentId,String opponentName) {
        final ParseObject game = new ParseObject("Game");
        game.put("Created_By", mUser);
        game.put("Opponent_Id", opponentId);
        game.put("Opponent_Name", opponentName);
        game.put("Actor_Name", actorName);
        game.put("Hard_Clue", hardClue);
        game.put("Medium_Clue", mediumClue);
        game.put("Easy_Clue_1", easy1Clue);
        game.put("Easy_Clue_2", easy2Clue);
        game.put("Give_Away_Clue", giveAwayClue);
        game.put("Creator_Score", mNoScoreYet);
        game.put("Opponent_Score", mNoScoreYet);
        game.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mObjectId = game.getObjectId();
                    Log.v("PL ObjectId: ", mObjectId);


                } else {
                    Log.v("The Save Failed: ", "reason: " + e);
                }
            }
        });


    }

    public String getObjectId() {
        return mObjectId;
    }






    /*//Retrieve active games from Parse
    public ArrayList<HashMap<String, String>> getCreatedGames() {


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
        query.whereEqualTo("Created_By", mUser);
        *//*query.whereExists("Opponent_Name");
        query.whereExists("Opponent_Id");*//*
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
            Log.v("Parse Returns", String.valueOf(list));

                *//*if (e == null && list.size() >= 1) {
                    mGameList = new ArrayList<>();
                    ParseObject opponentName;
                    ParseObject opponentId;
                    for (int i = 0; i < list.size(); i++) {
                        opponentName = list.get(i).getParseObject("Opponent_Name");
                        opponentId = list.get(i).getParseObject("Opponent_Id");
                        String oName = opponentName.getObjectId();
                        String name = opponentName.getString("Opponent_Name");
                        String oId = opponentId.getObjectId();
                        String id = opponentId.getString("Opponent_Id");
                        mGameList.add(i, oName);
                        mGameList.add(i, name);
                        mGameList.add(i, oId);
                        mGameList.add(i, id);

                        Log.v("Game List: ", String.valueOf(mGameList));
                    }

                } else {
                    if (e != null) {
                        Log.v("Game", "Error " + e.getMessage());
                    }
                }*//*
            }

        });






        return null;
    }*/

    //Retrieve games user created to find score
    public ParseObject getChallengedGames() {




        return null;
    }

    //update game object with users score
    public void updateGame(String objectId, final String userScore) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
        Log.v("object id ", objectId);
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject Game, ParseException e) {
                if(e == null){
                    Game.put("Creator_Score", ParseUser.getCurrentUser() + " " + userScore);
                    Game.saveInBackground();
                }
                else {
                    Log.v("The Save Failed: ", "reason: " + e);
                }
            }
        });

    }







}












/*package com.example.sca.ihavebeen;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

*//**
 * Created by Tom Schinler on 7/16/2015.
 *//*
    public class ParseLogic extends Application {



    ParseUser mUser = ParseUser.getCurrentUser();
    String hardClue = "";
    String mediumClue = "";
    String easy1Clue = "";
    String easy2Clue = "";
    String giveAwayClue = "";
    String actorName = "";
    String mObjectId;
    String mNoScoreYet = "";

    ArrayList<String> mGameList;


    //Parse Logic to handle the db call and distribute data
    public Cursor NewGameDbCall(GameDatabase db) {

        Cursor cursor = db.getActorsFromDB();
        try {

            while (cursor.moveToNext()) {
                actorName = cursor.getString(0);

                hardClue = cursor.getString(1);
                mediumClue = cursor.getString(2);
                easy1Clue = cursor.getString(3);
                easy2Clue = cursor.getString(4);
                giveAwayClue = cursor.getString(5);
            }
        } finally {
            cursor.close();
        }
        db.close();
        return cursor;
    }


    // Create ParseObject that for individual games
    public void newGame(String opponentId,String opponentName) {
        final ParseObject game = new ParseObject("Game");
        game.put("Created_By", mUser);
        game.put("Opponent_Id", opponentId);
        game.put("Opponent_Name", opponentName);
        game.put("Actor_Name", actorName);
        game.put("Hard_Clue", hardClue);
        game.put("Medium_Clue", mediumClue);
        game.put("Easy_Clue_1", easy1Clue);
        game.put("Easy_Clue_2", easy2Clue);
        game.put("Give_Away_Clue", giveAwayClue);
        game.put("Creator_Score", mNoScoreYet);
        game.put("Opponent_Score", mNoScoreYet);
        game.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mObjectId = game.getObjectId();
                    Log.v("PL ObjectId: ", mObjectId);


                } else {
                    Log.v("The Save Failed: ", "reason: " + e);
                }
            }
        });


    }


}*/



