package com.yandex.android.music.presenter.info;

import com.yandex.android.music.classes.Artist;
import com.yandex.android.music.classes.IArtistManager;
import com.yandex.android.music.classes.IArtistManagerListener;
import com.yandex.android.music.view.info.IInfoView;

/**
 * Created by Zver on 03.04.2016.
 */
public class InfoPresenter implements IInfoPresenter, IArtistManagerListener{

    private IInfoView mView;
    private IArtistManager mManager;

    public InfoPresenter(IInfoView view, IArtistManager manager) {
        super();

        this.mView = view;
        this.mManager = manager;

        this.mManager.setOnDataChangedListener(this);
    }

    @Override
    public void getInfo() {
       Artist artist = mManager.getSelectedArtist();

        if (artist != null){
            mView.showInfo(artist);
        }
    }

    @Override
    public void onDestroy() {
        this.mManager.removeOnDataChangedListener(this);
    }

    @Override
    public void onDataChanged() {
        getInfo();
    }
}
