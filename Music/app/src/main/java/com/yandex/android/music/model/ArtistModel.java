package com.yandex.android.music.model;

import com.google.gson.internal.LinkedTreeMap;
import com.yandex.android.music.classes.Artist;
import com.yandex.android.music.store.restful.IStore;
import com.yandex.android.music.store.restful.Store;

import java.util.ArrayList;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Zver on 03.04.2016.
 */
public class ArtistModel implements IArtistModel{
    private IStore storeInterface = Store.getStoreInterface();

    //создание presenter'а через injection
    @Override
    public Observable<ArrayList<Artist>> getList() {
        return storeInterface.getList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
