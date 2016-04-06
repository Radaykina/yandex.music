package com.yandex.android.music.injections;

import dagger.ObjectGraph;

/**
 * Created by Zver on 03.04.2016.
 */
public enum Injector {
    INSTANCE;

    private ObjectGraph objectGraph = null;

    public void init(final Object module)
    {

        if(objectGraph == null)
        {
            objectGraph = ObjectGraph.create(module);
        }
        else
        {
            objectGraph = objectGraph.plus(module);
        }

        // Inject statics
        objectGraph.injectStatics();

    }

    public void init(final Object module, final Object target)
    {
        init(module);
        inject(target);
    }

    public void inject(final Object target)
    {
        objectGraph.inject(target);
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }

    public <T> T resolve(Class<T> type)
    {
        return objectGraph.get(type);
    }
}
