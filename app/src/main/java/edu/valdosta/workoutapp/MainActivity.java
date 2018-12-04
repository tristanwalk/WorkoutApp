package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {

    Intent intent;
    public static String line;
    public DataDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DataDbHelper(this);
        //dbHelper.deleteDatabase(this);

        //default screen when opening the app
        intent = new Intent(this, MyWorkoutsActivity.class);
        startActivity(intent);
    }
}

