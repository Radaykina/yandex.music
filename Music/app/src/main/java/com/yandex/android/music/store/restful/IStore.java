package com.yandex.android.music.store.restful;

import com.google.gson.internal.LinkedTreeMap;
import com.yandex.android.music.classes.Artist;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Zver on 03.04.2016.
 */
public interface IStore {

    /********************************** artist methods *********************************/
    @GET("artists.json")
    Observable<ArrayList<Artist>> getList();
}
