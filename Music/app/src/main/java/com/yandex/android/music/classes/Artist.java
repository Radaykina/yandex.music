package com.yandex.android.music.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zver on 03.04.2016.
 */
public class Artist {
    @SerializedName("id") private int mId;
    @SerializedName("name") private String mName;
    @SerializedName("genres") private List<String> mGenres;
    @SerializedName("tracks") private int mTracks;
    @SerializedName("albums") private int mAlbums;
    @SerializedName("description") private String mDescription;
    @SerializedName("cover") private Photo mPhoto;


    // getters
    public int getId() {return mId;}
    public String getName() {return mName;}
    public List<String> getGenres() {return mGenres;}
    public int getTracks() {return mTracks;}
    public int getAlbums() {return mAlbums;}
    public String getDescription() {return mDescription;}
   public Photo getPhoto() {return mPhoto;}

}
