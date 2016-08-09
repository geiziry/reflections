package com.quran.android.quranandroidlearning.component;

import com.quran.android.quranandroidlearning.QuranActivity;
import com.quran.android.quranandroidlearning.module.ApplicationModule;
import com.quran.android.quranandroidlearning.ui.fragment.QuranSettingsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //Activities
    void inject(QuranActivity quranActivity);


    //fragments

    void inject(QuranSettingsFragment fragment);
}
