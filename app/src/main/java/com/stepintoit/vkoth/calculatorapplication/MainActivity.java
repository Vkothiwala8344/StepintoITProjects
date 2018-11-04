package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MYPREFERENCE = "mypref";
    public static final String KEY_USER = "userKey";
    public static final String KEY_PASSWORD = "passKey";

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
                    sharedPreferences = getSharedPreferences(MYPREFERENCE,
                            Context.MODE_PRIVATE);
                    if (sharedPreferences.contains(KEY_USER) && sharedPreferences.contains(KEY_PASSWORD)) {

                        startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
                        finish();
                    }

                    else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                }
            }
        };
        timer.start();
        }
}
