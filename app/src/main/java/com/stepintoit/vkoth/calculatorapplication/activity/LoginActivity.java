package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.stepintoit.vkoth.calculatorapplication.data.MySharedPreference;
import com.stepintoit.vkoth.calculatorapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private CallbackManager callbackManager;
    public static final String MYPREFERENCE = "mypref";
    public static final String KEY_USER = "userKey";
    public static final String KEY_PASSWORD = "passKey";
    private static final String EMAIL = "email";

    @BindView(R.id.pb_login)
    ProgressBar pbLogin;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login_fb)
    LoginButton btnLoginFb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class LoginTask extends AsyncTask<String, Integer, String> {

        String userName, passWord;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLogin.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

            String response = "";

            try {

                userName = strings[0];
                passWord = strings[1];

                JSONObject requestJsonObject = new JSONObject();
                requestJsonObject.put("email", userName);
                requestJsonObject.put("password", passWord);

                // This is getting the url from the string we passed in
                URL url = new URL("https://reqres.in/api/login");

                // Create the urlConnection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                urlConnection.setRequestProperty("Content-Type", "application/json");

                urlConnection.setRequestMethod("POST");


                // OPTIONAL - Sets an authorization header
                urlConnection.setRequestProperty("Authorization", "someAuthString");

                // Send the post body
//                if (this.postData != null) {
                Log.d("jasonlog", requestJsonObject.toString());
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(requestJsonObject.toString());
                writer.flush();
//                }

                int statusCode = urlConnection.getResponseCode();


                if (statusCode == 200) {

                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    response = getStringFromInputStream(inputStream);

                    // From here you can convert the string to JSON with whatever JSON parser you like to use
                    // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
                } else {
                    // Status code is not 200
                    // Do something to handle the error
                }

            } catch (Exception e) {
                //Log.d(TAG, e.getLocalizedMessage());
            }

            return response;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int timer = values[0];
            Toast.makeText(LoginActivity.this, Integer.toString(timer), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            pbLogin.setVisibility(View.INVISIBLE);

            Log.d("responselog", response);
            if (response != null && !response.isEmpty()) {

                try {
                    JSONObject tokenJson = new JSONObject(response);
                    // JSONObject token = tokenJson.getJSONObject("token");
                    String tokenKey = tokenJson.getString("token");
                    Log.d("token", tokenKey);

                    MySharedPreference.getInstance(LoginActivity.this).putValue(MySharedPreference.KEY_TOKEN, tokenKey);

                    startActivity(new Intent(LoginActivity.this, ProductActivity.class));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        }

        public String getStringFromInputStream(InputStream stream) throws IOException {
            int n = 0;
            char[] buffer = new char[1024 * 4];
            InputStreamReader reader = new InputStreamReader(stream, "UTF8");
            StringWriter writer = new StringWriter();
            while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
            return writer.toString();
        }
    }

    @OnClick(R.id.btn_submit)
    void verifyUser() {
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

    @OnClick(R.id.btn_login_fb)
    void facebookLoginButtonSetup() {
        btnLoginFb.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call btnLoginFb.setFragment(this);

        // Callback registration
        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Timber.i("access token=" + loginResult.getAccessToken().getToken());
                startActivity(new Intent(LoginActivity.this, ProductActivity.class));
                finish();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }
}



