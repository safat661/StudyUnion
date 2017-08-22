package com.icebug.android.studyunion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nafis on 20-Aug-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_name = "User.db";
    public static final String Table_name = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "College";
    public static final String COL_4 = "Major";
    public static final String COL_5 = "Year";
    public static final String COL_6 = "UID";
    public static final String COL_7 = "Name";

    public DatabaseHelper(Context context) {
        super(context, DB_name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ Table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_7+" TEXT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" TEXT, "+COL_6+" TEXT) " );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(db);
    }

    public Cursor getData(String info) {
        return (this.getReadableDatabase().rawQuery("SELECT  "+info+" FROM "+ Table_name, null));
    }

}
