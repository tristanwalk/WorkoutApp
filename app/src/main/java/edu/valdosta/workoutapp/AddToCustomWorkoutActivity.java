package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddToCustomWorkoutActivity extends AppCompatActivity {

    DataDbHelper mDatabaseHelper;
    EditText input;
    Intent intent;
    ArrayList<String> listOfItems;
    ArrayAdapter<String> arrayAdapter;
    ListView customWorkouts;
    private String exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_custom_workout);

        mDatabaseHelper = new DataDbHelper(this);
        input = findViewById(R.id.workoutName);
        customWorkouts = findViewById(R.id.customWorkoutsList);

        intent = getIntent();
        exercise = intent.getStringExtra("exercise");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*.5));

        populateList();

        customWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listOfItems.get(position);
                mDatabaseHelper.addToWorkout(name, exercise);
                intent = new Intent(getApplicationContext(),RegionActivity.class);
                //intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    public void populateList(){
        Cursor data = mDatabaseHelper.getData();
        String a;

        listOfItems = new ArrayList<>();

        while (data.moveToNext()) {
            a = data.getString(1);
            listOfItems.add(a);
        }
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listOfItems
        );
        customWorkouts.setAdapter(arrayAdapter);
    }

    public void onClick(View view){
        String i = input.getText().toString();
        if (!(i.equals(""))){
            //System.out.println(i);
            mDatabaseHelper.addToWorkout(i, exercise);
            intent = new Intent(this, MyWorkoutsActivity.class);
            startActivity(intent);
        }else {
            toastMessage("Text field cannot be blank");
        }
    }

    public void toastMessage (String m) {
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }
}

