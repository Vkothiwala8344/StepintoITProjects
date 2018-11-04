package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String userKey = "userKey";
    public static final String passKey = "passKey";

    Button submit;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        submit = (Button)findViewById(R.id.submit);
        username = (EditText)findViewById(R.id.username_edit);
        password = (EditText)findViewById(R.id.password_edit);


        //check if you have stored data in shared preferences

        sharedPreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
       if (sharedPreferences.contains(userKey) && sharedPreferences.contains(passKey)) {

           startActivity(new Intent(LoginActivity.this,CalculatorActivity.class));
           finish();
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname,pass;
                uname = username.getText().toString();
                pass = password.getText().toString();

              //  Log.d("Username",uname);
              //  Log.d("Password",pass);

            if(uname.equals("admin") && pass.equals("pass"))
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userKey,uname);
                editor.putString(passKey,pass);
                editor.commit();

                startActivity(new Intent(LoginActivity.this,CalculatorActivity.class));
            }


            }
        });

    }
}
