package edu.valdosta.workoutapp;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

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

    /*
    public static void addData(String name, String description, String region, String type, String link) {
        boolean insertData = dbHelper.addData(name, description, region, type, link);

        if (insertData) {
            Log.v("addData", "Successful");
        } else {
            Log.v("addData", "Not Successful");
        }
    }
    */
}

