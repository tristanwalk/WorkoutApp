package edu.valdosta.workoutapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Objects;

public class RegionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView exerciseList;
    private final static String TAG = "RegionActivity";
    DataDbHelper dbHelper;
    int id;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private int menuItemId;
    private String region, workoutName;
    ArrayList<String> ExerciseNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        Intent intent = getIntent();
        region = intent.getStringExtra("region");
        workoutName = intent.getStringExtra("name");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(region + " Exercises");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        exerciseList = findViewById(R.id.exerciseListView);
        dbHelper = new DataDbHelper(this);
        
        if (region != null) {
            populateListView(region , 0);
            toolbar.setTitle(region + " Exercises");
            setSupportActionBar(toolbar);

        } else if (workoutName != null){
            populateListView(workoutName, 1);
            toolbar.setTitle(workoutName + " Exercises");
            setSupportActionBar(toolbar);
        }
        exerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String exercise = ExerciseNames.get(i);
                System.out.println(exercise);
                Intent intent = new Intent(getApplicationContext(), DisplayExercise.class);
                intent.putExtra("exerciseName", exercise);
                startActivity(intent);
            }
        });
    }

    private void populateListView(String q, int i) {
        Cursor data = dbHelper.getName(q, i);
        ExerciseNames = new ArrayList<>();
        while(data.moveToNext()){
            ExerciseNames.add(data.getString(0));
        }

        ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ExerciseNames);
        exerciseList.setAdapter(listAdapter);
    }

    public void setNavigationViewListener() {
        navView = findViewById(R.id.navView);
        navView.setNavigationItemSelectedListener(this);
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
                intent = new Intent(this, MyWorkoutsActivity.class);
                startActivity(intent);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}