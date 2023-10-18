package com.example.madproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "C:\\Users\\Sudharsan\\AndroidStudioProjects\\madproject\\app\\src\\main\\assets\\data.sqlite";
    private static final int DATABASE_VERSION = 1;

    // Define your table creation SQL here
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS my_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema changes here
    }
}
