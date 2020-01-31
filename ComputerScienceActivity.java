package com.example.studyguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComputerScienceActivity extends AppCompatActivity {

    private Button buttonSoftware;
    private Button buttonAlgo;
    private Button buttonCpp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science);

        buttonSoftware = (Button) findViewById(R.id.btnSDL);
        buttonSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSoftwareDesignActivity();
            }
        });
        buttonAlgo = (Button) findViewById(R.id.btnAlgo);
        buttonAlgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSoftwareDesignActivity();
            }
        });
        buttonCpp= (Button) findViewById(R.id.btnCpp);
        buttonCpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSoftwareDesignActivity();
            }
        });

    }
    private void openSoftwareDesignActivity() {
        Intent intent = new Intent(this, SoftwareDesignActivity.class);
        startActivity(intent);
    }
}
