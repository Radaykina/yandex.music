package com.yandex.android.music.injections.modules.artist;

import com.yandex.android.music.classes.IArtistManager;
import com.yandex.android.music.injections.modules.AppModule;
import com.yandex.android.music.presenter.info.IInfoPresenter;
import com.yandex.android.music.presenter.info.InfoPresenter;
import com.yandex.android.music.view.info.IInfoView;
import com.yandex.android.music.view.info.InfoActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zver on 04.04.2016.
 */
@Module(
        injects = InfoActivity.class,
        addsTo = AppModule.class
)
public class InfoModule {
    private IInfoView view;

    public InfoModule(IInfoView view) {
        this.view = view;
    }

    @Provides
    public IInfoView provideView() {
        return view;
    }

    @Provides
    public IInfoPresenter provideInfoPresenter(IInfoView view, IArtistManager manager) {
        return new InfoPresenter(view, manager);
    }
}
