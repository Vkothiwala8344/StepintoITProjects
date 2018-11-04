package com.stepintoit.vkoth.calculatorapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    public static final String MYPREFERENCE = "mypref";
    public static final String KEY_USER = "userKey";
    public static final String KEY_PASSWORD = "passKey";

    private static MySharedPreference mySharedPreferenceInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    private MySharedPreference() {
    }

    public static MySharedPreference getInstance(Context context) {
        if (mySharedPreferenceInstance == null) {
            mySharedPreferenceInstance = new MySharedPreference();
            sharedPreferences = context.getSharedPreferences(MYPREFERENCE,
                    Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

        }
        return mySharedPreferenceInstance;

    }

    void putValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    String getValue(String key) {

        return sharedPreferences.getString(key, "");
    }



}
