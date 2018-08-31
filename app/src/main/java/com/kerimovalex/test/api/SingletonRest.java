package com.kerimovalex.test.api;

import com.kerimovalex.test.model.DataModel;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kerimovalex.test.api.ServerRequests.*;

public class SingletonRest {
    private static final String LIFE_URL = "https://hn.algolia.com/";
    private static SingletonRest instance;
    private IData iData;

    private SingletonRest() {
        init();
    }

    public static synchronized SingletonRest getInstance() {
        if (instance == null) {
            instance = new SingletonRest();
        }
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor().
                setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LIFE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        iData = retrofit.create(IData.class);
    }

    public Observable<DataModel> getDataInfo(Map<String, Object> map) {
        return iData.getData(map);
    }
}
