package edu.valdosta.workoutapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button createProfile;
    Intent intent;
    TextView bmiDisplay;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private int menuItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        createProfile = findViewById(R.id.button);

        bmiDisplay = findViewById(R.id.bmidisplaytextView);

        intent = getIntent();
        double bmi = intent.getDoubleExtra("bmi", 0);

        String bmiString = String.format("%.2f", bmi);
        if(bmi != 0){
            bmiDisplay.setText(String.valueOf(bmiString));
        }
    }

    public void createProfileOnClick(View view) {
        startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
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
                drawerLayout.closeDrawer(GravityCompat.START);
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
