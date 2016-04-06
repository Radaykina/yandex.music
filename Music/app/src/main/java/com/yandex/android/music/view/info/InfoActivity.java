package com.yandex.android.music.view.info;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yandex.android.music.R;
import com.yandex.android.music.classes.Artist;
import com.yandex.android.music.injections.modules.artist.InfoModule;
import com.yandex.android.music.presenter.info.IInfoPresenter;
import com.yandex.android.music.view.BaseActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class InfoActivity extends BaseActivity implements IInfoView {

    @Inject
    IInfoPresenter presenter;

    private TextView mGenresView;
    private TextView mAlbumaAndTracksView;
    private TextView mDescriptionsView;
    private ImageView mPhotoView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mGenresView = (TextView) findViewById(R.id.tvGenres);
        mAlbumaAndTracksView = (TextView) findViewById(R.id.tvTracksAndAlbums);
        mPhotoView = (ImageView) findViewById(R.id.ivPhoto);
        mDescriptionsView = (TextView) findViewById(R.id.tvDescription);

        //init imageLoader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        presenter.getInfo();

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new InfoModule(this));
    }


    @Override
    public void showInfo(Artist artist) {
        getSupportActionBar().setTitle(artist.getName());
        mGenresView.setText(TextUtils.join(", ", artist.getGenres()));
        mDescriptionsView.setText(artist.getDescription());
        mAlbumaAndTracksView.setText(String.format("%s альбомов * %s песен", artist.getAlbums(), artist.getTracks()));
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().showImageOnFail(R.mipmap.big_no_photo)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().displayImage(artist.getPhoto().getBigPhoto(), mPhotoView, imageOptions);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
