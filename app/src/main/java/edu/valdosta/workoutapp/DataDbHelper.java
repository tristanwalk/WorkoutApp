package edu.valdosta.workoutapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.SQL_CREATE_ENTRIES;
import static edu.valdosta.workoutapp.DatabaseContract.DataEntry.SQL_DELETE_ENTRIES;

public class DataDbHelper extends SQLiteOpenHelper{

    private Context mContext;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Workouts.db";


    public DataDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
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

    DataDbHelper dbHelper = new DataDbHelper(mContext);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

}
