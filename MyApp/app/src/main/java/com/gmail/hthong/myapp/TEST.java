package com.gmail.hthong.myapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TEST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printKeyHash();
    }
    public  void printKeyHash(){
        try {
            PackageInfo info=getPackageManager().getPackageInfo("com.gmail.hthong.myapp", PackageManager.GET_SIGNATURES);
            for(Signature signature: info.signatures)
            {
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
}