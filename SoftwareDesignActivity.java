package com.example.studyguideapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class SoftwareDesignActivity extends AppCompatActivity {

    private Button buttonSDLQuiz;
    private Button buttonSDLStudyGuide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_design);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSDLQuiz = (Button) findViewById(R.id.btnSDLQuiz);
        buttonSDLQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSDLQuizActivity();
            }
        });
        buttonSDLStudyGuide = (Button) findViewById(R.id.btnSDLStudy);
        buttonSDLStudyGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSDLStudyActivity();
            }
        });

    }

    private void openSDLQuizActivity() {
        Intent intent = new Intent(this, SDLQuizActivity.class);
        startActivity(intent);
    }
    private void openSDLStudyActivity() {
        Intent intent = new Intent(this, SDLStudyActivity.class);
        startActivity(intent);
    }
}
