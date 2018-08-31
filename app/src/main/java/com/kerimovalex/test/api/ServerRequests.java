package com.kerimovalex.test.api;

import com.kerimovalex.test.model.DataModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class ServerRequests {
    interface IData {
        @GET("api/v1/search_by_date")
        Observable<DataModel> getData(@QueryMap Map<String, Object> date);
    }
}
