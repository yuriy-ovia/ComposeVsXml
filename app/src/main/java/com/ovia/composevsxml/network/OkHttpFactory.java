package com.ovia.composevsxml.network;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public final class OkHttpFactory {

    // the default timeout for api calls is 30 seconds
    private static final int TIMEOUT = 30;

    // Directory for cache
    private static final String CACHE_DIR = "httpCache";

    // Cache size
    private static final long CACHE_SIZE = 10 * 1024 * 1024;

    private OkHttpFactory() {
    }

    public static OkHttpClient getClient(Context context) {
        // Use OkHttp for network requests
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // Set a short timeout on connects and reads
        builder.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(TIMEOUT, TimeUnit.SECONDS);

        // Configure caching
        File baseCacheDir = context.getCacheDir();
        File cacheDir = new File(baseCacheDir, CACHE_DIR);
        Cache cache = new Cache(cacheDir, CACHE_SIZE);
        builder.cache(cache);

        // Add an interceptor to log all requests and responses
        // NOTE: This needs to be the last interceptor added, so other interceptors will execute first
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        return builder.build();
    }
}
