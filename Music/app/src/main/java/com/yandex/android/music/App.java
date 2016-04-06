package com.yandex.android.music;

import android.app.Application;

import com.yandex.android.music.injections.Injector;
import com.yandex.android.music.injections.modules.AppModule;

/**
 * Created by Zver on 03.04.2016.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initialize Dependency Injections graph
        Injector.INSTANCE.init(new AppModule(this), this);


    }
}
