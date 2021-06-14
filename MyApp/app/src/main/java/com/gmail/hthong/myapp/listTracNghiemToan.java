package com.gmail.hthong.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.hthong.myapp.DAO.quizcontractDao;
import com.gmail.hthong.myapp.adapter.tracnghiemtoanAdapter;
import com.gmail.hthong.myapp.model.QuizContract;

import java.util.List;

public class listTracNghiemToan extends AppCompatActivity {
    RecyclerView recyclerView;
     List<QuizContract> listquiz;
    quizcontractDao quizcontractDao;
    tracnghiemtoanAdapter adapter;
    Button btnback,btnthem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trac_nghiem_toan);
        recyclerView = (RecyclerView) findViewById(R.id.listquiz);
        recyclerView.setLayoutManager(new LinearLayoutManager(listTracNghiemToan.this));
        quizcontractDao=new quizcontractDao(listTracNghiemToan.this);
        listquiz=quizcontractDao.getQuizbyCat(1);
        adapter=new tracnghiemtoanAdapter(listquiz,this);
        recyclerView.setAdapter(adapter);
        btnback=findViewById(R.id.btnback);
        btnthem=findViewById(R.id.btnthem);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemToan.this, addQuestionToan.class);
                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(listTracNghiemToan.this, com.gmail.hthong.myapp.quanlyTracNghiem.class);
                startActivity(intent);
            }
        });
    }
}