package com.jehandadk.oriontest;

import android.app.Application;

import com.jehandadk.oriontest.modules.ApiModule;
import com.jehandadk.oriontest.modules.AppModule;
import com.jehandadk.oriontest.modules.DaggerMainComponent;
import com.jehandadk.oriontest.modules.MainComponent;

/**
 * Created by jehandad.kamal on 7/13/2016.
 */
public class OrionApp extends Application {
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
    }

    protected void createComponent() {
        mainComponent = DaggerMainComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}
