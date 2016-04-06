package com.yandex.android.music.presenter.main;

/**
 * Created by Zver on 03.04.2016.
 */
public interface IMainPresenter {

    void getArtistList();
    void getArtistListCached();
    void setSelectedArtist(int index);
    void onDestroy();
}
