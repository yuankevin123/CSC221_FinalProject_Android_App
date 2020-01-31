package com.example.studyguideapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizSD_DB extends SQLiteOpenHelper {


    private static final String Database_name = "SDLQuiz1.db";
    private static final int Database_version = 4;
    private SQLiteDatabase db;
    public QuizSD_DB(Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.Table_Name + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.Column_Question + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option1 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option2 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option3 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option4 + " TEXT, " +
                QuizContract.QuestionsTable.Column_answer +  " INTEGER" + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillSDLQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.Table_Name);
        onCreate(db);

    }
    private void fillSDLQuestionsTable(){

        QuestionSDLQuiz1 q1 = new QuestionSDLQuiz1("What is true on the difference between static and non static methods? "
                , "static methods are accessible without an instance ",
                "static methods are access static methods only since non-static methods are only available when an instance is created ",
                "non-static methods can access both types", "all of the above", 4);
        addQuestion(q1);
        QuestionSDLQuiz1 q2= new QuestionSDLQuiz1("What is false on the difference between the two Types if data(Reference and primitive)?",
                "Primitive types are passed by reference only" , "Reference types are passed by reference only",
                "Reference Types are pointers to object", "Primitive types are not pointers", 1);
        addQuestion(q2);
        QuestionSDLQuiz1 q3 = new QuestionSDLQuiz1("What are Java Exceptions?", "An indication of a problem that occurs when typed" ,
                "An indication of a problem that occurred during a program's execution",
                "An indication of a problem that occurs when you have an alert class", "all of the above", 2);
        addQuestion(q3);
        QuestionSDLQuiz1 q4 = new QuestionSDLQuiz1("finish the code:(owners is the arraylist) \n public void addOwner(String owner)",
                "if(owners.contains(owner)){ owners.add(owner);}" ,
                "if(owner.contains(owner)){ owners.add(owner);}",
                "if(owner.contains(owners)){ owner.add(owners);",
                "if(!owners.contains(owner)){ owners.add(owner);}", 4);
        addQuestion(q4);

    }

    private void addQuestion(QuestionSDLQuiz1 question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.Column_Question, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.Column_Option1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.Column_Option2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.Column_Option3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.Column_Option4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.Column_answer, question.getAnswer());
        db.insert(QuizContract.QuestionsTable.Table_Name, null, cv);
    }

    public List<QuestionSDLQuiz1> getAllQuestions() {
        List<QuestionSDLQuiz1> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT * FROM " + QuizContract.QuestionsTable.Table_Name, null);
        if(c.moveToFirst()){
            do{
                QuestionSDLQuiz1 question = new QuestionSDLQuiz1();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.Column_Question)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.Column_Option1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.Column_Option2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.Column_Option3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.Column_Option4)));
                question.setAnswer(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.Column_answer)));
                questionList.add(question);
            }
            while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
