package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MYPREFERENCE = "mypref";
    public static final String KEY_USER = "userKey";
    public static final String KEY_PASSWORD = "passKey";

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

                if (uname.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "username and password can not be empty", Toast.LENGTH_SHORT).show();

                } else if (uname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "username can not be empty", Toast.LENGTH_SHORT).show();


                } else if (pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();

                } else if (Patterns.EMAIL_ADDRESS.matcher(uname).matches()){

                    MySharedPreference.getInstance(LoginActivity.this).putValue(MySharedPreference.KEY_USER, uname);
                    MySharedPreference.getInstance(LoginActivity.this).putValue(MySharedPreference.KEY_PASSWORD,pass);
                    startActivity(new Intent(LoginActivity.this, CalculatorActivity.class));
                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email id",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
