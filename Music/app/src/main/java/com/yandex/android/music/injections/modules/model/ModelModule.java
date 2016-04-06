package com.yandex.android.music.injections.modules.model;

import com.yandex.android.music.model.ArtistModel;
import com.yandex.android.music.model.IArtistModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zver on 03.04.2016.
 */
@Module(
        library = true
)
public class ModelModule {
    @Provides
    public IArtistModel provideArtistModel() { return new ArtistModel(); }
}
