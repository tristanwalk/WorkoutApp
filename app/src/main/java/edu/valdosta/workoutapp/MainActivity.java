package edu.valdosta.workoutapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int buttonId;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        buttonId = view.getId();

        switch (buttonId){
            case R.id.strengthButton:
                intent = new Intent(this, StrengthActivity.class);
                break;
            case R.id.enduranceButton:
                intent = new Intent(this, EnduranceActivity.class);
                break;
            case R.id.flexibilityButton:
                intent = new Intent(this, FlexibiltyActivity.class);
                break;
            case R.id.balanceButton:
                intent = new Intent(this, BalanceActivity.class);
                break;
        }
        startActivity(intent);
    }

    public void onClickSettings(View view){
        intent = new Intent (this, SettingsActivity.class);
    }
    }

