package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import java.util.Objects;

import static android.media.CamcorderProfile.get;

public class MyWorkoutsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton addCustomWorkout;
    private int buttonId;
    private int menuItemId, b;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    String workoutTableName, i;
    DataDbHelper mDatabaseHelper;
    ArrayList<String> listOfItems;
    ListView customWorkouts;
    ArrayAdapter<String> arrayAdapter;
    Button addButton;
    int newItemID = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);

        intent = getIntent();

        i = intent.getStringExtra("nameOfWorkout");
        b = intent.getIntExtra("ID", 0);



        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Workouts");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);


        customWorkouts = findViewById(R.id.customWorkoutsList);
        addButton = findViewById(R.id.addWorkoutButton);
        mDatabaseHelper = new DataDbHelper(this);


        customWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = listOfItems.get(position);
                intent = new Intent(getApplicationContext(),RegionActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);


            }
        });


        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listOfItems
        );


        populateList();

    }


    public void onClick(View view) {
        intent = new Intent(this, AddToWorkoutsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItemId = menuItem.getItemId();
        switch (menuItemId) {
            case R.id.Exercises: {
                intent = new Intent(this, ExerciseActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.MyWorkouts: {
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.Settings: {
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            }
        }
        return true;
    }

    public void setNavigationViewListener() {
        navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);

    }

    public void populateList() {
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

    public void onClickDelete (View view) {
        intent = new Intent(getApplicationContext(), DeleteFromWorkoutsActivity.class);
        startActivity(intent);
    }

    public void toastMessage (String m) {
        Toast.makeText(this,m, Toast.LENGTH_SHORT).show();
    }

}



