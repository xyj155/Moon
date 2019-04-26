package com.example.module_base;


import android.app.Application;

import com.example.module_base.util.ApplicationInitial;

public class App extends Application {
    private ApplicationInitial applicationInitial = new ApplicationInitial();
    private static App instance;

    public static App getInstance() {
        if (instance == null) {
            return new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationInitial.initBmob()
                .initBugly()
                .initToast()
                .initArouter()
                .initJpush()
                .initPayment();
    }
}
