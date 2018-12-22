package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.stepintoit.vkoth.calculatorapplication.data.MySharedPreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {
                    printHashKey(MainActivity.this);

                   String token = MySharedPreference.getInstance(MainActivity.this).getValue(MySharedPreference.KEY_TOKEN);
                  // String password = MySharedPreference.getInstance(MainActivity.this).getValue(MySharedPreference.KEY_PASSWORD);
                    AccessToken accessToken = AccessToken.getCurrentAccessToken();
                    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                    if(!token.isEmpty() || isLoggedIn)
                    {
                        startActivity(new Intent(MainActivity.this, ProductActivity.class));

                    }

                    else {

                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                    finish();
                }
            }
        };
        timer.start();
        }

        //startActivity(new Intent(MainActivity.this, ProductActivity.class));
        public static void printHashKey(Context pContext) {
            try {
                PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String hashKey = new String(Base64.encode(md.digest(), 0));
                    Log.i("MainActivity", "printHashKey() Hash Key: " + hashKey);
                }
            } catch (NoSuchAlgorithmException e) {
                Log.e("MainActivity", "printHashKey()", e);
            } catch (Exception e) {
                Log.e("MainActivity", "printHashKey()", e);
            }
        }


}

