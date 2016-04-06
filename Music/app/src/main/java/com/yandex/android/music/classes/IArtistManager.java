package com.yandex.android.music.classes;

import java.util.List;

/**
 * Created by Zver on 03.04.2016.
 */
public interface IArtistManager {
    List<Artist> getList();
    Artist getSelectedArtist();

    boolean IsListModified();

    void setSelectedArtist(Artist artist);
    void setOnDataChangedListener(IArtistManagerListener listener);
    void removeOnDataChangedListener(IArtistManagerListener listener);
    void notifyDataChanged();
    void addArtist(Artist artist);
    void removeArtist(int id);
    void clearList();

}
