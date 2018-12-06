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
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayExercise extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String exercise;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private Intent intent;
    private DataDbHelper dbHelper;
    private int menuItemId;
    private TextView scrollTextView;
    private ScrollView scrollView;
    private String regex = "Step ";
    private Pattern pattern = Pattern.compile(regex);
    private Matcher matcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_exercise);

        Intent intent = getIntent();
        exercise = intent.getStringExtra("exerciseName");

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(exercise);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        dbHelper = new DataDbHelper(this);
        scrollTextView = findViewById(R.id.scrollViewTextView);
        scrollView = findViewById(R.id.descriptionScrollView);

        populateTextView();
    }

    private void populateTextView() {
        Cursor data = dbHelper.getDescription(exercise);
        int spot = 0;
        while(data.moveToNext()){
            /*
            String line = data.getString(0);
            System.out.println(line);
            matcher = pattern.matcher(line);
            while (matcher.find()){
                int newSpot = matcher.start();
                if (newSpot == 1){
                    spot+=newSpot-1;
                    continue;
                }
                System.out.println("NewSpot: " + newSpot);
                String newLine = line.substring(spot, newSpot);
                spot += newSpot-1;
                System.out.println(newLine);
            }
            */

            scrollTextView.setText(data.getString(0));
        }
        //scrollView.setView(scrollTextView);
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
