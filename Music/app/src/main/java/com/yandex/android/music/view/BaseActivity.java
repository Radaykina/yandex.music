package com.yandex.android.music.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yandex.android.music.injections.Injector;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Zver on 03.04.2016.
 */

    public abstract class BaseActivity extends AppCompatActivity {
    private ObjectGraph mObjectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Object> objects = this.getModules();

        if (objects != null) {
            mObjectGraph = Injector.INSTANCE.createScopedGraph(objects.toArray());
            mObjectGraph.inject(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mObjectGraph = null;
    }

    protected abstract List<Object> getModules();

}
