package com.example.pezyandroid.context;

import android.app.Application;
import android.content.Context;

/**
 * Created by anubi on 4/19/2018.
 */

public class PezyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        PezyApplication.context = getApplicationContext();
    }

    public static Context getContext() {
        return PezyApplication.context;
    }
}
