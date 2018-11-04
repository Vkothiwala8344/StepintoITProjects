package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String userKey = "userKey";
    public static final String passKey = "passKey";

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
                    sharedPreferences = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    if (sharedPreferences.contains(userKey) && sharedPreferences.contains(passKey)) {

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
