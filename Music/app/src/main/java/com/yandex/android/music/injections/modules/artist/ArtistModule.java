package com.yandex.android.music.injections.modules.artist;

import com.yandex.android.music.classes.IArtistManager;
import com.yandex.android.music.injections.modules.AppModule;
import com.yandex.android.music.model.IArtistModel;
import com.yandex.android.music.presenter.main.IMainPresenter;
import com.yandex.android.music.presenter.main.MainPresenter;
import com.yandex.android.music.view.main.IMainView;
import com.yandex.android.music.view.main.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zver on 03.04.2016.
 */
@Module(
        injects = MainActivity.class,
        addsTo = AppModule.class
)
public class ArtistModule {
    private IMainView view;

    public ArtistModule(IMainView view) {
        this.view = view;
    }

    @Provides
    public IMainView provideView() {
        return view;
    }

    @Provides
    public IMainPresenter provideArtistPresenter(IMainView view, IArtistModel model, IArtistManager manager) {
        return new MainPresenter(view, model, manager);
    }
}
