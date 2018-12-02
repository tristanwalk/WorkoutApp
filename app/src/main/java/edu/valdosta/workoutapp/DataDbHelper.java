package edu.valdosta.workoutapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.COLUMN_NAME_DESCRIPTION;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.COLUMN_NAME_LINK;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.COLUMN_NAME_NAME;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.COLUMN_NAME_REGION;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.COLUMN_NAME_TYPE;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.SQL_CREATE_ENTRIES;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.SQL_DELETE_ENTRIES;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.TABLE_NAME;

public class DataDbHelper extends SQLiteOpenHelper{

    private Context mContext;

    public DataDbHelper dbhelper;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Workouts.db";
    private String line;
    SQLiteDatabase db;

    public DataDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        //SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_CREATE_ENTRIES);

        InputStream is = mContext.getResources().openRawResource(R.raw.exercises);
        //InputStream is = mc
        //read line by line
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        try {
            while ((line = buffer.readLine()) != null) {
                String[] columns = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println("columns[0]:" + columns[0]);
                System.out.println("columns[1]:" + columns[1]);
                System.out.println("columns[2]:" + columns[2]);
                System.out.println("columns[3]:" + columns[3]);
                System.out.println("columns[4]:" + columns[4]);
                boolean insertData = addData(db, columns[0].trim(), columns[1].trim(), columns[2].trim(), columns[3].trim(), columns[4].trim());
                if (insertData) {
                    Log.v("addData", "Successful");
                } else {
                    Log.v("addData", "Not Successful");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private boolean addData(SQLiteDatabase db, String name, String description, String region, String type, String link) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_NAME, name);
        contentValues.put(COLUMN_NAME_DESCRIPTION, description);
        contentValues.put(COLUMN_NAME_REGION, region);
        contentValues.put(COLUMN_NAME_TYPE, type);
        contentValues.put(COLUMN_NAME_LINK, link);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getName(String region){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME_NAME + " FROM " + TABLE_NAME + " WHERE region='"+region+"'";
        //Cursor data = db.query("workoutData", new String[]{"name"}, "name = ?", new String[]{region}, null, null, null);
        return db.rawQuery(query, null);
    }

    public Cursor getDescription(String exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NAME_DESCRIPTION + " FROM " + TABLE_NAME + " WHERE name='"+exercise+"'";
        return db.rawQuery(query, null);
    }

    /*
    public void closeDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        dbhelper.db.close();
        dbhelper = null;
    }

    public static void deleteDatabase(Context mContext){
        mContext.deleteDatabase(DATABASE_NAME);
        Log.v("deleteData", "Delete Succesful");
    }
    */
}
