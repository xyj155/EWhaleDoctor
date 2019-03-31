package com.example.module_library;

import android.app.Application;

import com.example.module_library.config.ApplicationInitial;

public class MyApp extends Application {
    public static MyApp instance;

    public static MyApp getInstance() {
        if (instance == null) {
            return new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ApplicationInitial applicationInitial = new ApplicationInitial();
        applicationInitial.initAliBabaRouter()
                .initToast()
                .initTencentQQ();
    }
}
