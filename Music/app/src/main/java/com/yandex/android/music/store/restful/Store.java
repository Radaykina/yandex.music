package com.yandex.android.music.store.restful;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.yandex.android.music.config.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Zver on 03.04.2016.
 */
public class Store {

    private static final boolean ENABLE_LOG = true;

    public static IStore getStoreInterface() {

        OkHttpClient httpClient = new OkHttpClient();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(httpClient);

        IStore apiInterface = builder.build().create(IStore.class);
        return apiInterface;
    }
}
