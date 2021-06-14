package com.gmail.hthong.myapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.login.widget.ProfilePictureView;
import com.gmail.hthong.myapp.DAO.General;
import com.gmail.hthong.myapp.DAO.userDao;
import com.gmail.hthong.myapp.adapter.userProfileAdapter;
import com.gmail.hthong.myapp.model.User;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class TaiKhoanFragment extends Fragment {

    RecyclerView recyclerView;
    Button btnlogout;
    ProfilePictureView imgProfile;
    com.gmail.hthong.myapp.DAO.userDao userDao ;
    List<User> listProfile;
    ImageView anh;
    LinearLayout fbProfile;
    TextView txtName,txtEmail,txtUsername;
    public TaiKhoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.profile);
        fbProfile= (LinearLayout) view.findViewById(R.id.fbProfile);
        txtEmail=view.findViewById(R.id.txtEmail);
        txtName=view.findViewById(R.id.txtName);
        txtUsername=view.findViewById(R.id.txtUsername);
        anh=(ImageView) view.findViewById(R.id.img);
        imgProfile=view.findViewById(R.id.imgProfile);

        if (Paper.book().read(General.USER_NAME).toString().equals("null")){

            recyclerView.setVisibility(View.GONE);
            fbProfile.setVisibility(View.VISIBLE);
            String name=Paper.book().read(General.NAME);
            String email=Paper.book().read(General.USER_EMAIL);
            String img=Paper.book().read(General.USER_IMAGE);
            String id=Paper.book().read(General.ID_FB);
            //anh.setVisibility(View.VISIBLE);
            anh.setVisibility(View.GONE);
            imgProfile.setVisibility(View.VISIBLE);
            txtUsername.setText(id);
            txtName.setText(name);
            txtEmail.setText(email);
            imgProfile.setProfileId(id);

        }else{

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            userDao=new userDao(getContext());
            listProfile=new ArrayList<>();
            String username= Paper.book().read(General.USER_NAME);
            User tien =userDao.getProfile(username);
            listProfile.add(tien);
            userProfileAdapter adt=new userProfileAdapter(listProfile);
            recyclerView.setAdapter(adt);
        }





        //logout
        btnlogout=view.findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder
                        .setTitle("Logout")
                        .setMessage("Are you sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getActivity(), MainActivity.class);
                                Paper.book().destroy();
                                startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();


            }
        });


        return view;
    }
}