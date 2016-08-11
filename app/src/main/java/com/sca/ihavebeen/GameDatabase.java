package com.sca.ihavebeen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Tom Schinler on 6/23/2015.
 */
public class GameDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "GameDB.sqlite";
    private static final int DATABASE_VERSION = 1;

    public GameDatabase(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }



    public Cursor getActorsFromDB() {

        SQLiteDatabase db = getReadableDatabase();

        //Raw Query for database retrieval
        Cursor cursor = db.rawQuery("SELECT Name, HClue, MClue, E1Clue, E2Clue, GAClue\n" +
                "FROM Actors, H_Clue, M_Clue, E1_Clue, E2_Clue, GA_Clue\n" +
                "WHERE Actors._ID = H_Clue.Actor_ID AND Actors._ID = M_Clue.Actor_ID\n" +
                "AND Actors._ID = E1_Clue.Actor_ID AND Actors._ID = E2_Clue.Actor_ID\n" +
                "AND Actors._ID = GA_Clue.Actor_ID ORDER BY Random() Limit 1", null);

        return cursor;
    }











}
