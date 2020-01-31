package com.example.studyguideapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SDLQuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    private static final int REQUEST_CODE_QUIZ2 = 1;
    public static final String SharedPref = "SharedPref";
    public static final String SharedPref2 = "SharedPref";
    public static final String KEY_HIGHSCORE = "HighScore";
    public static final String KEY_HIGHSCORE2 = "HighScore";
    private int highScore1;
    private int highScore2;
    private TextView textHighScore1;
    private TextView textHighScore2;
    private Button buttonQuiz1;
    private Button buttonQuiz2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdlquiz);
        textHighScore1 = findViewById(R.id.text_view_Highscore1);
        loadHighScore1();
        buttonQuiz1 = (Button) findViewById(R.id.btnSDLQuiz1_start);
        buttonQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSDLQuiz1Activity();
            }
        });
        textHighScore2 = findViewById(R.id.text_view_Highscore2);
        loadHighScore2();
        buttonQuiz2 = (Button) findViewById(R.id.btnSDLQuiz2_start);
        buttonQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSDLQuiz2Activity();
            }
        });
    }
    private void openSDLQuiz1Activity(){
        Intent intent = new Intent(this,SDLQuiz1_start.class);
        startActivityForResult(intent,REQUEST_CODE_QUIZ);
    }
    private void openSDLQuiz2Activity(){
        Intent intent = new Intent(this,SDLQuiz2_start.class);
        startActivityForResult(intent,REQUEST_CODE_QUIZ2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score1 = data.getIntExtra(SDLQuiz1_start.extraScore, 0);
                if (score1 > highScore1) {
                    updateHighScore1(score1);
                }
                int score2 = data.getIntExtra(SDLQuiz2_start.extraScore2, 0);
                if(score2 > highScore2){
                    updateHighScore2(score2);
                }
            }
        }
        if(requestCode == REQUEST_CODE_QUIZ2){
                int score2 = data.getIntExtra(SDLQuiz2_start.extraScore2, 0);
                if(score2 > highScore2){
                    updateHighScore2(score2);
                }
            }

    }
    private void loadHighScore2(){
        SharedPreferences prefs2 = getSharedPreferences(SharedPref2,MODE_PRIVATE);
        highScore2 = prefs2.getInt(KEY_HIGHSCORE2, 0);
        textHighScore2.setText("HighScore: " + highScore2);
       //prefs2.edit().clear().commit(); //use this if you want to reset high score
    }
    private void updateHighScore2(int newHighScore2) {
        highScore2 = newHighScore2;
        textHighScore2.setText("HighScore: " + highScore2);
        SharedPreferences prefs2 = getSharedPreferences(SharedPref2, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = prefs2.edit();
        editor2.putInt(KEY_HIGHSCORE2,highScore2);
        editor2.apply();

    }

    private void loadHighScore1(){
        SharedPreferences prefs = getSharedPreferences(SharedPref,MODE_PRIVATE);
        highScore1 = prefs.getInt(KEY_HIGHSCORE, 0);
        textHighScore1.setText("HighScore: " + highScore1);
        //prefs.edit().clear().commit(); // use this if you want to reset high score
    }

    private void updateHighScore1(int newHighScore1) {
        highScore1 = newHighScore1;
        textHighScore1.setText("HighScore: " + highScore1);
        SharedPreferences prefs = getSharedPreferences(SharedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highScore1);
        editor.apply();

    }
}
