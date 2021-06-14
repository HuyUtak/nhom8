package com.gmail.hthong.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.gmail.hthong.myapp.Screenshot.Screenshot;

public class KetQuaActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ImageView img,manhinh;
    Button btnthoat,btnshare;
    TextView txtcontent,txtscore;
    Bitmap bitmap;
    LinearLayout main;
    int total_score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        callbackManager = CallbackManager.Factory.create();
        img=findViewById(R.id.img);
        btnthoat=findViewById(R.id.btnthoat);
        btnshare=findViewById(R.id.btnshare);
        txtcontent=findViewById(R.id.content);
        txtscore=findViewById(R.id.score);
        main=findViewById(R.id.main);
         Intent intent=getIntent();

        shareDialog=new ShareDialog(KetQuaActivity.this);
        Bundle bundle=intent.getBundleExtra("DATA");
        int total_score=bundle.getInt("total_score");
         total_score=total_score;
        txtscore.setText("Score : "+total_score);
        if (total_score<50){
            img.setImageResource(R.drawable.sad);
            txtcontent.setText("Bạn Cần Cố Gắng Hơn :(");
        }
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(KetQuaActivity.this, bottomNavigation.class);
                startActivity(intent);
            }
        });

        //khoi tao fb
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bitmap= Screenshot.takscreenshotOfRootView(main);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .setShareHashtag(new ShareHashtag.Builder().setHashtag("#FreeEducation").build())
                        .build();
                shareDialog.show(content);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}


