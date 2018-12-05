package edu.valdosta.workoutapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button createProfile;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        createProfile = (Button) findViewById(R.id.createProfile);
    }
    public void createProfileOnClick(View view){
        startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
    }

}
