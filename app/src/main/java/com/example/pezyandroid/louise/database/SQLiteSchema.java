package com.example.pezyandroid.louise.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteSchema extends SQLiteOpenHelper {

    private Context fContext;

    public static final int VERSION = 9;
    public static final String DATABASE_NAME = "assettracking.db";

    public SQLiteSchema(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        fContext = context;
        onUpgrade(this.getWritableDatabase(), VERSION - 1, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.wtf("MYAPP", "SQLite onCreate");
        StringBuilder sb = new StringBuilder();
        /*sb.append("CREATE TABLE ")
                .append(UserDatabaseHelper.TABLE_NAME)
                .append(" ( ")
                .append(UserDatabaseHelper.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(UserDatabaseHelper.COL_2 + " INTEGER, ")
                .append(UserDatabaseHelper.COL_3 + " TEXT, ")
                .append(UserDatabaseHelper.COL_4 + " TEXT, ")
                .append(UserDatabaseHelper.COL_5 + " TEXT, ")
                .append(UserDatabaseHelper.COL_6 + " INTEGER(6), ")
                .append(UserDatabaseHelper.COL_7 + " INTEGER(1), ")
                .append(UserDatabaseHelper.COL_8 + " DATETIME, ")
                .append(UserDatabaseHelper.COL_9 + " DATETIME ")
                .append(" ) ");

        db.execSQL(sb.toString());
        sb.setLength(0);*/



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.wtf("MYAPP", "SQLite onUpgrade");
        /*db.execSQL(" DROP TABLE IF EXISTS " + UserDatabaseHelper.TABLE_NAME);
        onCreate(db);*/
    }
}
