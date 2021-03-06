package com.gmail.hthong.myapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmail.hthong.myapp.DB.DBHelper;
import com.gmail.hthong.myapp.model.QuizContract;

import java.util.ArrayList;
import java.util.List;

public class quizcontractDao  {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public static final String SQL_CREATE_QUIZ = "CREATE TABLE tbl_quiz(\n" +
            "\t id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t question text NOT NULL,\n" +
            "\t option1 text NOT NULL,\n" +
            "\t option2 text NOT NULL ,\n" +
            "\t option3 text NOT NULL ,\n" +
            "\t answer text NOT NULL, \n" +
            "\t category INTEGER NOT NULL);";
    public static final String TBL_QUIZ = "tbl_quiz";
    public static final String TAG = "QuizContractDAO";

    public quizcontractDao(Context context){
        dbHelper= new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }
    public int insertQuiz(QuizContract quiz){
        ContentValues values=new ContentValues();
        values.put("id",quiz.getId());
        values.put("question",quiz.getQuestion());
        values.put("option1",quiz.getOption1());
        values.put("option2",quiz.getOption2());
        values.put("option3",quiz.getOption3());
        values.put("answer",quiz.getAnswer());
        values.put("category",quiz.getCategory());
        try {
            if (db.insert(TBL_QUIZ,null, values)==-1    ) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;

    }
    public List<QuizContract> getQuizbyCat(int id){
        List<QuizContract> listQuiz=new ArrayList<>();
        String where="category = ?";
        String[] whereArgs={String.valueOf(id)};
        Cursor cursor = db.query(TBL_QUIZ, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();//b???t ?????u t??? h??ng ?????u ti??n
        while (cursor.isAfterLast() == false) { //n???u qua kh???i ph???n t??? cu???i c??ng
            QuizContract quiz = new QuizContract(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            );
            listQuiz.add(quiz);
            cursor.moveToNext();
            //Log.d("check",listQuiz.get(0).getAnswer());

        }
        cursor.close();


        return listQuiz;
    }
    public int deleteQuiz(QuizContract quiz){
        String where="id = ?";
        String[] whereArgs={String.valueOf(quiz.getId())};
        try {
            if (db.delete(TBL_QUIZ,where,whereArgs)<0){
                return -1;
            }

        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
        return 1;

    }
    public int updateQuiz(QuizContract quiz){
        ContentValues values=new ContentValues();
        values.put("question",quiz.getQuestion());
        values.put("option1",quiz.getOption1());
        values.put("option2",quiz.getOption2());
        values.put("option3",quiz.getOption3());
        values.put("answer",quiz.getAnswer());
        values.put("category",quiz.getCategory());
        String where="id = ?";
        String[] whereArgs={String.valueOf(quiz.getId())};
        try {
            if (db.update(TBL_QUIZ,values,where,whereArgs)==-1) {
                return -1;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return 1;
    }


}
