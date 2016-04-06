package com.yandex.android.music.injections.modules;

import android.app.Application;

import com.yandex.android.music.App;
import com.yandex.android.music.injections.modules.artist.ManagerModule;
import com.yandex.android.music.injections.modules.model.ModelModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zver on 03.04.2016.
 */

@Module(
        library = true,
        includes = {
                ModelModule.class,
                ManagerModule.class
        },
        injects = {
                App.class
        }
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Application provideApplication() {
        return app;
    }
}


