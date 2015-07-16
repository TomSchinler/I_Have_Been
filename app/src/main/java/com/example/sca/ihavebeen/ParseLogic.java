package com.example.sca.ihavebeen;

import android.app.Application;
import android.database.Cursor;
import android.os.Bundle;

import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Tom Schinler on 7/16/2015.
 */
public class ParseLogic extends Application {

    private GameDatabase db;

    String hardClue = "";
    String mediumClue = "";
    String easy1Clue = "";
    String easy2Clue = "";
    String giveAwayClue = "";
    String actorName = "";



    public Cursor NewGameDbCall() {
        db = new GameDatabase(this);



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





    public void newGame(){
        ParseObject game = new ParseObject("Game");
        game.put("Created By", ParseUser.getCurrentUser());
        game.put("Actor Name", actorName);
        game.put("Hard Clue", hardClue);
        game.put("Medium Clue", mediumClue);
        game.put("Easy XClue 1", easy1Clue);
        game.put("Easy Clue 2", easy2Clue);
        game.put("Give Away Clue", giveAwayClue);
    }



}
