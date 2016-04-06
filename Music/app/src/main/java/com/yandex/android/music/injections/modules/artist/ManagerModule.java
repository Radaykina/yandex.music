package com.yandex.android.music.injections.modules.artist;

import android.app.Application;

import com.yandex.android.music.classes.ArtistManager;
import com.yandex.android.music.classes.IArtistManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zver on 03.04.2016.
 */

@Module(
        complete = false,
        library = true
)

public class ManagerModule {

    @Provides
    @Singleton
    public IArtistManager provideArtistManager(Application app){
        return new ArtistManager(app);
    }
}
