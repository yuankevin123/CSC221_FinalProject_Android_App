package com.example.studyguideapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class QuizSD_DB2 extends SQLiteOpenHelper {


    private static final String Database_name = "SDLQuiz2.db2";
    private static final int Database_version = 2;
    private SQLiteDatabase db2;
    public QuizSD_DB2(Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        this.db2 = db2;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.Table_Name + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.Column_Question + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option1 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option2 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option3 + " TEXT, " +
                QuizContract.QuestionsTable.Column_Option4 + " TEXT, " +
                QuizContract.QuestionsTable.Column_answer +  " INTEGER" + ")";

        db2.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillSDLQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.Table_Name);
        onCreate(db);

    }
    private void fillSDLQuestionsTable(){

        QuestionSDLQuiz2 q1 = new QuestionSDLQuiz2("What is true for the differences between inheritance and composition in Java?"
                , "When the relation is an is-a relation and not has a relation ",
                "can access protected members ",
                "when the relation is a tight relation between objects", "all of the above", 4);
        addQuestion(q1);
        QuestionSDLQuiz2 q2= new QuestionSDLQuiz2("what is true for the similarities/differences between a Java abstract class and a Java interface?",
                "Both are Java's polymorphism implementation" ,
                "Both can neither be instantiated", "interfaces are extended but abstract classes are implemented", "A & C", 4);
        addQuestion(q2);
        QuestionSDLQuiz2 q3 = new QuestionSDLQuiz2("What is false about JavaFX", "FXML means FX markup Language"
                , "FXML file separates the GUI from the interface",
                "FXML is a Gui's event handler ",
                "FXML uses XML vocabulary for defining and arranging its Gui's controller without writing any Java code", 3);
        addQuestion(q3);
        QuestionSDLQuiz2 q4 = new QuestionSDLQuiz2("Is this true: the Controller class is the Gui's event handlers",
                "True" , "False", " ", " ", 1);
        addQuestion(q4);

    }

    private void addQuestion(QuestionSDLQuiz2 question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.Column_Question, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.Column_Option1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.Column_Option2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.Column_Option3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.Column_Option4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.Column_answer, question.getAnswer());
        db2.insert(QuizContract.QuestionsTable.Table_Name, null, cv);
    }

    public List<QuestionSDLQuiz2> getAllQuestions() {
        List<QuestionSDLQuiz2> questionList = new ArrayList<>();
        db2 = getReadableDatabase();
        Cursor c = db2.rawQuery( "SELECT * FROM " + QuizContract2.QuestionsTable.Table_Name, null);
        if(c.moveToFirst()){
            do{
                QuestionSDLQuiz2 question = new QuestionSDLQuiz2();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract2.QuestionsTable.Column_Question)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract2.QuestionsTable.Column_Option1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract2.QuestionsTable.Column_Option2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract2.QuestionsTable.Column_Option3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract2.QuestionsTable.Column_Option4)));
                question.setAnswer(c.getInt(c.getColumnIndex(QuizContract2.QuestionsTable.Column_answer)));
                questionList.add(question);
            }
            while(c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
