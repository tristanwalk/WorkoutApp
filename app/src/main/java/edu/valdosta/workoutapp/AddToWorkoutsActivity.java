package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddToWorkoutsActivity extends AppCompatActivity {

    String workoutTableName;
    DataDbHelper mDatabaseHelper;
    EditText input;
    ArrayList<String> listOfItems;
    ListView itemListView;
    ArrayAdapter<String> arrayAdapter;
    Button addButton;
    int newItemID = 0;
     Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_workouts);

        mDatabaseHelper = new DataDbHelper(this);
        input = findViewById(R.id.workoutName);

        intent = getIntent();
        workoutTableName = " ";

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.2));



    }

    public void onClick (View view) {
        String i = input.getText().toString();
        //fix so that i cannot be empty
        if (!(i.equals(""))){
            System.out.println(i);
            addData(i);
            intent = new Intent(this, MyWorkoutsActivity.class);
            startActivity(intent);
        }else {
            toastMessage("Text field cannot be blank");
        }
    }




    public void addData (String workout) {
        boolean insertData = mDatabaseHelper.addDataToCustomWorkouts(workout);

        if (insertData) {
            toastMessage("Data Successfully Inserted");

        } else {
            toastMessage("Something went wrong");
        }
    }




    public void toastMessage (String m) {
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }

    }



