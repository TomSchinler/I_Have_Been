package com.example.sca.ihavebeen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Tom Schinler on 6/23/2015.
 */
public class GameDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "GameDatabase.sql";
    private static final int DATABASE_VERSION = 1;

    public GameDatabase(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }
}
