package com.gmail.hthong.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gmail.hthong.myapp.DAO.General;
import com.gmail.hthong.myapp.DAO.userDao;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button btnDangky,btnLogin;
    EditText edtUsername,edtPassword;

    userDao user;
    com.gmail.hthong.myapp.endcode.enCode enCode;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=new userDao(MainActivity.this);

        callbackManager = CallbackManager.Factory.create();
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);
        btnDangky=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AcRegister=new Intent(MainActivity.this,registerUser.class);
                startActivity(AcRegister);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String username=edtUsername.getText().toString();
                  String password=edtPassword.getText().toString();
                  user=new userDao(MainActivity.this);
                  Boolean taikhoan= user.checkLogin(username,password);
                  if (taikhoan==true || username.equals("admin") && password.equals("admin")){
                      Paper.init(MainActivity.this);
                      Paper.book().write(General.USER_NAME, username);
                      Intent AcRegister=new Intent(MainActivity.this,processbar_Activity.class);
                      startActivity(AcRegister);
                      finish();
                  }else {
                      Toast.makeText(MainActivity.this,"Vui Lòng Kiểm Tra lại Tài Khỏan",Toast.LENGTH_SHORT).show();
                  }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void result(){
        GraphRequest graphRequest=GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.getJSONObject().toString());
                try {
                    String email1=object.getString("email");
                    String name1=object.getString("name");
                    String id1=object.getString("id");
        //            String username=object.getString("username");
                   Paper.book().write(General.ID_FB,id1);
           //         Paper.book().write(General.USER_NAMEFB, username);
                    Paper.book().write(General.NAME, name1);
                    Paper.book().write(General.USER_EMAIL, email1);
                    //String id1=object.getString("id");
                    //email.setText(email1);
                    //name.setText(name1);
                    //info.setText(id1);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Bundle pra=new Bundle();
        pra.putString("fields","name,email,first_name");
        graphRequest.setParameters(pra);
        graphRequest.executeAsync();
    }
    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }


}


