package com.yandex.android.music.model;

import com.yandex.android.music.classes.Artist;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Zver on 03.04.2016.
 */
public interface IArtistModel {
    Observable<ArrayList<Artist>> getList();
}
