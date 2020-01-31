package com.example.studyguideapp;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String Table_Name= "quiz_questions";
        public static final String Column_Question = "question";
        public static final String Column_Option1 = "option1";
        public static final String Column_Option2 = "option2";
        public static final String Column_Option3 = "option3";
        public static final String Column_Option4 = "option4";
        public static final String Column_answer = "answer";
    }
}
