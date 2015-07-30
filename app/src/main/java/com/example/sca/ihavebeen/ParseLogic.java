package com.example.sca.ihavebeen;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Tom Schinler on 7/16/2015.
 */
    public class ParseLogic extends Application {



    String hardClue = "";
    String mediumClue = "";
    String easy1Clue = "";
    String easy2Clue = "";
    String giveAwayClue = "";
    String actorName = "";



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
    public void newGame() {
        ParseObject game = new ParseObject("Game");
        game.put("Created_By", ParseUser.getCurrentUser());
        game.put("Actor_Name", actorName);
        game.put("Hard_Clue", hardClue);
        game.put("Medium_Clue", mediumClue);
        game.put("Easy_Clue_1", easy1Clue);
        game.put("Easy_Clue_2", easy2Clue);
        game.put("Give_Away_Clue", giveAwayClue);
        game.saveInBackground();
    }


}



