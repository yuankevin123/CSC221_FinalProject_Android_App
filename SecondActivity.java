package com.example.studyguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    private Button buttonCalculus;
    private Button buttonComputerScience;
    private Button buttonChemistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        buttonCalculus = (Button) findViewById(R.id.btnCalc);
        buttonComputerScience = (Button) findViewById(R.id.btnCsc);
        buttonChemistry = (Button) findViewById(R.id.btnChem);
        buttonCalculus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalculusActivity();
            }
        });
        buttonComputerScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openComputerScienceActivity();
            }
        });
        buttonChemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChemistryActivity();
            }
    });
    }
    private void openCalculusActivity(){
        Intent intent = new Intent(this,CalculusActivity.class);
        startActivity(intent);
    }
    private void openChemistryActivity(){
        Intent intent = new Intent(this,ChemistryActivity.class);
        startActivity(intent);
    }
    private void openComputerScienceActivity(){
        Intent intent = new Intent(this,ComputerScienceActivity.class);
        startActivity(intent);
    }
}
