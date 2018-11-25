package com.stepintoit.vkoth.calculatorapplication;

import android.app.Application;

import com.facebook.stetho.Stetho;

import timber.log.Timber;

public class MyApp extends Application {
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());
        }

    }
}
