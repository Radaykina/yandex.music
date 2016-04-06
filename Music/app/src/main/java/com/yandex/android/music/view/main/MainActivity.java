package com.yandex.android.music.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Joiner;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yandex.android.music.R;
import com.yandex.android.music.classes.Artist;
import com.yandex.android.music.injections.modules.artist.ArtistModule;
import com.yandex.android.music.presenter.main.IMainPresenter;
import com.yandex.android.music.view.BaseActivity;
import com.yandex.android.music.view.info.InfoActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainView,  AdapterView.OnItemClickListener{

    @Inject
    IMainPresenter presenter;

    // list view
    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RelativeLayout mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // bind UI elements
        mListView = (ListView) findViewById(R.id.lvArtists);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srRefresh);
        mProgressView = (RelativeLayout) findViewById(R.id.rlProgress);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                presenter.getArtistList();
            }
        });

        //set adapter
        BaseAdapter adapter = (BaseAdapter) presenter;
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

        //init imageLoader
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        // get cached list of artist
        presenter.getArtistListCached();

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ArtistModule(this));

    }


    @Override
    public void showProgress() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void notifyDataChanged() {
        mSwipeRefreshLayout.setRefreshing(false);
        ((BaseAdapter)presenter).notifyDataSetChanged();
    }



    @Override
    public void navigateToInfo() {
        this.startActivity(new Intent(this, InfoActivity.class));
    }

    @Override
    public View getListItemView(Artist artist) {
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // initialize the layout from xml
        final View listEntry = inflater.inflate(R.layout.item_list, null);

        try {
            // get UI controls
            final TextView artistNameView = (TextView) listEntry.findViewById(R.id.tvName);
            final TextView genresView = (TextView) listEntry.findViewById(R.id.tvGenres);
            final TextView tracksAndAlbumsView = (TextView) listEntry.findViewById(R.id.tvTracksAndAlbums);
            final ImageView photoView = (ImageView) listEntry.findViewById(R.id.ivPhoto);

            // bind UI controls
            artistNameView.setText(artist.getName());
            genresView.setText(TextUtils.join(", ", artist.getGenres()));
            tracksAndAlbumsView.setText(String.format("%s альбомов, %s песен", artist.getAlbums(), artist.getTracks()));
            DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().showImageOnFail(R.mipmap.small_no_photo)
                    .cacheInMemory(true)
                    .cacheOnDisc()
                    .build();
            ImageLoader.getInstance().displayImage(artist.getPhoto().getSmallPhoto(), photoView, imageOptions);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return listEntry;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.setSelectedArtist(position);
        this.navigateToInfo();
    }
}
