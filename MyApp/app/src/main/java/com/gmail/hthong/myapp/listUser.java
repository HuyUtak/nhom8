package com.gmail.hthong.myapp;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.hthong.myapp.DAO.userDao;
import com.gmail.hthong.myapp.adapter.userAdapter;
import com.gmail.hthong.myapp.model.User;

import java.util.List;

public class listUser extends AppCompatActivity {

    ListView lvUser;
    com.gmail.hthong.myapp.DAO.userDao userDao;
    List<User> dsUser;
    userAdapter adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        lvUser=(ListView) findViewById(R.id.listUser);
        userDao=new userDao(this);
        dsUser=userDao.getallUser();
        adt=new userAdapter(dsUser,this);
        lvUser.setAdapter(adt);
    }
}