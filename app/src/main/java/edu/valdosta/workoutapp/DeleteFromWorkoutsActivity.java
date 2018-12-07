package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteFromWorkoutsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_delete_from_workouts);

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

        System.out.println(i);
        Cursor data =  mDatabaseHelper.getItemIDToDelete(i);
        int itemID = -1;
        while(data.moveToNext()){
            itemID = data.getInt(0);
            newItemID = itemID;
        }
        System.out.print(itemID);
        if(itemID > -1){
            mDatabaseHelper.deleteData(i, newItemID);
            intent = new Intent(this, MyWorkoutsActivity.class);

            startActivity(intent);
        }
        else{
            toastMessage("No ID associated with that name");
        }
    }





    public void toastMessage (String m) {
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }

}
