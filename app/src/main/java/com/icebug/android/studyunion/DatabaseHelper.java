package com.icebug.android.studyunion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nafis on 20-Aug-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_name = "User.db";
    public static final String Table_name = "user_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "College";
    public static final String COL_4 = "Major";
    public static final String COL_5 = "Year";
    public static final String COL_6 = "UID";

    public DatabaseHelper(Context context) {
        super(context, DB_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ Table_name +" (NAME TEXT PRIMARY KEY NOT NULL, EMAIL TEXT, COLLEGE TEXT, MAJOR TEXT, YEAR TEXT, UID TEXT) " );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }
}
