package com.example.user.mid_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 07-12-2017.
 */

//Creating class extending SQLiteOpenHelper class.
public class DataBaseHelper extends SQLiteOpenHelper
{
    //constructor
    public DataBaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    //onCreate method
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {final String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" +Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.KEY_TITLE + " TEXT," + Constants.KEY_DESCRIPTION + " TEXT," + Constants.KEY_DATE + " TEXT," + Constants.KEY_STATUS + " INTEGER)";

        //executing query
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    //onUpgrade method
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
//end of class
