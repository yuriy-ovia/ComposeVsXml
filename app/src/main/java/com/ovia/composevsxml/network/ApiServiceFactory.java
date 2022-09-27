package com.ovia.composevsxml.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiServiceFactory {

    private ApiServiceFactory() {
    }

    public static ApiService create(OkHttpClient okHttpClient, String baseUrl) {

        // Build the Retrofit instance, using rxJava
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
