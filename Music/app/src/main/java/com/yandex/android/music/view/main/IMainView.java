package com.yandex.android.music.view.main;

import android.view.View;

import com.yandex.android.music.classes.Artist;

/**
 * Created by Zver on 03.04.2016.
 */
public interface IMainView {

    void showProgress();
    void hideProgress();
    void showError(String message);
    void notifyDataChanged();
    void navigateToInfo();

    View getListItemView(Artist artist);
}
