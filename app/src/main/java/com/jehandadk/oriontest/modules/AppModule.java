package com.jehandadk.oriontest.modules;

import android.content.Context;

import com.jehandadk.oriontest.OrionApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    OrionApp mApplication;

    public AppModule(OrionApp application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    OrionApp providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication;
    }


}