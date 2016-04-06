package com.yandex.android.music.presenter.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;


import com.yandex.android.music.classes.Artist;
import com.yandex.android.music.classes.IArtistManager;
import com.yandex.android.music.classes.IArtistManagerListener;
import com.yandex.android.music.model.IArtistModel;
import com.yandex.android.music.view.main.IMainView;


import java.util.ArrayList;

import rx.Observer;

/**
 * Created by Zver on 03.04.2016.
 */
public class MainPresenter extends BaseAdapter implements IMainPresenter, IArtistManagerListener, ListAdapter {
    private IMainView mView;
    private IArtistModel mModel;
    private IArtistManager mManager;

    public MainPresenter(IMainView view, IArtistModel model, IArtistManager manager) {
        super();

        this.mView = view;
        this.mModel = model;
        this.mManager = manager;

        // set notifications
        this.mManager.setOnDataChangedListener(this);
    }

    @Override
    public void getArtistList() {
        mView.showProgress();

        try {
            //запрос на сервер через библеотеку retrofit
            mModel.getList().subscribe(new Observer<ArrayList<Artist>> () {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    mView.hideProgress();
                    mView.showError(e.getLocalizedMessage());
                    return;
                }

                @Override
                public void onNext(ArrayList<Artist> result) {
                    if (mView == null){ return; }

                    mView.hideProgress();

                    ArrayList<Artist> list =  result;

                       if (list != null && list.size() > 0) {
                            mManager.clearList();
                            for (Artist a : list) { mManager.addArtist(a); }
                        }

                        mView.notifyDataChanged();




                }
            });
        }
        catch (Exception ex){
            mView.showError(ex.getLocalizedMessage());
        }
    }

    @Override
    public void getArtistListCached() {
        if (mManager.IsListModified()){
            mView.notifyDataChanged(); }
        else { this.getArtistList(); }
    }

    @Override
    public void setSelectedArtist(int index) {
       Artist artist = mManager.getList().get(index);

        if (artist != null){
            mManager.setSelectedArtist(artist);
        }
    }


    @Override
    public int getCount() {
        return mManager.getList().size();
    }

    @Override
    public Object getItem(int position) {
        try {
            return mManager.getList().get(position);
        }
        catch (Exception ex){}

        return null;
    }

    @Override
    public long getItemId(int position) {
        try {
            return mManager.getList().get(position).getId();
        }
        catch (Exception ex){}

        return  0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Artist artist = (Artist) this.getItem(position);
        return mView.getListItemView(artist);
    }

    @Override
    public void onDestroy() {
        this.mManager.removeOnDataChangedListener(this);
        mView = null;
    }


    @Override
    public void onDataChanged() {
        getArtistListCached();
    }
}
