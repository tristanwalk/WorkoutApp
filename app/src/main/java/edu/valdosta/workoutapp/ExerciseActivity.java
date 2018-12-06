package edu.valdosta.workoutapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

public class ExerciseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private int buttonId;
    private int menuItemId;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Exercises");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
    }

    public void onClick(View view) {
        buttonId = view.getId();

        switch (buttonId) {
            case R.id.shouldersButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "shoulders");
                break;
            case R.id.armsButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "arms");
                break;
            case R.id.chestButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "chest");
                break;
            case R.id.absButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "abs");
                break;
            case R.id.lowerBodyButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "legs");
                break;
            case R.id.backButton:
                intent = new Intent(this, RegionActivity.class);
                intent.putExtra("region", "back");
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItemId = menuItem.getItemId();
        switch (menuItemId) {
            case R.id.MyWorkouts: {
                intent = new Intent(this, MyWorkoutsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.Exercises: {
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
}
