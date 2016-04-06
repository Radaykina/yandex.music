package com.yandex.android.music.classes;


import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zver on 03.04.2016.
 */
public class ArtistManager implements IArtistManager {

    private Application mApplication;
    private List<Artist> mList;
    private Artist mSelectedArtist;
    private List<IArtistManagerListener> mListener;

    private boolean mIsListModified;

    public ArtistManager(Application application){
        mApplication = application;
        mList = new ArrayList<>();
        mListener = new ArrayList<>();
    }

    @Override
    public List<Artist> getList() {
        return mList;
    }

    @Override
    public Artist getSelectedArtist() {
        return mSelectedArtist;
    }

    @Override
    public boolean IsListModified() {
        return mIsListModified;
    }


    @Override
    public void setSelectedArtist(Artist employee) {
        mSelectedArtist = employee;
    }

    @Override
    public void setOnDataChangedListener(IArtistManagerListener listener) {
        this.mListener.add(listener);
    }

    @Override
    public void removeOnDataChangedListener(IArtistManagerListener listener) {
        if (mListener.contains(listener)){
            mListener.remove(listener);
        }
    }

    @Override
    public void notifyDataChanged() {
        for (IArtistManagerListener listener : mListener) { listener.onDataChanged(); }
    }
    @Override
    public void addArtist(Artist artist) {
        // select first added artist
        if (mSelectedArtist == null){
            mSelectedArtist = artist;
        }

        mList.add(artist);
        mIsListModified = true;


    }

    @Override
    public void removeArtist(int id){
        Artist artist = null;

        for (Artist a : mList) {
            if (a.getId() == id){
                artist = a;
                break;
            }
        }

        if (artist != null){
            mList.remove(artist);
        }
    }

    @Override
    public void clearList() {
        mIsListModified = false;
        mSelectedArtist = null;
        mList.clear();
    }

}
