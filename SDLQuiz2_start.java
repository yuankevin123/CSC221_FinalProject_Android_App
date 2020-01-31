package com.example.studyguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class SDLQuiz2_start extends AppCompatActivity {
    public static final String extraScore2 = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView TextViewQuestion;
    private TextView TextViewScore2;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup radioGroup;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private Button buttonConfirm;
    private List<QuestionSDLQuiz2> quiz2List;
    private ColorStateList textColorDefault;
    private ColorStateList getTextColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private int qCounter;
    private int qCountotal;
    private QuestionSDLQuiz2 currentQuestion;
    private int score2;
    private boolean answered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdlquiz1_start);
        TextViewQuestion = findViewById(R.id.text_view_question);
        TextViewScore2 = findViewById(R.id.text_view_Score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        radioGroup = findViewById(R.id.radio_group);
        radio1 = findViewById(R.id.radio_button1);
        radio2 = findViewById(R.id.radio_button2);
        radio3 = findViewById(R.id.radio_button3);
        radio4 = findViewById(R.id.radio_button4);
        buttonConfirm = findViewById(R.id.btnSDLQuiz1Next);

        textColorDefault = radio1.getTextColors();
        getTextColorDefaultCd = textViewCountDown.getTextColors();
        QuizSD_DB2 db2 = new QuizSD_DB2(this);
        quiz2List = db2.getAllQuestions();
        qCountotal = quiz2List.size();
        Collections.shuffle(quiz2List);// shuffles questions
        showNextQuestion();

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(radio1.isChecked() || radio2.isChecked() || radio3.isChecked() || radio4.isChecked()){
                        CheckAnswered();
                    }
                    else{
                        Toast.makeText(SDLQuiz2_start.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    showNextQuestion();
                }
            }
        });
    }
    private void showNextQuestion(){
        radio1.setTextColor(textColorDefault);
        radio2.setTextColor(textColorDefault);
        radio3.setTextColor(textColorDefault);
        radio4.setTextColor(textColorDefault);
        radioGroup.clearCheck();
        if(qCounter < qCountotal){
            currentQuestion = quiz2List.get(qCounter);

            TextViewQuestion.setText((currentQuestion.getQuestion()));
            radio1.setText(currentQuestion.getOption1());
            radio2.setText(currentQuestion.getOption2());
            radio3.setText(currentQuestion.getOption3());
            radio4.setText(currentQuestion.getOption4());
            qCounter++;
            textViewQuestionCount.setText("Question:" + qCounter + "/" + qCountotal);
            answered = false;
            buttonConfirm.setText("CONFIRM");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else{
            finishQuiz2();
        }
    }
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                CheckAnswered();

            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis < 10000){
            textViewCountDown.setTextColor(Color.RED);
        }else{
            textViewCountDown.setTextColor(Color.GREEN);
        }
    }
    private void CheckAnswered(){
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNbr = radioGroup.indexOfChild(rbSelected) + 1;
        if(answerNbr == currentQuestion.getAnswer()) {
            score2++;
            TextViewScore2.setText("Score: " + score2);
        }
        showSolution();
    }
    private void showSolution(){
        radio1.setTextColor(Color.RED);
        radio2.setTextColor(Color.RED);
        radio3.setTextColor(Color.RED);
        radio4.setTextColor(Color.RED);

        switch(currentQuestion.getAnswer()) {
            case 1:
                radio1.setTextColor(Color.GREEN);
                TextViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                radio2.setTextColor(Color.GREEN);
                TextViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                radio3.setTextColor(Color.GREEN);
                TextViewQuestion.setText("Answer 3 is correct");
                break;
            case 4:
                radio4.setTextColor(Color.GREEN);
                TextViewQuestion.setText("Answer 4 is correct");
                break;
        }
        if(qCounter < qCountotal) {
            buttonConfirm.setText("NEXT");
        }
        else{
            buttonConfirm.setText("FINISH");

        }
    }
    private void finishQuiz2(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(extraScore2,score2);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}
