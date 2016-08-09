package com.quran.android.quranandroidlearning.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HP_Spectre on 7/31/2016.
 */
@Module
public class ApplicationModule {
    private static final int DEFAULT_READ_TIMEOUT_SECONDS = 20;
    private static final int DEFAULT_CONNECT_TIMEOUT_SECONDS = 15;

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

}
