package com.jehandadk.oriontest.modules;


import com.jehandadk.oriontest.BuildConfig;
import com.jehandadk.oriontest.data.api.IApiClient;
import com.jehandadk.oriontest.data.api.RxErrorHandlingCallAdapterFactory;
import com.jehandadk.oriontest.ui.contacts.ContactsRepo;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by jehandad.kamal on 1/24/2016.
 */
@Module
public class ApiModule {

    public ApiModule() {
    }

    @Provides
    @Singleton
    ContactsRepo provideContactApiObservable(IApiClient apiClient) {
        return new ContactsRepo(apiClient);
    }

    @Provides
    @Singleton
    IApiClient provideApiService(Retrofit retrofit) {
        return retrofit.create(IApiClient.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.ENDPOINT)
                .build();
    }

    @Provides
    OkHttpClient provideClient(Interceptor logger) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(logger);
        builder.connectTimeout(120, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    Interceptor provideLogger() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.BASIC);
        return logger;
    }


}
