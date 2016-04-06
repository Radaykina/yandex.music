package com.yandex.android.music.classes;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zver on 04.04.2016.
 */
public class Photo {
    @SerializedName("big") private String mBigPhoto;
    @SerializedName("small") private String mSmallPhoto;

    // getters
    public String getBigPhoto() {return mBigPhoto;}
    public String getSmallPhoto() {return mSmallPhoto;}

}
