package edu.valdosta.workoutapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class ProfileActivity extends Activity{
    int age;
    int heightInInches;
    int bmi;
    int weight;
    String sex;
    String name;
    EditText nameView;
    EditText ageView;
    EditText heightView;
    RadioButton maleRadio;
    RadioButton femaleRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        maleRadio = (RadioButton) findViewById(R.id.maleRadio);
        femaleRadio = (RadioButton) findViewById(R.id.femaleRadio);
        nameView = (EditText) findViewById(R.id.nameView);
        ageView = (EditText) findViewById(R.id.ageView);
        heightView = (EditText) findViewById(R.id.heightView);
        age = 0;
        heightInInches = 0;
        bmi = 0;
        weight = 0;
    }
    public int calculateBMI(int heightInInches, int weight){
        bmi= 703 * weight/(heightInInches^2);
        return bmi;
    }
    public void  submitOnClick(){
        if(maleRadio.isChecked()){
            sex = "Male";
        }
        else{
            sex= "Female";
        }
        name = nameView.getText().toString();
        age = Integer.parseInt(ageView.getText().toString());
        heightInInches = Integer.parseInt(heightView.getText().toString());;

    }
}
