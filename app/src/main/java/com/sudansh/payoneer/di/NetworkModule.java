package com.sudansh.payoneer.di;

import com.google.gson.Gson;
import com.sudansh.payoneer.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    private static final String BASE_URL = "https://raw.githubusercontent.com/optile/checkout-android/";
    private static final Integer TIMEOUT = 60;

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new Gson().newBuilder().create();
    }

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    @Provides
    public static ApiService provideApi(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
