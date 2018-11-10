package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String MYPREFERENCE = "mypref";
    public static final String KEY_USER = "userKey";
    public static final String KEY_PASSWORD = "passKey";
    ProgressBar pbLogin;

    Button btnSubmit;
    EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSubmit = (Button) findViewById(R.id.btn_submit);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        pbLogin = (ProgressBar) findViewById(R.id.pb_login);


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

                } else if (!Patterns.EMAIL_ADDRESS.matcher(uname).matches()) {
                    Toast.makeText(getApplicationContext(), "Invalid email id", Toast.LENGTH_LONG).show();
                } else {
                    new LoginTask().execute(uname, pass);
                }
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Integer, Boolean> {

        String userName, passWord;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            userName = strings[0];
            passWord = strings[1];

            for (int i = 0; i < 10; i++) {
                if (i % 3 == 0) {
                    publishProgress(i);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return userName.equals("x@gmail.com") && passWord.equals("pass");

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int timer = values[0];
            Toast.makeText(LoginActivity.this, Integer.toString(timer), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            pbLogin.setVisibility(View.INVISIBLE);

            if (aBoolean) {
                MySharedPreference.getInstance(LoginActivity.this).putValue(MySharedPreference.KEY_USER, userName);
                MySharedPreference.getInstance(LoginActivity.this).putValue(MySharedPreference.KEY_PASSWORD, passWord);
                startActivity(new Intent(LoginActivity.this, CalculatorActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


