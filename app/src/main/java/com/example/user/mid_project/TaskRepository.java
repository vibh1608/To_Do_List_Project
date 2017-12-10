package com.example.user.mid_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by user on 09-12-2017.
 */

//class containing Methods to perform operations on database
public class TaskRepository
{
    //creating private reference of DataBaseHelper class
    public DataBaseHelper dataBaseHelper;

    //constructor
    public TaskRepository(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    //method to count number of rows of data present in the database
    public int getrowcount()
    {
        int row=0;

        //open connection to read database
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();
        Cursor cursor = database.query(false,Constants.TABLE_NAME,null,null,null,null,null,null,null);

        //moving cursor to first row of database
        cursor.moveToFirst();

        //counting number of rows and storing in row variable
        row=cursor.getCount();

        //returning number of rows
        return row;
    }

    //method to insert details in database
    public void insertTask(ContentValues contentValues)
    {
        //open connection to write in database
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        try
        {
            //inserting row in database
            database.insert(Constants.TABLE_NAME,null,contentValues);
        }catch (Exception e)
        {
            //in case of any exception
            e.printStackTrace();
        }
        database.close();
    }

    //Method to delete entry from database.
    public void delete(int id)
    {
        //open connection to write in database
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();

        //deleting row from database of  given id
        database.delete(Constants.TABLE_NAME, Constants.KEY_ID + "= ?", new String[] { String.valueOf(id) });
        database.close(); // Closing database connection
    }

    //method to update tasks
    public void update(Task task)
    {
        //creating object of content values
        ContentValues contentValues = new ContentValues();

        //putting values in contentValues
        contentValues.put(Constants.KEY_TITLE,task.getMtitle());
        contentValues.put(Constants.KEY_DESCRIPTION,task.getMdescription());
        contentValues.put(Constants.KEY_DATE,task.getMdate());
        contentValues.put(Constants.KEY_STATUS,task.getMstatus());

        //open connection to write in database
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();

        //updating row of database
        database.update(Constants.TABLE_NAME,contentValues,Constants.KEY_ID + "= ?",new String[]{String.valueOf(task.getMid())});
        database.close(); //Closing database connection
    }

    //method to retrive alltasks and returning the arraylist
    public ArrayList<Task> getAllTask()
    {
        //initialising arraylist
        ArrayList<Task> arrayList = new ArrayList<>();

        //creating databse object
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();

        //query to get all data from databse
        String query = "SELECT * FROM "+Constants.TABLE_NAME;

        //storing all data in cursor object
        Cursor cursor = database.rawQuery(query,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();

            //from this do-while loop getting all data from cursor object and storing it into arraylist
            do {
                //creating task object
                Task task = new Task();

                //setting data in task object
                task.setMid(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                task.setMtitle(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE)));
                task.setMdescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));
                task.setMdate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DATE)));
                task.setMstatus(cursor.getInt(cursor.getColumnIndex(Constants.KEY_STATUS)));

                //adding task in list
                arrayList.add(task);

            }while (cursor.moveToNext());
        }

        //sorting list
        Collections.sort(arrayList);

        cursor.close();   //closing cursor object
        database.close();  //closing database

        //returning arraylist
        return  arrayList;
    }

    //method to retrive all completed tasks and returning arraylist
    public ArrayList<Task> getCompletedTskList()
    {
        ArrayList<Task> arrayList = new ArrayList<>();

        //creating databse object
        SQLiteDatabase database = dataBaseHelper.getReadableDatabase();

        //query to get all data from databse
        String query = "SELECT * FROM "+Constants.TABLE_NAME;

        //storing all data in cursor object
        Cursor cursor = database.rawQuery(query,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();

            //from this do-while loop getting all data from cursor object and storing it into arraylist
            do {

                if(cursor.getInt(cursor.getColumnIndex(Constants.KEY_STATUS))==1)
                {
                    //creating task object
                    Task task = new Task();

                    //setting data in task object
                    task.setMid(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                    task.setMtitle(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE)));
                    task.setMdescription(cursor.getString(cursor.getColumnIndex(Constants.KEY_DESCRIPTION)));
                    task.setMdate(cursor.getString(cursor.getColumnIndex(Constants.KEY_DATE)));
                    task.setMstatus(cursor.getInt(cursor.getColumnIndex(Constants.KEY_STATUS)));

                    //adding task in list
                    arrayList.add(task);
                }

            }while (cursor.moveToNext());
        }

        //sorting arraylist
        Collections.sort(arrayList);

        //closing cursor
        cursor.close();

        //closing database
        database.close();

        //returning arraylist
        return arrayList;
    }
}
