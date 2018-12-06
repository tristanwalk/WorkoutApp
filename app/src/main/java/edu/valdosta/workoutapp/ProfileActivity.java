package edu.valdosta.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private int age;
    private double heightInInches;
    private double bmi;
    private double weight;
    private String sex;
    private String name;
    private EditText nameView;
    private EditText ageView;
    private EditText heightView;
    private EditText weightView;
    private RadioButton maleRadio;
    private RadioButton femaleRadio;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle abToggle;
    private android.support.v7.widget.Toolbar toolbar;
    private NavigationView navView;
    private int menuItemId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setNavigationViewListener();

        drawerLayout = findViewById(R.id.drawerLayout);
        abToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(abToggle);
        abToggle.syncState();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Create Profile");
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);

        maleRadio = findViewById(R.id.maleRadio);
        femaleRadio = findViewById(R.id.femaleRadio);
        nameView = findViewById(R.id.nameView);
        ageView = findViewById(R.id.ageView);
        heightView = findViewById(R.id.heightView);
        weightView = findViewById(R.id.weightView);
        age = 0;
        heightInInches = 0;
        bmi = 0;
        weight = 0;
    }
    public double calculateBMI(double heightInInches, double weight){
        //double height = heightInInches*heightInInches;
        //double value = weight/height;
        bmi = 703 * (weight/(heightInInches*heightInInches));
        //System.out.println(bmi);
        return bmi;
    }
    public void submitOnClick(View view){
        try {
            if (maleRadio.isChecked()) {
                sex = "Male";
            } else {
                sex = "Female";
            }

            name = nameView.getText().toString();
            age = Integer.parseInt(ageView.getText().toString());
            //System.out.print(age);
            heightInInches = Double.parseDouble(heightView.getText().toString());
            //System.out.print(heightInInches);
            weight = Double.parseDouble(weightView.getText().toString());
            //System.out.println(weight);
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra("bmi", calculateBMI(heightInInches, weight));
            startActivity(intent);
        }
        catch (NumberFormatException | IllegalStateException e){
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }
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
