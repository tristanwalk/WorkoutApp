package edu.valdosta.workoutapp;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

public final class DatabaseContract {
    private DatabaseContract(){}

    public static class DataEntry implements BaseColumns{
        public static final String TABLE_NAME = "workoutData";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_REGION = "region";
        public static final String COLUMN_NAME_LINK = "link";

        public static final String SQL_CREATE_ENTRIES = "Create table " +
                DataEntry.TABLE_NAME + " (" + DataEntry._ID + " INTEGER PRIMARY KEY, " +
                DataEntry.COLUMN_NAME_NAME + " TEXT, " +
                DataEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                DataEntry.COLUMN_NAME_REGION + " TEXT, " +
                DataEntry.COLUMN_NAME_LINK + " TEXT)";

        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DataEntry.TABLE_NAME;

    }
}
