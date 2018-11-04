package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

                //  Log.d("Username",uname);
                //  Log.d("Password",pass);

                if (uname.equals("admin") && pass.equals("pass")) {
                    sharedPreferences = getSharedPreferences(MYPREFERENCE,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USER, uname);
                    editor.putString(KEY_PASSWORD, pass);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, CalculatorActivity.class));

                } else if (uname.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "username and password can not be empty", Toast.LENGTH_SHORT).show();

                } else if (uname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "username can not be empty", Toast.LENGTH_SHORT).show();


                } else if (pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Username and password are not right", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
