package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String userKey = "userKey";
    public static final String passKey = "passKey";

    Button btnSubmit;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);


        //check if you have stored data in shared preferences



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, pass;
                uname = edtUsername.getText().toString();
                pass = edtPassword.getText().toString();

                //  Log.d("Username",uname);
                //  Log.d("Password",pass);

                if (uname.equals("admin") && pass.equals("pass")) {
                    sharedPreferences = getSharedPreferences(mypreference,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(userKey, uname);
                    editor.putString(passKey, pass);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, CalculatorActivity.class));
                }


            }
        });

    }
}
